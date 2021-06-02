package zw.co.malvern.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import zw.co.malvern.business.RegistrationService;
import zw.co.malvern.utils.response.Response;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static zw.co.malvern.utils.TestData.accountCreationRequest;

@WebMvcTest(controllers = RegistrationResource.class)
public class RegistrationResourceUnitTest {
    @MockBean
    private RegistrationService registrationService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void givenCustomerRequest_whenCreatingCustomerAccount_shouldReturnSuccess() throws Exception {
        final String url = "/api/kyc/account/create";
        final AccountResponse accountResponse = new AccountResponse("account successfully created",
                true,"1234");
        given(registrationService.createBankAccount(any(CustomerAccountRequest.class)))
                .willReturn(accountResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(objectMapper.writeValueAsString(accountCreationRequest()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(jsonPath("narrative").value("account successfully created"))
                .andExpect(jsonPath("success").value(true))
                .andExpect(jsonPath("accountNumber").value("1234"))
                .andExpect(status().isOk());
        verify(registrationService,times(1))
                .createBankAccount(any(CustomerAccountRequest.class));
    }
}
