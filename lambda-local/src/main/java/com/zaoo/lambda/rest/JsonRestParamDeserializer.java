package com.zaoo.lambda.rest;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaoo.lambda.ObjectMappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Support String, Integer, Long, Float, Double, Boolean, Enum, and JSON (Using jackson)
 */
public class JsonRestParamDeserializer implements RestParamDeserializer<Object> {
    private static final Logger log = LoggerFactory.getLogger(JsonRestParamDeserializer.class);
    private final ObjectMapper objectMapper = ObjectMappers.getInstance();

    @Override
    public Object deserialize(String str, JavaType javaType) {
        if (String.class.isAssignableFrom(javaType.getRawClass())) {
            return str;
        }
        if (Integer.class.isAssignableFrom(javaType.getRawClass()) || int.class.isAssignableFrom(javaType.getRawClass())) {
            return Integer.parseInt(str);
        }
        if (Long.class.isAssignableFrom(javaType.getRawClass()) || long.class.isAssignableFrom(javaType.getRawClass())) {
            return Long.parseLong(str);
        }
        if (Float.class.isAssignableFrom(javaType.getRawClass()) || float.class.isAssignableFrom(javaType.getRawClass())) {
            return Float.parseFloat(str);
        }
        if (Double.class.isAssignableFrom(javaType.getRawClass()) || double.class.isAssignableFrom(javaType.getRawClass())) {
            return Double.parseDouble(str);
        }
        if (Boolean.class.isAssignableFrom(javaType.getRawClass()) || boolean.class.isAssignableFrom(javaType.getRawClass())) {
            return Boolean.parseBoolean(str);
        }
        if (Enum.class.isAssignableFrom(javaType.getRawClass())) {
            return Enum.valueOf((Class) javaType.getRawClass(), str);
        }

        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.warn("Unable to parse obj in JSON", e);
            // Unable to read the str as JSON.
        }

        throw new IllegalArgumentException("Unable to get RestParamDeserializer of this type:" + javaType);
    }
}
