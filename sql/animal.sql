CREATE SEQUENCE animals.animal_id_animal_sq as integer START 1 INCREMENT BY 1;

create table animals.animal
(
    id_animal integer default nextval('animals.animal_id_animal_sq') not null
                constraint creature_pk primary key,
    name        text not null,
    cost        bigint not null,
    type_id     integer not null,
    age         smallint not null,
    created     timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated     timestamp with time zone default CURRENT_TIMESTAMP not null
);

ALTER SEQUENCE animals.animal_id_animal_sq OWNED BY animals.animal.id_animal;

ALTER TABLE animals.animal ADD CONSTRAINT type_id_animal_type_id_type_fk
FOREIGN KEY(type_id) REFERENCES animals.animal_type (id_type);