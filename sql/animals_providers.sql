create table animals.animals_providers
(
    id_animal_type integer not null,
    id_provider    integer not null,
    CONSTRAINT id_animal_type_id_provider_pk PRIMARY KEY (id_animal_type, id_provider)
);

ALTER TABLE animals.animals_providers ADD CONSTRAINT id_animal_type_animal_type_id_type_fk
FOREIGN KEY(id_animal_type) REFERENCES animals.animal_type (id_type);

ALTER TABLE animals.animals_providers ADD CONSTRAINT id_provider_provider_id_provider_fk
FOREIGN KEY(id_provider) REFERENCES animals.provider (id_provider);