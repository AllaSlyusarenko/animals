INSERT INTO animals.animal_type(type, is_wild)
VALUES
    ('Dog', false),
    ('Wolf', true),
    ('Cat', false);

INSERT INTO animals.creature(name, type_id, age)
VALUES
    ('Warik', 1, 3),
    ('Seriy', 2, 2),
    ('Piksel', 3, 1);

INSERT INTO animals.provider(name, phone)
VALUES
    ('Psarnya', '+79161234567'),
    ('Les', '+79169876543'),
    ('Kowarnja','+79162345678');

INSERT INTO animals.animals_providers(id_animal_type, id_provider)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

INSERT INTO animals.habitat(area)
VALUES
    ('Dvor'),
    ('Gustoi les'),
    ('Dom' );

INSERT INTO animals.animals_habitats(id_animal_type, id_area)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);
