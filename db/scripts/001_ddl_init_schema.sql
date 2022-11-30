create table if not exists tasks (
    id          serial primary key,
    name        text,
    description text,
    created     TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    done        boolean
);


