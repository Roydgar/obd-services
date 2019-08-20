package tk.roydgar.obdservices.mapper.response;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.domain.User;
import tk.roydgar.obdservices.domain.dto.response.CarDto;
import tk.roydgar.obdservices.domain.dto.response.UserDto;
import tk.roydgar.obdservices.util.StreamUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserResponseMapper {

    private final CarResponseMapper carResponseMapper;

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto, "password", "cars");

        List<CarDto> carDtos = StreamUtils.ofNullable(user.getCars())
                .map(carResponseMapper::toDto).collect(Collectors.toList());
        userDto.setCars(carDtos);
        return userDto;
    }

    public List<UserDto> toDtoList(List<User> cars) {
        return cars.stream().map(this::toDto).collect(Collectors.toList());
    }
}
