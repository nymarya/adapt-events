## Clonar o projeto
```git clone https://github.com/nymarya/adapt``` (via HTTP)
ou
```git clone git@github.com:nymarya/adapt.git``` (via ssh)

## Importar com projeto
Após o [Spring Tool Suite](https://spring.io/tools/sts) e instalar algum plugins do Gradle:
```File > Import > Import Existing Gradle Project``` e selecionar o projeto recém baixado.

Depois de carregado, clicar com o botão direito no projeto, e depois Gradle > Refresh gradle project.

## Configurando
Para configurar o banco, basta copiar o arquivo  ```application.properties.example``` para ```application.properties``` e substituir 
`dbname`, `dbuser` e `dbpass` pelo nome, usuário (owner) e senha do banco, respectivamente

## Rodando
Depois de todas as configurações, basta clicar com o botão direito no projeto e depois ```Run As > Spring Boot App```. Como estamos usando
o `devtools`, a cada alteraço no projeto o mesmo é compilado e atualizado automaticamente
