package ru.mts.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.mts.entity.AnimalType;
import ru.mts.service.CreateAnimalServiceImpl;

@Component
public class InitializationFieldAnimalTypeBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalServiceImpl) {
            CreateAnimalServiceImpl createAnimalService = (CreateAnimalServiceImpl) bean;
            createAnimalService.setAnimalType(AnimalType.getRandomAnimalType());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}