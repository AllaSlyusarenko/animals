create table animals.animals_habitats
(
    id_animal_type integer not null,
    id_area        integer not null,
    CONSTRAINT id_animal_type_id_area_pk PRIMARY KEY (id_animal_type, id_area)
);

ALTER TABLE animals.animals_habitats ADD CONSTRAINT id_animal_type_animal_type_id_type_fk
FOREIGN KEY(id_animal_type) REFERENCES animals.animal_type (id_type) ON DELETE CASCADE;

ALTER TABLE animals.animals_habitats ADD CONSTRAINT id_area_habitat_id_area_fk
FOREIGN KEY(id_area) REFERENCES animals.habitat (id_area) ON DELETE CASCADE;