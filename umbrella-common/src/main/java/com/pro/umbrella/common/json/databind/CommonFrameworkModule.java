package com.pro.umbrella.common.json.databind;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.pro.umbrella.api.json.KeyValue;
import com.pro.umbrella.api.json.helper.DateRangeJsonDeserializer;
import com.pro.umbrella.api.json.helper.DateRangeJsonSerializer;
import com.pro.umbrella.api.json.helper.ShortDateJsonDeserializer;
import com.pro.umbrella.api.json.helper.ShortDateJsonSerializer;
import com.pro.umbrella.api.json.helper.ShortTimeJsonDeserializer;
import com.pro.umbrella.api.json.helper.ShortTimeJsonSerializer;
import com.pro.umbrella.api.json.helper.SqlDateJsonDeserializer;
import com.pro.umbrella.api.json.helper.SqlDateJsonSerializer;
import com.pro.umbrella.api.json.helper.SqlTimeJsonDeserializer;
import com.pro.umbrella.api.json.helper.SqlTimeJsonSerializer;
import com.pro.umbrella.api.json.helper.TimestampJsonDeserializer;
import com.pro.umbrella.api.json.helper.TimestampJsonSerializer;
import com.pro.umbrella.api.pojo.date.DateRange;
import com.pro.umbrella.api.pojo.date.ShortDate;
import com.pro.umbrella.api.pojo.date.ShortTime;

/**
 * Common Framework支持Module。
 * Created by lixiang on 2019/04/27.
 */
public class CommonFrameworkModule extends SimpleModule {

    public CommonFrameworkModule() {
        super("CommonFrameworkModule", Version.unknownVersion());

        addDeserializer(ShortDate.class, new ShortDateJsonDeserializer());
        addSerializer(ShortDate.class, new ShortDateJsonSerializer());
        addDeserializer(DateRange.class, new DateRangeJsonDeserializer());
        addSerializer(DateRange.class, new DateRangeJsonSerializer());
        addDeserializer(ShortTime.class, new ShortTimeJsonDeserializer());
        addSerializer(ShortTime.class, new ShortTimeJsonSerializer());
        // sql
        addSerializer(java.sql.Date.class, new SqlDateJsonSerializer());
        addDeserializer(java.sql.Date.class, new SqlDateJsonDeserializer());
        addSerializer(java.sql.Time.class, new SqlTimeJsonSerializer());
        addDeserializer(java.sql.Time.class, new SqlTimeJsonDeserializer());
        addSerializer(Timestamp.class, new TimestampJsonSerializer());
        addDeserializer(Timestamp.class, new TimestampJsonDeserializer());
        // 兼容 jackson 2.5 以下的版本, 对 Map.Entry 序列化做特殊处理
        addSerializer(Map.Entry.class, new JsonSerializer<Map.Entry>() {
            @Override
            public void serialize(Map.Entry entry, JsonGenerator gen, SerializerProvider serializers)
                    throws IOException {
                gen.writeObject(new KeyValue(entry.getKey(), entry.getValue()));
            }
        });
    }
}
