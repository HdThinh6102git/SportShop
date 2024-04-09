# [SPORTSHOPAPI] Restful API for Sport Shop App Built With Java - Springboot - MySQL
## Introduction
The Sport Shop API is a project written in Java using the Spring Boot framework and MySQL. It is developed following the RESTful API standards and emphasizes the implementation of each functionality. It applies the 3-tier architecture and follows a logical folder structure for better organization. 
The project follows the RESTful API principles, ensuring that the API endpoints are designed to be stateless, scalable, and interoperable. It follows standard HTTP methods (GET, POST, PUT, DELETE) for different operations on resources.

The application is structured using the 3-tier architecture, which separates the concerns into three layers: presentation layer, business logic layer, and data access layer. This architecture promotes modularity, reusability, and maintainability of the codebase. The presentation layer handles the incoming requests and communicates with the business logic layer, which contains the core functionality and business rules. The data access layer interacts with the database and performs CRUD (Create, Read, Update, Delete) operations.

To ensure a logical and organized codebase, the project follows a well-defined folder structure. This structure may include directories for controllers, services, repositories, models, and utility classes. Each folder has a specific responsibility and helps in maintaining code separation and clarity.
## Main functions: 
* **Product Category Management**
* **Product Management**
* **Cart Management**
* **Order Management**
* **Payment**

### git commit rule
> type(scope?): subject
>
```angular2html
type ở trên có thể là:
    - build: Changes that affect the build system or external dependencies (example scopes: gulp, broccoli, npm)
    - ci: Changes to our CI configuration files and scripts (example scopes: Gitlab CI, Circle, BrowserStack, SauceLabs)
    - chore: add something without touching production code (Eg: update npm dependencies)
    - docs: Documentation only changes
    - feat: A new feature
    - fix: A bug fix
    - perf: A code change that improves performance
    - refactor: A code change that neither fixes a bug nor adds a feature
    - revert: Reverts a previous commit
    - style: Changes that do not affect the meaning of the code (Eg: adding white-space, formatting, missing semi-colons, etc)
    - test: Adding missing tests or correcting existing tests
```
