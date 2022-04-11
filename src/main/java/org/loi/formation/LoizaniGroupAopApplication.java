package org.loi.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import org.loi.formation.dto.pojouser;

@SpringBootApplication
public class LoizaniGroupAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoizaniGroupAopApplication.class, args);
	}	
	
	@Bean
	public pojouser methodBeanJumper() {		
		return new pojouser("SOTOMAYOR","Carlos") ;
	}		
	
	@Autowired
	ConfigurableApplicationContext myACDepuisControleurRest;
	
//	@Autowired
//	pojouser objuser ;
	
	
//	objuser = (pojouser) myACDepuisControleurRest.getBean("pojouser") ;
	


}
