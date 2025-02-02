package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class ShapeFunctionTest {
    private static final Validator VALIDATOR = new Validator();
    private MapSchema schema;
    private final HashMap<String, BaseSchema> schemas = new HashMap<>();

    private static final Map<String, Object> TEST_MAP_VALID = Map.of(
            "firstName", "John",
            "lastName", "Smith");
    private final   Map<String, Object> testMapNoValidEmpty = new HashMap<>();

    private static final Map<String, Object> TEST_MAP_NO_VALID_SHORT_SIZE = Map.of(
            "firstName", "Anna",
            "lastName", "B"
    );

    @BeforeEach
    public final void initialization() {
        schema = VALIDATOR.map();
        schemas.put("firstName", VALIDATOR.string().required());
        schemas.put("lastName", VALIDATOR.string().required().minLength(2));
        schema.shape(schemas);

        testMapNoValidEmpty.put("firstName", "John");
        testMapNoValidEmpty.put("lastName", null);
    }


    @Test
    public void shapeFunctionWithNull() {
        boolean resultWithEmpty = schema.isValid(testMapNoValidEmpty);
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
