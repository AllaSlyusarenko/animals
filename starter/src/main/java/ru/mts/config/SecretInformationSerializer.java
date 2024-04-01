package ru.mts.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;

public class SecretInformationSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String information,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(new String(new Base64().encode(information.getBytes())));
    }
}