CREATE SEQUENCE animals.creature_id_creature_sq as integer START 1 INCREMENT BY 1;

create table animals.creature
(
    id_creature integer default nextval('animals.creature_id_creature_sq') not null
                constraint creature_pk primary key,
    name        text not null,
    type_id     integer not null,
    age         smallint not null,
    created     timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated     timestamp with time zone default CURRENT_TIMESTAMP not null
);

ALTER SEQUENCE animals.creature_id_creature_sq OWNED BY animals.creature.id_creature;

ALTER TABLE animals.creature ADD CONSTRAINT type_id_animal_type_id_type_fk
FOREIGN KEY(type_id) REFERENCES animals.animal_type (id_type);