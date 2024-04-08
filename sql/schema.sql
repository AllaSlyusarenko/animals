create database animals with owner postgres;

create table creature
(
    id_creature bigint not null constraint creature_pk primary key,
    name        text,
    type_id     integer,
    age         smallint,
    created     timestamp with time zone,
    updated     timestamp with time zone
);

alter table creature owner to postgres;

create table animal_type
(
    id_type integer not null constraint animal_type_pk primary key,
    type    char(50),
    is_wild boolean,
    created timestamp with time zone,
    updated timestamp with time zone
);

alter table animal_type owner to postgres;
