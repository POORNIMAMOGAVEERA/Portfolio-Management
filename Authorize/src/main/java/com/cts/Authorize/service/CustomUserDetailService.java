package com.cts.Authorize.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.Authorize.exception.ResourceNotFound;
import com.cts.Authorize.model.User;
import com.cts.Authorize.repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailService.class);

	private UserRepository userRepository;

	@Autowired
	public CustomUserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/*loadbyUserName function loads user from the repository 
	 * returns UserDetails 
	 */

	
	//loading user name from user database passing to spring provided UserDetails  
	
	public User getUser(String username) throws UsernameNotFoundException{
    	return   userRepository.findByUserName(username);
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
		LOGGER.info("STARTED - loadUserByUsername");
		User user = userRepository.findByUserName(username);
		LOGGER.info("END - loadUserByUsername");
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
		
		}catch(Exception e)
		{
			LOGGER.error("ERROR-username not found");
			throw new ResourceNotFound("User by the given username not found");
		}
		
	}

}
