
package com.eightkdata.mongowp.mongoserver.api.safe.library.v3m0.commands.repl;

import com.eightkdata.mongowp.mongoserver.api.safe.impl.AbstractCommand;
import com.eightkdata.mongowp.mongoserver.api.safe.library.v3m0.commands.repl.ReplSetFreezeCommand.ReplSetFreezeArgument;
import com.eightkdata.mongowp.mongoserver.api.safe.library.v3m0.commands.repl.ReplSetFreezeCommand.ReplSetFreezeReply;
import javax.annotation.Nullable;
import org.bson.*;

/**
 * <em>Freeze</em> or <em>unfreeze</em> this node.
 *
 * This node will not attempt to become primary until the time period specified
 * expires. Calling {replSetFreeze:0} will unfreeze the node.
 */
public class ReplSetFreezeCommand extends AbstractCommand<ReplSetFreezeArgument, ReplSetFreezeReply>{

    public static final ReplSetFreezeCommand INSTANCE = new ReplSetFreezeCommand();

    private ReplSetFreezeCommand() {
        super("replSetFreeze");
    }
    
    @Override
    public Class<? extends ReplSetFreezeArgument> getArgClass() {
        return ReplSetFreezeArgument.class;
    }

    @Override
    public ReplSetFreezeArgument unmarshallArg(BsonDocument requestDoc) {
        int freezeSecs = requestDoc.getInt32("replSetFreeze").getValue();
        
        return new ReplSetFreezeArgument(freezeSecs);
    }

    @Override
    public BsonDocument marshallArg(ReplSetFreezeArgument request) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO
    }

    @Override
    public Class<? extends ReplSetFreezeReply> getResultClass() {
        return ReplSetFreezeReply.class;
    }

    @Override
    public BsonDocument marshallResult(ReplSetFreezeReply reply) {
        BsonDocument doc = new BsonDocument();

        String info = reply.getInfo();
        if (info != null) {
            doc.append("info", new BsonString(info));
        }
        String warning = reply.getWarning();
        if (warning != null) {
            doc.append("warning", new BsonString(warning));
        }
        
        return doc;
    }

    @Override
    public ReplSetFreezeReply unmarshallResult(BsonDocument resultDoc) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO
    }
    
    public static class ReplSetFreezeArgument {

        private final int freezeSecs;

        public ReplSetFreezeArgument(int freezeSecs) {
            this.freezeSecs = freezeSecs;
        }

        public int getFreezeSecs() {
            return freezeSecs;
        }
    }
    
    public static class ReplSetFreezeReply {

        private final String info;
        private final String warning;

        public ReplSetFreezeReply(@Nullable String info, @Nullable String warning) {
            this.info = info;
            this.warning = warning;
        }

        public String getInfo() {
            return info;
        }

        public String getWarning() {
            return warning;
        }

    }

}
