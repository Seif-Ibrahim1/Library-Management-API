# Library Management System API

This is a Library Management System API built using Spring Boot. It allows librarians to manage books, patrons, and borrowing records.

## Features
### Data Storage with H2 Database

- **Entities**: The API includes entities for Books, Patrons, and Borrowing Records. These entities are mapped to corresponding tables in the H2 database.
- **Relationships**: Proper relationships are established between entities, such as one-to-many between books and borrowing records, ensuring data consistency and integrity.

### Transaction Management

- **Declarative Transactions**: The API implements declarative transaction management using Spring's `@Transactional` annotation. This ensures that critical operations, such as borrowing and returning books, are atomic and maintain data integrity.

### Validation and Error Handling

- **Input Validation**: Input validation is implemented for API requests to ensure that required fields are provided and data formats are correct. This prevents invalid data from being persisted in the database.
- **Graceful Error Handling**: The API handles exceptions gracefully and returns appropriate HTTP status codes and error messages when errors occur during request processing.

### Testing

- **Unit Tests**: The functionality of API endpoints is validated through unit tests. Testing frameworks like JUnit, Mockito, and SpringBootTest are used for testing to ensure that each endpoint behaves as expected.

### Caching

- **Spring Caching Mechanisms**: Spring's caching mechanisms are utilized to cache frequently accessed data, such as book details or patron information, improving system performance.

### Book Management

- **GET /api/books**: Retrieve a list of all books.
  - Endpoint to fetch all books stored in the system.
- **GET /api/books/{id}**: Retrieve details of a specific book by ID.
  - Endpoint to retrieve details of a book using its unique identifier.
- **POST /api/books**: Add a new book to the library.
  - Endpoint to add a new book with provided details to the library.
- **PUT /api/books/{id}**: Update an existing book's information.
  - Endpoint to update information of an existing book based on its ID.
- **DELETE /api/books/{id}**: Remove a book from the library.
  - Endpoint to delete a book from the library using its ID.

### Patron Management

- **GET /api/patrons**: Retrieve a list of all patrons.
  - Endpoint to fetch details of all patrons registered in the system.
- **GET /api/patrons/{id}**: Retrieve details of a specific patron by ID.
  - Endpoint to retrieve details of a patron using their unique identifier.
- **POST /api/patrons**: Add a new patron to the system.
  - Endpoint to register a new patron with provided details in the system.
- **PUT /api/patrons/{id}**: Update an existing patron's information.
  - Endpoint to update information of an existing patron based on their ID.
- **DELETE /api/patrons/{id}**: Remove a patron from the system.
  - Endpoint to delete a patron from the system using their ID.

### Borrowing

- **POST /api/borrow/{bookId}/patron/{patronId}**: Allow a patron to borrow a book.
  - Endpoint to allow a patron to borrow a book by providing book ID and patron ID.
- **PUT /api/return/{bookId}/patron/{patronId}**: Record the return of a borrowed book by a patron.
  - Endpoint to record the return of a borrowed book by a patron using book ID and patron ID.

## Getting Started

1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn spring-boot:run` to start the application.
4. The application will be available at `http://localhost:8080`.

## Usage

1. Import the provided Postman collection.
2. Make requests to different endpoints to interact with the API.
