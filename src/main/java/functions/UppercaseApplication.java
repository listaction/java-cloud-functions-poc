package functions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
@SpringBootApplication
public class UppercaseApplication {

	@Bean
	public Function<String, String> uppercase() {
		return s -> Optional.ofNullable(s).map(m->m.toUpperCase()).orElse("ivegotnull");
	}

	public static void main(String[] args) {
		SpringApplication.run(UppercaseApplication.class, args);
	}
}
