### Hexlet tests and linter status:
[![Actions Status](https://github.com/AlexSorb/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/AlexSorb/java-project-78/actions)

# Validator
[![Vakudator-CI](https://github.com/AlexSorb/java-project-78/actions/workflows/validator-CI.yml/badge.svg)](https://github.com/AlexSorb/java-project-78/actions/workflows/validator-CI.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/8d20511ba2a3768047f9/maintainability)](https://codeclimate.com/github/AlexSorb/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/8d20511ba2a3768047f9/test_coverage)](https://codeclimate.com/github/AlexSorb/java-project-78/test_coverage)

## Описание
Этот проект на Java предназначен для валидации объектов типов Integer, Map и String.
Он предоставляет набор инструментов и методов для проверки корректности данных,
что позволяет разработчикам легко интегрировать валидацию в свои приложения.

## Как работает

### Validator
Основной обыект для получения схемы валидации для конкретного типа данных.

```java
    import hexlet.code.Validator;

    Validator validator = new Validator();

    // Получаем настраиваемую схему для валидации строк
    var stringSchema = validator.string();

    // Получаем настраиваемую схему для валидации числовых значений
    var numberSchema = validator.number();

    // Получаем настраиваемую схему для валидации мапы
    var mapSchema = validator.map();
```

## Настрока схемы валидации

### Валидация String
Схема валидации строк имеет несколько возможных настроек:
required - проверка на Null и на пустую строку
minLength - устанавливат минимальный размер строки
contains - проверяет на вхождение строки в тестируемаю строку 
```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator validator = new Validator();
StringSchema stringSchema = validator.string();

// Проверка на null и на путую строку 
stringSchema.required();

// Устанавливает ограничение на размер строки
stringSchema.minLength(4);

// Устанавливает ограничение на вхождение строки "Hello"
stringSchema.contains("Hello");
```

### Валидция Integer

```java
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

Validator validator = new Validator();
NumberSchema numberSchema = validator.number();

```
### Валидация Map

```java

import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator validator = new Validator();

MapSchema mapSchema = new MapSchema();

```
