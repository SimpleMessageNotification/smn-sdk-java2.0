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
package com.smn.util;

public class StringUtil {

    public static boolean isBlank(final CharSequence charSequence) {
        int charSequenceLen;
        if ( null == charSequence || 0 == (charSequenceLen = charSequence.length())) {
            return true;
        }
        for (int index = 0; index < charSequenceLen; index++) {
            if (!Character.isWhitespace(charSequence.charAt(index))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return  null == charSequence || 0 == charSequence.length();
    }
}
