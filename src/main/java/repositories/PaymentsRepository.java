package repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.models.Payment;

public interface PaymentsRepository extends MongoRepository<Payment, String>{

}
