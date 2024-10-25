### Hexlet tests and linter status:
[![Actions Status](https://github.com/AlexSorb/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/AlexSorb/java-project-78/actions)

# Validator
[![Vakudator-CI](https://github.com/AlexSorb/java-project-78/actions/workflows/validator-CI.yml/badge.svg)](https://github.com/AlexSorb/java-project-78/actions/workflows/validator-CI.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/8d20511ba2a3768047f9/maintainability)](https://codeclimate.com/github/AlexSorb/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/8d20511ba2a3768047f9/test_coverage)](https://codeclimate.com/github/AlexSorb/java-project-78/test_coverage)

## Описание
Валидатор данных – библиотека, с помощью которой можно проверять корректность любых данных. 


### Реализация 

Возможно будет применен паттерн Decorator 


## Тесты

### Тест StringSchema
Создать класс StringSchemaTest

#### Тестирование required()
Проверить работу при строке Null до использования и после использования

#### Тестирование minLength()
Проверить работу добовля обычную строку и стандартную строку

Проверить работу затирания при котором добавлении двух minLength()
затирается все кроме последний

Продумать работу при добавлении в minLength отрицательного числа

Продумать работу при Nullевой функциональной строке и minLength

### Тестирование contains() 
Проверить проверить полную функциональную работу 

Продумать работу при NUll искомой строки 

Продумать работу при NULL функциональной строки 