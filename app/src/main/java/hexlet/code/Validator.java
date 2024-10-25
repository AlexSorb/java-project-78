package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class Validator {

    // Вызов метода string() создает схему StringSchema.
    public static StringSchema string() {
        return new StringSchema();
    }
}
