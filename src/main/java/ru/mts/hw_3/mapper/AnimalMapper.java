package ru.mts.hw_3.mapper;

import lombok.experimental.UtilityClass;
import ru.mts.hw_3.dto.AnimalDto;
import ru.mts.hw_3.entity.Animal;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class AnimalMapper {
    public AnimalDto animalToAnimalDto(Animal animal) {
        AnimalDto animalDto = new AnimalDto();
        animalDto.setIdAnimal(animal.getIdAnimal());
        animalDto.setName(animal.getName());
        animalDto.setAnimalType(animal.getAnimalType());
        animalDto.setAge(animal.getAge());
        animalDto.setCreated(animal.getCreated());
        animalDto.setUpdated(animal.getUpdated());
        animalDto.setBreed(animal.getBreed());
        animalDto.setCost(animal.getCost());
        animalDto.setBirthDate(animal.getBirthDate());
        return animalDto;
    }

    public List<AnimalDto> animalsToAnimalDtos(List<Animal> animals) {
        return animals.stream().map(AnimalMapper::animalToAnimalDto).collect(Collectors.toList());
    }
}
