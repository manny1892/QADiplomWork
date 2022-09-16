package data;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class APIHelper {
    private static final RequestSpecification reqSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void buyApprovedPayCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200);
    }

    public static void buyDeclinedPayCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidDeclinedCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200);
    }

    public static void buyApprovedCreditCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200);
    }

    public static void buyDeclinedCreditCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidDeclinedCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200);
    }

    public static void buyEmptyPayCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getEmptyCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
    }

    public static void buyApprovedPayCardAndRandomInvalidOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCardAndRandomInvalidOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
    }

    public static void buyApprovedPayCardAndEmptyOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCardAndEmptyOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
    }

    public static void buyRandomCardNumberAndValidOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getRandomCardNumberAndValidOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
    }

    public static void buyEmptyCreditCardAPI() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getEmptyCard());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(400);
    }

    public static void buyApprovedCreditCardAndRandomInvalidOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCardAndRandomInvalidOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(400);
    }

    public static void buyApprovedCreditCardAndEmptyOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getValidApprovedCardAndEmptyOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(400);
    }

    public static void buyRandomCreditCardNumberAndValidOtherField() {
        Gson gson = new Gson();
        String data = gson.toJson(DataHelper.getRandomCardNumberAndValidOtherField());
        given()
                .spec(reqSpec)
                .body(data)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(400);
    }
}
