
# MagicFridgeAI

MagicFridgeAI é uma API inteligente de gerenciamento de alimentos, que permite registrar itens alimentícios, verificar o que há disponível e gerar sugestões de receitas criativas baseadas nos ingredientes cadastrados. O sistema integra-se ao ChatGPT para sugerir receitas personalizadas e práticas.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção de APIs RESTful.
- **WebClient**: Para integração com a API do ChatGPT.
- **H2 Database**: Banco de dados em memória para armazenamento dos itens alimentícios.
- **ChatGPT API**: Utilizada para gerar sugestões de receitas.
- **Lombok**: Biblioteca para simplificar a criação de getters, setters e construtores.
- **Reactor**: Para suporte a programação reativa com `Mono` e `Flux`.

## Instalação

### Requisitos

- **Java 17+**
- **Maven**
- **IDE de sua preferência** (IntelliJ, Eclipse, VS Code, etc.)

### Passos para rodar o projeto

1. Clone o repositório para sua máquina local:

   ```bash
   git clone https://github.com/BrisaTielly/MagicFridgeAI.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd MagicFridgeAI
   ```

3. Adicione sua chave da API do ChatGPT em uma variável de ambiente chamada `API_KEY`.

   No Linux/Mac:

   ```bash
   export API_KEY="sua-chave-aqui"
   ```

   No Windows (via Command Prompt):

   ```bash
   set API_KEY="sua-chave-aqui"
   ```

4. Compile e execute o projeto com Maven:

   ```bash
   mvn clean spring-boot:run
   ```

5. A API estará disponível em `http://localhost:8080`.

## Endpoints

### 1. **Cadastrar Alimento**

**POST** `/food/save`

Cadastra um novo item alimentício.

- **Corpo da Requisição** (JSON):

  ```json
  {
    "name": "Maçã",
    "foodCategory": "FRUTAS",
    "quantity": 5,
    "expiryDate": "2025-03-10"
  }
  ```

- **Resposta**:

  ```json
  {
    "id": 1,
    "name": "Maçã",
    "foodCategory": "FRUTAS",
    "quantity": 5,
    "expiryDate": "2025-03-10"
  }
  ```

### 2. **Listar Todos os Alimentos**

**GET** `/food/list`

Retorna todos os itens alimentícios cadastrados.

- **Resposta**:

  ```json
  [
    {
      "id": 1,
      "name": "Maçã",
      "foodCategory": "FRUTAS",
      "quantity": 5,
      "expiryDate": "2025-03-10"
    },
    ...
  ]
  ```

### 3. **Buscar Alimento por ID**

**GET** `/food/list/{id}`

Retorna o item alimentício com o ID especificado.

- **Exemplo de Requisição**: `/food/list/1`

- **Resposta**:

  ```json
  {
    "id": 1,
    "name": "Maçã",
    "foodCategory": "FRUTAS",
    "quantity": 5,
    "expiryDate": "2025-03-10"
  }
  ```

### 4. **Atualizar Alimento**

**PUT** `/food/update/{id}`

Atualiza os dados de um item alimentício existente.

- **Corpo da Requisição** (JSON):

  ```json
  {
    "name": "Maçã Verde",
    "foodCategory": "FRUTAS",
    "quantity": 5,
    "expiryDate": "2025-03-15"
  }
  ```

- **Resposta**:

  ```json
  {
    "id": 1,
    "name": "Maçã Verde",
    "foodCategory": "FRUTAS",
    "quantity": 5,
    "expiryDate": "2025-03-15"
  }
  ```

### 5. **Deletar Alimento**

**DELETE** `/food/delete/{id}`

Deleta o item alimentício com o ID especificado.

- **Exemplo de Requisição**: `/food/delete/1`

- **Resposta**:

  ```json
  "Deletado com sucesso"
  ```

### 6. **Gerar Receita**

**GET** `/generate`

Com base nos alimentos cadastrados, gera uma receita criativa e prática utilizando a API do ChatGPT.

- **Resposta**:

  ```text
  "**Receita de Maçã Assada com Mel** 
  
  **Ingredientes:** 
  - 5 maçãs - 5 colheres de sopa de mel 
  
  **Instruções:** 
  1. **Preparar as maçãs**: Preaqueça o forno a 180°C Lave bem as maçãs e retire o miolo com a ajuda de uma faca ou utensílio específico para isso, tomando cuidado para não furar o fundo. 
  2. **Adicionar o mel**: Coloque uma colher de sopa de mel dentro de cada maçã, preenchendo o espaço do miolo.
  3. **Assar**: Coloque as maçãs em uma assadeira e leve ao forno por cerca de 25-30 minutos, ou até que as maçãs estejam macias e levemente caramelizadas. 
  4. **Servir**: Retire do forno e deixe esfriar um pouco antes de servir. Essa sobremesa é deliciosa sozinha ou acompanhada de um sorvete de creme. Aproveite sua sobremesa saudável e saborosa!"
  ```

## Como Funciona

- **Cadastro de Alimentos**: O usuário pode cadastrar alimentos, definindo o nome, categoria (frutas, verduras, carnes, etc.), quantidade e validade.
- **Integração com o ChatGPT**: A cada solicitação de receita, o sistema busca todos os alimentos cadastrados, formata-os e envia ao ChatGPT para gerar uma sugestão de receita.
- **Base de Dados**: Utiliza o H2 para persistir os alimentos, permitindo adicionar, listar, atualizar e excluir itens.

### Categorias de Alimentos

Os alimentos cadastrados podem ser classificados nas seguintes categorias:

- **FRUTAS**
- **VERDURAS**
- **LEGUMES**
- **CARNES**
- **PEIXES_FRUTOS_DO_MAR**
- **GRAOS_CEREAIS**
- **LATICINIOS**
- **OVOS**
- **OLEAGINOSAS**
- **TEMPERO_ERVAS**
- **BEBIDAS**
- **DOCES_SOBREMESAS**
- **MASSAS**
- **ENLATADOS_CONSERVAS**
- **OUTROS**

Essas categorias são usadas para classificar os itens alimentícios, o que facilita a organização e a geração de receitas com base nos ingredientes disponíveis.

## Variáveis de Ambiente

- **API_KEY**: A chave da API do ChatGPT necessária para gerar receitas.
