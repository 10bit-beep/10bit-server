package com.tenbit.beep;

import com.tenbit.beep.auth.repository.UserRepository;
import com.tenbit.beep.auth.service.DBService;
import com.tenbit.beep.auth.service.impl.DBServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BeepApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeepApplication.class, args);

		ApplicationContext context = SpringApplication.run(BeepApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		DBService dbService = new DBServiceImpl(userRepository);
		dbService.resetDB();

		dbService.deleteByPublicId("");
	}

}
