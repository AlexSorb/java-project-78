package hexlet.code.schemas;

public class NumberSchema {

    // Проверка на null
    public NumberSchema required() {
        return this;
    }

    // Проверка на положетельное число
    public NumberSchema positive() {
        return this;
    }

    public NumberSchema range(Integer left, Integer right){
        return this;
    }
    public boolean isValid(Integer number) {
        return true;
    }
}
