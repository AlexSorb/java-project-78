package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BaseSchemaTest {
    private BaseSchema<Object> baseSchema;

    private final static Object TEST_OBJECT = "Any object";

    @BeforeEach
    public void initialization() {
        baseSchema = new BaseSchema<>();
    }

    @Test
    public void isValidWithoutSchemasWithNullTest() {
        boolean resultNull = baseSchema.isValid(null);
        assertTrue(resultNull);
    }

    @Test
    public void isValidWithoutSchemasWithObjectTest() {
        boolean resultTestAnyObject = baseSchema.isValid(TEST_OBJECT);
        assertTrue(resultTestAnyObject);
    }

    @Test
    public void requiredSchemaWithNullTest() {
        baseSchema.required();
        boolean resultNull = baseSchema.isValid(null);
        assertFalse(resultNull);
    }

    @Test
    public void requiredSchemaWithObjectTest() {
        baseSchema.required();
        boolean resultTestAnyObject = baseSchema.isValid(TEST_OBJECT);
        assertTrue(resultTestAnyObject);
    }

}
