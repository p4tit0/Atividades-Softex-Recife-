package controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class WebController {
	private static Map<String, String> getFormData(String requestBody) throws UnsupportedEncodingException {
	    String decoded = java.net.URLDecoder.decode(requestBody, "UTF-8");
	    String[] rawValues = decoded.split("&");
	    Map<String, String> values = new HashMap<String, String>();
	    for (String value : rawValues) {
	    	String[] SplitValue = value.split("=");
	    	values.put(SplitValue[0], SplitValue[1]);
	    }
	    return values;
	}
	
	
	//////////////////////// CREATE ////////////////////////
	
	@PostMapping("/login")
	public ResponseEntity<String> login(HttpServletRequest req, HttpServletResponse res, Model model) {
		try {
			//OBTENÇÂO DOS DADOS
			String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			Map<String, String> values = getFormData(body);
			
			System.out.printf(
					  "EMAIL: %s\n"
					+ "SENHA: %s\n", 
					values.get("email"), values.get("pswd"));
			
			//Verifica se o usuário existe
			//...
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Login realizado com sucesso");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao fazer login!");
		}
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> SignUp(HttpServletRequest req, HttpServletResponse res, Model model) {
		try {
			//OBTENÇÂO DOS DADOS
			String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			Map<String, String> values = getFormData(body);
			
			System.out.printf(
					  "NOME: %s\n"
					+ "EMAIL: %s\n"
					+ "SENHA: %s\n"
					+ "CONFIRMAÇÃO SENHA: %s\n", 
					values.get("name"), values.get("email"), values.get("pswd"), values.get("confpswd"));
			
			//Verifica as informações e realiza o cadastro...
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso!");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao realizar cadastro!");
		}
		
	}
	
	
	//////////////////////// READ ////////////////////////
	
	@GetMapping("/login")
	public ResponseEntity<String> loginPage(HttpServletRequest req, HttpServletResponse res, Model model) {
		return ResponseEntity.status(HttpStatus.OK).body("página login");
	}
	
	@GetMapping("/signup")
	public ResponseEntity<String> signUpPage(HttpServletRequest req, HttpServletResponse res, Model model) {
		return ResponseEntity.status(HttpStatus.OK).body("página cadastro");
	}
	
	@GetMapping("/user")
	public ResponseEntity<String> userPage(HttpServletRequest req, HttpServletResponse res, Model model, @RequestParam("data") String StrId) {
		int id  = Integer.valueOf(StrId);
		
		// Seleciona usuário pelo id...
		
		return ResponseEntity.status(HttpStatus.OK).body("página user");
	}
	

	//////////////////////// UPDATE ////////////////////////
	
	@PutMapping("/update")
	public ResponseEntity<String> update(HttpServletRequest req, HttpServletResponse res, Model model,  @RequestParam("data") String StrId) {
		try {
			int id  = Integer.valueOf(StrId);
			
			//Altera usuário com determinao id 
			
			return ResponseEntity.status(HttpStatus.OK).body("Usuário alterado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		
	}
	
	//////////////////////// DELETE ////////////////////////
		
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(HttpServletRequest req, HttpServletResponse res, Model model,  @RequestParam("data") String StrId) {
		try {
			int id  = Integer.valueOf(StrId);
		
			//Deleta usuário com determinao id 
		
			return ResponseEntity.status(HttpStatus.OK).body("Usuário alterado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
	
	}
	
	
}
