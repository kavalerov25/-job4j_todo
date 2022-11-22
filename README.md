# job4j_todo

## Приложение "TODO list"

Это веб-приложение TODO List. Есть список заданий.

Приложение позволяет:

- добавлять задачи в список
- удалять задачи
- помечать задачи как выполненные
- редактировать задачи -отображать
- завершенные/незавершенные задачи отдельными списками

## Использованные технологии

![java](https://img.shields.io/badge/Java-17-red)
![Spring Boot](https://img.shields.io/badge/Spring-Boot-green)
![PostgresSQL](https://img.shields.io/badge/PostgresSQL-42.3.6-brightgreen)
![Liquibase](https://img.shields.io/badge/Liquibase-core-red)
![Hibernate](https://img.shields.io/badge/Hibernate-5.6.11.Final-red)
![Lombok](https://img.shields.io/badge/Lombok-1.18.22-lightgrey)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0.15-blue)
![Bootstrap](https://img.shields.io/badge/Bootstrap-style-blue)

Перед запуском установите:

- Java 17
- Apache Maven 3.x
- PostgreSQL 14

## Запуск приложения

1. Создать бд:

```sql
    create database todo;
```

2. Запуск приложения с maven. Перейдите в корень проекта через командную строку и выполните команды:

```
    mvn clean install
    mvn spring-boot:run
```

3.поменять login/password в файлах src/main/resources/db.properties и src/main/resources/hibernate.cfg.xml на требуемый
![](images/step1.png)
![](images/step2.png)

## Examples

Список всех задач (завершенных/незавершенных)
![index page](images/Tasks.png)
Список завершенных задач
![index page](images/CompletedTasks.png)
Список новых задач
![index page](images/NewTasks.png)
При нажатии на кнопку "Изменить задачу" происходит переход в меню редактирования задачи
![index page](images/EditTask.png)

### Контакты:

email: kavalerov24@gmail.ru