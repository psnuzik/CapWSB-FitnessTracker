package pl.wsb.fitnesstracker.user.internal;

import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }


    @GetMapping("/simple")
    public List<UserDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("/{id}")
    public List<UserDto> getUserByID(@PathVariable long id) {
        return userService.getUser(id)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/email/{email}")
    public List<UserDto> getUserByEmailIgnoreCase(@PathVariable String email) {
        return userService.getUserByEmailIgnoreCase(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    @GetMapping("/onlyemail/{email}")
    public List<UserDto> getOnlyIDByEmailIgnoreCase(@PathVariable String email) {
        return userService.getUserByEmailIgnoreCase(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    @GetMapping("/older/{age}")
    public List<UserDto> getUserByAgeGreaterThan(@PathVariable int age) {
        return userService.getUsersOlderThan(age)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }


    @PostMapping("/createUser")
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.createUser(user);
        return userMapper.toDto(createdUser);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return userMapper.toDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }





}


