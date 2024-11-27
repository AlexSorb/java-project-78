package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private boolean sizeOfFlag = false;
    private int checkedSize = 0;
    private boolean shapeFlag = false;
    private Map<?, BaseSchema> schemas;

    public MapSchema sizeof(int size) {
        sizeOfFlag = true;
        checkedSize = size;
        return this;
    }

    public void shape(Map<?, BaseSchema> schema) {
        shapeFlag = true;
        this.schemas = schema;
    }

    public boolean isValid(Map<?, ?> map) {
        var isValid = super.isValid(map);

        if(isValid && sizeOfFlag) {
            isValid = map.size() == checkedSize;
        }
        if(isValid && shapeFlag) {

            for (var key : map.keySet()) {
                var currentSchema = schemas.get(key);
                isValid = currentSchema.isValid(map.get(key));
            }
        }
        return isValid;
    }
}
