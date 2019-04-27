package com.blibee.umbrella.api.pojo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by lixiang on 2019/04/27.
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = -6781577186340650624L;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

}