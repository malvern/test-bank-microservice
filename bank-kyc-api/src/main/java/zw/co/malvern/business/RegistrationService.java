package zw.co.malvern.business;


import zw.co.malvern.api.AccountResponse;
import zw.co.malvern.api.CustomerAccountRequest;

@FunctionalInterface
public interface RegistrationService {
    AccountResponse createBankAccount(CustomerAccountRequest customerAccountRequest);
}
