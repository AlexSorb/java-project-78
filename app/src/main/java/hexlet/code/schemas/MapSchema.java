package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<? extends Object, ? extends Object>> {
    private boolean sizeOfFlag = false;
    private int checkedSize = 0;
    private boolean shapeFlag = false;

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
        return isValid;
    }

    public void shape(Map<? extends Object, BaseSchema<String>> schema) {
        shapeFlag = true;
    }
}
