package com.proto.file;

public enum EnumTest {
    abc("가나다"),
    efg("라마바");

    private final String value;

    EnumTest(final String value) {
        this.value = value;
    }
}
