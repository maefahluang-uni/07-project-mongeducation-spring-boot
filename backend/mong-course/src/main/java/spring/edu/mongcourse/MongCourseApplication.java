package spring.edu.mongcourse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import spring.edu.mongcourse.model.Category;
import spring.edu.mongcourse.repository.CategoryRepository;

@SpringBootApplication
public class MongCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongCourseApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

	@Bean
	CommandLineRunner commandLineRunner(CategoryRepository categoryRepository) {
		return args -> {
			Category Mathematics = new Category(null, "Mathematics");
			categoryRepository.save(Mathematics);

			Category Science = new Category(null, "Science");
			categoryRepository.save(Science);

			Category History = new Category(null, "History");
			categoryRepository.save(History);

			Category Literature = new Category(null, "Literature");
			categoryRepository.save(Literature);

			Category Psychology = new Category(null, "Psychology");
			categoryRepository.save(Psychology);

			Category Sociology = new Category(null, "Sociology");
			categoryRepository.save(Sociology);
		};
	}

}
