package tk.roydgar.obdservices.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.obdservices.domain.User;
import tk.roydgar.obdservices.domain.dto.request.user.CreateUserRequest;
import tk.roydgar.obdservices.domain.dto.request.user.UpdateUserRequest;
import tk.roydgar.obdservices.exception.ExceptionFactory;
import tk.roydgar.obdservices.mapper.request.UserRequestMapper;
import tk.roydgar.obdservices.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;

    private final PasswordEncoder passwordEncoder;

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.userNotFoundException(id));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User create(CreateUserRequest createUserRequest) {
        User user = userRequestMapper.toEntity(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        return userRepository.save(user);
    }

    public User update(UpdateUserRequest updateCarRequest) {
        User user = userRequestMapper.toEntity(updateCarRequest);
        user.setPassword(passwordEncoder.encode(updateCarRequest.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User delete(Long id) {
        User workshop = userRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.userNotFoundException(id));

        userRepository.delete(workshop);
        return workshop;
    }

}
