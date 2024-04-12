CREATE SEQUENCE animals.creature_id_creature_sq as integer START 1 INCREMENT BY 1;

create table animals.creature
(
    id_creature bigint default nextval('animals.creature_id_creature_sq') not null
                constraint creature_pk primary key,
    name        text not null,
    type_id     integer not null,
    age         smallint not null,
    created     timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated     timestamp with time zone
);

alter sequence animals.creature_id_creature_sq owned by animals.creature.id_creature;