package demo.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Payment;

import repositories.PaymentsRepository;

@RestController
@RequestMapping("/")
public class Validation {

//	@Autowired
//	PaymentsRepository repository;
	
	@RequestMapping(method=RequestMethod.POST, value="validation")
	public ResponseEntity receiveC2BConfirmation(@RequestBody Payment payments) {
//		repository.save(payments);
		return new ResponseEntity<Payment>(payments, HttpStatus.OK);
	}
}
