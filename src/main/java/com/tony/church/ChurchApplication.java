package com.tony.church;

import com.tony.church.config.ChurchDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChurchApplication {

	public static void main(String[] args) {

		//SpringApplication.run(ChurchApplication.class, args);

		SpringApplication application =
				new SpringApplication(ChurchApplication.class);
		application.setAdditionalProfiles("production");
		application.run(args);
	}

}
