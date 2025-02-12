package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberSchemaTest {
    private static final Validator VALIDATOR = new Validator();
    private NumberSchema schema;
    private static final Integer TEST_POSITIVE_NUMBER = 5;
    private static final Integer TEST_NEGATIVE_NUMBER = -5;
    private static final Integer TEST_NEGATIVE_NUMBER_IN_RANGE = -1;
    private static final Integer TEST_ZERO_NUMBER = 0;

    private static final Integer TEST_LEFT_RANGE = -4;
    private static final Integer TEST_RIGHT_RANGE = 5;

    @BeforeEach
    public final void initialization() {
        schema = VALIDATOR.number();
    }

    @Test
    public void isValidWithoutSchemasWithNullTest() {
        boolean resultNull = schema.isValid(null);
        assertTrue(resultNull);
    }

    @Test
    public void isValidWithoutSchemasWithNumberTest() {
        boolean resultPositiveNumber = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultPositiveNumber);

        boolean resultNegativeNumber = schema.isValid(TEST_NEGATIVE_NUMBER);
        assertTrue(resultNegativeNumber);
    }

    @Test
    public void requiredWithNullTest() {
        schema.required();

        boolean resultRequiredWithNull = schema.isValid(null);
        assertFalse(resultRequiredWithNull);
    }

    @Test
    public void requiredWithIntegerPositiveNumberTest() {
        schema.required();

        boolean resultRequiredWithPositiveNumber = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultRequiredWithPositiveNumber);

    }

    @Test
    public void positiveWithIntegerNegativeNumberTest() {
        schema.positive();

        boolean resultPositiveWithNegativeNumber = schema.isValid(TEST_NEGATIVE_NUMBER);
        assertFalse(resultPositiveWithNegativeNumber);
    }

    @Test
    public void positiveWithIntegerZeroNumberTest() {
        schema.positive();

        boolean resultPositiveWithZeroNumber = schema.isValid(TEST_ZERO_NUMBER);
        assertFalse(resultPositiveWithZeroNumber);
    }

    @Test
    public void positiveWithIntegerPositiveNumberTest() {
        schema.positive();

        boolean resultPositiveWithPositiveNumber = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultPositiveWithPositiveNumber);
    }

    @Test
    public void positiveWithNullTest() {
        schema.positive();

        boolean resultPositiveWithNull = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultPositiveWithNull);
    }

    @Test
    public void rangeWithNullTest() {
        schema.range(TEST_LEFT_RANGE, TEST_RIGHT_RANGE);

        boolean resultRangeWithNull = schema.isValid(null);
        assertFalse(resultRangeWithNull);
    }

    @Test
    public void rangeWithNumberInRangeTest() {
        schema.range(TEST_LEFT_RANGE, TEST_RIGHT_RANGE);

        boolean resultRangeWithNumberInRange = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultRangeWithNumberInRange);
    }

    @Test
    public void rangeWithNumberOutOfRangeTest() {
        schema.range(TEST_LEFT_RANGE, TEST_RIGHT_RANGE);

        boolean resultRangeWithMaxValue = schema.isValid(Integer.MAX_VALUE);
        assertFalse(resultRangeWithMaxValue);

        boolean resultRangeWithMinValue = schema.isValid(Integer.MIN_VALUE);
        assertFalse(resultRangeWithMinValue);
    }

    @Test
    public void rangeWithNumberBordNullTest() {
        var throwContains = assertThrows(IllegalArgumentException.class, () -> schema.range(null, TEST_RIGHT_RANGE));
        assertEquals("Граница диапазона не может быть null", throwContains.getMessage());

        throwContains = assertThrows(IllegalArgumentException.class, () -> schema.range(TEST_LEFT_RANGE, null));
        assertEquals("Граница диапазона не может быть null", throwContains.getMessage());

        throwContains = assertThrows(IllegalArgumentException.class, () -> schema.range(null, null));
        assertEquals("Граница диапазона не может быть null", throwContains.getMessage());
    }

    @Test
    public void interactionFullSchemasTest() {
        schema.positive().range(Integer.MIN_VALUE, Integer.MAX_VALUE).range(TEST_LEFT_RANGE, TEST_RIGHT_RANGE);
        boolean resultFullTest = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultFullTest);
    }

    @Test
    public void interactionSchemasRequiredAndPositive() {
        schema.positive().required();

        boolean resultWithNull = schema.isValid(null);
        assertFalse(resultWithNull);

        boolean resultWithZeroNumber = schema.isValid(TEST_ZERO_NUMBER);
        assertFalse(resultWithZeroNumber);

        boolean resultWithNegativeNumber = schema.isValid(TEST_NEGATIVE_NUMBER);
        assertFalse(resultWithNegativeNumber);

        boolean resultWithPositiveNumber = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultWithPositiveNumber);
    }

    @Test
    public void interactionSchemasRequiredAndRange() {
        schema.range(TEST_LEFT_RANGE, TEST_RIGHT_RANGE).required();

        boolean resultWithNull = schema.isValid(null);
        assertFalse(resultWithNull);

        boolean resultWithNoRangeNumber = schema.isValid(Integer.MAX_VALUE);
        assertFalse(resultWithNoRangeNumber);

        boolean resultWithInRangeNumber = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultWithInRangeNumber);
    }

    @Test
    public void interactionSchemasPositiveAndRange() {
        schema.positive().range(TEST_LEFT_RANGE, TEST_RIGHT_RANGE);

        boolean resultWithNegativeNumberOutOfRange = schema.isValid(TEST_NEGATIVE_NUMBER);
        assertFalse(resultWithNegativeNumberOutOfRange);

        boolean resultWithNegativeNumberInRange = schema.isValid(TEST_NEGATIVE_NUMBER_IN_RANGE);
        assertFalse(resultWithNegativeNumberInRange);

        boolean resultWithPositiveNumberInRange = schema.isValid(TEST_POSITIVE_NUMBER);
        assertTrue(resultWithPositiveNumberInRange);
    }
}
