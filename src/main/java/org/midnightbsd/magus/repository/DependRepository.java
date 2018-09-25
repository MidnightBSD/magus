package org.midnightbsd.magus.repository;

import org.midnightbsd.magus.model.Depend;
import org.midnightbsd.magus.model.DependId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lucas Holt
 */
public interface DependRepository extends JpaRepository<Depend, DependId> {
}
