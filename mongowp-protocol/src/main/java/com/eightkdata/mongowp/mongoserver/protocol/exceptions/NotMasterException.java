
package com.eightkdata.mongowp.mongoserver.protocol.exceptions;

import com.eightkdata.mongowp.mongoserver.protocol.MongoWP.ErrorCode;

/**
 *
 */
public class NotMasterException extends MongoException {
    private static final long serialVersionUID = 1L;

    public NotMasterException(String customMessage) {
        super(customMessage, ErrorCode.NOT_MASTER);
    }

    public NotMasterException(String customMessage, Throwable cause) {
        super(customMessage, cause, ErrorCode.NOT_MASTER);
    }

    public NotMasterException() {
        super(ErrorCode.NOT_MASTER);
    }

    public NotMasterException(Throwable cause) {
        super(cause, ErrorCode.NOT_MASTER);
    }

}
