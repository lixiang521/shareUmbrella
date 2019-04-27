package com.blibee.umbrella.api.json.helper;

import java.io.IOException;

import com.blibee.umbrella.api.pojo.date.ShortDate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created by lixiang on 2019/04/27.
 */
public class ShortDateJsonSerializer extends JsonSerializer<ShortDate> {

    @Override
    public void serialize(ShortDate shortDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(shortDate != null ? shortDate.toString() : "null");
    }

}
