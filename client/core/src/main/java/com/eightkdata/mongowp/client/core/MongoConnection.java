
package com.eightkdata.mongowp.client.core;

import com.eightkdata.mongowp.messages.request.DeleteMessage;
import com.eightkdata.mongowp.messages.request.QueryMessage;
import com.eightkdata.mongowp.messages.request.UpdateMessage;
import com.eightkdata.mongowp.mongoserver.api.safe.Command;
import com.eightkdata.mongowp.mongoserver.api.safe.CommandReply;
import com.eightkdata.mongowp.mongoserver.api.safe.pojos.MongoCursor;
import com.eightkdata.mongowp.mongoserver.protocol.exceptions.MongoException;
import com.mongodb.annotations.NotThreadSafe;
import java.io.Closeable;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bson.BsonDocument;

/**
 *
 */
@NotThreadSafe
public interface MongoConnection extends Closeable {

    @Nonnull
    public MongoClient getClientOwner();

    @Nonnull
    public MongoCursor<BsonDocument> query(
            String database,
            String collection,
            EnumSet<QueryMessage.Flag> flags,
            @Nullable BsonDocument query,
            int numberToSkip,
            int numberToReturn,
            @Nullable BsonDocument projection) throws MongoException;

    public void asyncKillCursors(@Nonnull Iterable<Long> cursors) throws
            MongoException;

    public void asyncKillCursors(long[] cursors) throws
            MongoException;

    public void asyncInsert(
            @Nonnull String database,
            @Nonnull String collection,
            boolean continueOnError,
            List<? extends BsonDocument> docsToInsert
    ) throws MongoException;

    public void asyncUpdate(
            @Nonnull String database,
            @Nonnull String collection,
            @Nonnull EnumSet<UpdateMessage.Flag> flags,
            @Nonnull BsonDocument selector,
            @Nonnull BsonDocument update) throws MongoException;

    public void asyncDelete(
            @Nonnull String database,
            @Nonnull String collection,
            @Nonnull EnumSet<DeleteMessage.Flag> flags,
            @Nonnull BsonDocument selector) throws MongoException;

    /**
     *
     * @param <Arg>
     * @param <Result>
     * @param command
     * @param database
     * @param isSlaveOk if the execution on slave nodes has to be enforced
     * @param arg
     * @return the reply to the given command. The reply is always
     *         {@linkplain CommandReply#isOk() ok} because all errors are
     *         reported as exceptions
     * @throws MongoException
     */
    @Nonnull
    public <Arg, Result> Result execute(
            @Nonnull Command<? super Arg, Result> command,
            String database,
            boolean isSlaveOk,
            @Nonnull Arg arg)
            throws MongoException;

    /**
     * Returns true iff this object represents a remote server.
     *
     * This method will be return true in most cases, but when used in a mongod
     * node, an instance of this object can represent the same mongod
     * @return
     */
    public boolean isRemote();

    @Override
    public void close();
}
