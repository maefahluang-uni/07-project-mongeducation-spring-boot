package spring.edu.mongreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"spring.edu.mongreport", "spring.edu.mongstudent"})
public class MongReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongReportApplication.class, args);
	}

}
