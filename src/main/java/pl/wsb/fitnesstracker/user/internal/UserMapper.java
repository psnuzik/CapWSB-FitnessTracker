package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

@Component
class UserMapper {
    /**
     * Bean representing user with complete data
     * @param user user object
     * @return User with details
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Bean representing user with data excluding birthday and email
     * @param user user object
     * @return UserDto
     */
    UserDto toSimpleDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                null,
                null
        );
    }

    /**
     * Bean representing user with data excluding birthday
     * @param user user object
     * @return UserDto
     */
    UserDto toEmailDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                null,
                user.getEmail()
        );
    }

    /**
     * Bean representing user with all of the related
     * @param userDto user object
     * @return UserDTO
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()
        );

    }

}
