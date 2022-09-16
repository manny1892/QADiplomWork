package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.APIHelper;
import data.DBHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.TourPage;

public class APITourTest {
    TourPage tourPage = new TourPage();

    @AfterEach
    void clearDB() {
        DBHelper.clearDB();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("87) HappyPath PayCard Status Approved API Test Response 200")
    void shouldResponseTwoHundredByPayPayCardApproved() {
        Assertions.assertAll(
                APIHelper::buyApprovedPayCardAPI,
                () -> tourPage.payApprovedStatusAssertion(),
                () -> tourPage.payAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("88) HappyPath PayCard Status Declined API Test Response 200")
    void shouldResponseTwoHundredByPayPayCardDeclined() {
        Assertions.assertAll(
                APIHelper::buyDeclinedPayCardAPI,
                () -> tourPage.payDeclinedStatusAssertion(),
                () -> tourPage.payAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("89) HappyPath CreditCard Status Approved API Test Response 200")
    void shouldResponseTwoHundredByPayCreditCardApproved() {
        Assertions.assertAll(
                APIHelper::buyApprovedCreditCardAPI,
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("90) HappyPath CreditCard Status Declined API Test Response 200")
    void shouldResponseTwoHundredByPayCreditCardDeclined() {
        Assertions.assertAll(
                APIHelper::buyDeclinedCreditCardAPI,
                () -> tourPage.creditDeclinedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("91) Empty PayCard API Test Response 400")
    void shouldResponseFourHundredByPayEmptyPayCard() {
        Assertions.assertAll(
                APIHelper::buyEmptyPayCardAPI,
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("92) Approved PayCard And Random Invalid Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedPayCardAndRandomInvalidOtherField() {
        Assertions.assertAll(
                APIHelper::buyApprovedPayCardAndRandomInvalidOtherField,
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("93) Approved PayCard And Empty Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedPayCardAndEmptyOtherField() {
        Assertions.assertAll(
                APIHelper::buyApprovedPayCardAndEmptyOtherField,
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("94) Random PayCard Number And Valid Other Field API Test Response 400")
    void shouldResponseFourHundredByRandomCardNumberAndValidOtherField() {
        Assertions.assertAll(
                APIHelper::buyRandomCardNumberAndValidOtherField,
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("95) Empty CreditCard API Test Response 400")
    void shouldResponseFourHundredByPayEmptyCreditCard() {
        Assertions.assertAll(
                APIHelper::buyEmptyCreditCardAPI,
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("96) Approved CreditCard And Random Invalid Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedCreditCardAndRandomInvalidOtherField() {
        Assertions.assertAll(
                APIHelper::buyApprovedCreditCardAndRandomInvalidOtherField,
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("97) Approved CreditCard And Empty Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedCreditCardAndEmptyOtherField() {
        Assertions.assertAll(
                APIHelper::buyApprovedCreditCardAndEmptyOtherField,
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("98) Random CreditCard Number And Valid Other Field API Test Response 400")
    void shouldResponseFourHundredByRandomCreditCardNumberAndValidOtherField() {
        Assertions.assertAll(
                APIHelper::buyRandomCreditCardNumberAndValidOtherField,
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }
}
