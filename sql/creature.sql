create table creature
(
    id_creature bigint generated by default as identity
                constraint creature_pk primary key,
    name        text not null,
    type_id     integer not null,
    age         smallint not null,
    created     timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated     timestamp with time zone default CURRENT_TIMESTAMP
);

CREATE SEQUENCE creature_id_creature_seq  START WITH 1 INCREMENT BY 1;
alter sequence creature_id_creature_seq owned by creature.id_creature;
