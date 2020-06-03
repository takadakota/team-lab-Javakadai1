package com.example.TLBkadai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableOAuth2Sso
public class TLBkadai1Application {

	public static void main(String[] args) {
		SpringApplication.run(TLBkadai1Application.class, args);
	}

}
