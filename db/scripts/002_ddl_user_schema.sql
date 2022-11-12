create table if not exists users(
id serial primary key,
name text,
login varchar unique ,
password varchar
)