package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class ShapeFunctionTest {
    private final static Validator VALIDATOR = new Validator();
    private MapSchema schema;
    private HashMap<String, BaseSchema> schemas = new HashMap<>();

    private final static Map<String,String> TEST_MAP_VALID = Map.of(
            "firstName", "John",
            "lastName", "Smith"
    );
    private final static Map<String, String> TEST_MAP_NO_VALID_EMPTY = Map.of(
            "firstName", "John",
            "lastName", null
    );

    private final static Map<String, String> TEST_MAP_NO_VALID_SHORT_SIZE = Map.of(
            "firstName", "Anna",
            "lastName", "B"
    );

    @BeforeEach
    public void initialization() {
        schema = VALIDATOR.map();
        schemas.put("firstName", VALIDATOR.string().required());
        schemas.put("lastName", VALIDATOR.string().required().minLength(2));
        schema.shape(schemas);
    }


    @Test
    public void shapeFunctionWithNull() {
        boolean resultWithEmpty = schema.isValid(TEST_MAP_NO_VALID_EMPTY);
        assertFalse(resultWithEmpty);
    }

    @Test
    public void shapeFunctionWithValidMap() {
        boolean resultWithValidMap = schema.isValid(TEST_MAP_VALID);
        assertTrue(resultWithValidMap);
    }

    @Test
    public void shapeFunctionWithShortSize() {
        boolean resultWithShortSize = schema.isValid(TEST_MAP_NO_VALID_SHORT_SIZE);
        assertFalse(resultWithShortSize);
    }

}
