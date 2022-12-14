# Сервис перевода с карты на карту
## Описание

Проект предоставляет сервис для перевода денег с карты на карту.

Приложение написано с использованием Spring Boot и Hibernate.

Для запуска приложения используется Docker

Данные о картах и операциях хранятся в базе данных

Используется логирование с использованием log4j.

## Фронт приложение

UI для перевода денег с карты на карту: https://github.com/serp-ya/card-transfer

Развернутое демо: https://serp-ya.github.io/card-transfer/

## Backend приложение

Для запуска приложения необходимо склонировать репозиторий, открыть корневую папку проекта и в консоли выполнить команду ./mvnw clean package.
Затем запустить сервис с помощью команды docker-compose up.



## Настройка и тестовые данные

В файле `src/main/resources/application.properties` находятся следующие настройки и их значения:

`server.port = 5500` - порт приложения

`verification.test.value = 0000` - код верификации операции

`commission.pct = 1` - комиссионный процент

`rounding.scale = 2` - шкала округления

`rounding.mode = HALF_UP` - режим округления

В классе `src/main/java/ru/durov/moneytransferservice/AppRunner.java` на старте приложения создаются 3 карты и баковские счета к ним
- 1111111111111111 "01/28" "017"
- 2222222222222222 "02/28" "018"
- 3333333333333333 "03/28" "019"
