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

package com.smn.signer.Util;

import java.util.Locale;

public class BinaryUtils {

    /**
     * convert byte[] to hex string
     *
     * @param bytesData src byte[] data
     * @return hex String
     */
    public static String toHex(byte[] bytesData) {
        StringBuilder stringBuilder = new StringBuilder(bytesData.length * 2);

        for (int index = 0; index < bytesData.length; index++) {
            String hexString = Integer.toHexString(bytesData[index]);
            if (hexString.length() == 1) {
                stringBuilder.append("0");
            } else if (hexString.length() == 8) {
                hexString = hexString.substring(6);
            }

            stringBuilder.append(hexString);
        }

        return stringBuilder.toString().toLowerCase(Locale.getDefault());
    }
}
