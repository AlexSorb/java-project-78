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

    public final void shape(Map<?, BaseSchema> schema) {
        Predicate<Map<?, ?>> predicate = (data) -> {

            if (data == null) {
                return false;
            }

            var keyDataSet = data.keySet();
            boolean isValid = true;

            for (var key : keyDataSet) {
                var currentSchema = schema.get(key);
                if (isValid) {
                    isValid = currentSchema.isValid(data.get(key));
                }
            }
            return isValid;
        };
        addCheck(SHAPE_SCHEMA_NAME, predicate);
    }

}
