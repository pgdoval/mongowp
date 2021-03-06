/*
 * MongoWP
 * Copyright © 2014 8Kdata Technology (www.8kdata.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.eightkdata.mongowp.bson.impl;

import com.eightkdata.mongowp.bson.BsonValue;
import com.eightkdata.mongowp.bson.abst.AbstractBsonDocument;
import com.eightkdata.mongowp.bson.annotations.NotMutable;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

import java.util.LinkedHashMap;

/**
 *
 */
public class MapBasedBsonDocument extends AbstractBsonDocument {

  private static final long serialVersionUID = 4020431717465865262L;

  private final LinkedHashMap<String, BsonValue<?>> map;

  public MapBasedBsonDocument(@NotMutable LinkedHashMap<String, BsonValue<?>> map) {
    this.map = map;
  }

  @Override
  public BsonValue<?> get(String key) {
    return map.get(key);
  }

  @Override
  public boolean containsKey(String key) {
    return map.containsKey(key);
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public Entry<?> getEntry(String key) {
    BsonValue<?> value = map.get(key);
    if (value == null) {
      return null;
    }
    return new SimpleEntry<>(key, value);
  }

  @Override
  public UnmodifiableIterator<Entry<?>> iterator() {
    return Iterators.unmodifiableIterator(
        Iterators.transform(
            map.entrySet().iterator(),
            AbstractBsonDocument.FromEntryMap.INSTANCE
        )
    );
  }
}
