package demo.example.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Payment;
import com.example.demo.service.PaymentsService;

import repositories.PaymentsRepository;

@RestController
@RequestMapping("/payment")
public class Confirmation {
	
	@Autowired
	PaymentsRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(Confirmation.class);
	
	@RequestMapping(method=RequestMethod.POST, value="confirm")
	public void receiveC2BConfirmation (@RequestBody String object) throws Exception{
		String msg = null;
		HashMap<String,String> map = new HashMap<>();
		if(object.isEmpty()) {
			logger.debug("STK callback empty");
			msg = "STK callback empty";
		}else {
			JSONObject request = new JSONObject();
			JSONObject incoming = new JSONObject(object);
			request = incoming.getJSONObject("Body").getJSONObject("stkCallback");
			String merchantRequestID = request.getString("MerchantRequestID");
			String checkoutRequestID = request.getString("CheckoutRequestID");
			int resultCode = request.getInt("ResultCode");
			String resultDesc = request.getString("ResultDesc");
			if(resultCode != 0) {
				logger.debug("STK has a problem exiting with code"+ resultCode);
				msg = "STK error exiting with code" +resultCode;
			}else {
				JSONArray items = request.getJSONObject("CallbackMetadata").getJSONArray("Item");
				for(int i = 0; i < items.length(); i++) {
					String name = items.getJSONObject(i).getString("Name");
					String value = null;
					if(items.getJSONObject(i).has("Value")) {
						Object aObj = items.getJSONObject(i).get("Value");
						if(aObj instanceof Integer) {
							map.put("Amount", String.valueOf(items.getJSONObject(i).getInt("Value")));
						}else if(aObj instanceof Long) {
							map.put("PhoneNumber", String.valueOf(items.getJSONObject(i).getLong("Value")));
						}
//						else if(aObj instanceof Long && String.valueOf(items.getJSONObject(i).getLong("Value")).length() == 14){
//							map.put("TransactionDate", String.valueOf(aObj));
//						}
						else {
							System.out.println(aObj);
							map.put("MpesaReceiptNumber", String.valueOf(aObj));
						}
					}
				}
			}
			
			msg = "Successfully processed";
			Payment payment = new Payment();
			payment.setMerchantRequestID(merchantRequestID);
			payment.setAmount(map.get("Amount"));
			payment.setPhoneNumber(map.get("PhoneNumber"));
			payment.setMpesaReceiptNumber(map.get("MpesaReceiptNumber"));
			payment.setResultCode(resultCode);
			payment.setResultDesc(resultDesc);	
			payment.setTransactionDate(new Date());
			payment.setCheckoutRequestID(checkoutRequestID);
			repository.save(payment);
			System.out.println(object);
		}
	}
	
//	@RequestMapping(method=RequestMethod.POST, value="url")
//	public void registerURL() {
//		try {
//			new PaymentsService().registerURL();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
