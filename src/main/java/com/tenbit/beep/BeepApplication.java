package com.tenbit.beep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeepApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeepApplication.class, args);

//		ApplicationContext context = SpringApplication.run(BeepApplication.class, args);
//		UserRepository userRepository = context.getBean(UserRepository.class);
//		DBService dbService = new DBServiceImpl(userRepository);
//		dbService.resetDB();

//		dbService.deleteByPublicId("");
	}

}
