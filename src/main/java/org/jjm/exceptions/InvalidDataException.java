package org.jjm.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidDataException extends Exception {
    private static final Logger logger = LogManager.getLogger(InvalidDataException.class);

    public InvalidDataException() {
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(InvalidIDataType type, String filePath) {
        super(String.format("%s. File: %s", type.getDescription(), filePath));
        logger.error(this);
    }

    public InvalidDataException(InvalidIDataType type, String filePath, Throwable e) {
        super(String.format("%s. File: %s", type.getDescription(), filePath), e);
        logger.error(this);
    }
}
