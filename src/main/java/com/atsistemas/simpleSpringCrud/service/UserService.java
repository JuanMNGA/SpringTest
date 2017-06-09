package com.atsistemas.simpleSpringCrud.service;

/**
 * Created by jmruiz on 09/06/2017.
 */

import com.atsistemas.simpleSpringCrud.dao.UserDAO;
import com.atsistemas.simpleSpringCrud.dto.UserDTO;
import com.atsistemas.simpleSpringCrud.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CommonService commonService;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll(){
        final Iterator<User> iterator = userDAO.findAll().iterator();
        final List<UserDTO> res = new ArrayList<>();
        while (iterator.hasNext()) {
            final User r = iterator.next();
            res.add(this.transform(r));
        }
        return res;
    }

    @Transactional(readOnly = true)
    public User findOne(Integer id) throws Exception {
        final User user = userDAO.findOne(id);
        if (user == null)
            throw new Exception();
        return user;
    }

    public UserDTO create(UserDTO userDto) {
        User user = this.transform(userDto);
        return this.transform(userDAO.save(user));
    }

    public void update(Integer id, UserDTO userDto) throws Exception {
        if (userDto.getId() != null && id != userDto.getId())
            throw new Exception();
        User u = userDAO.findOne(id);
        u.setName(userDto.getName());
        u.setEmail(userDto.getEmail());
        userDAO.save(u);
    }

    public void delete(Integer id) throws Exception {
        final User user = findOne(id);
        userDAO.save(user);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> search(String name, String email, Pageable pageable) {
        return userDAO.search(name, email, pageable);
    }
    
    public User transform(UserDTO userDTO){
    	return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
    
    public UserDTO transform(User user){
    	return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

}
