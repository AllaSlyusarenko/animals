//package ru.mts.hw_3.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.mts.hw_3.config.ConfigPropertiesForDB;
//import ru.mts.hw_3.entity.AnimalType;
//import ru.mts.hw_3.entity.Creature;
//import ru.mts.hw_3.entity.Habitat;
//import ru.mts.hw_3.entity.Provider;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Service
//public class ServiceDBImpl implements ServiceDB {
//    private static Connection connection;
//
//    @Autowired
//    public ServiceDBImpl(ConfigPropertiesForDB configPropertiesForDB) throws SQLException {
//        connection = DriverManager.getConnection(configPropertiesForDB.getUrl(), configPropertiesForDB.getUserName(),
//                configPropertiesForDB.getPassword());
//    }
//
//    /**
//     * Метод - возвращает всех животных из бд animals
//     */
//    @Override
//    public List<Creature> getAllCreatures() throws SQLException {
//        String sqlAllCreatures = "SELECT * FROM animals.creature";
//        PreparedStatement statement = connection.prepareStatement(sqlAllCreatures);
//        ResultSet resultSet = statement.executeQuery();
//        List<Creature> creatures = new ArrayList<>();
//        while (resultSet.next()) {
//            creatures.add(new Creature(
//                    resultSet.getInt("id_creature"),
//                    resultSet.getString("name"),
//                    resultSet.getInt("type_id"),
//                    resultSet.getShort("age"),
//                    resultSet.getDate("created").toLocalDate(),
//                    resultSet.getDate("updated").toLocalDate()
//            ));
//        }
//        log.info("Creatures in DB: {}", creatures);
//        return creatures;
//    }
//
//    /**
//     * Метод - возвращает все типы животных из бд animals
//     */
//    public List<AnimalType> getAllAnimalTypes() throws SQLException {
//        String sqlAllAnimalTypes = "SELECT * FROM animals.animal_type";
//        PreparedStatement statement = connection.prepareStatement(sqlAllAnimalTypes);
//        ResultSet resultSet = statement.executeQuery();
//        List<AnimalType> animalTypes = new ArrayList<>();
//        while (resultSet.next()) {
//            animalTypes.add(new AnimalType(
//                    resultSet.getInt("id_type"),
//                    resultSet.getString("type").strip(),
//                    resultSet.getBoolean("is_wild"),
//                    resultSet.getDate("created").toLocalDate(),
//                    resultSet.getDate("updated").toLocalDate()
//            ));
//        }
//        log.info("AnimalTypes in DB: {}", animalTypes);
//        return animalTypes;
//    }
//
//    /**
//     * Метод - возвращает все среды обитания животных из бд animals
//     */
//    public List<Habitat> getAllHabitats() throws SQLException {
//        String sqlAllHabitats = "SELECT * FROM animals.habitat";
//        PreparedStatement statement = connection.prepareStatement(sqlAllHabitats);
//        ResultSet resultSet = statement.executeQuery();
//        List<Habitat> habitats = new ArrayList<>();
//        while (resultSet.next()) {
//            habitats.add(new Habitat(
//                    resultSet.getInt("id_area"),
//                    resultSet.getString("area").strip(),
//                    resultSet.getDate("created").toLocalDate(),
//                    resultSet.getDate("updated").toLocalDate()
//            ));
//        }
//        log.info("Habitats in DB: {}", habitats);
//        return habitats;
//    }
//
//    /**
//     * Метод - возвращает всех поставщиков животных из бд animals
//     */
//
//    public List<Provider> getAllProviders() throws SQLException {
//        String sqlAllProviders = "SELECT * FROM animals.provider";
//        PreparedStatement statement = connection.prepareStatement(sqlAllProviders);
//        ResultSet resultSet = statement.executeQuery();
//        List<Provider> providers = new ArrayList<>();
//        while (resultSet.next()) {
//            providers.add(new Provider(
//                    resultSet.getInt("id_provider"),
//                    resultSet.getString("name"),
//                    resultSet.getString("phone").strip(),
//                    resultSet.getDate("created").toLocalDate(),
//                    resultSet.getDate("updated").toLocalDate()
//            ));
//        }
//        log.info("Providers in DB: {}", providers);
//        return providers;
//    }
//}