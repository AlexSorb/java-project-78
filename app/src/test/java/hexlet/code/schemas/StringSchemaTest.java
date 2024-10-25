package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {

    @Test
    public void requiredTest() {
        var v = new Validator();
        var schema = v.string();

        String anotherString  = "Same String";

        assertTrue(schema.isValid(anotherString));
        assertTrue(schema.isValid(null));

        schema.required();
        assertTrue(schema.isValid(anotherString));
        assertFalse(schema.isValid(null));

    }

    @Test
    public void minLengthTest() {
        var v = new Validator();
        var schema = v.string();
        String anotherString  = "Same String";

        // Проверить работу добовля обычную строку и стандартную строку
        schema.minLength(10);
        assertTrue(schema.isValid(anotherString));

        schema.minLength(100);
        assertFalse(schema.isValid(anotherString));

        // Проверить работу затирания при котором добавлении двух minLength() затирается все кроме последний
        schema.minLength(100).minLength(5).minLength(10);
        assertTrue(schema.isValid(anotherString));

        // Продумать работу при добавлении в minLength отрицательного числа
        schema.minLength(-100);
        assertTrue(schema.isValid(anotherString));

        // Продумать работу при Nullевой функциональной строке и minLength
        schema.minLength(5);
        assertFalse(schema.isValid(null));
    }

    @Test
    public void containsTest() {
        var v = new Validator();
        var schema = v.string();
        String anotherString  = "Same String";

        // Проверить проверить полную функциональную работу
        schema.contains("Same");
        assertTrue(schema.isValid(anotherString));
        assertFalse(schema.isValid("String"));


        //Продумать работу при NUll искомой строки


        // Продумать работу при NULL функциональной строки

    }
}
