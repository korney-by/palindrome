## Палиндром

#### Палиндром - это cлово или фраза одинаково читающееся в обоих направлениях.

### Примеры:
    "топот"
	"а роза упала на лапу Азора"

### Задача
Нужно спроектировать и написать логическое ядро (набор взаимосвязанных
компонентов) игры, которая бы принимала на вход слово/фразу, проверяла является ли
эта фраза/слово палиндромом и если да, то начисляла бы пользователю очки в
зависимости от длинны фразы/слова (+1 за каждый символ).

- Так же у игры должна быть "доска лидеров", на которой бы хранились 5 пользователей с наибольшим количеством очков.
- Игра не должна проверять слово/фразу на грамотность и на существование
  таких слов в реальности. 
- Нужно написать только ядро такой игры, не нужно думать о UI
  или о том, как слова/фразы попадают в систему из внешнего мира, а только базовые
  классы/интерфейсы обеспечивающие описанный функционал.
- Игра хранит свое состояние в памяти.
- Игра длится бесконечно и у нее нет очередности, но один и тот же пользователь не
  должен получать очки за фразу, которую он уже использовал.
- При проектировании стоит предусмотреть дальнейшее расширение (например
  подключение какой-нибудь базы данных).

### Дополнительные требования
Код должен быть написан на Kotlin без использования каких либо
фрэймворков (кроме maven и junit). Это должен быть maven/gradle проект.
Результат предоставить на GitHub.
Успехов!
