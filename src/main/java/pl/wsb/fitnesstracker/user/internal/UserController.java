package pl.wsb.fitnesstracker.user.internal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
    public Optional<UserDto> getUserByID(@PathVariable long id) {
        return userService.getUser(id)
                .stream()
                .map(userMapper::toDto)
                .findFirst();
    }

    @GetMapping("/email")
    public List<UserDto> getUserByEmailIgnoreCase(@RequestParam String email) {
        return userService.getUserByEmailIgnoreCase(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    @GetMapping("/onlyemail/{email}")
    public List<UserDto> getOnlyIDByEmailIgnoreCase(@RequestParam String email) {
        return userService.getUserByEmailIgnoreCase(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    @GetMapping("/older/{date}")
    public List<UserDto> getUserByAgeGreaterThan(@PathVariable String date) {
        LocalDate beforeDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return userService.getUsersOlderThan(beforeDate)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }


    @PostMapping("/createUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.createUser(user);
        //return userMapper.toDto(createdUser);
        return new ResponseEntity<UserDto>(userMapper.toDto(createdUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return userMapper.toDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }





}

//A
