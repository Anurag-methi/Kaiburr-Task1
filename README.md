# Task 1: Java Backend REST API

**Developer: Anurag**  
**Date: September 28, 2025**

## 📋 Project Overview
A Spring Boot application providing REST API for task management with MongoDB integration. The application allows creating, reading, updating, deleting, and executing shell commands as tasks.

## ✅ Features Implemented
- **REST API** with all required endpoints
- **MongoDB persistence** for task storage
- **Command security validation** to prevent unsafe commands
- **Task execution history** with timestamps and output
- **Search functionality** by task name
- **Health check endpoint** for monitoring

## 🚀 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks?id={id}` | Get task by ID |
| PUT | `/api/tasks` | Create/update task |
| DELETE | `/api/tasks/{id}` | Delete task |
| GET | `/api/tasks/search?name={name}` | Search tasks by name |
| PUT | `/api/tasks/{id}/execute` | Execute task command |
| GET | `/api/tasks/health` | Health check |

## 🛠 Technology Stack
- **Java 17**
- **Spring Boot 3.2.0**
- **MongoDB**
- **Maven**
- **Docker**

## 📥 Installation & Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Docker

## 1. Clone the Repository
```bash
git clone https://github.com/Anurag-methi/Kaiburr-Task1.git
cd Task1
```
## 2. Start MongoDB
```bash
docker run -d -p 27017:27017 --name mongodb-anurag mongo:latest
```
## 3. Build the Application
```bash
mvn clean package
```
## 4. Run the Application
```bash
java -jar target/task-app-1.0.0.jar
```

## 5. Verify Installation
```bash
curl http://localhost:8080/api/tasks/health
```
```text
Task1/
├── src/
│   └── main/
│       ├── java/com/anurag/taskapp/
│       │   ├── TaskAppApplication.java
│       │   ├── controller/TaskController.java
│       │   ├── model/Task.java
│       │   ├── model/TaskExecution.java
│       │   ├── repository/TaskRepository.java
│       │   └── service/TaskService.java
│       └── resources/
│           └── application.yml
├── screenshots/
│   ├── health-check.png
│   ├── create-task.png
│   ├── get-all-tasks.png
│   ├── get-task-by-id.png
│   ├── search-tasks.png
│   └── execute-command.png
├── pom.xml
├── README.md
```

## 👨‍💻 Developer
**Anurag - Complete implementation of REST API with MongoDB integration.**
