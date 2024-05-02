package ru.mts.hw_3.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Breed;
import ru.mts.hw_3.entity.Creature;
import ru.mts.hw_3.service.CreatureService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName(value = "Database tests")
class CreatureRepositoryTest {
    @Autowired
    CreatureRepository creatureRepository;
    @Autowired
    AnimalTypeRepository animalTypeRepository;
    @Autowired
    CreatureService creatureService;

    @Test
    @DisplayName(value = "Creature object creation list test")
    public void createCreatures() {
        Map<String, List<Creature>> creatures = creatureService.createCreatures(10);
        List<Creature> creatureList = creatures.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        assertNotNull(creatures);
        assertEquals(1, creatures.size());
        assertEquals(10, creatureList.size());
    }

    @Test
    @DisplayName(value = "Empty creature object creation test")
    public void addEmptyCreature() {
        List<Creature> creatures = creatureRepository.findAll();
        assertNotNull(creatures);
        Creature creatureEmptyIn = new Creature();
        Creature creatureOut = creatureRepository.create(creatureEmptyIn);
        assertNotNull(creatureOut);
        assertNull(creatureOut.getName());
        assertEquals(0, creatureOut.getAge());
        assertNull(creatureOut.getTypeId());
    }

    @Test
    @DisplayName(value = "Creature object creation test")
    public void addNotEmptyCreature() {
        AnimalType animalType = new AnimalType();
        animalTypeRepository.create(animalType);
        Breed breed = new Breed();
        Creature creatureEmptyIn = new Creature(0, "Puwistik", animalType, (short) 5, LocalDate.now(), LocalDate.now(), breed);
        Creature creatureOut = creatureRepository.create(creatureEmptyIn);
        assertNotNull(creatureOut);
        assertEquals("Puwistik", creatureOut.getName());
        assertEquals(5, creatureOut.getAge());
    }
}