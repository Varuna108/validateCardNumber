package tests.validate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static tests.validate.helpers.HelperMethods.*;

@Tag("test")
public class ValidateCardNumber {

    @Test
    void validateCardBankAndBinBank() {
        String resultResponse =
                when()
                        .get("https://api.randomdatatools.ru/?count=50&params=bankCard")
                        .then()
                        .statusCode(200).extract().response().asString().replaceAll("[^\\p{N}]", "");

        String[] newStrings = stringToArray(resultResponse);
        for (int i = 0; i < newStrings.length; i++) {
            try {
                assertThat(moonAlgorithm(newStrings[i]), containsString("Card Valid"));
                System.out.println("Valid Card: " + newStrings[i] + ", index in the list: " + i);
            } catch (AssertionError ignored) {
            }
        }

        String[] validCard = arrayValidCard();
        try {
            for (int i = 0; i < validCard.length; i++) {
                System.out.println("Bin bank: " + validCard[i]);
                when()
                        .get("https://lookup.binlist.net/" + validCard[i])
                        .then()
                        .log().body()
                        .statusCode(200).extract().response().asString();
            }
        } catch (AssertionError ignored) {
        }
    }
}



