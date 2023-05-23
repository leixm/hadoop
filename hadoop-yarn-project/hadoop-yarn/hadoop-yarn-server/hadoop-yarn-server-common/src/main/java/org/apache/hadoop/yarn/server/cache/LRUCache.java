/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.yarn.server.cache;

import org.apache.hadoop.yarn.util.LRUCacheHashMap;

import java.util.Map;

public class LRUCache<K, V extends CacheNode<?>> {

  private final int expireTimeMs;
  private final Map<K, V> cache;

  public LRUCache(int capacity) {
    this(capacity, -1);
  }

  public LRUCache(int capacity, int expireTimeMs) {
    cache = new LRUCacheHashMap<>(capacity, true);
    this.expireTimeMs = expireTimeMs;
  }

  public synchronized V get(K key) {
    V value = cache.get(key);
    if (value != null) {
      cache.remove(key);
      if (expireTimeMs > 0 && System.currentTimeMillis() > value.getCacheTime() + expireTimeMs) {
        return null;
      } else {
        cache.put(key, value);
      }
    }
    return value;
  }

  public synchronized V put(K key, V value) {
    V v = cache.get(key);
    if (v != null) {
      cache.remove(key);
    }
    return cache.put(key, value);
  }

  public synchronized void clear() {
    cache.clear();
  }

  public int size() {
    return cache.size();
  }
}