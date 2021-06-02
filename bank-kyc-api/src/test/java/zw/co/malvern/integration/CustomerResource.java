package zw.co.malvern.integration;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.junit.jupiter.Testcontainers;
import zw.co.malvern.api.AccountResponse;
import zw.co.malvern.api.CustomerAccountRequest;
import zw.co.malvern.utils.response.Response;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static zw.co.malvern.configuration.TestContainerConfiguration.composeContainer;
import static zw.co.malvern.utils.TestApi.createAccountUrl;
import static zw.co.malvern.utils.TestData.accountCreationRequest;

@Testcontainers
@ActiveProfiles("dev")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Creating customer account")
public class CustomerResource {
    @LocalServerPort
    private int localPort;
    private TestRestTemplate testRestTemplate;


    @BeforeAll
    void setUp() {
        testRestTemplate = new TestRestTemplate();
        composeContainer.start();
    }

    @Test
    @DisplayName("success response: 200")
    void givenCustomerDetails_whenCreatingCustomerAccount_shouldReturnAccountResponse() {
        final AccountResponse expectedResponse = new AccountResponse("account for "
                + accountCreationRequest().getName() + " was successfully created", true,
                "12345");
        processRequestAndAssert(accountCreationRequest(), expectedResponse, 200);
    }


    @Test
    @DisplayName("failed response: 400")
    void givenInvalidCustomerDetails_whenCreatingCustomerAccount_shouldReturnFailedResponse() {
        final CustomerAccountRequest customerAccountRequest = accountCreationRequest();
        customerAccountRequest.setName("");
        final Response response = new Response("failed to create account.customer name cannot be empty or null",
                false);
        processRequestAndAssert(customerAccountRequest, response, 400);
    }

    @Test
    @DisplayName("internal error response: 500")
    void givenCustomerDetails_whenCreatingCustomerAccount_shouldReturnInternalErrorResponse() {
        //TODO looking for edge case that might cause internal error

    }


    private void processRequestAndAssert(CustomerAccountRequest customerAccountRequest,
                                         Response expectedResponse,
                                         int expectedStatusCode) {
        final UriComponents url = UriComponentsBuilder.fromUriString(createAccountUrl)
                .port(localPort).build();
        final ResponseEntity<AccountResponse> response = testRestTemplate
                .postForEntity(url.toString(), customerAccountRequest, AccountResponse.class);
        assertThat(response).as("account creation response").isNotNull();
        assertThat(response.getStatusCode().value()).as("status").isEqualTo(expectedStatusCode);
        assertThat(Objects.requireNonNull(response.getBody()).isSuccess()).as("truth").isFalse();
        assertThat(response.getBody().getNarrative()).as("narrative")
                .isEqualTo(expectedResponse.getNarrative());
        if (expectedResponse instanceof AccountResponse) {
            assertThat(response.getBody().getAccountNumber()).as("account number").isNotNull();
            assertThat(response.getBody().getAccountNumber()).as("actual account number")
                    .isEqualTo(((AccountResponse) expectedResponse).getAccountNumber());
        }
    }

    @AfterAll
    void tearDown() {
        composeContainer.stop();
    }
}
