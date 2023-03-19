package com.gdsc.EmotionalDiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EmotionalDiaryApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmotionalDiaryApplication.class, args);
	}

}
