package com.example.web;

import com.example.domain.User;
import com.example.service.UserService;
import com.example.vo.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDTO> getUsers(Pageable pageable) {
        return null;
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable Long userId) {
        return null;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User user = new User();

        BeanUtils.copyProperties(userDTO, user);
        user = userService.createUser(user);

        UserDTO result = new UserDTO();

        BeanUtils.copyProperties(user, result);

        return result;
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return null;
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public UserDTO deleteUser(@PathVariable String userId) {
        return null;
    }
}
