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

import org.apache.commons.lang3.Range;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.yarn.api.protocolrecords.ApplicationsRequestScope;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class AppsCacheKey{
  private ApplicationsRequestScope scope;
  private Set<String> users;
  private Set<String> queues;
  private Set<String> applicationTypes;
  private Set<String> applicationTags;
  private EnumSet<YarnApplicationState> applicationStates;
  private Range<Long> startRange;
  private Range<Long> finishRange;
  private Long limit;
  private UserGroupInformation ugi;

  public AppsCacheKey(ApplicationsRequestScope scope, Set<String> users, Set<String> queues,
      Set<String> applicationTypes, Set<String> applicationTags,
      EnumSet<YarnApplicationState> applicationStates, Range<Long> startRange,
      Range<Long> finishRange, Long limit, UserGroupInformation ugi){
    this.scope = scope;
    this.users = users;
    this.queues = queues;
    this.applicationTypes = applicationTypes;
    this.applicationTags = applicationTags;
    this.applicationStates = applicationStates;
    this.startRange = startRange;
    this.finishRange = finishRange;
    this.limit = limit;
    this.ugi = ugi;
  }

  public static AppsCacheKey create(ApplicationsRequestScope scope, Set<String> users,
      Set<String> queues, Set<String> applicationTypes, Set<String> applicationTags,
      EnumSet<YarnApplicationState> applicationStates, Range<Long> startRange,
      Range<Long> finishRange, Long limit, UserGroupInformation ugi) {
    return new AppsCacheKey(scope, users, queues, applicationTypes, applicationTags,
        applicationStates, startRange, finishRange, limit, ugi);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppsCacheKey that = (AppsCacheKey) o;
    return scope == that.scope &&
               Objects.equals(users, that.users) &&
               Objects.equals(queues, that.queues) &&
               Objects.equals(applicationTypes, that.applicationTypes) &&
               Objects.equals(applicationTags, that.applicationTags) &&
               Objects.equals(applicationStates, that.applicationStates) &&
               Objects.equals(startRange, that.startRange) &&
               Objects.equals(finishRange, that.finishRange) &&
               Objects.equals(limit, that.limit) &&
               Objects.equals(ugi, that.ugi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scope, users, queues, applicationTypes, applicationTags, applicationStates,
        startRange, finishRange, limit, ugi);
  }

  @Override
  public String toString() {
    return "AppsCacheKey{" +
               "scope=" + scope +
               ", users=" + users +
               ", queues=" + queues +
               ", applicationTypes=" + applicationTypes +
               ", applicationTags=" + applicationTags +
               ", applicationStates=" + applicationStates +
               ", startRange=" + startRange +
               ", finishRange=" + finishRange +
               ", limit=" + limit +
               ", ugi=" + ugi +
               '}';
  }
}
