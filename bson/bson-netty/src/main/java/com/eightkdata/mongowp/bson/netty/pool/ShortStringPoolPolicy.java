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

package com.eightkdata.mongowp.bson.netty.pool;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import io.netty.buffer.ByteBuf;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Inject;
import javax.inject.Qualifier;

/**
 *
 */
public class ShortStringPoolPolicy extends StringPoolPolicy {

  final int sizeLimit;

  @Inject
  public ShortStringPoolPolicy(@SizeLimit int sizeLimit) {
    this.sizeLimit = sizeLimit;
  }

  @Override
  public boolean apply(boolean likelyCacheable, ByteBuf input) {
    return isShort(input);
  }

  protected final boolean isShort(ByteBuf input) {
    return input.readableBytes() < sizeLimit;
  }

  @Override
  public String toString() {
    return "size < " + sizeLimit;
  }

  @Qualifier
  @Target({FIELD, PARAMETER, METHOD})
  @Retention(RUNTIME)
  public static @interface SizeLimit {
  }
}
