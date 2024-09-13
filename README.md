# LBootAPI
### About this project
Were created to improve my knowledge with Data Base management, EndPoint creating, security configuration, errors resolution and development using Spring Framework.
___
### How it works?
- A user was registered with a specific role.
- The user is persisted in the DataBase (PostgresSQL).
- By his role, the user is available to access to some EndPoints.
___
# Explanation and conventions
## Entities
For convention, the entity's classes will be named with the suffix "Entity", to prevent errors during development by the existence of 'User' Interface in Spring. The DataBase schema's names will use the prefix "entity" with the entity's name in plural, separate with underscore ('entity_example').

The entities use the Builder Pattern for have a comfortable construction and improve the recognition of assigned parameters.
###   Users
- Names can be repeated.
- Emails must be unique.
- Passwords are encrypted with BCrypt.
- ManyToMany relationship between User and Role.
- The parameters 'isEnabled','accountNoExpired','accountNoLocked' y 'credentialsNoExpired' are used by Spring Security during authentication.

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
Each repository create custom methods using the JPA Query methods due to its ease and flexibility. For each Repository have to be a Service that implements the business logic.

Are named with: the entity's name which is related and the suffix "Repository" (ExampleRepository).
## Services
Services have to implement and use the business logic in ALL custom methods created in the repositories, and the used default methods. 

Are named with: the repository's name which is related and replace the suffix "Repository" with "Service"(ExampleService).
#### Business Logic
Each custom and default methods that will used have to have an implementation that meets the following aspects:
- No blank data.
- No empty data.
- If not interfere in DataBase: return (in a successful query) the received value, or (in a not successful query) a null or empty value.
- if interfere in DataBase: return (in a correct and complete execution) a message that inform the successful task; or (in an incorrect or incomplete execution) a custom exception managed with ExceptionHandler.

##### ExceptionHandlers
- ExceptionDTO: contain the exception parameters to return a specific message and HttpStatusCode.
- Organization: have to create an ExceptionHandler for each problem that can return an exception

## Controllers
All controller's classes will be named by the entity class name which is related with the suffix "Controller" (ExampleController). 
#### EndPoints
The EndPoints URI will consist in two parts: /{EntityName}/{NameMethodReference}
#### Security
On first instance in class level will use `@PreAuthorize` with all roles how has authorized access (Admin, Developer and CorrespondingUserRole). On individual methods how needs an authorization change will use `@PreAuthorize` to overwrite the authorized roles.
#### Methods
When a method will call the method result will be stored in a variable; using an if() to return a correct ResponseEntity ONLY if the result is true/correct. Else the method will return an empty ResponseEntity.
```java
@RestController
@AllArgsConstructor
@RequestMapping("/example")
@PreAuthorize("hasAnyRole('ROLE_EXAMPLE', 'ROLE_ADMIN', 'ROLE_DEVELOPER')")
public class ExampleController{

private ExampleService service;

@GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<ExampleEntity>> getExampleById(@PathVariable Long id){
        Optional<ExampleEntity> result = service.findByExampleID(id);
        if (example.isPresent()){
            return ResponseEntity.ok().body(result); //here return a 200 HttpResponse cause result it's true/present/correct
        }
        return ResponseEntity.notFound().build();
    }
}
```

# Partitions
Are named with: prefix "part_", partitioned entity's name, partition column, and partition data.

'part_entity_column_data'

# Dependencies
### Spring Security
Add and manage the project security configuration.
### Spring JPA
Connect and persist in a DataBase.
### Spring Web
Support to web creation. 
### Lombok
Simplify and economize repetitive code.
### PostgresSQL
Connect with a PostgresSQL DataBase.
### FlyWay
Manage changes in new DataBase Versions.
