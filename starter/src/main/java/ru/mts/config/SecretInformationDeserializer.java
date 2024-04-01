package ru.mts.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;

public class SecretInformationDeserializer extends StdDeserializer<String> {
    public SecretInformationDeserializer() {
        this(null);
    }

    public SecretInformationDeserializer(Class<String> t) {
        super(t);
    }

    @Override
    public String deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        String secretInfo = parser.getText();
        return new String(new Base64().decode(secretInfo.getBytes("UTF-8")));
    }
}