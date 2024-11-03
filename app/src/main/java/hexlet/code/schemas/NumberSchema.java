package hexlet.code.schemas;

public class NumberSchema {
    private boolean state = true;

    private boolean flagRequired = false;
    private boolean flagPositive = false;
    private boolean flagRange = false;
    private Integer left;
    private Integer right;

    // Проверка на null
    public NumberSchema required() {
        flagRequired = true;
        return this;
    }

    // Проверка на положетельное число
    public NumberSchema positive() {
        flagPositive = true;
        return this;
    }

    public NumberSchema range(Integer left, Integer right){
        flagRange = true;
        this.left = left;
        this.right = right;
        return this;
    }
    public boolean isValid(Integer number) {
        if (flagRequired) {
            if (state == true) {
                state = number!= null;
            }
        }

        if (flagPositive) {
            if (state == true) {
                state = number > 0;
            }
        }

        if (flagRange) {
            if (state == true) {
                state = number >= left && number <= right;
            }
        }

        return state;
    }
}
