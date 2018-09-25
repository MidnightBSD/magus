package org.midnightbsd.magus.repository;

import org.midnightbsd.magus.model.Lock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lucas Holt
 */
public interface LockRepository extends JpaRepository<Lock, Integer> {
}
