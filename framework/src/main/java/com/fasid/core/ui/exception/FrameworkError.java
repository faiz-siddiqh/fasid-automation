package com.fasid.core.ui.exception;


/**
 * Framework Specific Error
 */
public class FrameworkError extends Error {

    /**
     * Throws error with message - On framework level
     *
     * @param message
     */
    public FrameworkError(final String message) {
        super(message);
    }

    /**
     * Throws message and cause of the Error - framework level
     *
     * @param message
     * @param cause
     */
    public FrameworkError(final String message, final Throwable cause) {
        super(message, cause);
    }

}
