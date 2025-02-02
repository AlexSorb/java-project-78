package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private final static String SIZE_OF_SCHEMA_NAME = "sizeof";
    private final static String SHAPE_SCHEMA_NAME = "shape";


    public MapSchema sizeof(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Размер не может быть отрицательным");
        }

        final int checkSize = size;
        Predicate<Map<?, ?>> predicate = data -> data.size() == checkSize;
        super.namedPredicate.put(SIZE_OF_SCHEMA_NAME, predicate);
        return this;
    }

    public void shape(Map<?, BaseSchema> schema) {
        Predicate<Map<?, ?>> predicate = (data) -> {
            var keyDataSet = data.keySet();
            boolean isValis = true;

            for (var key : keyDataSet) {
                var currentSchema = schema.get(key);
                if (isValis == true) {
                    isValis = currentSchema.isValid(data.get(key));
                }
            }
                return isValis;
        };

        super.namedPredicate.put(SHAPE_SCHEMA_NAME, predicate);
    }

    @Override
    public boolean isValid(Map<?, ?> data) {

        return super.isValid(data);
    }
}
