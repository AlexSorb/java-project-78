package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private final String SIZEOF_NAME = "sizeof";

    private Map<?, BaseSchema> schemas;
    private boolean isValid = true;

    public MapSchema sizeof(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Размер не может быть отрицательным");
        }

        final int checkSize = size;
        Predicate<Map<?, ?>> predicate = data -> data.size() == checkSize;
        super.namedPredicate.put(SIZEOF_NAME, predicate);
        return this;
    }

    public void shape(Map<?, BaseSchema> schema) {
        this.schemas = schema;
    }

//    @Override
//    public boolean isValid(Map<?, ?> data) {
//
//        if (schemas != null && isValid) {
//            for (var key : data.keySet()) {
//                var currentSchema = schemas.get(key);
//                isValid = currentSchema.isValid(data.get(key));
//            }
//        } else if (schemas == null && isValid) {
//
//        }
//
//        boolean result = isValid;
//        isValid = true;
//        return result;
//    }
}
