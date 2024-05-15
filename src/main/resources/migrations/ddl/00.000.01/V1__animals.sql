create schema if not exists animals;

---

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

---

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

---

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

---

CREATE SEQUENCE if not exists animals.habitat_id_area_sq as integer START 1 INCREMENT BY 1;

create table if not exists animals.habitat
(
    id_area     integer default nextval('animals.habitat_id_area_sq') not null
                constraint habitat_pk primary key,
    area        varchar(50) not null,
    created timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated timestamp with time zone default CURRENT_TIMESTAMP not null
);

ALTER SEQUENCE animals.habitat_id_area_sq OWNED BY animals.habitat.id_area;

---

create table if not exists animals.animals_habitats
(
    id_animal_type integer not null,
    id_area        integer not null,
    CONSTRAINT id_animal_type_id_area_pk PRIMARY KEY (id_animal_type, id_area)
);

ALTER TABLE animals.animals_habitats ADD CONSTRAINT id_animal_type_animal_type_id_type_fk
FOREIGN KEY(id_animal_type) REFERENCES animals.animal_type (id_type);

ALTER TABLE animals.animals_habitats ADD CONSTRAINT id_area_habitat_id_area_fk
FOREIGN KEY(id_area) REFERENCES animals.habitat (id_area);

---

CREATE SEQUENCE if not exists animals.provider_id_provider_sq as integer START 1 INCREMENT BY 1;

create table if not exists animals.provider
(
    id_provider integer default nextval('animals.provider_id_provider_sq') not null
                constraint provider_pk primary key,
    name        text not null,
    phone       varchar(50) not null,
    created timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated timestamp with time zone default CURRENT_TIMESTAMP not null
);

ALTER SEQUENCE animals.provider_id_provider_sq OWNED BY animals.provider.id_provider;

---

create table if not exists animals.animals_providers
(
    id_animal_type integer not null,
    id_provider    integer not null,
    CONSTRAINT id_animal_type_id_provider_pk PRIMARY KEY (id_animal_type, id_provider)
);

ALTER TABLE animals.animals_providers ADD CONSTRAINT id_animal_type_animal_type_id_type_fk
FOREIGN KEY(id_animal_type) REFERENCES animals.animal_type (id_type);

ALTER TABLE animals.animals_providers ADD CONSTRAINT id_provider_provider_id_provider_fk
FOREIGN KEY(id_provider) REFERENCES animals.provider (id_provider);