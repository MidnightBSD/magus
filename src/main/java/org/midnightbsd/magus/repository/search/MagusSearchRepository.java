package org.midnightbsd.magus.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Lucas Holt
 */
public interface MagusSearchRepository extends ElasticsearchRepository<org.midnightbsd.magus.model.search.MagusItem, Integer> {

    Page<org.midnightbsd.magus.model.search.MagusItem> findByIdContainsOrDescriptionContainsAllIgnoreCase(String name, String description, Pageable page);
}
