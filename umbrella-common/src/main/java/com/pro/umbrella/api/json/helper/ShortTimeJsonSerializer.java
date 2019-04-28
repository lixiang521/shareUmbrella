package com.pro.umbrella.api.json.helper;

import java.io.IOException;

import com.pro.umbrella.api.pojo.date.ShortTime;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created by lixiang on 2019/04/27.
 */
public class ShortTimeJsonSerializer extends JsonSerializer<ShortTime> {

    @Override
    public void serialize(ShortTime shortTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(shortTime != null ? shortTime.toString() : "null");
    }

}