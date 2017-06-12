package com.atsistemas.simpleSpringCrud.dao;

import com.atsistemas.simpleSpringCrud.dto.UserDTO;
import com.atsistemas.simpleSpringCrud.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jmruiz on 09/06/2017.
 */
@Repository
public interface UserDAO extends CrudRepository<User, Integer>{

    @Query(value = "SELECT new com.atsistemas.simpleSpringCrud.dto.UserDTO(u.id, u.name, u.email) from User as u WHERE (u.name LIKE %:name% OR :name IS NULL) AND (u.email LIKE %:email% OR :email IS NULL)")
    public List<UserDTO> search(@Param("name") String name, @Param("email") String email,
                                Pageable pageable);

    @Query(value = "SELECT u FROM User AS u WHERE u.id = :id")
    public User findOne(@Param("id") Integer id);

    
    @Query(value = "SELECT u FROM User AS u WHERE")
    public List<User> findAll(Pageable pageable);
    
}
