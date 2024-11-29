<h1>Projeto prático do curso "Spring Boot 3:
desenvolva uma API Rest em Java"</h1> 

Kaban Board: <https://github.com/users/MarceloJSSantos/projects/2/views/2>


<h3>DADOS BANCO DE DADOS</h3>
No application.properties


<h3>DOCUMENTAÇÃO DAS APIs (SpringDoc)</h3>

Página da interface do usuário do Swagger
http://localhost:8080/swagger-ui.html

Descrição do OpenAPI no formato json
http://localhost:8080/v3/api-docs

<h4>Usuário para login</h4>

Json da requisição para retorno do token
```json
{
  "login": "ana.ferreira@business.email.com",
  "senha": "123456"
}
````

Json da resposta com o token

```json
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmEuZmVycmVpcmFAYnVzaW5lc3MuZW1haWwuY29tIiwiaXNzIjoiQVBJIFZvbGwubWVkIiwiaWQiOjEsImV4cCI6MTczMjcyMjk4M30.4JGt1i_pKS4kkyo7Cd-2vg_LOcneFVD_Ik_RK4JfL_Q"
}
```

Com o valor do token registrar em "Authorize"

<h3>TESTES AUTOMATIZADOS ABORDADOS</h3>
* Controlleries    ====>   APIs
* Repositoies      ====>   Queries personalizadas

Nota: Criado o BD 'vollmed_api_test' para uso dos testes

<h3>DEPLOY</h3>
Rodar no servidor onde a aplicação ficará hospedada
```shell
java '-Dspring.profiles.active=prod' '-DDATASOURCE_PASSWORD=123456' '-DDATASOURCE_URL=jdbc:mysql://localhost/vollmed_api' '-DDATASOURCE_USER=root' '-DJWT_SECRET=123456' -jar .\api-0.0.1-SNAPSHOT.jar
```


