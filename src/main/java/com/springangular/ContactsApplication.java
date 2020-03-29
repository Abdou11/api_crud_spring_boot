package com.springangular;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springangular.domain.Contact;
import com.springangular.repos.ContactRepos;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ContactsApplication implements CommandLineRunner {

	@Autowired
	private ContactRepos contactRepos;

	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		contactRepos.save(new Contact("Ahmed", "Selim", "0990024532", "Selim@gmail.com", "photo.png",
				LocalDate.parse("1992-01-19")));
		contactRepos.save(new Contact("Karim", "Samir", "07895555", "Karim@gmail.com", "photo.png",
				LocalDate.parse("1992-02-19")));
		contactRepos.save(new Contact("Ahlam", "Mourad", "06954888", "Mourad@gmail.com", "photo.png",
				LocalDate.parse("1992-03-19")));
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }

}
