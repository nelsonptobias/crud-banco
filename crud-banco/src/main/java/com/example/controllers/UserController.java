package com.example.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;

import com.example.dao.User;
import com.example.repository.UserRepository;
import com.mongodb.MongoClient;




@RestController
@RequestMapping("/usuario")
public class UserController {
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public User postUser(@RequestBody User user) {
		
		return repository.save(user);
	
		 
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUser() {
		return repository.findAll();
	}
	
	@RequestMapping(value = "getById", method = RequestMethod.GET)
    public Optional<User> getUserById(@RequestParam("id") String aID){
        return repository.findById(aID);
    }

	
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void  deleteUsuario(@RequestParam("id") String aID){
         repository.deleteById(aID);

    }
    
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public User putUsuario(@RequestParam("id") String aID, @RequestBody User user){
    	 MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "user");
    	 
    	Query query = new Query();
    	query.addCriteria(Criteria.where("_id").is(aID));
        
    	User update =  mongoOps.findOne(query, User.class);
   
    	if (update != null) {
	    	update.email = user.email;
	    	update.senha = user.senha;
	   
	    	return mongoOps.save(update);
    	}else {
    		 throw new ResponseStatusException(
    		          HttpStatus.NOT_FOUND, "User Not Found");
    	}
    }
	
	

}