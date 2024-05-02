package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.Creature;

import java.util.List;
import java.util.Map;

public interface CreatureService {
    Map<String, List<Creature>> createCreatures(int N);
}