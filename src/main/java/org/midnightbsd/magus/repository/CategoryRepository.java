package org.midnightbsd.magus.repository;

import org.midnightbsd.magus.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lucas Holt
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
