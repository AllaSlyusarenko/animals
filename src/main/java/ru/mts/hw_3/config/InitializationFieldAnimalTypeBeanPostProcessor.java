package ru.mts.hw_3.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.service.CreateAnimalServiceImpl;

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
