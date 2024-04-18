CREATE SEQUENCE animals.provider_id_provider_sq as integer START 1 INCREMENT BY 1;

create table animals.provider
(
    id_provider integer default nextval('animals.provider_id_provider_sq') not null
                constraint provider_pk primary key,
    name        text not null,
    phone       char(50) not null,
    created     timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated     timestamp with time zone default CURRENT_TIMESTAMP not null
);

ALTER SEQUENCE animals.provider_id_provider_sq OWNED BY animals.provider.id_provider;