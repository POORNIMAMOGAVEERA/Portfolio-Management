package com.cts.Authorize.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Authorize.exception.ResourceNotFound;
import com.cts.Authorize.model.AuthRequest;
import com.cts.Authorize.model.User;
import com.cts.Authorize.model.UserToken;
import com.cts.Authorize.service.CustomUserDetailService;
import com.cts.Authorize.util.JwtUtil;



@RestController
public class AuthorizationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);

	private JwtUtil jwtUtil;

	private CustomUserDetailService userDetailService;

	private AuthenticationManager authenticationManager;

	
	@Autowired
	public AuthorizationController(JwtUtil jwtUtil, CustomUserDetailService userDetailService,
			AuthenticationManager authenticationManager) {

		this.jwtUtil = jwtUtil;
		this.userDetailService = userDetailService;
		this.authenticationManager = authenticationManager;
	}

	//starting message 
	
	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		LOGGER.info("STARTED authorization microservice welcome");
		LOGGER.info("END - authorization microservice welcome");
		return ResponseEntity.ok("Wecome to security application");
	}

	//jwt token authentication using user name and password
	
	 @PostMapping("/authenticate")
	    public ResponseEntity<UserToken> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	            throw new Exception("inavalid username/password");
	        }
	        User user=userDetailService.getUser(authRequest.getUserName());
			return new ResponseEntity<>(new UserToken(user.getId(),jwtUtil.generateToken(authRequest.getUserName())),
					HttpStatus.OK);
	    }
	
	
	//validtiion of the generated jwt token to access '/authorize' endpoint

	@GetMapping("/validate")
	public ResponseEntity<?> authorization(@RequestHeader("Authorization") String token1) {

		LOGGER.info("STARTED - authorization");
		String token = token1.substring(7);

		UserDetails user = userDetailService.loadUserByUsername(jwtUtil.extractUsername(token));

		if (jwtUtil.validateToken(token, user)) {
			LOGGER.info("END - authorization");
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			LOGGER.info("END - authorization");
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}

	}

}
