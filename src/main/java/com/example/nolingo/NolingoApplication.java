package com.example.nolingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
public class NolingoApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(NolingoApplication.class, args);
	}

}
