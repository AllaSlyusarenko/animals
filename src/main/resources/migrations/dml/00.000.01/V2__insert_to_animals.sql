
INSERT INTO animals.animal_type(type, is_wild)
SELECT concat('animal type ',substr(gen_random_uuid()::text, 1, 10)),
       (array [true, false])[floor(random() * 2 + 1)]
FROM
  generate_series(1, 10);


INSERT INTO animals.breed(name)
SELECT concat('breed ', substr(gen_random_uuid()::text, 1, 10))
FROM
  generate_series(1, 10);


INSERT INTO animals.animal(name, cost, type_id, age, birth_date, id_breed)
SELECT concat('animal ', substr(gen_random_uuid()::text, 1, 10)),
       round(random() * 25000 + 100)::numeric,
       round(random() * 8.9 + 1)::integer,
       round(random() * 25 + 1)::integer,
       now() - (random() * (interval '25 years')),
       round(random() * 8.9 + 1)::integer
FROM
  generate_series(1, 10);


INSERT INTO animals.habitat(area)
SELECT concat('area ', substr(gen_random_uuid()::text, 1, 10))
FROM
  generate_series(1, 10);

INSERT INTO animals.animals_habitats(id_animal_type, id_area)
VALUES  (1,10),
        (2,9),
        (3,8),
        (4,7),
        (5,6),
        (6,5),
        (7,4),
        (8,3),
        (9,2),
        (10,1);

INSERT INTO animals.provider(name, phone)
SELECT concat('provider ', substr(gen_random_uuid()::text, 1, 10)),
      concat('+7 ',repeat((array ['0','1', '2', '3', '4', '5', '6', '7', '8', '9'])[floor(random() * 8.9 + 1)],10))
FROM
  generate_series(1, 10);

INSERT INTO animals.animals_providers(id_animal_type, id_provider)
VALUES  (1,10),
        (2,9),
        (3,8),
        (4,7),
        (5,6),
        (6,5),
        (7,4),
        (8,3),
        (9,2),
        (10,1);