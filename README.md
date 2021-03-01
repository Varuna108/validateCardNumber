# Валидация номера карты с использованием алгоритма Луна

**Скрипт отправляет get запрос к [тестовым данным](https://api.randomdatatools.ru/?count=50&params=bankCard) с использованием библиотеки rest assured, где автоматически генерируются номера банковских карт. Может быть сгенерировано одновременно от 1 до 100 значений, формат страницы html**  

```[{"bankCard":"4528 5874 5015 2448"},{"bankCard":"4466 3667 2427 4208"},{"bankCard":"5209 7331 5573 6463"}]```

**Номера карт добавляются в список:**  
`List of all cards: [5660479896337804, 5704512829309873, 4443326841984023]`

**Список всех полученных карт прогоняется методом, где реализован алгоритм Луна. Возвращаются номера карт с указанием индекса корректного номера карты в общем списке:**  
`Valid Card: 5704512829309873, index in the list: 1`  
`Valid Card: 5913623422888184, index in the list: 11`  
`Valid Card: 4490410229752563, index in the list: 17`  

**Номера карт прошедших проверку алгоритмом редактируются. Номера обрезаются до первых 8 символов:**  
```4969595159865948 -> 49695951```

**Таким образом формируется список БИНов (Банковский Идентификационный Номер):**  
```[59415562, 49125149, 54427518, 56775988, 58609173]```

**Данные БИНы используются для идентификации банка, платежной системы, валюты и т.д. Чтобы получить данную информацию, сформированный список БИНов прогоняется через открытое АПИ предоставляющее всю интересующую информацию по БИНу. Запрос реализован методом get с использованием библиотеки rest assured. Пример ответа:**  

```
Bin bank: 46397285

{
    "number": {
        "length": 16,
        "luhn": false
    },
    "scheme": "visa",
    "type": "debit",
    "brand": "Traditional",
    "prepaid": false,
    "country": {
        "numeric": "840",
        "alpha2": "US",
        "name": "United States of America",
        "emoji": "🇺🇸",
        "currency": "USD",
        "latitude": 38,
        "longitude": -97
    },
    "bank": {
        "name": "WASHINGTON POSTAL EMPLOYEES F.C.U.",
        "url": "www.wpefcu.com",
        "phone": "202-636-4530"
    }
}
```


