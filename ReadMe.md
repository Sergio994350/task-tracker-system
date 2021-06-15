Task tracker system
We need to develop simple console task tracker system.

Part 1
The system should have following capabilities:
1. Create, show and delete users, projects and tasks [Done]
2. Assign user on the project [Done]
3. Assign task on the user [Done]
4. Generate the report of all tasks created for specified Projects by specified User [Done]
Application should use embedded or innemory database (e.g. SQLite [this], H2). [Done]
Initial data should be loaded to the database from files when application is starting. 
It might be csv[this], tsv, json, xml or any file type convenient for you. [Done]
Pay attention: it is not necessary to save any new data to files. 
They are used only for initial loading to the system. [Done]

Part 2 (optional)
Now we want to have the ability to create subtasks for tasks:
1. Each task may have any number of subtasks [Done]
2. Each subtask may have any number of subtasks 
3. When some task/subtask is closing, 
all it's subtasks should be closed too [Done]
4. Task should have the ability 
get sum of remaining time include all subtasks [Done]

Common recommendations:
• In case all parts weren’t completed it is still possible to send the task for check
• Keep it simple. It is strong not recommended to develop any features not specified in
requirements. [Done]
• Build tools is very useful. Do not hesitate use one of them. [ Maven ]
• Do not upload sources to the public repos. Please upload it to google disk and send link [Done]
Task should be implemented in 1 week since it was provided. [got - 18/05, done - 21/05] 
Exceptions are possible but we should be notified in timely manner. 

===========================================

// ОС от <EPAM>:
[ получено: 27.05., переработано: 29.05.]

// 1) Слабый код с точки зрения разбивки на классы, методы.
// [ Done / Капитально переработан весь код ]
// [ Было -> Стало (кроме POJO): (9 классов | 36 методов) -> (10 классов | 67 методов) ]

// 2) Нет соблюдения чистоты кода (лишние пробелы, отступы,
// лишние иницилазиации).
// [ Done / Edited ] 

// ** reworked CSVReader: 176 lines -> 98 lines (- 78 lines)
// ** reworked DBHandler: 598 lines -> 476 lines (- 102 lines)
// ** reworked MVLogic: 383 lines -> 39 lines (- 344 lines)
// ** add CommandHandler Class: + 510 lines
// ** Total: 1157 -> 1123 lines (-34 lines)

// 3) В разных частях кода используются
// разные подходы, где-то они правильные, где-то нет,
// ощущение что кандидат не до конца понимает как работают
// иснтрументы (пример try-with-resources).
// [ Done / Edited ] 

// 4) У таблиц в базе данных отсуствует какая либо
// связь с друг другом (ключи).
// [ Done / Edited ]


   Comments: 
   1) SQL инъекции, в половине методов DbHandler есть защита от них, 
   а в половине они отсутствуют
   
   2) Отступы, имеются ввиду новые строки между методами, у всех 
   должен быть единое расстояние, между методами, с табуляцией и 
   переходами на н.с. в методах таже история
   
   3) Не хватает JavaDoc в некоторых методах, которые не являются 
   самоописывающими
   
   4) Много ненужных инициализаций String line = "";
   
   5) Много ненужных символов в регулярных выражения: [\s\\,]+
   
   6) Многие try блоки захватывают одни и теже исключения: 
   FileNotFound и IOException
   
   7) DbHandler реализован как Singleton класс, это было сделано 
   из-за такой вещи, как Connection, поэтому её по логике нужно 
   держать как final
   
   8) Явно указывать класс шаблона в реализующих интерфейс коллекциях 
   не стоит, так как этот класс уже явно указан в интерфейсе: 
   List projects = new ArrayList();
   
   9) MVLogic, в данном классе поддержка Switch практически невозможна, 
   если только с болью. Необходимо большие куски кода вывести в отдельные 
   методы, может быть даже класс, тут уже от предпочтений зависит и общего 
   соглашения. Тогда switch можно будет уместить в 40 строчек и при 
   необходимости прыгать к необходимым участкам кода и пилить их
   
   10) try на всю программу раскачивать не стоит, 
   табуляция на один уровень уехала и всё ради одной строчки, лучше 
   уж отлавливать ошибки в одном маленьком месте и остальной код уже 
   держать на одном уровне
   
   11 ) CSVReader: 
   close в finally после проверки br !=null
   
   12 ) слишком много public, методы надо закрывать 