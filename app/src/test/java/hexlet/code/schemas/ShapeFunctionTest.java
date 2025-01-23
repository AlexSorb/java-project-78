package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class ShapeFunctionTest {
    private Validator validator = new Validator();
    private MapSchema schema;
    private HashMap<String, BaseSchema> schemas = new HashMap<>();

    @BeforeEach
    public void initialization() {
        schema = validator.map();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));
    }

    @Test
    public void shapeTest() {
        schema.shape(schemas);

//        Map<String, String> human1 = new HashMap<>();
//        human1.put("firstName", "John");
//        human1.put("lastName", "Smith");
//        assertTrue(schema.isValid(human1));
//
//        Map<String, String> human2 = new HashMap<>();
//        human2.put("firstName", "John");
//        human2.put("lastName", null);
//        assertFalse(schema.isValid(human2));
//
//        Map<String, String> human3 = new HashMap<>();
//        human3.put("firstName", "Anna");
//        human3.put("lastName", "B");
//        assertFalse(schema.isValid(human3)); // false
    }
}
