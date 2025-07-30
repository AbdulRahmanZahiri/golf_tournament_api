# Golf Club Tournament API

This is a Spring Boot REST API for managing a golf club system. It allows you to manage members, tournaments, and their relationships, with support for Docker deployment and MySQL via AWS RDS.

---

##  Features

- Add/Search Members
- Add/Search Tournaments
- Assign Members to Tournaments
- Search APIs (by name, date, location, etc.)
- Docker support
- AWS RDS integration
- Postman tested

---

##  API Endpoints


###  Member Endpoints

| Method | Endpoint             | Description                |
|--------|----------------------|----------------------------|
| GET    | /api/members         | Get all members            |
| POST   | /api/members         | Add new member             |
| GET    | /api/members/{id}    | Get member by ID           |
| DELETE | /api/members/{id}    | Delete member              |

###  Member Search APIs

| Method | Endpoint                             | Description                        |
|--------|--------------------------------------|------------------------------------|
| GET    | /api/members/searchByName            | Search by first & last name        |
| GET    | /api/members/searchByPhone           | Search by phone number             |
| GET    | /api/members/searchByStartDate       | Members registered on a date       |

---

###  Tournament Endpoints

| Method | Endpoint               | Description                  |
|--------|------------------------|------------------------------|
| GET    | /api/tournaments       | Get all tournaments          |
| POST   | /api/tournaments       | Add new tournament           |
| GET    | /api/tournaments/{id}  | Get tournament by ID         |
| DELETE | /api/tournaments/{id}  | Delete tournament            |
| POST   | /api/tournaments/{id}/register/{memberId} | Register member to tournament |

###  Tournament Search APIs

| Method | Endpoint                             | Description                        |
|--------|--------------------------------------|------------------------------------|
| GET    | /api/tournaments/searchByLocation    | Search by location                 |
| GET    | /api/tournaments/searchByStartDate   | Search by start date               |
| GET    | /api/tournaments/{id}/participants   | Get all registered members         |

---


## ScreenShot






##  How to Run with Docker

> Make sure Docker is installed and your MySQL (AWS RDS) database is ready.

### Build Docker Image

```bash
docker build -t golf_tournament-app:latest .
