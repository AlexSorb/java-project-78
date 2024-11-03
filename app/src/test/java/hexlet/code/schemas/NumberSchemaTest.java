package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NumberSchemaTest {
    private final static Validator VALIDATOR = new Validator();
    private NumberSchema schema;
    private final static Integer checkedPositiveNumber = 5;
    private final static Integer checkedNegativeNumber = -5;

    @BeforeEach
    public void initialization() {
        schema = VALIDATOR.number();
    }

    @Test
    public void noChecksTest() {
        assertTrue(schema.isValid(checkedPositiveNumber));
    }

    @Test
    public void requiredTest() {
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(checkedPositiveNumber));

    }

    @Test
    public void positiveTest() {
        schema.positive();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(checkedPositiveNumber));
        assertFalse(schema.isValid(checkedNegativeNumber));
        assertFalse(schema.isValid(0));
    }

    @Test
    public void rangeTest() {
        schema.range(5, 10);
        assertTrue(schema.isValid(checkedPositiveNumber));
        assertFalse(schema.isValid(4));


        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(11));
    }
}
