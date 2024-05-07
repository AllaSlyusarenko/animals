create schema if not exists animals;


CREATE SEQUENCE if not exists animals.animal_type_id_type_sq as integer START 1 INCREMENT BY 1;

create table if not exists animals.animal_type
(
    id_type integer default nextval('animals.animal_type_id_type_sq') not null
            constraint animal_type_pk primary key,
    type    varchar(50) not null,
    is_wild boolean not null,
    created timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated timestamp with time zone default CURRENT_TIMESTAMP not null
);

alter sequence animals.animal_type_id_type_sq owned by animals.animal_type.id_type;


CREATE SEQUENCE if not exists animals.breed_id_breed_sq as integer START 1 INCREMENT BY 1;

create table if not exists animals.breed
(
    id_breed integer default nextval('animals.breed_id_breed_sq') not null
                             constraint breed_pk primary key,
    name    varchar(50) not null,
    created timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated timestamp with time zone default CURRENT_TIMESTAMP not null
);

ALTER SEQUENCE animals.breed_id_breed_sq OWNED BY animals.breed.id_breed;


CREATE SEQUENCE if not exists animals.animal_id_animal_sq as integer START 1 INCREMENT BY 1;

create table if not exists animals.animal
(
    id_animal integer default nextval('animals.animal_id_animal_sq') not null
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

ALTER SEQUENCE animals.animal_id_animal_sq OWNED BY animals.animal.id_animal;

ALTER TABLE animals.animal ADD CONSTRAINT type_id_animal_type_id_type_fk
FOREIGN KEY(type_id) REFERENCES animals.animal_type (id_type);

ALTER TABLE animals.animal ADD CONSTRAINT id_breed_breed_fk
FOREIGN KEY(id_breed) REFERENCES animals.breed (id_breed);