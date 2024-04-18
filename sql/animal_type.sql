CREATE SEQUENCE animals.animal_type_id_type_sq as integer START 1 INCREMENT BY 1;

create table animals.animal_type
(
    id_type integer default nextval('animals.animal_type_id_type_sq') not null
            constraint animal_type_pk primary key,
    type    char(50) not null,
    is_wild boolean not null,
    created timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated timestamp with time zone default CURRENT_TIMESTAMP not null
);

alter sequence animals.animal_type_id_type_sq owned by animals.animal_type.id_type;