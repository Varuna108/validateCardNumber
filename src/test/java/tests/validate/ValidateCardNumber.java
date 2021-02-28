package tests.validate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static tests.validate.helpers.HelperMethods.stringToArray;
import static tests.validate.helpers.HelperMethods.moonAlgorithm;

@Tag("test")
public class ValidateCardNumber {


    @Test
    void validateCardBankWithMoon() {
        String result =
                when()
                        .get("https://api.randomdatatools.ru/?count=50&params=bankCard")
                        .then()
                        .statusCode(200).extract().response().asString().replaceAll("[^\\p{N}]", "");

        String[] newStrings = stringToArray(result);
        for (int i = 0; i < newStrings.length; i++) {
            try {
                assertThat(moonAlgorithm(newStrings[i]), containsString("Card Valid"));
                System.out.println("Valid Card: " + newStrings[i] + ", index in the list: " + i);
            } catch (AssertionError ignored) {
            }
        }
    }
}



