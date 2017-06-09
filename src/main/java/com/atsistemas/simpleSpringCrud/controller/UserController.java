package com.atsistemas.simpleSpringCrud.controller;

import com.atsistemas.simpleSpringCrud.dto.UserDTO;
import com.atsistemas.simpleSpringCrud.service.CommonService;
import com.atsistemas.simpleSpringCrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jmruiz on 09/06/2017.
 */
@RestController
@RequestMapping(value ="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(method = {RequestMethod.GET})
    public List<UserDTO> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ){
        return userService.search(name, email, new PageRequest(page, size));
    }
    @RequestMapping(method = { RequestMethod.POST })
    public UserDTO create(@RequestBody UserDTO user) {
        System.out.println(user.toString());
        return userService.create(user);
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.GET })
    public UserDTO findOne(@PathVariable("id") Integer id) throws Exception {
        return userService.transform(userService.findOne(id));
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
    public void update(
            @PathVariable("id") Integer id,
            @RequestBody UserDTO userDTO) throws Exception {
        userService.update(id, userDTO);
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
    public void delete(@PathVariable("id") Integer id) throws Exception {
        userService.delete(id);
    }
}
