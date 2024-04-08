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