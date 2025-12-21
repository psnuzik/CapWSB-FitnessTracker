package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Representation of Training repository - interface providing training ids
 */

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
