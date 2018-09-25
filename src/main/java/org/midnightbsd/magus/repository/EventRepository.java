package org.midnightbsd.magus.repository;

import org.midnightbsd.magus.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lucas Holt
 */
public interface EventRepository extends JpaRepository<Event, Integer> {
}
