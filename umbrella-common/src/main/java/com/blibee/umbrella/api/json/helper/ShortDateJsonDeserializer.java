package com.blibee.umbrella.api.json.helper;

import java.io.IOException;

import com.blibee.umbrella.api.pojo.date.ShortDate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Created by lixiang on 2019/04/27.
 */
public class ShortDateJsonDeserializer extends JsonDeserializer<ShortDate> {
    @Override
    public ShortDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String time = jsonParser.getText();
        if (time != null && !time.isEmpty()) {
            return ShortDate.create(time);
        }
        return null;
    }
}