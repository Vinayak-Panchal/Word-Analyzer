# Word Analyzer (Spring Boot)

## Description

A REST API that analyzes a list of words based on user-defined rules:
- Count of words starting with a specific character
- List of words longer than a specific length

## Features
- Modular and testable design using the Strategy Pattern
- Rules are encapsulated as classes implementing the `WordFilterRule` interface
- Unit test coverage with JUnit and JaCoCo
- Dockerized for deployment

## API Usage

**POST /api/words/analyze **

### Request Body
```
{
  "words": ["Mouse", "man", "Elephant", "dog", "mountain", "Maxwell", "pen", "Marker"],
  "filters": {
	"startsWith": "m",
    "minLength": 5
  }
}
```

### Response
```
{
  "results": {
        "minLength": [
            "Mouse",
            "Elephant",
            "mountain",
            "Maxwell",
            "Marker"
        ],
        "startsWith": 5
    }
}
```
### How to Run

## Build and Run with Maven

```bash
mvn clean package
java -jar target/wordanalyzer-0.0.1-SNAPSHOT.jar
```

## Run with Docker

```bash
docker build -t word-analyzer-app .
docker run -p 8083:8083 word-analyzer-app
```

## Test Coverage

Generate JaCoCo report:

```bash
mvn clean verify
```
View JaCoCo report: `target/site/jacoco/index.html`

## Tests
```bash
mvn clean test
```

## Extending Business Rules
Add a new class implementing the `WordFilterRule` interface and use it in the service.
