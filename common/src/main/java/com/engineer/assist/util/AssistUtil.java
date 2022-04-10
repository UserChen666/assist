package com.engineer.assist.util;

import cn.hutool.core.lang.UUID;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AssistUtil {
    public static String getUuid(boolean local){

        if(local) {
            long l = ThreadLocalRandom.current().nextLong();
            return String.valueOf(l);
        }else{
            UUID uuid = UUID.randomUUID();

            return uuid.toString();
        }
    }

    public static <T,R> List<R> getProperty(List<T> data, Function<T,R> func, Class<R> r){
        if(CollectionUtils.isEmpty(data)) {
            return Lists.newArrayList();
        }

        return data.stream().map(func).collect(Collectors.toList());
    }
}
