package de.seuhd.campuscoffee.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static de.seuhd.campuscoffee.api.util.ControllerUtils.getLocation;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import de.seuhd.campuscoffee.domain.ports.UserService;
import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.api.mapper.UserDtoMapper;

@Tag(name = "Users", description = "Operations related to user management.")
@Controller
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;
    public final UserDtoMapper userDtoMapper;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(
            userService.getAllUsers().stream()
                .map(userDtoMapper::fromDomain)
                .toList()
        );
            
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(
            userDtoMapper.fromDomain(
                userService.getUserById(id))
        );
}



    @GetMapping("/login")
    public ResponseEntity<UserDto> getUserByLoginName(@RequestParam("loginName") String loginName) {
        return ResponseEntity.ok(
            userDtoMapper.fromDomain(
                userService.getUserByLoginName(loginName))
        );
    }  

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto){
        UserDto created = upsert(userDto);
        return ResponseEntity.created(getLocation(created.id())).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id,@RequestBody @Valid UserDto userDto){
        if (!id.equals(userDto.id())) {
            throw new IllegalArgumentException("User ID in path and body do not match.");
        }
        return ResponseEntity.ok(upsert(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();

    }





    private UserDto upsert(UserDto userDto) {
        return userDtoMapper.fromDomain(
            userService.upsert(
                userDtoMapper.toDomain(userDto)
            )
        );
    }
}