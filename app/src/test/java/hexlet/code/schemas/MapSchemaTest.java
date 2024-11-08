package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashMap;
import java.util.Map;

public class MapSchemaTest {
    Validator validator = new Validator();
    MapSchema schema;

    @BeforeEach
    public void initialization() {
        schema = validator.map();
    }


    @Test
    public void requiredTest() {
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        var data = new HashMap<String,String>();
        data.put("anyKey", "anyValue");

        assertTrue(schema.isValid(data));
    }

    @Test
    public void sizeOfTest() {
        schema.sizeof(2);
        var data = new HashMap<Integer, String>();
        data.put(1, "one");
        assertFalse(schema.isValid(data));
        data.put(2, "two");
        assertTrue(schema.isValid(data));
    }
}
