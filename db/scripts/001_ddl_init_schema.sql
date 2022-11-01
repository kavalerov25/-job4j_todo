create table if not exists tasks (
    id          serial primary key,
    name        varchar(50),
    description text,
    created     timestamp,
    done        boolean
);


insert into tasks(id, name, description, created, done)
 values
 (1, 'тренировка', 'in monday', '2001-09-28 23:00', true),
 (2, 'уборка', 'in monday', '2001-09-28 23:00', false),
 (3, 'учёба', 'in monday', '2001-09-28 23:00', false);

select * from tasks;

