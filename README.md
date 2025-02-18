### Hexlet tests and linter status:
[![Actions Status](https://github.com/AlexSorb/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/AlexSorb/java-project-78/actions)

# Validator
[![Validator-CI](https://github.com/AlexSorb/java-project-78/actions/workflows/validator-CI.yml/badge.svg)](https://github.com/AlexSorb/java-project-78/actions/workflows/validator-CI.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/8d20511ba2a3768047f9/maintainability)](https://codeclimate.com/github/AlexSorb/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/8d20511ba2a3768047f9/test_coverage)](https://codeclimate.com/github/AlexSorb/java-project-78/test_coverage)

## Описание Проекта
<p>Этот проект на Java предназначен для валидации объектов типов Integer, Map и String.
Он предоставляет набор инструментов и методов для проверки корректности данных,
что позволяет разработчикам легко интегрировать валидацию в свои проекты. Проект использует Java 21 и инструмент сборки Gradle.
</p>

## Как работает

### Validator
<p>
    Класс Validator является основным компонентом, который возвращает конктретный валидатор в зависимости от типа данных.  
</p>

- string() - возвращает настраиваемую схему валидации для объектов типа String.
- number() - возвращает настраиваемую схему валидации для объектов типа Integer.
- map() - возвращает настраиваемую схему валидации для объектов типа Map. 

#### Пример использования 

```java
import hexlet.code.Validator;

public class Example {
    public static void main(String[] args) {

         Validator validator = new Validator();

        // Получаем настраиваемую схему для валидации строк
        var stringSchema = validator.string();

        // Получаем настраиваемую схему для валидации числовых значений
        var numberSchema = validator.number();

        // Получаем настраиваемую схему для валидации объектов Map
        var mapSchema = validator.map();
    }
}
```

## Настрока схемы валидации

### StringSchema

<p>
    Эта схема отвеячает за валидацию объектов типа String. <br> Схема валидации строк имеет несколько возможных настроек:
</p>

* required( ) - проверка на Null и на пустую строку.
* minLength(int size) - устанавливат минимальный размер строки.
* contains(String text) - проверяет на вхождение строки text в тестируемаю строку.


#### Пример использования
```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

public class Example {
    public static void main(String[] args) {

        Validator validator = new Validator();
        StringSchema stringSchema = validator.string();

        // Проверка на null и на путую строку 
        stringSchema.required();

        // Устанавливает ограничение на размер строки
        stringSchema.minLength(4);

        // Устанавливает ограничение на вхождение строки "Hello"
        stringSchema.contains("Hello");
    }
}
```


### NumberSchema
<p>
    Эта схема отвеячает за валидацию объектов типа Integer.<br> 
    Схема валидации чисел имеет несколько возможных настроек:
</p>

* required() - проверка на null.
* range(Integer left, Integer right) - проверка на вхождение проверяемого числа в диаппазон [left, right].
* positive() - проверяет является ли число положительным. 

#### Пример использования 

```java
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

public class Example {
    public static void main(String[] args) {

        Validator validator = new Validator();
        NumberSchema numberSchema = validator.number();

        // Проверка на null
        numberSchema.required();

        // Устанавливает диаппазон ограничения
        numberSchema.range(int left, int right);

        // Проверяет является ли число положительным
        numberSchema.positive();
    }
}

```

### MapSchema
<p>
    Эта схема отвеячает за валидацию объектов типа Map.
    Схема валидации объектов Map имеет несколько возможных настроек:
</p>

* required() - проверка на Null
* sizeof(int size) - проверка соответстве размера Map размеру size

#### Пример использования 
  
```java

import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator validator = new Validator();

MapSchema mapSchema = new MapSchema();

public class Example {
    public static void main(String[] args) {

        Validator validator = new Validator();
        MapSchema mapSchema = new MapSchema();

        // Проверка на null
        mapSchema.required();

        // Проверка соответствия размера
        mapSchema.sizeof(2);
    }
}
```

## Проверка валидности
