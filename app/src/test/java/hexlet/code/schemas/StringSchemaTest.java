package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSchemaTest {
    private final Validator validator = new Validator();
    private StringSchema schema;
    private static final String ANOTHER_STRING = "what does the fox say";

    private static final int LITTLE_TEST_LENGTH = 5;
    private static final int MIDDLE_TEST_LENGTH = 10;
    private static final int BIG_TEST_LENGTH = 100;
    private static final int NEGATIVE_TEST_LENGTH = -100;


    @BeforeEach
    public void initialization() {
        schema = validator.string();
    }


    @Test
    public void noAttributeTest(){
        // Тест работы без добовления required
        assertTrue(schema.isValid(ANOTHER_STRING));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
    }

    @Test
    public void requiredTest() {

        // Тест работы с добовлением required
        schema.required();
        assertTrue(schema.isValid(ANOTHER_STRING));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    public void minLengthTest() {

        schema.minLength(MIDDLE_TEST_LENGTH);
        assertTrue(schema.isValid(ANOTHER_STRING));
        schema.minLength(BIG_TEST_LENGTH);
        assertFalse(schema.isValid(ANOTHER_STRING));

        schema.minLength(BIG_TEST_LENGTH).minLength(LITTLE_TEST_LENGTH).minLength(MIDDLE_TEST_LENGTH);
        assertTrue(schema.isValid(ANOTHER_STRING));

        schema.minLength(NEGATIVE_TEST_LENGTH);
        assertTrue(schema.isValid(ANOTHER_STRING));
        assertFalse(schema.isValid(null));
    }

    @Test
    public void containsTest() {

        schema.contains("what");
        assertTrue(schema.isValid(ANOTHER_STRING));
        schema.contains("wh");
        assertTrue(schema.isValid(ANOTHER_STRING));
        assertFalse(schema.isValid("String"));

        var throwContains = assertThrows(IllegalArgumentException.class, () -> {
            schema.contains(null).isValid(ANOTHER_STRING);
        });

        String errorMessage = "Not null";
        assertEquals(errorMessage, throwContains.getMessage());

        assertFalse(schema.contains("wh").isValid(null));

    }

    @Test
    public void fullTest() {
        schema.required().minLength(LITTLE_TEST_LENGTH).contains("what");

        assertTrue(schema.isValid(ANOTHER_STRING));
        assertFalse(schema.isValid(null));
    }
}
