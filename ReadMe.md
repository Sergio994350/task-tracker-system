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
Task should be implemented in 1 week since it was provided. 
Exceptions are possible but we should be notified in timely manner. 
