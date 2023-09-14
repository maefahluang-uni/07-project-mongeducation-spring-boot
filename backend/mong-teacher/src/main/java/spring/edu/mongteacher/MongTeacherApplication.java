package spring.edu.mongteacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "spring.edu.mongteacher")
public class MongTeacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongTeacherApplication.class, args);
	}

}
