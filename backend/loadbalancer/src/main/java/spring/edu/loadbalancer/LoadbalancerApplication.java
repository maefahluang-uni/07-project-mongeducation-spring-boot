package spring.edu.loadbalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
    "spring.edu.loadbalancer",
    "spring.edu.mongstudent.Student"
})
public class LoadbalancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadbalancerApplication.class, args);
	}

}
