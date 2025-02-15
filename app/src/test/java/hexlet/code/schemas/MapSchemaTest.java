package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class MapSchemaTest {
    private static final Validator VALIDATOR = new Validator();
    private MapSchema schema;
    private static final Map<Integer, String> TESTING_MAP = Map.of(
            0, "value0",
            1, "value1",
            2, "value2",
            3, "value3",
            4, "value4",
            5, "value5",
            6, "value6",
            7, "value7",
            8, "value8",
            9, "value9"
    );

    private static final int LITTLE_SIZE_TEST_NUMBER = 2;
    private static final int NEGATIVE_SIZE_TEST_NUMBER = -1;

    @BeforeEach
    public final void initialization() {
        schema = VALIDATOR.map();
    }

    @Test
    public void isValidWithoutSchemaWithNullTest() {
        boolean resultNull = schema.isValid(null);
        assertTrue(resultNull);
    }

    @Test
    public void isValidWithoutSchemaWithMapTest() {
        boolean resultMap = schema.isValid(TESTING_MAP);
        assertTrue(resultMap);
    }

    @Test
    public void requiredSchemaWithNullTest() {
        schema.required();
        boolean resultRequiredNull = schema.isValid(null);
        assertFalse(resultRequiredNull);
    }

    @Test
    public void requiredSchemaWithMapTest() {
        schema.required();
        boolean resultRequiredMap = schema.isValid(TESTING_MAP);
        assertTrue(resultRequiredMap);
    }


    @Test
    public void sizeOfSchemaWithLittleSize() {
        schema.sizeof(LITTLE_SIZE_TEST_NUMBER);
        boolean resultLittleSeize = schema.isValid(TESTING_MAP);
        assertFalse(resultLittleSeize);
    }

    @Test
    public void sizeOfSchemaWithCurrentSize() {
        schema.sizeof(TESTING_MAP.size());
        boolean resultCurrentSeize = schema.isValid(TESTING_MAP);
        assertTrue(resultCurrentSeize);
    }

    @Test
    public void sizeOfSchemaWithNegativeSize() {
        var throwContent = assertThrows(IllegalArgumentException.class, () -> schema.sizeof(NEGATIVE_SIZE_TEST_NUMBER));
        assertEquals("Размер не может быть отрицательным", throwContent.getMessage());
    }

    @Test
    public void interactionFullSchemasTest() {
        schema.sizeof(TESTING_MAP.size()).required();

        boolean resultWithNull = schema.isValid(null);
        assertFalse(resultWithNull);

        boolean resultWithEmptyMap = schema.isValid(new HashMap<>());
        assertFalse(resultWithEmptyMap);

        boolean resultWithTestMap = schema.isValid(TESTING_MAP);
        assertTrue(resultWithTestMap);
    }






}
