package com.blibee.umbrella.api.json.helper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.blibee.umbrella.api.pojo.date.DateRange;

import org.springframework.util.StringUtils;

/**
 * Created by lixiang on 2019/04/27.
 */
public class DateRangeJsonDeserializer extends JsonDeserializer<DateRange> {
    @Override
    public DateRange deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        String source = p.getText();
        if (!StringUtils.hasText(source)) return null;
        if ("null".equalsIgnoreCase(source)) return null;

        try {
            return DateRange.create(source);
        } catch (Throwable t) {
            throw new JsonParseException(p, "cannot parse date range string: " + source, p.getCurrentLocation(), t);
        }
    }
}