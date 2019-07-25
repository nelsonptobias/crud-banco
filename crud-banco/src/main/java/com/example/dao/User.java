package com.example.dao;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	public String id;
	public String email;
	public String senha;
    
    public User() {}


    public User(String email, String senha) {

        this.email = email;
        this.senha = senha;
    }
    
    
}
