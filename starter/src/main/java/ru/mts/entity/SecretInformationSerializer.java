package ru.mts.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;

public class SecretInformationSerializer extends JsonSerializer<String> {
//    public SecretInformationSerializer() {
//        super(String.class);
//    }

    @Override
    public void serialize(String tmpInt,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeObject(new String(new Base64().encode(tmpInt.getBytes())));
    }
}