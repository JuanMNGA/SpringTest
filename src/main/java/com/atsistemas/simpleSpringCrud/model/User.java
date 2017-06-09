package com.atsistemas.simpleSpringCrud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by jmruiz on 09/06/2017.
 */
@Entity
public class User implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6796426360973958583L;

	@Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String email;
    
    public User(){
    	super();
    }

    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
