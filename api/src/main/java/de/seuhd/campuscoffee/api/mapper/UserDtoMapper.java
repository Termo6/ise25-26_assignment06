package de.seuhd.campuscoffee.api.mapper;
import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.domain.model.User;
//DONE
public interface UserDtoMapper {
    UserDto fromDomain(User user);
    User toDomain(UserDto dto);

}