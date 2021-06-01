package zw.co.malvern.utils;

import org.junit.jupiter.api.Test;
import zw.co.malvern.api.CustomerRequest;

import java.time.LocalDate;

public class TestData {

    public static CustomerRequest accountCreationRequest(){
        final CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("malvern");
        customerRequest.setSurname("dongeni");
        customerRequest.setAge(30);
        return customerRequest;
    }
    
}
