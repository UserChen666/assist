package com.engineer.assist.util;

import cn.hutool.core.lang.UUID;

import java.util.concurrent.ThreadLocalRandom;

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
}
