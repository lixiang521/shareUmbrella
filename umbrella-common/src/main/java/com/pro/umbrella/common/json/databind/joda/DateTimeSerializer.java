package com.pro.umbrella.common.json.databind.joda;

import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;

/**
 * {@link org.joda.time.DateTime} 序列化器。
 * Created by lixiang on 2019/04/27.
 */
public class DateTimeSerializer extends com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer {

    private boolean custom = false;

    public DateTimeSerializer(JacksonJodaDateFormat format, boolean custom) {
        super(format);
        this.custom = custom;
    }

    @Override
    public com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer withFormat(JacksonJodaDateFormat formatter) {
        return new DateTimeSerializer(formatter, true);
    }

    @Override
    protected boolean _useTimestamp(SerializerProvider provider) {
        if (custom) {
            return false;
        }
        return super._useTimestamp(provider);
    }
}
