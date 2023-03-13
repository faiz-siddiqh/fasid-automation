package com.fasid.core.ui.utils;

import com.fasid.core.ui.exception.FrameworkError;
import com.fasid.enums.Message;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static java.text.MessageFormat.format;
import static org.apache.logging.log4j.LogManager.getLogger;

public class ErrorUtils {

    private static Logger LOGGER = getLogger();

    /**
     * This message throws Error with message provided
     *
     * @param message
     * @param args
     */
    public static void throwError(final Message message, final Object... args) {

        throw new FrameworkError(format(message.getMessageText(), args));
    }

    /**
     * This method is used to handle exceptions , print stack trace and throw wrapped error
     *
     * @param message
     * @param cause
     * @param args
     */
    public static void handleAndThrow(final Message message, final Throwable cause, final Object... args) {
        var throwable = cause;
        final var stack = new ArrayList<>();
        stack.add(format("Error Occured at ({0})", throwable.getClass().getName()));
        final var stackTrace = "\t at {0}:{1} Line Number :{2}";

        do {
            if (stack.size() > 1) {
                stack.add(format("caused by :({0})", throwable.getCause()));
            }
            stack.add(format("Message:{0}", throwable.getMessage()));
            for (final var trace : cause.getStackTrace()) {
                stack.add(format(stackTrace, trace.getClassName(), trace.getMethodName(), trace.getLineNumber()));
            }
        } while (throwable != null);

        throwable = throwable.getCause();
        stack.forEach(LOGGER::error);
        throw new FrameworkError(format(message.getMessageText(), args), cause);

    }

    /**
     * This method is used to check if the subject is null , throw error if null
     *
     * @param subject
     * @param throwMessage
     * @param args
     * @param <T>
     * @return
     */
    public static <T> T requireNonNull(final T subject, final Message throwMessage, final Object... args) {
        if (subject == null) {
            throw new FrameworkError(format(throwMessage.getMessageText(), args));
        }
        return subject;
    }

    private ErrorUtils() {

        // Utility classes should be private
    }

}
