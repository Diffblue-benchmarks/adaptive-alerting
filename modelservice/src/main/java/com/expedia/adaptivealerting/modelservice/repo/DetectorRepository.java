/*
 * Copyright 2018 Expedia Group, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expedia.adaptivealerting.modelservice.repo;

import com.expedia.adaptivealerting.modelservice.entity.Detector;
import com.expedia.adaptivealerting.modelservice.entity.projection.InlineType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author shsethi
 */
@RepositoryRestResource(excerptProjection = InlineType.class)
public interface DetectorRepository extends PagingAndSortingRepository<Detector, Long> {

    List<Detector> findByUuid(@Param("uuid") String uuid);

    List<Detector> findByCreatedBy(@Param("owner") String owner);

    @Query("select mmm.detector from MetricDetectorMapping mmm where mmm.metric.hash = :hash")
    List<Detector> findByMetricHash(@Param("hash") String hash);
}
