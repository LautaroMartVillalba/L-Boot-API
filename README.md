# LBootAPI
### About this project
Were createn to improve my knowledge with Data Base management, EndPoint creating, security configuration, errors resolution and development using Spring Framework.
___
### How it works?
- An user was registered with an specific role.
- The user are persisted in the DataBase (PostgreSQL).
- By his role, the user are available to access to some EndPoints.
___
# Explanation and conventions
## Entities
For convention, the entity's classes will be name with the sufix "Entity", to prevent errors during development by the existence of 'User' Interface in Spring. The DataBase schema's names will use the prefix "entity" with the entity's name in plural, separate with underscore ('entity_ejemplos').

The entities use the Builder Pattern for have a confortable construction and improve the recognition of assigned parameters.
###   Users
- Names can be repeated.
- Emails must be uniques.
- Passwords are encripted with BCrypt.
- ManyToMany relationship between User and Role.
- The parameters 'isEnabeled','accountNoExpired','accountNoLocked' y 'credentialsNoExpired' are used by Spring Security during authentication.

#### Roles and Privileges
One role have many privileges. Privileges determinate what task an User can perform.
###### Privileges:
- GET
- POST
- DELETE
- PATCH
- PUT
- HEAD
- OPTIONS
###### Roles:
- READER(For BookController, GET, POST, DELETE, PATCH)
- DRIVER(For CarController, GET, POST, DELETE, PATCH)
- MECHANIC(For ToolController, GET, POST, DELETE, PATCH)
- ADMIN(For any controller, GET, DELETE, POST, PUT, PATCH, OPTION)
- DEVELOPER(For any controller, GET, DELETE, HEAD, POST, PUT, PATCH, OPTION)

## Repositories
Each repository create custom methods using the JPA Query methods due to its ease and flexibility. For earch Repository have to be a Service that implements the business logic.

Are named with: the entity's name which is related and the sufix "Reposiroty" (ExampleRepository).
## Services
Services have to implement and use the business logic in ALL custom methos created in the repositories, and the used default methods. 

Are named with: the repository's name which is related and replace the sufix "Repository" with "Service"(ExampleService).
#### Business Logic
Each custom and default methods that will used have to have an implementation that meets the following aspecs:
- No blank data.
- No emptu data.
- If not interfere in DataBase: return (in a successful query) the received value, or (in a not successful query) a null or empty value.
- if interfere in DataBase: return (in a correct and complete execution) a messagge that inform the successful task; or (in a incorrect or incomplete execution) an custom exception managed with ExceptionHandler.

##### ExceptionHandlers
- ExceptionDTO: containg the exception parameters to return a specefic messagge (now just have one type String messagge).
- Organization: for each entity will be one package with two packages -> Advice & Types
Types: Exception classes for each problem will may be in the execution.
Advice: Use `@RestControllerAdvice` and `@ExceptionHandler` to manage custom exceptions and return a customized message with ResponseEntity<> constructed by ExceptionDTO using Builder Pattern and a HttpStatus.

## Controllers
All controller's classes will be named by the entity class name which is related with the sufix "Controller" (ExampleController). 
#### EndPoints
The EndPoints URI will consist in two parts: /{EntityName}/{NameMethodReference}
#### Security
On first instance in class level will use `@PreAuthorize` with all roles how has authorized access (Admin, Developer and CorrespondingUserRole). In inidividual methods how needs an authorization change will use `@PreAuthorize` to overwrite the authorized roles.
#### Methods
When a method will called the method result will be stored in a variable; using an if() to return a correct ResponseEntity ONLY if the result is true/correct. Else the method will return an empty RresponseEntity.
```java
@RestController
@AllArgsConstructor
@RequestMapping("/example")
@PreAuthorize("hasAnyRole('ROLE_EXAMPLE', 'ROLE_ADMIN', 'ROLE_DEVELOPER')")
public class ExampleController{

private ExampleService service;

@GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<ExampleEntity>> getExmapleById(@PathVariable Long id){
        Optional<ExampleEntity> result = service.findByExampleID(id);
        if (example.isPresent()){
            return ResponseEntity.ok().body(result); //here return a 200 HttpResponse cause result it's true/present/correct
        }
        return ResponseEntity.notFound().build();
    }
}
```

# Dependencies
### Spring Security
Add and manage the project security configuration.
### Spring JPA
Connect and persist in a DataBase.
### Spring Web
Suport to web creation. 
### Lombok
Simplify and economize repetitive code.
### PostgreSQL
Connect with a PostgreSQL DataBase.
### FlyWay
Manage changes in new DataBase Versions.
