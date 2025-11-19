# Study API

API REST desenvolvida com Spring Boot para gerenciamento de médicos numa clínica.

## 📋 Tecnologias

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **PostgreSQL 13**
- **Lombok**
- **Maven**

## 🏗️ Estrutura do Projeto

O projeto segue uma arquitetura em camadas bem definida:

```
src/main/java/com/github/julioctavares/study_api/
├── config/              # Classes de configuração
├── controllers/         # Camada de controle (REST endpoints)
│   ├── doctor/          # Controllers específicos de Doctor
│   └── HealthController.java
├── dto/                 # Data Transfer Objects (DTOs)
├── entities/            # Entidades JPA (modelos de domínio)
├── exceptions/          # Tratamento de exceções customizadas
├── repositories/        # Interfaces de repositório (Spring Data JPA)
│   └── DoctorRepository.java
├── services/            # Camada de serviço (lógica de negócio)
└── StudyApiApplication.java
```

### 📦 Camadas da Aplicação

#### **Entities** (`entities/`)
Representam as entidades do domínio e são mapeadas para tabelas no banco de dados através do JPA/Hibernate.

**Exemplo:**
- `Doctor.java` - Entidade que representa um médico no sistema

#### **DTOs** (`dto/`)
Data Transfer Objects utilizados para transferência de dados entre as camadas, especialmente nas requisições e respostas da API. Separam a representação interna (Entity) da representação externa (API).

**Exemplo:**
- `DoctorRequestDTO.java` - DTO para criação/atualização de médico
- `DoctorResponseDTO.java` - DTO para resposta da API

#### **Repositories** (`repositories/`)
Interfaces que estendem `JpaRepository` e fornecem métodos de acesso aos dados. O Spring Data JPA implementa automaticamente os métodos básicos de CRUD.

**Exemplo:**
- `DoctorRepository.java` - Repositório para operações com a entidade Doctor

#### **Services** (`services/`)
Contêm a lógica de negócio da aplicação. Fazem a ponte entre os controllers e os repositories, processando e validando os dados.

**Exemplo:**
- `DoctorService.java` - Serviço com a lógica de negócio relacionada a médicos

#### **Controllers** (`controllers/`)
Camada responsável por receber as requisições HTTP e retornar as respostas. Utilizam os services para processar as requisições.

**Exemplo:**
- `DoctorController.java` - Controller REST para endpoints relacionados a médicos
- `HealthController.java` - Controller para verificação de saúde da API

## 🚀 Como Executar

### Pré-requisitos

- Java 21
- Maven 3.6+
- Docker e Docker Compose (para o banco de dados)

### Configuração

1. Clone o repositório:
```bash
git clone <url-do-repositorio>
cd study-api
```

2. Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:
```env
POSTGRES_USER=seu_usuario
POSTGRES_PASSWORD=sua_senha
POSTGRES_DB=study_db
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
```

3. Inicie o banco de dados PostgreSQL usando Docker Compose:
```bash
docker-compose up -d
```

4. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

Ou usando Maven:
```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## 📡 Endpoints

### Health Check
- **GET** `/health` - Verifica se a API está funcionando

## 🗄️ Banco de Dados

O projeto utiliza PostgreSQL como banco de dados. A configuração está no arquivo `application.yaml` e utiliza variáveis de ambiente para maior segurança.

O Hibernate está configurado com `ddl-auto: update`, o que significa que as tabelas serão criadas/atualizadas automaticamente baseadas nas entidades JPA.

## 🧪 Testes

Execute os testes com:
```bash
./mvnw test
```

## 📝 Convenções

- **Entities**: Classes anotadas com `@Entity`, representam tabelas no banco
- **DTOs**: Classes simples para transferência de dados, sem anotações JPA
- **Repositories**: Interfaces que estendem `JpaRepository<Entity, ID>`
- **Services**: Classes anotadas com `@Service`, contêm a lógica de negócio
- **Controllers**: Classes anotadas com `@RestController`, expõem os endpoints REST

## 🔄 Fluxo de Desenvolvimento de Features

Ao desenvolver uma nova feature, siga esta ordem de implementação das camadas:

### 1. **Entity** (`entities/`)
Comece criando a entidade JPA que representa o modelo de domínio.

**Passos:**
- Criar a classe da entidade no pacote `entities/`
- Adicionar anotações JPA (`@Entity`, `@Table`, `@Id`, `@Column`, etc.)
- Definir os campos e relacionamentos
- Adicionar anotações do Lombok (`@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, etc.)

