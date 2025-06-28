# Portfolio Backend

## Introduction

This project is a backend service built with Java and Spring Boot. It provides RESTful APIs for validating emails and
phone numbers, handling contact forms, interacting with GitHub files, and basic health checks. The backend is designed
to be used with a frontend application or as a standalone API.

## API Information

### Base URL

```
http://localhost:8080
```

### Available Endpoints

#### 1. Email Validation

- `GET /email/validate?email=example@example.com` — Validate an email address.

#### 2. Phone Validation

- `GET /phone/validate?phone=+1234567890` — Validate a phone number.

#### 3. Health Check

- `GET /ping` — Check if the server is running.

#### 4. GitHub Files

- `GET /files/contacts` — Get a list of contact files from GitHub.

#### 5. GitHub Commit

- `PUT /add/file?path=your/path` — Create or update a file in a GitHub repository. (Request body: GithubCommitModel)
- `POST /add/contact` — Commit a contact form. (Request body: ContactModel)

### Request & Response Format

- All endpoints accept and return JSON.
- Use appropriate HTTP methods (GET, POST, PUT) as described above.

### Example: Email Validation Request

```
GET /email/validate?email=test@example.com
```

### Example: Phone Validation Request

```
GET /phone/validate?phone=+1234567890
```

### Example: Health Check

```
GET /ping
Response: { "message": "Server is awake and ready to serve! 🚀" }
```

## Getting Started

1. Clone the repository.
2. Run `./gradlew bootRun` to start the server.
3. Access the API at `http://localhost:8080`.

## License

This project is licensed under the MIT License.
