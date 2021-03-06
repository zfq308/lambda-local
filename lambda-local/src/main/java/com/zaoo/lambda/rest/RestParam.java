package com.zaoo.lambda.rest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RestParam {
    String value();

    boolean required() default true;

    Class<? extends RestParamDeserializer<?>> deserializer() default JsonRestParamDeserializer.class;
}
