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

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ValidationUtil test
 *
 * @author zhangyx
 * @version 2.0.5
 */
public class ValidationUtilTest {

    @Test
    public void testContainSignNameFromMessage() {
        List<String[]> data = new ArrayList<String[]>() {{
            add(new String[]{"【华为云】好的", "true"});
            add(new String[]{"好的【华为云】", "true"});
            add(new String[]{"【华为云华为云华为云】好的", "false"});
            add(new String[]{"好的【华为云华为云华为云】", "false"});
            add(new String[]{"好的[huawei]", "true"});
            add(new String[]{"[huawei]好的", "true"});
            add(new String[]{"[huaweihuawei]好的", "false"});
            add(new String[]{"好的[huaweihuawei]", "false"});
            add(new String[]{"[huawei好]好的", "false"});
            add(new String[]{"好的[huawei好]", "false"});
        }};

        for (String[] testCase : data) {
            Assert.assertEquals(Boolean.valueOf(testCase[1]), ValidationUtil.containSignNameFromMessage(testCase[0]));
        }
    }
}
