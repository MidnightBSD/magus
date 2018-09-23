package org.midnightbsd.magus.repository;

import org.midnightbsd.magus.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Lucas Holt
 */
public interface PortRepository extends JpaRepository<Port, Integer> {
    Port findOneByName(@Param("name") String name);
}
