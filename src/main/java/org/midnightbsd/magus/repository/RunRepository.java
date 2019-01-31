package org.midnightbsd.magus.repository;

import org.midnightbsd.magus.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Lucas Holt
 */
public interface RunRepository extends JpaRepository<Run, Integer> {

    List<Run> findByStatus(@Param("status") String status);
}
