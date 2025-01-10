package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private boolean shapeFlag = false;
    private Map<?, BaseSchema> schemas;

    public MapSchema sizeof(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Размер не может быть отрицательным");
        }

        String predicateName = "sizeof";
        final int checkSize = size;
        Predicate<Map<?, ?>> predicate = data -> data.size() == checkSize;
        //super.checkList.add(predicate);
        super.namedPredicate.put(predicateName,predicate);
        return this;
    }

    public void shape(Map<?, BaseSchema> schema) {
        shapeFlag = true;
        this.schemas = schema;
    }

    public boolean isValid(Map<?, ?> map) {
        var isValid = super.isValid(map);

        if(isValid && shapeFlag) {

            for (var key : map.keySet()) {
                var currentSchema = schemas.get(key);
                isValid = currentSchema.isValid(map.get(key));
            }
        }
        return isValid;
    }
}
