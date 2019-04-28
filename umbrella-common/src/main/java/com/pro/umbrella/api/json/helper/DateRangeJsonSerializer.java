package com.pro.umbrella.api.json.helper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pro.umbrella.api.pojo.date.DateRange;

/**
 * Created by lixiang on 2019/04/27.
 */
public class DateRangeJsonSerializer extends JsonSerializer<DateRange> {

    @Override
    public void serialize(DateRange value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value != null ? value.toString() : "null");
    }

}