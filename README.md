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

A base segue uma arquitetura em quatro módulos principais:

```
src/main/java/com/github/julioctavares/study_api/
├── domain/
│   ├── entities/            # Modelos centrais (ex.: Doctor, Address)
│   ├── entities/exceptions/ # Exceções do domínio
│   └── repositories/        # Contratos de acesso a dados (Spring Data JPA)
├── application/
│   ├── dtos/                # Objetos de transporte (req/res)
│   └── services/            # Casos de uso e lógica de aplicação
├── infrastructure/
│   ├── config/              # Configurações técnicas
│   └── persistence/         # Adaptadores de persistência
├── presentation/
│   ├── controllers/         # Endpoints REST
│   └── handlers/            # Tratadores globais de erro
└── StudyApiApplication.java
```

### 📦 O que vai em cada módulo

- **Domain**: entidades JPA puras, exceções de negócio e repositórios.  
- **Application**: DTOs + serviços (ex.: `CreateDoctorService`) que orquestram o domínio.  
- **Infrastructure**: configurações e integrações específicas (datasources, providers, etc.).  
- **Presentation**: controllers REST e `@RestControllerAdvice` que expõem a API.

### 🧱 Exemplo de persistência no estilo hexagonal

No domínio declaramos apenas o contrato necessário:

```3:10:src/main/java/com/github/julioctavares/study_api/domain/repositories/DoctorRepository.java
public interface DoctorRepository {
    Doctor save(Doctor doctor);
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByCrm(String crm);
}
```

Na infraestrutura criamos um adaptador que delega para um `JpaRepository`, mantendo o domínio desacoplado do Spring Data:

```1:24:src/main/java/com/github/julioctavares/study_api/infrastructure/persistence/jpa/DoctorRepositoryAdapter.java
@Repository
@RequiredArgsConstructor
public class DoctorRepositoryAdapter implements DoctorRepository {
    private final SpringDoctorJpaRepository springDoctorJpaRepository;

    @Override
    public Doctor save(Doctor doctor) {
        return springDoctorJpaRepository.save(doctor);
    }

    @Override
    public Optional<Doctor> findByEmail(String email) {
        return springDoctorJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<Doctor> findByCrm(String crm) {
        return springDoctorJpaRepository.findByCrm(crm);
    }
}
```

Com isso os serviços da camada Application dependem apenas do contrato do domínio, enquanto detalhes de persistência ficam isolados em `infrastructure/persistence`.

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

1. **Domain (`domain/`)**
   - Modele ou ajuste entidades JPA, exceções e contratos de repositório.
   - Garanta que as regras críticas residam no domínio (ex.: `@PrePersist`, invariantes).

2. **Application (`application/`)**
   - Crie/atualize DTOs de request/response com Bean Validation.
   - Implemente serviços anotados com `@Service` que orquestram os repositórios do domínio e retornam DTOs.

3. **Infrastructure (`infrastructure/`)**
   - Adapte configurações ou implementações quando a feature depende de integrações técnicas (banco, mensageria, storage, etc.).

4. **Presentation (`presentation/`)**
   - Desenvolva os controllers REST que consomem os DTOs e chamam os serviços.
   - Mapeie exceções com handlers globais (`@RestControllerAdvice`).

5. **Testes e documentação**
   - Execute `./mvnw test` (ou adicione testes específicos).
   - Atualize README / OpenAPI quando necessário.

### 📋 Checklist de Feature

- [ ] Domínio atualizado (entidades, repositórios, exceções)  
- [ ] DTOs e serviços na camada Application implementados  
- [ ] Controllers/handlers revisados na camada Presentation  
- [ ] Configurações/infraestrutura ajustadas (se aplicável)  
- [ ] Testes executados com sucesso  
- [ ] Documentação atualizada

## 🔧 Desenvolvimento

O projeto utiliza:
- **Lombok** para reduzir boilerplate code
- **Spring DevTools** para hot reload durante o desenvolvimento
- **Spring Data JPA** para abstração de acesso a dados

## 📄 Licença

Este projeto é um projeto de estudo/demonstração.

