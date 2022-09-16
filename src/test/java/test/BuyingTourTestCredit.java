package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.TourPage;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class BuyingTourTestCredit {

    TourPage tourPage = new TourPage();

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

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

    // Полные пути

    @Test
    @DisplayName("2) HappyPath CreditCard Status APPROVED")
    void shouldSuccessfulBuyByCreditCardApproved() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }


    @Test
    @DisplayName("4) HappyPath CreditCard Status DECLINED")
    void shouldFailureBuyByCreditCardDeclined() {
        tourPage.completeCreditFrom(DataHelper.getDeclinedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.creditDeclinedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }


    @Test
    @DisplayName("6) UnHappyPath CreditCard Random Invalid CardField")
    void shouldFailureBuyByCreditCardInvalidCardField() {
        tourPage.completeCreditFrom(DataHelper.getRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.payDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    // Поле номер карты

    @Test
    @DisplayName("11) CreditCard Positive Test CardField 16 symbols")
    void shouldSuccessfulBuyByValidCreditCardFieldSixteenSymbols() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("12) CreditCard Negative Test CardField 15 symbols")
    void shouldFailureBuyByInvalidCreditCardFieldFifteenSymbols() {
        tourPage.completeCreditFrom(DataHelper.getFifteenRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.numberFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("13) CreditCard Negative Test CardField Empty")
    void shouldFailureBuyByInvalidCreditCardFieldEmpty() {
        tourPage.completeCreditFrom(DataHelper.getEmptyNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.numberFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("14) CreditCard Negative Test CardField One Symbol")
    void shouldFailureBuyByInvalidCreditCardFieldOneSymbol() {
        tourPage.completeCreditFrom(DataHelper.getOneRandomNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.numberFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    // Поле месяц

    @Test
    @DisplayName("23) CreditCard Positive Test MonthField 12 month")
    void shouldSuccessfulBuyByValidTwelveMonthMonthFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getTwelveMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("24) CreditCard Positive Test MonthField 11 month")
    void shouldSuccessfulBuyByValidElevenMonthMonthFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getElevenMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("25) CreditCard Positive Test MonthField 01 month")
    void shouldSuccessfulBuyByValidFirstMonthMonthFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getFirstMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("26) CreditCard Positive Test MonthField 02 month")
    void shouldSuccessfulBuyByValidSecondMonthMonthFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getSecondMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("27) CreditCard Negative Test MonthField 13 month")
    void shouldFailureBuyByInvalidThirteenMonthMonthFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getThirteenMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.monthFieldPeriodError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("28) CreditCard Negative Test MonthField 00 month")
    void shouldFailureBuyByInvalidZeroMonthMonthFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getZeroMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.monthFieldPeriodError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("29) CreditCard Negative Test MonthField Empty month")
    void shouldFailureBuyByInvalidEmptyMonthMonthFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getEmptyMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.monthFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("30) CreditCard Negative Test MonthField One Symbol month")
    void shouldFailureBuyByInvalidOneSymbolMonthMonthFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getOneSymbolMonth(), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.monthFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    //Поле год


    @Test
    @DisplayName("39) CreditCard Positive Test YearField Current Year")
    void shouldSuccessfulBuyByValidCurrentYearYearFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("40) CreditCard Positive Test YearField Current Year Plus One")
    void shouldSuccessfulBuyByValidCurrentYearPlusOneYearFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("41) CreditCard Positive Test YearField Current Year Plus Five")
    void shouldSuccessfulBuyByValidCurrentYearPlusFiveYearFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(5), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("42) CreditCard Positive Test YearField Current Year Plus Four")
    void shouldSuccessfulBuyByValidCurrentYearPlusFourYearFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(4), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("43) CreditCard Negative Test YearField Current Year Minus One")
    void shouldFailureBuyByValidCurrentYearMinusOneYearFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(-1), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.yearFieldMinusPeriodError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("44) CreditCard Negative Test YearField Current Year Plus Six")
    void shouldFailureBuyByValidCurrentYearPlusSixYearFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(6), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.yearFieldPlusPeriodError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("45) CreditCard Negative Test YearField Current Year Empty")
    void shouldFailureBuyByInvalidYearEmptyYearFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getEmptyYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.yearFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("46) CreditCard Negative Test YearField Current Year One Symbol")
    void shouldFailureBuyByInvalidYearOneSymbolYearFieldCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getOneSymbolYear(), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.yearFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    //Поле владелец

    @Test
    @DisplayName("60) CreditCard Positive Test HolderField Three Symbols")
    void shouldSuccessfulBuyByValidHolderFieldThreeSymbolsCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("61) CreditCard Positive Test HolderField Four Symbols")
    void shouldSuccessfulBuyByValidHolderFieldFourSymbolsCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("62) CreditCard Positive Test HolderField Nineteen Symbols")
    void shouldSuccessfulBuyByValidHolderFieldNineteenSymbolsCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???????????????????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("63) CreditCard Positive Test HolderField Twenty Symbols")
    void shouldSuccessfulBuyByValidHolderFieldTwentySymbolsCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("????????????????????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("64) CreditCard Positive Test HolderField With Spacebar")
    void shouldSuccessfulBuyByValidHolderFieldWithSpaceBarCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("??????? ???????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("65) CreditCard Positive Test HolderField With Dash")
    void shouldSuccessfulBuyByValidHolderFieldWithDashCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("???????-???????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("66) CreditCard Negative Test HolderField TwentyOne Symbols")
    void shouldFailureBuyByInvalidHolderFieldTwentyOneSymbolsCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("?????????????????????"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.holderFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("67) CreditCard Negative Test HolderField Two Symbols")
    void shouldFailureBuyByInvalidHolderFieldTwoSymbolsCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("??"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.holderFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("68) CreditCard Negative Test HolderField Empty")
    void shouldFailureBuyByInvalidHolderFieldEmptyCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEmptyHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.holderFieldEmptyError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );

    }

    @Test
    @DisplayName("69) CreditCard Negative Test HolderField SpecialCharacters")
    void shouldFailureBuyByInvalidHolderFieldSpecialCharactersCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getSpecialCharactersHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.holderFieldEmptyError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("70) CreditCard Negative Test HolderField Spacebars")
    void shouldFailureBuyByInvalidHolderFieldSpacebarsCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getSpacesHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.holderFieldEmptyError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("71) CreditCard Negative Test HolderField Numerify")
    void shouldFailureBuyByInvalidHolderFieldNumerifyCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getNumerifyHolder("########"), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.holderFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("72) CreditCard Negative Test HolderField RU")
    void shouldFailureBuyByInvalidHolderFieldRUCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getRuHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.holderFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("73) CreditCard Negative Test HolderField Start And Finish Spacebars")
    void shouldFailureBuyByInvalidHolderFieldStartAndFinishSpacebarsCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getChoiceSymbolHolder("  ??????   ????  "), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.holderFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("74) CreditCard Negative Test HolderField With Lower Case")
    void shouldFailureBuyByInvalidHolderFieldWithLowerCaseCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolderWithLowerCase(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.holderFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    //Поле CVC


    @Test
    @DisplayName("80) CreditCard Positive Test CVCField Three Symbols")
    void shouldSuccessfulBuyByValidCVCFieldThreeSymbolsCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.acceptAssertion(),
                () -> tourPage.creditApprovedStatusAssertion(),
                () -> tourPage.creditAcceptCountAssertion(),
                () -> tourPage.orderAcceptCountAssertion()
        );
    }

    @Test
    @DisplayName("81) CreditCard Negative Test CVCField Two Symbols")
    void shouldFailureBuyByInvalidCVCFieldTwoSymbolsCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getTwoSymbolsCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.CVCFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("82) CreditCard Negative Test CVCField One Symbols")
    void shouldFailureBuyByInvalidCVCFieldOneSymbolsCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getOneSymbolCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.CVCFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("83) CreditCard Negative Test CVCField Empty")
    void shouldFailureBuyByInvalidCVCFieldEmptyCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getEmptyCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.CVCFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    @Test
    @DisplayName("84) CreditCard Negative Test CVCField Zero")
    void shouldFailureBuyByInvalidCVCFieldZeroCreditCard() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getZeroSymbolsCVC());
        tourPage.continueClick();
        Assertions.assertAll(
                () -> tourPage.denialAssertion(),
                () -> tourPage.CVCFieldFormatError(),
                () -> tourPage.creditDenialCountAssertion(),
                () -> tourPage.orderDenialCountAssertion()
        );
    }

    //Остальные пути


    @Test
    @DisplayName("86) CreditCard Change On PayCard Stay Completed ")
    void shouldSuccessfulChangeOnPayCardStayCompleted() {
        tourPage.completeCreditFrom(DataHelper.getApprovedNumber(), DataHelper.getMonth(0), DataHelper.getYear(0), DataHelper.getEngHolder(), DataHelper.getCVC());
        ArrayList<String> beforeClick = tourPage.getFrom();
        tourPage.clickPayButton();
        ArrayList<String> afterClick = tourPage.getFrom();
        Assertions.assertAll(
                () -> Assertions.assertEquals(beforeClick.get(0), afterClick.get(0)),
                () -> Assertions.assertEquals(beforeClick.get(1), afterClick.get(1)),
                () -> Assertions.assertEquals(beforeClick.get(2), afterClick.get(2)),
                () -> Assertions.assertEquals(beforeClick.get(3), afterClick.get(3)),
                () -> Assertions.assertEquals(beforeClick.get(4), afterClick.get(4))
        );
    }
}