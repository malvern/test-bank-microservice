package zw.co.malvern.api;

import zw.co.malvern.utils.response.Response;

public class AccountResponse extends Response {
    private String accountNumber;

    public AccountResponse(String narrative, boolean success, String accountNumber) {
        super(narrative, success);
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
