package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BaseSchemaTest {
    BaseSchema<Object> baseSchema;

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
        Object testObject = "Any object";
        boolean resultTestAnyObject = baseSchema.isValid(testObject);
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
        Object testObject = "Any object";

        boolean resultTestAnyObject = baseSchema.isValid(testObject);
        assertTrue(resultTestAnyObject);
    }

}
