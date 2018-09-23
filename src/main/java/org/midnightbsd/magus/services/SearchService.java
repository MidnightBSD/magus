package org.midnightbsd.magus.services;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.midnightbsd.magus.model.Port;
import org.midnightbsd.magus.model.search.MagusItem;
import org.midnightbsd.magus.repository.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Lucas Holt
 */
@Slf4j
@Service
public class SearchService {

    @Autowired
    private org.midnightbsd.magus.repository.search.MagusSearchRepository searchRepository;

    @Autowired
    private PortRepository portRepository;

    // @Cacheable(key="#p0.concat('-').concat(#p1.getPageNumber())", value = "search")
    public Page<org.midnightbsd.magus.model.search.MagusItem> find(String term, Pageable page) {
        return searchRepository.findByIdContainsOrDescriptionContainsAllIgnoreCase(term, term, page);
    }

    @CacheEvict(value = "search", allEntries = true)
    @Transactional
    @Async
    public void indexAllNvdItems() {
        try {
            Pageable pageable = PageRequest.of(0, 100);

            Page<org.midnightbsd.magus.model.Port> ports = portRepository.findAll(pageable);
            for (int i = 0; i < ports.getTotalPages(); i++) {
                final ArrayList<org.midnightbsd.magus.model.search.MagusItem> items = new ArrayList<>();

                for (final org.midnightbsd.magus.model.Port port : ports) {
                    items.add(convert(port));
                }

                log.debug("Saving a page of ports to elasticsearch. pg " + i);
                searchRepository.saveAll(items);

                pageable = PageRequest.of(i + 1, 100);
                ports = portRepository.findAll(pageable);
            }
        } catch (final ElasticsearchException es) {
            log.error(es.getDetailedMessage(), es);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @CacheEvict(value = "search", allEntries = true)
    @Transactional
    public void index(@NonNull final org.midnightbsd.magus.model.Port port) {
        log.debug("Indexing port id: " + port.getId());
        searchRepository.save(convert(port));
    }

    public org.midnightbsd.magus.model.search.MagusItem convert(@NonNull final Port port) {
        log.trace("Converting port id: " + port.getId());

        final MagusItem magusItem = new MagusItem();

        magusItem.setId(port.getId());

        magusItem.setName(port.getName());
        magusItem.setPkgVersion(port.getVersion());
        magusItem.setDescription(port.getDescription());
        magusItem.setVersion(Calendar.getInstance().getTimeInMillis());

        return magusItem;
    }
}
