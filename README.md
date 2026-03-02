# Task Management Application

This is a full-stack application with a **Spring Boot** backend and a **Svelte** frontend.

## Prerequisites

*   **Java 17** or higher
*   **Node.js** (v18+ recommended) and **npm**
*   **Maven** (optional, wrapper included)

## Project Structure

*   `src/main/java`: Spring Boot Backend
*   `frontend`: Svelte Frontend

## How to Run

### 1. Backend (Spring Boot)

Open a terminal in the root directory (`Task/`) and run:

**Using Maven Wrapper (Windows):**
```bash
./mvnw spring-boot:run
```

**Using Maven Wrapper (Linux/Mac):**
```bash
./mvnw spring-boot:run
```

The backend server will start at `http://localhost:8080`.

### 2. Frontend (Svelte)

Open a new terminal, navigate to the `frontend` folder, and start the development server:

```bash
cd frontend
npm install
npm run dev
```

The frontend will be available at `http://localhost:5173`.

## Usage

1.  Open your browser and go to `http://localhost:5173`.
2.  The frontend proxies API requests (starting with `/api`) to the backend running on port 8080.
