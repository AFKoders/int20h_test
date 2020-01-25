### Музичний Акінатор by AFKoders
**Умови:** <br/>
Користувач загадує пісню. Він вводить рядок з тексту, а застосунок має вгадати цю пісню та дати
прослухати. Користувач надає відповідь, чи було вгадано трек:<br/>Якщо так - перемагає застосунок,
якщо ні - користувач.<br/><br/>

**Пункти, що обов’язкові для виконання:**
- [x] Реалізований один з перелічених методів пошуку треку на вибір
- [x] Вивід знайденого треку
- [x] Можливість прослухати трек у додатку
- [x] Код розмістити у відкритому репозиторії на github або подібному сховищі. У файлі readme
додати інструкцію як запустити проект (завдання без опису перевірятись не буде)
- [x] Заповнити гугл-форму для здачі роботи до 25 січня 23:59

**Додаткові пункти для реалізації:**
- [x] Реалізація форми гри
    - [x] відповідь на спробу вгадати пісню користувача: вгадана або ні
    - [x] повтор спроб на вгадування пісні до 5-ти разів
    - [x] вивід результату гри (переможця та правильну відповідь)
- [x] наявність .apk файлу в репозиторії

<br/>**API яке використовувалось для завдання:**
- [Audd](https://audd.io/)
- [Deezer](https://developers.deezer.com/api)

<br/>**Дизайн для застосунку:**<br/>
Весь дизайн доступний за [посиланням](https://www.figma.com/file/7qDWZzYt28soERMdjTk67V/Who%E2%80%99s-singing%3F) у Figma.

<p align="center">
	<kbd>
 		<img src="https://github.com/AFKoders/int20h_test/blob/readme/assets/preview.gif" alt="preview gif" width="300" height="570"/>
	</kbd>
</p>

<br/><br/>**Інструкція запуску проекта:**<br/>
1. Натисніть на зелену кнопку `Clone or Download`.
2. Скопіюйте посилання на репозиторій.

<p align="center">
	<kbd>
 		<img src="https://github.com/AFKoders/int20h_test/blob/readme/assets/1.png" alt="1" width="300" height="570"/>
	</kbd>
</p>

3. Відкрийте Android Studio.
4. Натисніть на `Check out project from Version Control`.
5. Виберіть в меню пункт `Git`.

<p align="center">
	<kbd>
 		<img src="https://github.com/AFKoders/int20h_test/blob/readme/assets/2.png" alt="2" width="300" height="570"/>
	</kbd>
</p>

6. Вставте в поле посилання яке ви скопіювали в п.2 .
7. Натисніть кнопку `Clone`.

<p align="center">
	<kbd>
 		<img src="https://github.com/AFKoders/int20h_test/blob/readme/assets/3.png" alt="3" width="300" height="570"/>
	</kbd>
</p>

8. Виберіть доступний вам емулятор в меню (Або приєднайте телефон до комп'ютера).

<p align="center">
	<kbd>
 		<img src="https://github.com/AFKoders/int20h_test/blob/readme/assets/4.png" alt="4" width="720" height="90"/>
	</kbd>
</p>

9. Відкрийте структуру проекту та відкрийте файл `build.gradle (Module: app)`.

<p align="center">
	<kbd>
 		<img src="https://github.com/AFKoders/int20h_test/blob/readme/assets/5.png" alt="5" width="400" height="370"/>
	</kbd>
</p>

10. Знайдіть 45 строку у файлі або натисніть комбінацію `CMD + L` (`Ctrl + G` для Windows/Linux).
11. Впевніться що ви знайшли потрібну строку з назвами `AUDD_API_TOKEN`.

<p align="center">
	<kbd>
 		<img src="https://github.com/AFKoders/int20h_test/blob/readme/assets/6.png" alt="6" width="720" height="20"/>
	</kbd>
</p>

12. Замініть виклик функції `CONFIG("AUDD_API_TOKEN")` на `"\"*\""` - де **\*** це ваш токен від [Audd](https://audd.io/).

<p align="center">
	<kbd>
 		<img src="https://github.com/AFKoders/int20h_test/blob/readme/assets/7.png" alt="7" width="720" height="20"/>
	</kbd>
</p>

13. Запусть проект.

<p align="center">
	<kbd>
 		<img src="https://github.com/AFKoders/int20h_test/blob/readme/assets/8.png" alt="8" width="720" height="85"/>
	</kbd>
</p>

<br/>

---

<br/>
<p align="center">
  <a href="https://www.websitepolicies.com/policies/view/VIdzKqQj">Terms and Conditions</a>
</p>