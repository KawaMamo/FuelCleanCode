package org.nestech.identityprovider;

import org.nestech.identityprovider.auth.service.AuthenticationService;
import org.nestech.identityprovider.auth.requests.RegisterRequest;
import org.nestech.identityprovider.user.model.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class IdentityProviderApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext run = SpringApplication.run(IdentityProviderApplication.class, args);
		AuthenticationService service = run.getBean(AuthenticationService.class);
		Environment env = run.getEnvironment();
		service.register(new RegisterRequest(
				env.getProperty("super.admin.username"),
				env.getProperty("super.admin.password"),
				Role.SUPER_ADMIN));
	}

}
