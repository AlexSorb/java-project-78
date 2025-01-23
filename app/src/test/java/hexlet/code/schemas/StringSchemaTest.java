package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSchemaTest {

    private StringSchema schema;
    private static final String TESTING_STRING = "what does the fox say";

    private static final int LITTLE_LENGTH = 5;
    private static final int BIG_LENGTH = 100;

    @BeforeEach
    public void initialization() {
        schema = new Validator().string();
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
        boolean resultRandomString = schema.isValid(TESTING_STRING);
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
    public void minLengthSchemaWithLittleLengthTest(){

        schema.minLength(LITTLE_LENGTH);
        boolean resultFiveLength = schema.isValid(TESTING_STRING);
        assertTrue(resultFiveLength);


    }

    @Test
    public void minLengthSchemaWithBigLengthTest() {
        schema.minLength(BIG_LENGTH);
        boolean resultHundredLength = schema.isValid(TESTING_STRING);
        assertFalse(resultHundredLength);
    }

    @Test
    public void redefineMinLengthSchemaTest() {
        schema.minLength(BIG_LENGTH).minLength(LITTLE_LENGTH);
        boolean resultChangeLength = schema.isValid(TESTING_STRING);
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
        var throwContains = assertThrows(IllegalArgumentException.class, () ->{
            schema.minLength(-LITTLE_LENGTH);
        });
        assertEquals("Length less than zero", throwContains.getMessage());
    }

    @Test
    public void containsSchemaTest() {


        schema.contains("what");
        assertTrue(schema.isValid(TESTING_STRING));
        schema.contains("wh");
        assertTrue(schema.isValid(TESTING_STRING));
        assertFalse(schema.isValid("String"));

        var throwContains = assertThrows(IllegalArgumentException.class, () -> {
            schema.contains(null).isValid(TESTING_STRING);
        });

        String errorMessage = "Not null";
        assertEquals(errorMessage, throwContains.getMessage());

        assertFalse(schema.contains("wh").isValid(null));

    }
}
