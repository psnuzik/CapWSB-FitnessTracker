package pl.wsb.fitnesstracker.user.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(final User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(final User updatedUser) {
        return userRepository.findById(updatedUser.getId())
                .map(existingUser -> {
                    // Aktualizujemy tylko proste pola tekstowe/daty
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setBirthdate(updatedUser.getBirthdate());

                    return userRepository.save(existingUser);
                }).orElseThrow(() -> new IllegalArgumentException("User with ID " + updatedUser.getId() + " not found!"));

        /*
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID not specified!");
        }
        if (!userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " not found!");
        }

        return userRepository.save(user);*/
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUserByEmailIgnoreCase(final String email) { return userRepository.findByEmailIngoreCase(email); }


    @Override
    public List<User> getUsersOlderThan(final int age) { return userRepository.findOlderThan(age) ;}

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}