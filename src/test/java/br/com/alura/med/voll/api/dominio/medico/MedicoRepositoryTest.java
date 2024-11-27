package br.com.alura.med.voll.api.dominio.medico;

import br.com.alura.med.voll.api.dominio.consulta.Consulta;
import br.com.alura.med.voll.api.dominio.endereco.EnderecoDados;
import br.com.alura.med.voll.api.dominio.paciente.Paciente;
import br.com.alura.med.voll.api.dominio.paciente.PacienteDadosCadastro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver 'null' quando único médico cadastrado não está livre na Data e Hora")
    void escolherMedicoAleatorioLivreNaDataHoraCenario1() {
        //given ou arrage
        var dataHoraProximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var especialidade = Especialidade.CARDIOLOGIA;

        var medicoCadastrado  = cadastraMedico("Medico", "994555", "medico@email.com", "(21) 98888-8888", especialidade);
        var pacienteCadastrado = cadastraPaciente("Paciente", "paciente@email.com", "(21) 97777-7777", "11122233344");
        cadastraConsulta(medicoCadastrado, pacienteCadastrado, dataHoraProximaSegundaAs10);

        //when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaDataHora(especialidade, dataHoraProximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver um médico quando o único médico cadastrado está livre na Data e Hora")
    void escolherMedicoAleatorioLivreNaDataHoraCenario2() {
        //given ou arrage
        var dataHoraProximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var especialidade = Especialidade.CARDIOLOGIA;

        var medicoCadastrado  = cadastraMedico("Medico", "994555", "medico@email.com", "(21) 98888-8888", especialidade);

        //when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaDataHora(especialidade, dataHoraProximaSegundaAs10);

        //then ou assert
        assertThat(medicoLivre).isEqualTo(medicoCadastrado);
    }


    //métodos auxiliares
    private void cadastraConsulta(Medico medico, Paciente paciente, LocalDateTime dataHora){
        em.persist(new Consulta(null, paciente, medico, dataHora, null));
    }

    private Medico cadastraMedico(String nome, String crm, String email, String telefone, Especialidade especialidade){

        var medico = new Medico(medicoDadosCadastro(nome, crm, email, telefone, especialidade));
        em.persist(medico);
        return medico;
    }

    private MedicoDadosCadastro medicoDadosCadastro(String nome, String crm, String email, String telefone, Especialidade especialidade){
        return new MedicoDadosCadastro(nome, crm, email, telefone, especialidade, enderecoDados());
    }

    private Paciente cadastraPaciente(String nome, String email, String telefone, String cpf){
        var paciente = new Paciente(pacienteDadosCadastro(nome, email, telefone,cpf));
        em.persist(paciente);
        return paciente;
    }

    private PacienteDadosCadastro pacienteDadosCadastro(String nome, String email, String telefone, String cpf){
        return new PacienteDadosCadastro(nome, email, telefone, cpf, enderecoDados());
    }

    private EnderecoDados enderecoDados(){
        return  new EnderecoDados(
                "rua a",
                "bairro",
                "11000-000",
                "cidade",
                "RJ",
                "100",
                "compl. A");
    }
}