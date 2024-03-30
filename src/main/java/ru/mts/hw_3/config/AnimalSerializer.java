package ru.mts.hw_3.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.tomcat.util.codec.binary.Base64;
import ru.mts.entity.AbstractAnimal;

import java.io.IOException;

public class AnimalSerializer extends StdSerializer<AbstractAnimal> {
    public AnimalSerializer() {
        super(AbstractAnimal.class);
    }

    @Override
    public void serialize(AbstractAnimal value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        System.out.println("метод сериализации");
    }

//    @Override
//    public void serialize(AbstractAnimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
//        jgen.writeStartObject();
//        jgen.writeStringField("breed", value.getBreed());
//        jgen.writeStringField("name", value.getName());
//        jgen.writeNumberField("cost", 5);
//        jgen.writeStringField("character", value.getCharacter());
//        jgen.writeStringField("birthDate", value.getBirthDateString());
//        jgen.writeStringField("secretInformation", new String(new Base64().encode(value.getSecretInformation().getBytes())));
//        jgen.writeEndObject();
//    }

//    @Override
//    public Class<AbstractAnimal> handledType() {
//        return AbstractAnimal.class;
//    }

}