package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<? extends Object, ? extends Object>> {
    boolean sizeOfFlag = false;
    int checkedSize = 0;
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
}
