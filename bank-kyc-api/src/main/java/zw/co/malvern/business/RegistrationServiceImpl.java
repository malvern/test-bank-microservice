package zw.co.malvern.business;

import zw.co.malvern.api.AccountResponse;
import zw.co.malvern.api.CustomerAccountRequest;
import zw.co.malvern.domain.Account;
import zw.co.malvern.repository.AccountRepository;
import zw.co.malvern.utils.exceptions.ValidationException;

import static zw.co.malvern.utils.validators.CustomValidators.validateAgeLimit;
import static zw.co.malvern.utils.validators.CustomValidators.validateStringAndThrow;

public class RegistrationServiceImpl implements RegistrationService {
    private final AccountRepository accountRepository;

    public RegistrationServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse createBankAccount(CustomerAccountRequest customerAccountRequest) {
        validateStringAndThrow(customerAccountRequest.getSurname());
        validateStringAndThrow(customerAccountRequest.getName());
        validateAgeLimit((long) customerAccountRequest.getAge());
        //TODO Generate account number for customer account
        accountRepository.save(convertToAccount(customerAccountRequest));
        return null;
    }

    private Account convertToAccount(CustomerAccountRequest customerAccountRequest) {
        final Account account = new Account();
        if(customerAccountRequest == null)
            throw new ValidationException("Customer Account Request object cannot be null");
        account.setAge((long)customerAccountRequest.getAge());
        account.setSurname(customerAccountRequest.getSurname());
        account.setName(customerAccountRequest.getName());
        return account;
    }
}
