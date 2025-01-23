package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NumberSchemaTest {
    private final static Validator VALIDATOR = new Validator();
    private NumberSchema schema;
    private final static Integer TEST_POSITIVE_NUMBER = 5;
    private final static Integer TEST_NEGATIVE_NUMBER = -5;
    private final static Integer TEST_ZERO_NUMBER = 0;

    private final static Integer TEST_LEFT_RANGE = -4;
    private final static Integer TEST_RIGHT_RANGE = 5;

    @BeforeEach
    public void initialization() {
        schema = VALIDATOR.number();
    }

    @Test
    public void requiredTest() {
        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(TEST_POSITIVE_NUMBER));

    }

    @Test
    public void positiveTest() {
        schema.positive();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(TEST_POSITIVE_NUMBER));
        assertFalse(schema.isValid(TEST_NEGATIVE_NUMBER));
        assertFalse(schema.isValid(0));
    }

    @Test
    public void rangeTest() {
        schema.range(5, 10);
        assertTrue(schema.isValid(TEST_POSITIVE_NUMBER));
        assertFalse(schema.isValid(4));


        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(11));
    }


    // NEW TEST

    // IS_VALID testing
    @Test
    public void isValidWithoutSchemasWithNull() {
        boolean resultNull = schema.isValid(null);
        assertTrue(resultNull);
    }

    @Test
    public void isValidWithoutSchemasWithNumber() {
        boolean resultPositiveNumber = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultPositiveNumber);

        boolean resultNegativeNumber = schema.isValid(TEST_NEGATIVE_NUMBER);
        assertTrue(resultNegativeNumber);
    }

    // required testing
    @Test
    public void requiredWithNullTest() {
        schema.required();

        boolean resultRequiredWithNull = schema.isValid(null);
        assertFalse(resultRequiredWithNull);
    }

    @Test
    public void requiredWithIntegerPositiveNumber() {
        schema.required();

        boolean resultRequiredWithPositiveNumber = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultRequiredWithPositiveNumber);

    }

    // positive TESTING
    @Test
    public void positiveWithIntegerNegativeNumber() {
        schema.positive();

        boolean resultPositiveWithNegativeNumber = schema.isValid(TEST_NEGATIVE_NUMBER);
        assertFalse(resultPositiveWithNegativeNumber);
    }

    @Test
    public void positiveWithIntegerZeroNumber() {
        schema.positive();

        boolean resultPositiveWithZeroNumber = schema.isValid(TEST_ZERO_NUMBER);
        assertFalse(resultPositiveWithZeroNumber);
    }

    @Test
    public void positiveWithIntegerPositiveNumber() {
        schema.positive();

        boolean resultPositiveWithPositiveNumber = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultPositiveWithPositiveNumber);
    }

    @Test
    public void positiveWithNull() {
        schema.positive();

        boolean resultPositiveWithNull = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultPositiveWithNull);
    }


    // TEST RANGE
    @Test
    public void rangeWithNullTest() {
        schema.range(TEST_LEFT_RANGE, TEST_RIGHT_RANGE);

        boolean resultRangeWithNull = schema.isValid(null);
        assertFalse(resultRangeWithNull);
    }

    @Test
    public void rangeWithNumberInRange() {
        schema.range(TEST_LEFT_RANGE, TEST_RIGHT_RANGE);

        boolean resultRangeWithNumberInRange = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultRangeWithNumberInRange);
    }
}
