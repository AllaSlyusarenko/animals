package ru.mts.hw_3.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.mts.entity.AbstractAnimal;

import java.io.IOException;
import java.io.StringWriter;

public class MyPairSerializer extends JsonSerializer<AbstractAnimal> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(AbstractAnimal value,
                          JsonGenerator gen,
                          SerializerProvider serializers)
            throws IOException, JsonProcessingException {

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        gen.writeFieldName(writer.toString());
    }
}
