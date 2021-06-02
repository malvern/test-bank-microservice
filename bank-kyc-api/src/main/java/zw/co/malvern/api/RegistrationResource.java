package zw.co.malvern.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.malvern.business.RegistrationService;

@CrossOrigin
@RestController
@RequestMapping("api/kyc")
public class RegistrationResource {

    private RegistrationService registrationService;

    public RegistrationResource(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("account/create")
    public ResponseEntity<AccountResponse> createCustomerAccount(
            @RequestBody CustomerAccountRequest customerAccountRequest) {
        return ResponseEntity.ok(registrationService.createBankAccount(customerAccountRequest));
    }
}