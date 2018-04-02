package com.smn.signer;

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
            this.signingKey = (byte[])signingKey.clone();
        }
    }

    public long getNumberOfDaysSinceEpoch() {
        return this.numberOfDaysSinceEpoch;
    }

    public byte[] getSigningKey() {
        return (byte[])this.signingKey.clone();
    }
}
