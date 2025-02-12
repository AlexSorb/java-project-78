package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSchemaTest {

    private static final Validator VALIDATOR = new Validator();
    private StringSchema schema;
    private static final String TESTING_VALID_STRING = "what does the fox say";
    private static final String TESTING_NOT_VALID_STRING = "This string don't contain searched string";
    private static final String TESTING_SHORT_STRING = "min";
    private static final String CONTAINS_WORD = "what";
    private static final String CONTAINS_SYLLABLE = "wh";
    private static final String NOT_CONTAINS_WORD = "Not Contain";

    private static final int LITTLE_LENGTH = 5;
    private static final int BIG_LENGTH = 100;
    private static final int NEGATIVE_LENGTH = -100;

    @BeforeEach
    public final void initialization() {
        schema = VALIDATOR.string();
    }

    // Тестирование isValid без схемы
    @Test
    public void isValidWithoutSchemasWithNullTest() {
        boolean resultNull = schema.isValid(null);
        assertTrue(resultNull);
    }

    @Test
    public void isValidWithoutSchemasWithEmptyStringTest() {
        boolean resultEmptyString = schema.isValid("");
        assertTrue(resultEmptyString);
    }

    @Test
    public void isValidWithoutSchemasWithStringTest() {
        boolean resultRandomString = schema.isValid(TESTING_VALID_STRING);
        assertTrue(resultRandomString);
    }


    // Тестирование схемы required
    @Test
    public void requiredSchemaWithNullTest() {
        schema.required();
        boolean resultNull = schema.isValid(null);
        assertFalse(resultNull);

    }

    @Test
    public void requiredSchemaWithEmptyStringTest() {
        schema.required();
        boolean resultEmptyString = schema.isValid("");
        assertFalse(resultEmptyString);
    }

    @Test
    public void requiredSchemaWithStringTest() {
        schema.required();
        boolean resultRandomString = schema.isValid("Same string");
        assertTrue(resultRandomString);
    }

    @Test
    public void minLengthSchemaWithLittleLengthTest() {
        schema.minLength(LITTLE_LENGTH);
        boolean resultFiveLength = schema.isValid(TESTING_VALID_STRING);
        assertTrue(resultFiveLength);
    }

    @Test
    public void minLengthSchemaWithBigLengthTest() {
        schema.minLength(BIG_LENGTH);
        boolean resultHundredLength = schema.isValid(TESTING_VALID_STRING);
        assertFalse(resultHundredLength);
    }

    @Test
    public void redefineMinLengthSchemaTest() {
        schema.minLength(BIG_LENGTH).minLength(LITTLE_LENGTH);
        boolean resultChangeLength = schema.isValid(TESTING_VALID_STRING);
        assertTrue(resultChangeLength);
    }

    @Test
    public void minLengthSchemaWithNullTest() {
        schema.minLength(LITTLE_LENGTH);
        boolean resultNull = schema.isValid(null);
        assertFalse(resultNull);
    }

    @Test
    public void minLengthSchemaWithNegativeValueLengthTest() {
        var throwContains = assertThrows(IllegalArgumentException.class, () -> schema.minLength(NEGATIVE_LENGTH));
        assertEquals("Length less than zero", throwContains.getMessage());
    }

    @Test
    public void containsSchemaWithContainsFindWordTest() {
        schema.contains(CONTAINS_WORD);
        boolean resultContainWord = schema.isValid(TESTING_VALID_STRING);
        assertTrue(resultContainWord);
    }

    @Test
    public void containsSchemaWithContainsFindSyllableTest() {
        schema.contains(CONTAINS_SYLLABLE);
        boolean resultContainSyllable = schema.isValid(TESTING_VALID_STRING);
        assertTrue(resultContainSyllable);
    }

    @Test
    public void containsSchemaWithNotContainsFindWordTest() {
        schema.contains(NOT_CONTAINS_WORD);
        boolean resultNotContainWord = schema.isValid(TESTING_VALID_STRING);
        assertFalse(resultNotContainWord);
    }

    @Test
    public void containsSchemaWithNullContainsStringTest() {
        var throwContains = assertThrows(IllegalArgumentException.class, () -> {
            schema.contains(null);
            schema.isValid(TESTING_VALID_STRING);
        });

        String errorMessage = "Not null";
        assertEquals(errorMessage, throwContains.getMessage());
    }

    @Test
    public void containsSchemaWithNullDataStringTest() {
        schema.contains(TESTING_VALID_STRING);
        boolean resulNullData = schema.isValid(null);
        assertFalse(resulNullData);
    }

    @Test
    public void interactionFullSchemasTest() {
        schema.minLength(BIG_LENGTH).contains(NOT_CONTAINS_WORD).minLength(LITTLE_LENGTH).contains(CONTAINS_WORD);
        boolean resultFullTest = schema.isValid(TESTING_VALID_STRING);
        assertTrue(resultFullTest);
    }

    @Test
    public void interactionSchemasMinLengthAndContainsTest() {
        schema.minLength(LITTLE_LENGTH).contains(CONTAINS_WORD);

        boolean resultWithNull = schema.isValid(null);
        assertFalse(resultWithNull);

        boolean resultWithNotContainsWord = schema.isValid(TESTING_NOT_VALID_STRING);
        assertFalse(resultWithNotContainsWord);

        boolean resultWithShortString = schema.isValid(TESTING_SHORT_STRING);
        assertFalse(resultWithShortString);

        boolean resultWithValidString = schema.isValid(TESTING_VALID_STRING);
        assertTrue(resultWithValidString);
    }

    @Test
    public void interactionSchemasRequiredAndContainsTest() {
        schema.required().contains(CONTAINS_SYLLABLE);

        boolean resultWithNull = schema.isValid(null);
        assertFalse(resultWithNull);

        boolean resultWithEmptyString = schema.isValid("");
        assertFalse(resultWithEmptyString);

        boolean resultWithNotValidString = schema.isValid(TESTING_NOT_VALID_STRING);
        assertFalse(resultWithNotValidString);

        boolean resultWithValidString = schema.isValid(TESTING_VALID_STRING);
        assertTrue(resultWithValidString);

    }

    @Test
    public void interactionSchemasRequiredAndMinLengthTest() {
        schema.minLength(LITTLE_LENGTH).required();

        boolean resultWithNull = schema.isValid(null);
        assertFalse(resultWithNull);

        boolean resultWithEmptyString = schema.isValid("");
        assertFalse(resultWithEmptyString);

        boolean resultWithShortString = schema.isValid(TESTING_SHORT_STRING);
        assertFalse(resultWithShortString);

        boolean resultWithValidString = schema.isValid(TESTING_VALID_STRING);
        assertTrue(resultWithValidString);
    }

}