**Exemplo:**
```java
@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    // ... outros campos
}
```

### 2. **DTOs** (`dto/`)
Crie os DTOs para receber e retornar dados da API.

**Passos:**
- Criar `EntityRequestDTO.java` para dados de entrada (criação/atualização)
- Criar `EntityResponseDTO.java` para dados de saída
- Adicionar validações com Bean Validation (`@NotNull`, `@NotBlank`, `@Email`, etc.)
- Usar Lombok para reduzir boilerplate

**Exemplo:**
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    
    @Email(message = "Email inválido")
    private String email;
    
    // ... outros campos
}
```

### 3. **Repository** (`repositories/`)
Crie a interface do repositório para acesso aos dados.

**Passos:**
- Criar interface que estende `JpaRepository<Entity, ID>`
- Adicionar métodos de consulta customizados se necessário
- Usar convenções do Spring Data JPA para queries derivadas

**Exemplo:**
```java
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByEmail(String email);
    List<Doctor> findBySpecialty(String specialty);
}
```

### 4. **Service** (`services/`)
Implemente a lógica de negócio na camada de serviço.

**Passos:**
- Criar interface do serviço (opcional, mas recomendado)
- Criar classe de serviço implementando a interface
- Adicionar anotação `@Service`
- Injetar o repository via construtor
- Implementar métodos de negócio (criar, buscar, atualizar, deletar)
- Converter entre Entity e DTO
- Adicionar validações de negócio
- Tratar exceções ou lançar exceções customizadas

**Exemplo:**
```java
@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    
    public DoctorResponseDTO create(DoctorRequestDTO dto) {
        // Validações de negócio
        if (doctorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EntityAlreadyExistsException("Email já cadastrado");
        }
        
        // Converter DTO para Entity
        Doctor doctor = convertToEntity(dto);
        
        // Salvar
        Doctor saved = doctorRepository.save(doctor);
        
        // Converter Entity para DTO de resposta
        return convertToResponseDTO(saved);
    }
    
    // ... outros métodos
}
```

### 5. **Controller** (`controllers/`)
Crie o controller REST para expor os endpoints.

**Passos:**
- Criar classe controller no pacote `controllers/` (ou subpacote específico)
- Adicionar anotações `@RestController` e `@RequestMapping`
- Injetar o service via construtor
- Criar endpoints HTTP (GET, POST, PUT, DELETE)
- Mapear DTOs nas requisições e respostas
- Adicionar tratamento de exceções com `@ExceptionHandler` ou usar `@ControllerAdvice`
- Documentar com Swagger/OpenAPI se disponível

**Exemplo:**
```java
@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> create(@Valid @RequestBody DoctorRequestDTO dto) {
        DoctorResponseDTO response = doctorService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> findById(@PathVariable UUID id) {
        DoctorResponseDTO response = doctorService.findById(id);
        return ResponseEntity.ok(response);
    }
    
    // ... outros endpoints
}
```

### 6. **Exceptions** (`exceptions/`) - Se necessário
Crie exceções customizadas para tratamento de erros específicos.

**Passos:**
- Criar classes de exceção customizadas
- Criar `@ControllerAdvice` para tratamento global de exceções
- Mapear exceções para respostas HTTP apropriadas

### 📋 Checklist de Feature

Ao finalizar uma feature, verifique:

- [ ] Entity criada com todas as anotações JPA necessárias
- [ ] DTOs criados (Request e Response) com validações
- [ ] Repository criado com métodos necessários
- [ ] Service implementado com lógica de negócio
- [ ] Controller criado com endpoints REST
- [ ] Tratamento de exceções implementado
- [ ] Testes unitários criados (opcional, mas recomendado)
- [ ] Documentação atualizada (se necessário)

## 🔧 Desenvolvimento

O projeto utiliza:
- **Lombok** para reduzir boilerplate code
- **Spring DevTools** para hot reload durante o desenvolvimento
- **Spring Data JPA** para abstração de acesso a dados

## 📄 Licença

Este projeto é um projeto de estudo/demonstração.

