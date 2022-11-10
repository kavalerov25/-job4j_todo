create table if not exists users(
id serial primary key,
name text,
login varchar,
password varchar(16)
)