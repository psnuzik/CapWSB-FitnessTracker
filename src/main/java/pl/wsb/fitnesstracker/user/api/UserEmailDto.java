package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * UserEmail Data Transfer Object
 * @param id primary key
 * @param firstName first name of the user
 * @param lastName surname of the user
 * @param email email address
 */
public record UserEmailDto(@Nullable Long id, String firstName, String lastName,
                      String email) {

}
