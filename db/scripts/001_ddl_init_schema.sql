create table if not exists tasks (
    id          serial primary key,
    name        text,
    description text,
    created     timestamp,
    done        boolean
);


