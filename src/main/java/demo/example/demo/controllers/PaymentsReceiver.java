package demo.example.demo.controllers;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Orders;
import com.example.demo.models.Payment;
import com.example.demo.service.PaymentsService;

@RestController
@RequestMapping("/")
public class PaymentsReceiver {
	
	@RequestMapping(method=RequestMethod.POST, value="orders")
	public ResponseEntity<?> createOrder(@RequestBody String payments) throws Exception{
		JSONObject object = new JSONObject(payments);
		String amount = object.getString("amount");
		String msisdn = object.getString("msisdn");
		String data = new PaymentsService().initiateRequest(amount, msisdn);
		System.out.println(data);
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}
}
