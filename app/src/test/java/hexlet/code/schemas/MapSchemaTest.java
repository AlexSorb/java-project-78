package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;

public class MapSchemaTest {
    private final static Validator VALIDATOR = new Validator();
    private MapSchema schema;
    private final static Map<Integer, String> TESTING_MAP = Map.of(
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

    @BeforeEach
    public void initialization() {
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
    public void requiredSchemaWithNullTest(){
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
        schema.sizeof(2);
        boolean resultLittleSeize = schema.isValid(TESTING_MAP);
        assertFalse(resultLittleSeize);
    }

    @Test
    public void sizeOfSchemaWithCurrentSize() {
        schema.sizeof(TESTING_MAP.size());
        boolean resultCurrentSeize = schema.isValid(TESTING_MAP);
        assertTrue(resultCurrentSeize);
    }
}
