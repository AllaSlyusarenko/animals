create schema if not exists animals;

create table if not exists animals.animal_type
(
    id_type int generated by default as identity primary key,
    type    char(50) not null,
    is_wild boolean not null,
    created timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated timestamp with time zone default CURRENT_TIMESTAMP not null
);

create table  if not exists animals.creature
(
    id_creature int generated by default as identity primary key,
    name        text not null,
    type_id     integer references animals.animal_type(id_type) not null,
    age         smallint not null,
    created     timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated     timestamp with time zone default CURRENT_TIMESTAMP not null
);

create table if not exists animals.provider
(
    id_provider int generated by default as identity primary key,
    name        text not null,
    phone       char(50) not null,
    created     timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated     timestamp with time zone default CURRENT_TIMESTAMP not null
);

create table if not exists animals.animals_providers
(
    id_animal_type  integer references animals.animal_type(id_type)  not null,
    id_provider     integer references animals.provider(id_provider) not null,
    CONSTRAINT id_animal_type_id_provider_pk PRIMARY KEY (id_animal_type, id_provider)
);

create table if not exists animals.habitat
(
    id_area     int generated by default as identity primary key,
    area        char(50) not null,
    created     timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated     timestamp with time zone default CURRENT_TIMESTAMP not null
);

create table if not exists animals.animals_habitats
(
    id_animal_type integer references animals.animal_type(id_type) not null,
    id_area        integer references animals.habitat(id_area) not null,
    CONSTRAINT id_animal_type_id_area_pk PRIMARY KEY (id_animal_type, id_area)
);