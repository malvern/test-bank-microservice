package zw.co.malvern.api;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/kyc")
public class RegistrationResource {

    @PostMapping("account/create")
    public String createCustomerAccount(@RequestBody CustomerRequest customerRequest){
        return " Account for "+ customerRequest.getName()+" was successfully created";
    }
}
