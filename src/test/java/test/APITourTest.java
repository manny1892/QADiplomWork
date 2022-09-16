package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.APIHelper;
import data.DBHelper;
import data.DataHelper;
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
                () -> Assertions.assertEquals(200, APIHelper.buyPayCardAPI(DataHelper.getValidApprovedCard())),
                () -> tourPage.payApprovedStatusAssertion(),
                () -> tourPage.payAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("88) HappyPath PayCard Status Declined API Test Response 200")
    void shouldResponseTwoHundredByPayPayCardDeclined() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(200, APIHelper.buyPayCardAPI(DataHelper.getValidDeclinedCard())),
                () -> tourPage.payDeclinedStatusAssertion(),
                () -> tourPage.payAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("89) HappyPath CreditCard Status Approved API Test Response 200")
    void shouldResponseTwoHundredByPayCreditCardApproved() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(200, APIHelper.buyCreditCardAPI(DataHelper.getValidApprovedCard())),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("90) HappyPath CreditCard Status Declined API Test Response 200")
    void shouldResponseTwoHundredByPayCreditCardDeclined() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(200, APIHelper.buyCreditCardAPI(DataHelper.getValidDeclinedCard())),
                () -> tourPage.creditDeclinedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("91) Empty PayCard API Test Response 400")
    void shouldResponseFourHundredByPayEmptyPayCard() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(400, APIHelper.buyPayCardAPI(DataHelper.getEmptyCard())),
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("92) Approved PayCard And Random Invalid Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedPayCardAndRandomInvalidOtherField() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(400, APIHelper.buyPayCardAPI(DataHelper.getValidApprovedCardAndRandomInvalidOtherField())),
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("93) Approved PayCard And Empty Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedPayCardAndEmptyOtherField() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(400, APIHelper.buyPayCardAPI(DataHelper.getValidApprovedCardAndEmptyOtherField())),
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("94) Random PayCard Number And Valid Other Field API Test Response 400")
    void shouldResponseFourHundredByRandomCardNumberAndValidOtherField() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(400, APIHelper.buyPayCardAPI(DataHelper.getRandomCardNumberAndValidOtherField())),
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("95) Empty CreditCard API Test Response 400")
    void shouldResponseFourHundredByPayEmptyCreditCard() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(400, APIHelper.buyCreditCardAPI(DataHelper.getEmptyCard())),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("96) Approved CreditCard And Random Invalid Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedCreditCardAndRandomInvalidOtherField() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(400, APIHelper.buyCreditCardAPI(DataHelper.getValidApprovedCardAndRandomInvalidOtherField())),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("97) Approved CreditCard And Empty Other Field API Test Response 400")
    void shouldResponseFourHundredByApprovedCreditCardAndEmptyOtherField() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(400, APIHelper.buyCreditCardAPI(DataHelper.getValidApprovedCardAndEmptyOtherField())),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("98) Random CreditCard Number And Valid Other Field API Test Response 400")
    void shouldResponseFourHundredByRandomCreditCardNumberAndValidOtherField() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(400, APIHelper.buyCreditCardAPI(DataHelper.getRandomCardNumberAndValidOtherField())),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }
}
