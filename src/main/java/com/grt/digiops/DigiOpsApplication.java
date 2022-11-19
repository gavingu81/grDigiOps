package com.grt.digiops;

import com.grt.digiops.account.domain.AppUser;
import com.grt.digiops.account.domain.Role;
import com.grt.digiops.account.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DigiOpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigiOpsApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	};

	@Bean
	CommandLineRunner run(AppUserService appUserService){
		return args -> {
			appUserService.saveRole(new Role(null, "ROLE_USER"));
			appUserService.saveRole(new Role(null,"ROLE_MANAGER"));
			appUserService.saveRole(new Role(null,"ROLE_ADMIN"));
			appUserService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			appUserService.saveAppUser(new AppUser(null,"Gavin GU","gavin","1234",new ArrayList<>()));
			appUserService.saveAppUser(new AppUser(null,"Kelvin SHEN","kelvin","1234",new ArrayList<>()));
			appUserService.saveAppUser(new AppUser(null,"Jeff XIA","jeff","1234",new ArrayList<>()));
			appUserService.saveAppUser(new AppUser(null,"Jianguo ZHANG","jianguo", "1234",new ArrayList<>()));

			appUserService.addRoleToAppUser("gavin","ROLE_ADMIN");
			appUserService.addRoleToAppUser("gavin","ROLE_USER");
			appUserService.addRoleToAppUser("gavin","ROLE_SUPER_ADMIN");
			appUserService.addRoleToAppUser("kelvin","ROLE_MANAGER");
			appUserService.addRoleToAppUser("jeff","ROLE_USER");
			appUserService.addRoleToAppUser("jianguo","ROLE_USER");


		};
	}

}
