package com.pro.umbrella.api.json.helper;

import java.io.IOException;
import java.sql.Timestamp;

import com.pro.umbrella.common.json.databind.JacksonDateFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created by lixiang on 2019/04/27.
 */
public class TimestampJsonSerializer extends JsonSerializer<Timestamp> {

    public static final JacksonDateFormat DATE_FORMAT = new JacksonDateFormat(TimestampJsonDeserializer.DATE_PATTERN);

    @Override
    public void serialize(Timestamp date, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(date != null ? DATE_FORMAT.format(date) : "null");
    }
}

