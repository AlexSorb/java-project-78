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
    public void isValidWithoutSchemasTest() {

        // Проверка на валидность Null
        boolean resultNull = baseSchema.isValid(null);
        assertTrue(resultNull);

        // Проверка на валиднорсть случайного значения
        Object testObject = "Any object";
        boolean resultTestAnyObject = baseSchema.isValid(testObject);
        assertTrue(resultTestAnyObject);
    }

    @Test
    public void requiredSchemaTest() {
        baseSchema.required();

        //Проверка на не валидность null
        boolean resultNull = baseSchema.isValid(null);
        assertFalse(resultNull);

        // Проверка на валиднорсть случайного значения
        Object testObject = "Any object";
        boolean resultTestAnyObject = baseSchema.isValid(testObject);
        assertTrue(resultTestAnyObject);

    }
}
