/*
 * Copyright (C) 2018. Huawei Technologies Co., LTD. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of Apache License, Version 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Apache License, Version 2.0 for more details.
 */

package com.smn.signer;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class SignerUtil {
    private static final SimpleDateFormat dateForamte1 = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat dateForamte2 = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");

    public static String formatDateStamp(long timeMilli) {
        dateForamte1.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateForamte1.format(timeMilli);
    }

    public static String formatTimestamp(long timeMilli) {
        dateForamte2.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateForamte2.format(timeMilli);
    }

    public static long numberOfDaysSinceEpoch(long milliSinceEpoch) {
        return TimeUnit.MILLISECONDS.toDays(milliSinceEpoch);
    }
}
