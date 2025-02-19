package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private static final String SIZE_OF_SCHEMA_NAME = "sizeof";
    private static final String SHAPE_SCHEMA_NAME = "shape";


    public final MapSchema sizeof(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Размер не может быть отрицательным");
        }

        final int checkSize = size;
        Predicate<Map<?, ?>> predicate = data -> {
            if (data == null) {
                return false;
            }
            return data.size() == checkSize;
        };
        addCheck(SIZE_OF_SCHEMA_NAME, predicate);
        return this;
    }

    public final <T> void shape(Map<?, BaseSchema<T>> schema) {

        Predicate<Map<?, ?>> predicate = data -> {
            if (data == null) {
                return false;
            }

            return schema.entrySet().stream()
                    .allMatch(baseSchemaEntry -> {
                        var currentKey = baseSchemaEntry.getKey();
                        var currentPredicate = baseSchemaEntry.getValue();

                        return currentPredicate.isValid((T) data.get(currentKey));
                    });

        };

        addCheck(SHAPE_SCHEMA_NAME, predicate);
    }

}
