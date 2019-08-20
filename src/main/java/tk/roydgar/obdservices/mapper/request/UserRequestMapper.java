package tk.roydgar.obdservices.mapper.request;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.domain.User;
import tk.roydgar.obdservices.domain.dto.request.user.CreateUserRequest;

@Component
public class UserRequestMapper {

    public User toEntity(CreateUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }

}
