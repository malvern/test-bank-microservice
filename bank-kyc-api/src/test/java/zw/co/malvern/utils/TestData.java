package zw.co.malvern.utils;

import zw.co.malvern.api.CustomerRequest;

public class TestData {

    public static CustomerRequest accountCreationRequest(){
        final CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("malvern");
        customerRequest.setSurname("dongeni");
        customerRequest.setAge(30);
        return customerRequest;
    }
}
