package fr.okayo.exercice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Okayo Exercice"
        )
)
public class ExerciceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciceApplication.class, args);
	}

}
