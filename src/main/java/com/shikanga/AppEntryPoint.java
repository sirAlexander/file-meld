package com.shikanga;

import io.quarkus.runtime.Quarkus;

public class AppEntryPoint {

    public static void main(String... args) {
        Quarkus.run(FileMeldApplication.class, args);
    }
}
