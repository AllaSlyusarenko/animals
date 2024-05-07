create schema if not exists test;


CREATE SEQUENCE if not exists test.animal_type_id_type_sq as integer START 1 INCREMENT BY 1;

create table if not exists test.animal_type
(
    id_type integer default nextval('test.animal_type_id_type_sq') not null
            constraint animal_type_pk primary key,
    type    varchar(50) not null,
    is_wild boolean not null,
    created timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated timestamp with time zone default CURRENT_TIMESTAMP not null
);

alter sequence test.animal_type_id_type_sq owned by test.animal_type.id_type;


CREATE SEQUENCE if not exists test.breed_id_breed_sq as integer START 1 INCREMENT BY 1;

create table if not exists test.breed
(
    id_breed integer default nextval('test.breed_id_breed_sq') not null
                             constraint breed_pk primary key,
    name    varchar(50) not null,
    created timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated timestamp with time zone default CURRENT_TIMESTAMP not null
);

ALTER SEQUENCE test.breed_id_breed_sq OWNED BY test.breed.id_breed;


CREATE SEQUENCE if not exists test.animal_id_animal_sq as integer START 1 INCREMENT BY 1;

create table if not exists test.animal
(
    id_animal integer default nextval('test.animal_id_animal_sq') not null
                constraint creature_pk primary key,
    name        text not null,
    cost        numeric not null,
    type_id     integer not null,
    age         integer not null,
    birth_date  date not null,
    id_breed    integer not null,
    created     timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated     timestamp with time zone default CURRENT_TIMESTAMP not null
);

ALTER SEQUENCE test.animal_id_animal_sq OWNED BY test.animal.id_animal;

ALTER TABLE test.animal ADD CONSTRAINT type_id_animal_type_id_type_fk
FOREIGN KEY(type_id) REFERENCES test.animal_type (id_type);

ALTER TABLE test.animal ADD CONSTRAINT id_breed_breed_fk
FOREIGN KEY(id_breed) REFERENCES test.breed (id_breed);