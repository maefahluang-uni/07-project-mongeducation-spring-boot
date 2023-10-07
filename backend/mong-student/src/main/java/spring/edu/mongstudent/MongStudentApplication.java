package spring.edu.mongstudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MongStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongStudentApplication.class, args);
	}

}
