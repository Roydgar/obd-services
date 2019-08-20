package tk.roydgar.obdservices.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.obdservices.domain.dto.request.user.CreateUserRequest;
import tk.roydgar.obdservices.domain.dto.request.user.UpdateUserRequest;
import tk.roydgar.obdservices.domain.dto.response.UserDto;
import tk.roydgar.obdservices.mapper.response.UserResponseMapper;
import tk.roydgar.obdservices.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserResponseMapper responseMapper;

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return responseMapper.toDto(userService.getById(id));
    }

    @GetMapping
    public List<UserDto> getAll() {
        return responseMapper.toDtoList(userService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(CreateUserRequest request) {
        return responseMapper.toDto(userService.create(request));
    }

    @PutMapping
    public UserDto update(UpdateUserRequest request) {
        return responseMapper.toDto(userService.update(request));
    }

    @DeleteMapping("/{id}")
    public UserDto delete(@PathVariable Long id) {
        return responseMapper.toDto(userService.delete(id));
    }

}
