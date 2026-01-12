package org.nestech.identityprovider;

import org.junit.jupiter.api.Test;
import org.nestech.identityprovider.auth.requests.AuthenticationRequest;
import org.nestech.identityprovider.auth.service.AuthenticationService;
import org.nestech.identityprovider.auth.requests.RegisterRequest;
import org.nestech.identityprovider.user.request.ResetPassRequest;
import org.nestech.identityprovider.user.model.Role;
import org.nestech.identityprovider.user.request.UserRequest;
import org.nestech.identityprovider.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdentityProviderApplicationTests {

	/*@Autowired
	UserService userService;

	@Autowired
	AuthenticationService authenticationService;*/
	/*@Test
	void contextLoads() {

	}*/

	/*@Test
	void userService(){
		*//*UserRequest userRequest = new UserRequest();
		userRequest.setLocked(false);
		userRequest.setEmail("kawa");

		System.out.println(userService.blockUser(userRequest));
		System.out.println(userService.getUser(1));
		System.out.println(userService.getUsers(0, 10, "email", "desc", 1));
		System.out.println(userService.assignToEmployee(1, 201));
		System.out.println(userService.assignToEmployee(2, 201));
		System.out.println(userService.resetPassword(new ResetPassRequest(1, "mamo", "kawa")));
		System.out.println(userService.resetPassword(new ResetPassRequest(2, "kawa", "mamo")));
		System.out.println(userService.resetPassword(new ResetPassRequest(1, "rrrrr", "mamo")));*//*
	}*/

	/*@Test
	void authenticationService(){
		*//*System.out.println(authenticationService.authenticate(new AuthenticationRequest("kawa", "kawa")));
		System.out.println(authenticationService.register(new RegisterRequest("test", "password", 123, Role.SCHOOL_ADMIN)));
		System.out.println(authenticationService.getPublicKey());*//*
	}*/

}
