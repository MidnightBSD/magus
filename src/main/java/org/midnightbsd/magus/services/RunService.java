package org.midnightbsd.magus.services;

import lombok.extern.slf4j.Slf4j;
import org.midnightbsd.magus.model.Run;
import org.midnightbsd.magus.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Lucas Holt
 */
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "runs")
@Slf4j
@Service
public class RunService implements AppService<Run> {

    @Autowired
    private RunRepository repository;

    @Cacheable(unless = "#result == null")
    public List<Run> list() {
        return repository.findAll();
    }

    public List<Run> listByStatus(String status) {
       return repository.findByStatus(status);
    }

    public Page<Run> get(final Pageable page) {
        return repository.findAll(page);
    }

    @Cacheable(unless = "#result == null", key = "#id")
    public Run get(final int id) {
        Optional<Run> run = repository.findById(id);
        return run.orElse(null);

    }

    @CacheEvict(allEntries = true)
    @Transactional
    public Run save(final Run run) {
        Run adv = repository.getOne(run.getId());
        if (adv == null) {
            return repository.saveAndFlush(run);
        }
        log.info("Updating {}", adv.getId());

        adv.setArch(run.getArch());
        adv.setBlessed(run.getBlessed());
        adv.setStatus(run.getStatus());
        // todo Do we want to add anything else?

        adv = repository.saveAndFlush(adv);

        return adv;
    }
}
