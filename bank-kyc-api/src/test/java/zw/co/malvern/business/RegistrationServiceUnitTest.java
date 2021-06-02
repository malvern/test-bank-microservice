package zw.co.malvern.business;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import zw.co.malvern.repository.AccountRepository;

import static org.mockito.Mockito.mock;

public class RegistrationServiceUnitTest {

    @Mock
    private AccountRepository accountRepository;
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        registrationService = new RegistrationServiceImpl(accountRepository);
    }
}
