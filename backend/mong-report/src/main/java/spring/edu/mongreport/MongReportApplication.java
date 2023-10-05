package spring.edu.mongreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
    "spring.edu.mongreport",
    "spring.edu.mongcourse.model.Course"
})

public class MongReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongReportApplication.class, args);
	}

}
