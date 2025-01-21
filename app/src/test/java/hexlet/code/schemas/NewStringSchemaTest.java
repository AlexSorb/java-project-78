package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewStringSchemaTest {

    private StringSchema schema;
    private static final String TESTING_STRING = "what does the fox say";

    private static final int LITTLE_LENGTH = 5;
    private static final int BIG_LENGTH = 100;

    @BeforeEach
    public void initialization() {
        schema = new StringSchema();
    }

    @Test
    public void isValidWithoutSchemasTest() {

        // Тест на волидность Null
        boolean resultNull = schema.isValid(null);
        assertTrue(resultNull);

        // Тест на валидность пустой строки
        boolean resultEmptyString = schema.isValid("");
        assertTrue(resultEmptyString);

        // Тест на валидность случайно строки
        boolean resultRandomString = schema.isValid("Same string");
        assertTrue(resultRandomString);
    }

    @Test
    public void requiredSchemaTest() {
        schema.required();

        // Тест на не волидность Null
        boolean resultNull = schema.isValid(null);
        assertFalse(resultNull);

        // Тест на невалидность пустой строки
        boolean resultEmptyString = schema.isValid("");
        assertFalse(resultEmptyString);

        // Тест на валидность случайно строки
        boolean resultRandomString = schema.isValid("Same string");
        assertTrue(resultRandomString);
    }

    @Test
    public void minLengthSchemaTest(){

        // Тест валидности строки при проверки минимальной длинны в 5 символов
        boolean resultFiveLength = schema.minLength(LITTLE_LENGTH).isValid(TESTING_STRING);
        assertTrue(resultFiveLength);

        // Тест на невалидность при проверки минимальной длинны в 100 символов
        boolean resultHundredLength = schema.minLength(BIG_LENGTH).isValid(TESTING_STRING);
        assertFalse(resultHundredLength);

        // Тест на "затирание" размерности при повторном указании минимальной длинны
        schema.minLength(BIG_LENGTH).minLength(LITTLE_LENGTH);
        boolean resultChangeLength = schema.isValid(TESTING_STRING);
        assertTrue(resultChangeLength);

        // Тест на невалидность Null
        boolean resultNull = schema.isValid(null);
        assertFalse(resultNull);

        // Тест на выкидывание ошибки при передачи отрицательной минимальной длинны
        var throwContains = assertThrows(IllegalArgumentException.class, () ->{
           schema.minLength(-LITTLE_LENGTH);
        });
        assertEquals("Length less than zero", throwContains.getMessage());
    }
}
