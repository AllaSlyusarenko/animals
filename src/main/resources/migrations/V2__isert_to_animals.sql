
INSERT INTO animals.animal_type(type, is_wild)
SELECT substr(gen_random_uuid()::text, 1, 10),
       (array [true, false])[floor(random() * 2 + 1)]
FROM
  generate_series(1, 10);


INSERT INTO animals.breed(name)
SELECT substr(gen_random_uuid()::text, 1, 10)
FROM
  generate_series(1, 10);


INSERT INTO animals.animal(name, cost, type_id, age, birth_date, id_breed)
SELECT substr(gen_random_uuid()::text, 1, 10),
       round(random() * 25000 + 100)::numeric,
       round(random() * 8.9 + 1)::integer,
       round(random() * 25 + 1)::integer,
       now() - (random() * (interval '25 years')),
       round(random() * 8.9 + 1)::integer
FROM
  generate_series(1, 10);


INSERT INTO animals.habitat(area)
SELECT substr(gen_random_uuid()::text, 1, 10)
FROM
  generate_series(1, 10);

INSERT INTO animals.animals_habitats(id_animal_type, id_area)
SELECT round(random() * 8.9 + 1)::integer,
       round(random() * 8.9 + 1)::integer
FROM
  generate_series(1, 10);

INSERT INTO animals.provider(name, phone)
SELECT substr(gen_random_uuid()::text, 1, 10),
       substring('0123456789', round(random() * 10)::integer, 1)
FROM
  generate_series(1, 10);