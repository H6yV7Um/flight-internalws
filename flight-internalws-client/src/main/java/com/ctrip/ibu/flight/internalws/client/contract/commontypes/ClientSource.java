package com.ctrip.ibu.flight.internalws.client.contract.commontypes;

public enum ClientSource {

    UNKNOWN(0),
    ONLINE(1),
    ANDROID(2),
    IOS(3),
    WAP(4);

    private final int value;

    ClientSource(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ClientSource findByValue(int value) {
        switch (value) {
            case 0:
                return UNKNOWN;
            case 1:
                return ONLINE;
            case 2:
                return ANDROID;
            case 3:
                return IOS;
            case 4:
                return WAP;
            default:
                return null;
        }
    }
}
