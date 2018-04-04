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

/**
 * Signer key
 *
 * @version 2.0.4
 */
public class SignerKey {
    private final long numberOfDaysSinceEpoch;
    private final byte[] signingKey;

    public SignerKey(long numberOfDaysSinceEpoch, byte[] signingKey) {
        if (numberOfDaysSinceEpoch <= 0L) {
            throw new IllegalArgumentException("Not able to cache signing key. Signing date to be cached is invalid");
        } else if (signingKey == null) {
            throw new IllegalArgumentException("Not able to cache signing key. Signing Key to be cached are null");
        } else {
            this.numberOfDaysSinceEpoch = numberOfDaysSinceEpoch;
            this.signingKey = (byte[]) signingKey.clone();
        }
    }

    public long getNumberOfDaysSinceEpoch() {
        return this.numberOfDaysSinceEpoch;
    }

    public byte[] getSigningKey() {
        return (byte[]) this.signingKey.clone();
    }
}
