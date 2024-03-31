package ru.mts.hw_3.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class SecretInformationSerializer extends StdSerializer<String> {
    public SecretInformationSerializer() {
        super(String.class);
    }

    @Override
    public void serialize(String tmpInt,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeObject(tmpInt.toString());
    }
}
