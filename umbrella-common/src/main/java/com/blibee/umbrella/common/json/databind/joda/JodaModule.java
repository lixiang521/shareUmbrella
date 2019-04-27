package com.blibee.umbrella.common.json.databind.joda;

import com.blibee.umbrella.common.json.databind.joda.DateTimeDeserializer;
import com.blibee.umbrella.common.json.databind.joda.DateTimeSerializer;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;

/**
 * {@link com.fasterxml.jackson.datatype.joda.JodaModule} 扩展
 * Created by lixiang on 2019/04/27.
 */
public class JodaModule extends com.fasterxml.jackson.datatype.joda.JodaModule {

    protected JacksonJodaDateFormat jacksonJodaDateFormat;

    public JodaModule(String pattern) {
        jacksonJodaDateFormat = new JacksonJodaDateFormat(DateTimeFormat.forPattern(pattern));
        addSerializer(DateTime.class, new DateTimeSerializer(jacksonJodaDateFormat, false));
        addDeserializer(DateTime.class, forType(DateTime.class));
    }

    @SuppressWarnings("unchecked")
    public <T extends ReadableInstant> JsonDeserializer<T> forType(Class<T> cls) {
        return (JsonDeserializer<T>) new DateTimeDeserializer(cls, jacksonJodaDateFormat);
    }
}
