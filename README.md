
# ⛳ Golf Club Management API

## 🎯 **Project Summary**
The Golf Club Management API is a powerful backend system designed to manage all essential operations of a golf club. It supports complete CRUD functionalities for:
- **Members** – Manage personal information, contact details, and tournament registrations.
- **Tournaments** – Organize and schedule tournaments with location and participant data.
- **Participation** – Handle many-to-many relationships between members and tournaments.

## 🌟 **Main Highlights**
- ✅ RESTful API with full CRUD capabilities
- ✅ MySQL support via AWS RDS and local Docker setup
- ✅ Docker integration for streamlined local development
- ✅ Secure, production-ready environment configurations

## 🚀 **Getting Started**

### 🔧 Run Locally with Docker
```bash
git clone <your-repo-url>
cd golf-api
docker-compose up --build
```
🌐 The API will be available at: `http://localhost:8080`

### ☁️ Run in Production with AWS RDS
```bash
export RDS_DB_URL="jdbc:mysql://your-rds-endpoint:3306/golf_db"
export RDS_DB_USERNAME="your-username"
export RDS_DB_PASSWORD="your-password"

./mvnw clean package
java -Dspring.profiles.active=aws -jar target/demo-0.0.1-SNAPSHOT.jar
```

## 📡 **API Overview**

### 👥 Members
| Method | Endpoint | Purpose |
|--------|----------|---------|
| GET | `/api/members` | Retrieve all members |
| GET | `/api/members/{id}` | Get member by ID |
| POST | `/api/members` | Create a new member |
| PUT | `/api/members/{id}` | Update member information |
| DELETE | `/api/members/{id}` | Delete a member |
| GET | `/api/members/search/name?name=` | Search members by name |
| GET | `/api/members/search/membership-type?membershipType=` | Filter by membership type |

### 🏌️ Tournaments
| Method | Endpoint | Purpose |
|--------|----------|---------|
| GET | `/api/tournaments` | Retrieve all tournaments |
| GET | `/api/tournaments/{id}` | Get tournament by ID |
| POST | `/api/tournaments` | Create a tournament |
| PUT | `/api/tournaments/{id}` | Update tournament details |
| DELETE | `/api/tournaments/{id}` | Delete a tournament |
| GET | `/api/tournaments/search/location?location=` | Search tournaments by location |

### 🔁 Member-Tournament Participation
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/tournaments/{tournamentId}/members/{memberId}` | Register member to tournament |
| DELETE | `/api/tournaments/{tournamentId}/members/{memberId}` | Remove member from tournament |
| GET | `/api/tournaments/{tournamentId}/members` | View all participants in a tournament |

## ⚙️ **Technical Details**

### 🧱 Tech Stack
- **Language**: Java 21
- **Framework**: Spring Boot 3.5.3
- **Database**: MySQL 8.0 (Docker & AWS RDS)
- **ORM**: Hibernate with HikariCP
- **Build Tool**: Maven
- **Testing**: JUnit 5 and Mockito
- **DevOps**: Docker, GitHub Actions

### 🗂 Project Layout
```
src/
├── main/java/com/golfclub/
│   ├── members/
│   ├── tournaments/
│   ├── exception/
│   └── GolfClubApplication.java
├── resources/
│   ├── application.properties
│   ├── application-docker.properties
│   ├── application-aws.properties
│   ├── application-test.properties
└── test/
```

## 🔨 **Build & Execution**

### 🧪 Build and Test
```bash
./mvn clean compile
./mvn test
./mvn clean package
```

### 🚀 Run Locally
```bash
./mvn spring-boot:run
```

### 🌐 Run with Profile
```bash
./mvn spring-boot:run -Dspring.profiles.active=aws
```

## ✅ **Testing Framework**

- Coverage includes controllers, services, and repositories
- Uses H2 in-memory database for isolated test runs
- Includes integration tests for end-to-end validation
- 69+ test methods ensure system reliability

```bash
./mvn test
./mvn test jacoco:report
```

## 🔐 **Security Features**

- Input validation using annotations with custom messages
- CORS configured to restrict external access
- Global exception handler for consistent API responses
- SQL injection prevention using prepared statements
- Efficient connection pooling with HikariCP

## 🌍 **Deployment Options**

### 🐳 Docker (Local)
```bash
docker-compose up --build
```

### ☁️ AWS RDS Deployment
1. Set up MySQL RDS on AWS
2. Enable port `3306` in the security group
3. Configure environment variables
4. Deploy using AWS profile

## 🔁 **CI/CD Pipeline**
- GitHub Actions for continuous integration
- Test automation and build validation on every push
- Code quality reports and coverage metrics generated

## 📝 **Sample Requests**

### ➕ Create Member

POST /api/members
{
  "name": "Alice",
  "lastname": "Smith",
  "email": "alice@golf.com",
  "phone": "123-4567",
  "address": "789 Green Way",
  "durationOfMembership": "3 years"
}
```

### ➕ Create Tournament

POST /api/tournaments
{
  "startDate": "2025-09-01T09:00:00",
  "endDate": "2025-09-03T17:00:00",
  "location": "Pine Hills",
  "entryFee": 100.00,
  "cashPrize": 3000.00
}
```


**Crafted with ❤️ by Abdul Rahman Zahiri using Java 21 and Spring Boot 3.5.3**
