# Asep User Guide

Welcome to Asep, your personal task management chatbot. Asep helps you efficiently manage your tasks by allowing you to add todos, deadlines, and events, as well as update, delete, and search through your tasks via simple text commands.

## 1. Introduction

Asep is a command-line based chatbot designed to simplify task management. Whether you need to remember to read a book, submit an assignment, or attend a meeting, Asep is here to help. This guide provides step-by-step instructions on how to use Asep's features.

## 2. Quick Start

To start using Asep, run the application. Once running, you can begin entering commands. For a quick overview, try the following:
- Type `list` to display your current tasks.
- Type `todo` followed by a task description to add a todo.
- Type `deadline` or `event` to add tasks with specific timings.
- Use `mark`, `unmark`, and `delete` to manage your tasks.
- Type `bye` to exit the application.

## 3. Features

### 3.1 Adding Tasks

#### 3.1.1 Adding Todos

To add a todo, use the `todo` command followed by your task description.

```
todo Read a book
```

**Expected Outcome:**

```
Got it. I've added this task:
   [T][ ] Read a book
Now you have X tasks in the list.
```

#### 3.1.2 Adding Deadlines

To add a deadline task, use the `deadline` command with the task description, and specify the due date using `/by` in the format `yyyy-MM-dd`.

```
deadline Submit assignment /by 2025-03-15
```

**Expected Outcome:**

```
Got it. I've added this task:
   [D][ ] Submit assignment (by: Mar 15 2025)
Now you have X tasks in the list.
```

#### 3.1.3 Adding Events

To add an event task, use the `event` command with the task description, and specify the start and end times using `/from` and `/to`.

```
event Team meeting /from 2pm /to 3pm
```

**Expected Outcome:**

```
Got it. I've added this task:
   [E][ ] Team meeting (from: 2pm to: 3pm)
Now you have X tasks in the list.
```

### 3.2 Managing Tasks

#### 3.2.1 Marking Tasks as Done

To mark a task as completed, use the `mark` command followed by the task number.

```
mark 2
```

**Expected Outcome:**

```
Nice! I've marked this task as done:
   [X][Task details]
```

#### 3.2.2 Marking Tasks as Not Done

To mark a task as not done, use the `unmark` command followed by the task number.

```
unmark 2
```

**Expected Outcome:**

```
OK, I've marked this task as not done yet:
   [ ][Task details]
```

#### 3.2.3 Deleting Tasks

To delete a task, use the `delete` command followed by the task number.

```
delete 3
```

**Expected Outcome:**

```
Noted. I've removed this task:
   [Task details]
Now you have X tasks in the list.
```

#### 3.2.4 Listing All Tasks

To view all your tasks, simply type the `list` command.

```
list
```

**Expected Outcome:**

```
1. [Task details]
2. [Task details]
...
```

#### 3.2.5 Finding Tasks

To search for tasks that contain a specific keyword, use the `find` command followed by the keyword.

```
find meeting
```

**Expected Outcome:**

```
Here are the matching tasks in your list:
1. [Task details]
...
```

### 3.3 Exiting Asep

To exit the application, type the `bye` command.

```
bye
```

**Expected Outcome:**

```
Bye. Hope to see you again soon!
```

## 4. Command Summary

| Command    | Usage Format                                     | Description                                |
|------------|--------------------------------------------------|--------------------------------------------|
| `todo`     | `todo <description>`                             | Adds a simple todo task                    |
| `deadline` | `deadline <description> /by <yyyy-MM-dd>`        | Adds a deadline task                       |
| `event`    | `event <description> /from <start> /to <end>`      | Adds an event task                         |
| `mark`     | `mark <task number>`                             | Marks a task as completed                  |
| `unmark`   | `unmark <task number>`                           | Marks a task as not completed              |
| `delete`   | `delete <task number>`                           | Deletes a task                             |
| `list`     | `list`                                           | Lists all tasks                            |
| `find`     | `find <keyword>`                                 | Finds tasks containing a keyword           |
| `bye`      | `bye`                                            | Exits the application                      |

## 5. Additional Help

If you need further assistance, please refer to the documentation provided with Asep or contact our support team.

---

*End of Asep User Guide*
