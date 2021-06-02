package zw.co.malvern.utils;

import zw.co.malvern.api.CustomerAccountRequest;

public class TestData {

    public static CustomerAccountRequest accountCreationRequest(){
        final CustomerAccountRequest customerAccountRequest = new CustomerAccountRequest();
        customerAccountRequest.setName("malvern");
        customerAccountRequest.setSurname("dongeni");
        customerAccountRequest.setAge(30);
        return customerAccountRequest;
    }
    
}
