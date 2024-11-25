package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private final Validator VALIDATOR = new Validator();
    private StringSchema schema;
    private final static String ANOTHER_STRING = "what does the fox say";

    @BeforeEach
    public void initialization() {
        schema = VALIDATOR.string();
    }


    @Test
    public void requiredTest() {

        assertTrue(schema.isValid(ANOTHER_STRING));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.required();
        assertTrue(schema.isValid(ANOTHER_STRING));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    public void minLengthTest() {
        assertTrue(schema.isValid(ANOTHER_STRING));
        schema.minLength(10);
        assertTrue(schema.isValid(ANOTHER_STRING));
        schema.minLength(100);
        assertFalse(schema.isValid(ANOTHER_STRING));

        schema.minLength(100).minLength(5).minLength(10);
        assertTrue(schema.isValid(ANOTHER_STRING));

        schema.minLength(-100);
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


        assertFalse(schema.contains(null).isValid(ANOTHER_STRING));
        assertFalse(schema.contains("wh").isValid(null));

    }

    @Test
    public void fullTest() {
        schema.required().minLength(3).contains("what");

        assertTrue(schema.isValid(ANOTHER_STRING));
        assertFalse(schema.isValid(null));
    }
}
