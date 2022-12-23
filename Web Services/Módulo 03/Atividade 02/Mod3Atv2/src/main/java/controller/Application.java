package controller;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.setOut(new PrintStream(System.out, true, "UTF-8"));
		SpringApplication.run(Application.class, args);
	}

}
