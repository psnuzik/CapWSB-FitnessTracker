package pl.wsb.fitnesstracker.statistics.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Retrieves a statistics.
 * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
 */
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
