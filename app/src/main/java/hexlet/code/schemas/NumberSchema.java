package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer>{
    private boolean flagPositive = false;
    private boolean flagRange = false;
    private Integer left;
    private Integer right;

    // Проверка на null
    public NumberSchema required() {
        super.required();
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
        boolean isValid = super.isValid(number);

        if (flagPositive) {
            isValid = number > 0;
        }

        if (flagRange) {
            isValid = number >= left && number <= right;
        }

        return isValid;
    }
}
