
package com.eightkdata.mongowp.mongoserver.api.safe.impl;

import com.eightkdata.mongowp.mongoserver.api.safe.CommandReply;
import com.eightkdata.mongowp.mongoserver.api.safe.tools.bson.BsonDocumentBuilder;
import com.eightkdata.mongowp.mongoserver.callback.WriteOpResult;
import com.eightkdata.mongowp.mongoserver.protocol.MongoWP;
import com.eightkdata.mongowp.mongoserver.protocol.MongoWP.ErrorCode;
import com.eightkdata.mongowp.mongoserver.protocol.exceptions.MongoException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bson.BsonDocument;

import static com.eightkdata.mongowp.mongoserver.api.safe.CommandReply.ERR_MSG_FIELD;
import static com.eightkdata.mongowp.mongoserver.api.safe.CommandReply.OK_FIELD;

/**
 *
 */
public class FailedCommandReply<R> implements CommandReply<R> {

    private final MongoException exception;
    private final WriteOpResult writeOpResult;

    public FailedCommandReply(@Nonnull MongoException exception) {
        this(exception, null);
    }

    public FailedCommandReply(@Nonnull MongoException exception, @Nullable WriteOpResult writeOpResult) {
        this.exception = exception;
        this.writeOpResult = writeOpResult;
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public ErrorCode getErrorCode() {
        return exception.getErrorCode();
    }

    @Override
    public String getErrorMessage() throws IllegalStateException {
        return exception.getMessage();
    }

    @Override
    public MongoException getErrorAsException() throws IllegalStateException {
        return exception;
    }

    @Override
    public WriteOpResult getWriteOpResult() {
        return writeOpResult;
    }

    @Override
    public R getResult() throws IllegalStateException {
        throw new IllegalStateException("The command did not finish correctly");
    }

    @Override
    public BsonDocument marshall() {
        return new BsonDocumentBuilder()
                .append(ERR_MSG_FIELD, getErrorMessage())
                .append(OK_FIELD, MongoWP.KO)
                .build();
    }

}
