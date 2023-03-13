package com.fasid.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Validation messages
 *
 * @author : Faiz Ahmed Siddiqh
 */
@AllArgsConstructor
@Getter
public enum Message {

    ELEMENT_NOT_FOUND("Element [{0}] not found ..."),
    DRIVER_ERROR_OCCURED("Error encountered by Driver: {0}"),
    LOGS_CREATION_ERROR("Error while creating logs: {0}"),
    LOGS_WRITING_ERROR("Error while writing logs: {0}"),

    DROPDOWN_DESELECT_ERROR("Multiple Selects not present .");

    private final String messageText;
}
