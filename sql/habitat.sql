CREATE SEQUENCE animals.habitat_id_area_sq as integer START 1 INCREMENT BY 1;

create table animals.habitat
(
    id_area     integer default nextval('animals.habitat_id_area_sq') not null
                constraint habitat_pk primary key,
    area        char(50) not null,
    created     timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated     timestamp with time zone
);

ALTER SEQUENCE animals.habitat_id_area_sq OWNED BY animals.habitat.id_area;