/*
 *     This file is part of mongowp.
 *
 *     mongowp is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     mongowp is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with mongowp. If not, see <http://www.gnu.org/licenses/>.
 *
 *     Copyright (c) 2014, 8Kdata Technology
 *     
 */
package com.eightkdata.mongowp.mongoserver.protocol.exceptions;

import com.eightkdata.mongowp.mongoserver.protocol.MongoWP;

/**
 *
 */
public class NoSuchKeyException extends MongoException {
    private static final long serialVersionUID = 1L;
    private final String key;

    public NoSuchKeyException(String key) {
        super(MongoWP.ErrorCode.NO_SUCH_KEY, key);
        this.key = key;
    }

    public NoSuchKeyException(String key, String customMessage) {
        super(customMessage, MongoWP.ErrorCode.NO_SUCH_KEY);
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    
}
