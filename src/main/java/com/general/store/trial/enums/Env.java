package com.general.store.trial.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Env {
    EMULATOR("src/main/resources/env/emulator.properties"),
    DEVICE("src/main/resources/env/device.properties");
    private final String path;
}
