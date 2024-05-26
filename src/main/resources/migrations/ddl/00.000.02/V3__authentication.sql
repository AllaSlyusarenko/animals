CREATE SEQUENCE if not exists animals.role_id_role_sq as integer START 1 INCREMENT BY 1;

create table if not exists animals.role
(
    id_role     integer default nextval('animals.role_id_role_sq') not null
                constraint role_pk primary key,
    role_name   varchar(50) not null
);

CREATE SEQUENCE if not exists animals.person_id_person_sq as integer START 1 INCREMENT BY 1;

create table if not exists animals.person
(
    id_person   integer default nextval('animals.person_id_person_sq') not null
                constraint person_pk primary key,
    name        varchar(50) not null,
    password    varchar(50) not null
);

create table if not exists animals.person_roles
(
    person_id integer not null,
    role_id    integer not null,
    CONSTRAINT person_id_role_id_pk PRIMARY KEY (person_id, role_id)
);

ALTER TABLE animals.person_roles ADD CONSTRAINT person_id_fk
FOREIGN KEY(person_id) REFERENCES animals.person (id_person);

ALTER TABLE animals.person_roles ADD CONSTRAINT role_id_fk
FOREIGN KEY(role_id) REFERENCES animals.role (id_role);