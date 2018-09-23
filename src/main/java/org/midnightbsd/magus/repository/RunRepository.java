package org.midnightbsd.magus.repository;

import org.midnightbsd.magus.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lucas Holt
 */
public interface RunRepository extends JpaRepository<Run, Integer> {
}
