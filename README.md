# School APP
API para prover recursos cadastro de aluno

A Arquitetura da nossa plataforma é constituida por:

**Pacotes:**

| Pacote        | Definição                                                                   |
|---------------|-----------------------------------------------------------------------------|
| Configuration | Existência das classes de configuração do Swagger e ModelMaper              |
| infra         | Existência das classes para tratamento de exceções de negócio               |
| model         | Classes e entidades com base nas diretivas do negócio da aplicação          |
| repository    | Intefarces para persistências de dados com base no Spring Data JPA          |
| service       | Classes contendo todas as regras de negócio da aplicação                    |
| controller    | Recursos HTTPs para disponibilizar o acesso as funcionalidades da aplicação |

# Demonstração

Para a nossa demonstração estamos interagindo com nossa api diretamente atrvés do swagger confome url:
```
http://localhost:8080/swagger-ui/index.html
