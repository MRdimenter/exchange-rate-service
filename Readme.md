# Exchange rate service

Тестовый проект-сервис курса валют, который умеет отображать gif-изображение, в зависимости, от курса USD по отношению к выбранной валюте. Сравнение происходит по вчерашнему и сегодняшнему дню.
____
## GET-запросы 

Главная страница приложения
```
http://localhost:8080/
```
Получение гиф-изображения.
Полный список названия валют: https://docs.openexchangerates.org/docs/supported-currencies
```
http://localhost:8080/currency/gif/{currency names}
```


____
## Стек технологий
- Java 11
- Spring Boot 2
- Feign
- Thymeleaf
- Lombok 
- Docker
____
## Установка
### C помощью Gradle
Необходимо выполнить команды: 
```
clean
```
```
build
```
И запустить *jar-файл* в папке ___build/libs___

### C помощью Docker 
Необходимо выполнить команды:
```
docker pull 89033823869/exchange-rate-service:latest
```

```
docker run -d -p 8080:8080 -t 89033823869/exchange-rate-service:latest
```
