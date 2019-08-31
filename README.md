# TodoistApiJava
This is a simple and lightweight unofficial library for the Todoist Sync API v8. Originally forked from [the version by LikiPiki](https://github.com/LikiPiki/TodoistApiJava), this library has several advantages:

- Uses the v8 Sync API from Todoist instead of the now-deprecated v7 
- Built for Android
- Supports new arguments for adding items (due date/time, including reminders, and priority)

Note: this library is made for simple tasks (reading+creating items and reading+creating projects in Todoist), and would need some additional development to power a feature-rich Todoist client.

## Setup

Download project and drag all files in the `src` folder to your Java project in Android Studio.

## How to use

***Todoist Reader***: read all items
```java
String token = "Your todoist token here";

TodoistReader tr = new TodoistReader(token);

try {
    ArrayList<TodoistItem> items = tr.readItems();
    for (TodoistItem item : items) {
        Log.d("TodoistProjectLog", item.getContent());
    }
} catch (Exception e) {
    e.printStackTrace();
}
```

**OUTPUT**
```
TodoistProjectLog, Contact dog walker
TodoistProjectLog, Finish Todoist library
TodoistProjectLog, Charge battery for camera
TodoistProjectLog, Set up password manager
.....
```
<hr>

***Todoist Reader***: read projects
```java

TodoistReader tr = new TodoistReader(token);
try {
    ArrayList<TodoistProject> projects = tr.readProjects();
    for (TodoistProject project : projects) {
        Log.d("TodoistProjectLog", project.getName() + " : " + project.getId() + " : " + project.getIndent());
    }
} catch (Exception e) {
    e.printStackTrace();
}
```

**OUTPUT**
```
TodoistProjectLog, Inbox : 125065255 : 1
TodoistProjectLog, Project 1 : 125065256 : 1
TodoistProjectLog, Project 2 : 125065257 : 1
TodoistProjectLog, Project 3 : 125065258 : 2
.....
```
<hr>

***Todoist Writer***: write item
```java
String token = "Your todoist token here";
long project_id = projects.get(0).getId(); // change to desired project; this will use Inbox's ID (125065255)
int priority = 2; // max = 4 (very urgent), min = 1 (natural)
Boolean reminder = true; // auto-set reminder at time in due date string

try {
    TodoistWriter wr = new TodoistWriter(token);

    String response = wr.writeItem("New task title", "9/1/2019 at 3:00PM", priority, reminder, project_id);

    if(!response.contains("error")) {
        Log.d("TodoistProjectLog", "Successfully created new item");
        yourSuccessFunction();
    } else {
        Log.d("TodoistProjectLog", "Error creating new item");
        yourFailureFunction();
    }
} catch (Exception e) {
    e.printStackTrace();
    yourFailureFunction();
}
```
<hr>

***Todoist Writer***: write project
```java
String token = "Your todoist token here";

try {
    TodoistWriter wr = new TodoistWriter(token);

    String response = wr.writeProject("New project name");

    if(!response.contains("error")) {
        Log.d("TodoistProjectLog", "Successfully created new project");
        yourSuccessFunction();
    } else {
        Log.d("TodoistProjectLog", "Error creating new project");
        yourFailureFunction();
    }
} catch (Exception e) {
    e.printStackTrace();
    yourFailureFunction();
}
```
<hr>



## Repo/API Info

Feel free to use, fork, and modify the code in this repo as you'd like. If you have any questions, [you can contact me](https://henrystern.nyc/contact).

Lots of helpful info, from how to find your Todoist API token to the ins and outs of the API, available in [Todoist's official documentation here](https://developer.todoist.com/sync/v8).
