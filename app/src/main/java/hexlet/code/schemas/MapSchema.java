package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<? extends Object, ? extends Object>> {
    private boolean sizeOfFlag = false;
    private int checkedSize = 0;
    private boolean shapeFlag = false;
    private Map<? extends Object, BaseSchema> schemas;

    public MapSchema sizeof(int size) {
        sizeOfFlag = true;
        checkedSize = size;
        return this;
    }

    public boolean isValid(Map<? extends Object, ? extends Object> map) {
        var isValid = super.isValid(map);

        if(sizeOfFlag) {
            var mapSize = map.size();
            isValid = mapSize == checkedSize;
        }
        if(shapeFlag) {
            var mapKeys = map.keySet();
            for (var key : mapKeys) {
                var currentSchema = schemas.get(key);
                isValid = currentSchema.isValid(map.get(key));
            }
        }
        return isValid;
    }

    public void shape(Map<? extends Object, BaseSchema> schema) {
        shapeFlag = true;
        this.schemas = schema;
    }
}
