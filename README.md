```markdown
# Criando um Projeto Spring Boot Multi-Módulo com Kotlin

Este script automatiza o processo de criação de um projeto Spring Boot multi-módulo utilizando Kotlin e Gradle. Ele baixa o esqueleto do projeto a partir do [Spring Initializr](https://start.spring.io/), cria a estrutura de módulos necessária, e gera os arquivos `build.gradle.kts` para cada módulo.

## Requisitos

Antes de executar o script, verifique se você possui os seguintes requisitos:

- **Curl**: Ferramenta para realizar requisições HTTP (geralmente instalada por padrão em sistemas Unix-like).
- **Java 21**: A versão do Java definida no script é 21.
- **Kotlin**: O script permite a escolha do Kotlin como linguagem (ou qualquer outra linguagem que o Spring Boot suporte).
- **Gradle**: Para gerenciar dependências e construir o projeto.
- **Unzip**: Utilitário para descompactar arquivos `.zip`.

## Como Usar

### Passo 1: Preparação do Ambiente

1. **Certifique-se de que as ferramentas necessárias estão instaladas**. Caso contrário, instale-as conforme necessário.
   - **Curl**: [Instalar Curl](https://curl.se/docs/install.html)
   - **Java 21**: [Baixar Java 21](https://adoptium.net/)
   - **Gradle**: [Instalar Gradle](https://gradle.org/install/)
   - **Unzip**: [Instalar Unzip](https://linux.die.net/man/1/unzip)

2. **Baixar ou criar o script**: Baixe o script ou crie um arquivo chamado `create_spring_boot_project.sh`.

### Passo 2: Preparar o Script

1. **Defina o nome do seu projeto**: O nome do projeto será passado como argumento ao script.
   
2. **Escolha a linguagem**: Você pode definir a linguagem como `kotlin` ou outra compatível com Spring Boot. Caso não defina, o script usará `kotlin` por padrão.

### Passo 3: Executando o Script

1. Torne o script executável:
   ```bash
   chmod +x create_spring_boot_project.sh
   ```

2. Execute o script passando o nome do projeto como argumento. Opcionalmente, você pode passar a linguagem (o padrão é Kotlin):
   ```bash
   ./initializer.sh nome_do_projeto
   ```

   Ou, se desejar especificar a linguagem (por exemplo, Java):
   ```bash
   ./initializer.sh nome_do_projeto java
   ```
### Passo 4: O que o Script Faz

1. **Baixa o esqueleto do projeto**: Utiliza o Spring Initializr para baixar o arquivo `.zip` com a estrutura básica do projeto Spring Boot.
2. **Descompacta o projeto**: O arquivo baixado é descompactado em um diretório com o nome do projeto.
3. **Cria módulos**:
  - `rest-api`: Para a API REST.
  - `async-processor`: Para processamento assíncrono.
  - `core`: Para a lógica de negócios central.
4. **Configura o arquivo `build.gradle.kts`** para cada módulo.
5. **Cria e configura o arquivo `application.properties`** para os módulos `rest-api` e `async-processor`.
6. **Configura o arquivo `settings.gradle.kts`** para incluir os três módulos no projeto.

### Passo 5: Estrutura do Projeto

Após a execução do script, a estrutura do projeto será semelhante a esta:

```
nome_do_projeto/
├── nome_do_projeto-rest-api/
│   ├── build.gradle.kts
│   └── src/main/resources/application.properties
├── nome_do_projeto-async-processor/
│   ├── build.gradle.kts
│   └── src/main/resources/application.properties
├── nome_do_projeto-core/
│   └── build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
```
 
### Passo 6: Próximos Passos

1. **Adicionar dependências**: Adicione as dependências necessárias para os módulos de acordo com os requisitos do seu projeto.
2. **Desenvolver a Lógica**: Implemente as funcionalidades específicas de cada módulo.
3. **Construir o Projeto**: Utilize o Gradle para construir e executar o projeto:
   ```bash
   ./gradlew build
   ```

## Personalizações

- **Versão do Spring Boot**: O script utiliza a versão `3.4.1` do Spring Boot. Caso queira usar outra versão, basta alterar a variável `version` no script.
- **Versão do Java**: A versão do Java usada é `21`. Se desejar usar uma versão diferente, altere a variável `java`.

## Contribuindo

Sinta-se à vontade para contribuir para este projeto. Se encontrar erros ou tiver sugestões de melhorias, envie um pull request ou abra uma issue no repositório.
```