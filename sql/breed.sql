CREATE SEQUENCE animals.breed_id_breed_sq as integer START 1 INCREMENT BY 1;

create table animals.breed
(
    id_breed integer default nextval('animals.breed_id_breed_sq') not null
                             constraint breed_pk primary key,
    name    char(50) not null,
    created timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated timestamp with time zone default CURRENT_TIMESTAMP not null
);

ALTER SEQUENCE animals.breed_id_breed_sq OWNED BY animals.breed.id_breed;