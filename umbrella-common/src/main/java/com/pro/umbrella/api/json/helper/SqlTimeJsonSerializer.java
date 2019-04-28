package com.pro.umbrella.api.json.helper;

import java.io.IOException;
import java.sql.Time;

import com.pro.umbrella.common.json.databind.JacksonDateFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created by lixiang on 2019/04/27.
 */
public class SqlTimeJsonSerializer extends JsonSerializer<Time> {

    public static final JacksonDateFormat DATE_FORMAT = new JacksonDateFormat(SqlTimeJsonDeserializer.DATE_PATTERN);

    @Override
    public void serialize(Time date, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(date != null ? DATE_FORMAT.format(date) : "null");
    }
}