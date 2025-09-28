# Task 1: Java Backend REST API

**Developer: Anurag**  
**Date: $(current_date)**

A Spring Boot application providing REST API for task management with MongoDB integration.

## Features
- Create, read, update, delete tasks
- Execute shell commands safely
- Search tasks by name and owner
- MongoDB persistence
- Command safety validation

## Prerequisites
- Java 17+
- Maven 3.6+
- MongoDB
- Docker (optional)

## Setup

1. **Start MongoDB:**
   ```bash
   docker run -d -p 27017:27017 --name mongodb-anurag mongo:latest