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

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class SdkDigestInputStream extends DigestInputStream {

    public SdkDigestInputStream(InputStream stream, MessageDigest digest) {
        super(stream, digest);
    }

    public final long skip(long n) throws IOException {
        if (n <= 0L) {
            return n;
        } else {
            byte[] b = new byte[(int) Math.min(2048L, n)];

            long m;
            int len;
            for (m = n; m > 0L; m -= (long) len) {
                len = this.read(b, 0, (int) Math.min(m, (long) b.length));
                if (len == -1) {
                    return m == n ? -1L : n - m;
                }
            }

            assert m == 0L;

            return n;
        }
    }
}