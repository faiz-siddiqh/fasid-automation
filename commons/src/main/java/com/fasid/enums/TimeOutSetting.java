package com.fasid.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TimeOutSetting {

    EXPLICIT_WAIT(10),
    HIGHLIGHT_DELAY(100),
    IMPLICIT_WAIT(10),
    PAGE_LOAD_TIMEOUT(10),
    SCRIPT_TIMEOUT(10);

    private final long duration;

}
