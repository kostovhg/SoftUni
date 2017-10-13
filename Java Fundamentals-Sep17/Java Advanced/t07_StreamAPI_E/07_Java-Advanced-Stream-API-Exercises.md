#
# Exercises

# : Stream API

This document defines the exercises for [&quot;Java Advanced&quot; course @ Software University](https://softuni.bg/trainings/1377/advanced-java-may-2016). Please submit your solutions (source code) of all below described problems in [Judge](https://judge.softuni.bg/).

1. Problem 1.Students by Group

Print all students from group number 2. Use a Stream query. Order the students by **First Name**.

### Examples

| **Input** | **Output** |
| --- | --- |
| Sara Mills 1Andrew Gibson 2Craig Ellis 1Steven Cole 2 Andrew Carter 2END | Andrew Gibson Andrew Carter Steven Cole |

1. Problem 2.Students by First and Last Name

Using the same input as above print all students whose first name is before their last name lexicographically. Use a Stream query. Print them in order of appearance.

### Examples

| **Input** | **Output** |
| --- | --- |
| Sara MillsAndrew GibsonCraig EllisSteven ColeAndrew CarterEND | Andrew GibsonCraig EllisAndrew Carter |

1. Problem 3.Students by Age

Write a Stream query that finds the first name and last name of all students with age between 18 and 24. The query should return the **first name** , **last name** and **age**. Print them in order of appearance.

### Examples

| **Input** | **Output** |
| --- | --- |
| Sara Mills 24Andrew Gibson 21Craig Ellis 19Steven Cole 35 Andrew Carter 15END | Sara Mills 24Andrew Gibson 21Craig Ellis 19 |

1. Problem 4.Sort Students

Using the lambda expressions with Stream query syntax sort the students first by **last name** in **ascending** order and then by **first name** in **descending** order.

### Examples

| **Input** | **Output** |
| --- | --- |
| Sara GibsonAndrew GibsonCraig EllisSteven ColeAndrew EllisEND | Steven ColeCraig EllisAndrew EllisSara GibsonAndrew Gibson |

1. Problem 5.Filter Students by Email Domain

Print all students that have email **@gmail.com**. Use Stream API. Print the in order of appearance.

### Examples

| **Input** | **Output** |
| --- | --- |
| Sara Mills smills@gmail.comAndrew Gibson agibson@abv.bgCraig Ellis cellis@cs.edu.govSteven Cole themachine@abv.bgAndrew Carter [ac147@gmail.com](mailto:ac147@gmail.com)END | Sara MillsAndrew Carter |

1. Problem 6.Filter Students by Phone

Print all students with phones in Sofia (starting with **02** / **+3592** ). Use a Stream.

### Examples

| **Input** | **Output** |
| --- | --- |
| Sara Mills 02435521Andrew Gibson 0895223344Craig Ellis +3592667710Steven Cole 3242133312Andrew Carter +001234532END | Sara MillsCraig Ellis |

1. Problem 7.Excellent Students

Print all students that have **at least one mark Excellent (6)**. Use a Stream.

### Examples

| **Input** | **Output** |
| --- | --- |
| Sara Mills 6 6 6 5Andrew Gibson 3 4 5 6Craig Ellis 4 2 3 4Steven Cole 5 6 5 5Andrew Carter 5 3 4 2END | Sara MillsAndrew GibsonSteven Cole |

1. Problem 8.Weak Students

Write a similar program to the previous one to extract the **students with at least 2 marks under or equal to &quot;3&quot;**. Use a Stream.

### Examples

| **Input** | **Output** |
| --- | --- |
| Sara Mills 6 6 6 5Andrew Gibson 3 4 5 6Craig Ellis 4 2 3 4Steven Cole 5 6 5 5Andrew Carter 5 3 4 2END | Craig EllisAndrew Carter |

1. Problem 9.Students Enrolled in 2014 or 2015

Extract and print the **Marks** of the students that **enrolled in 2014 or 2015** (the students from 2014 have 14 as their 5-th and 6-th digit in the **FacultyNumber** , those from 2015 have 15).

### Examples

| **Input** | **Output** |
| --- | --- |
| 554214 6 6 6 5653215 3 4 5 6156212 4 2 3 4324413 5 6 5 5134014 5 3 4 2END | 6 6 6 53 4 5 65 3 4 2 |

1. Problem 10.\* Group by Group

Create a class **Person**. It should consists of **properties** : **name** and **group** (String, Integer). Write a program that extracts all persons (students), **grouped by**** GroupName **and then prints them on the console. Print all group names along with the students in each group. Use the** group by** Stream operations. You will be given an input on the console.

**Output format** : **{group} - {name1}, {name2}, {name3}, ...**

### Examples

| **Input** | **Output** |
| --- | --- |
| Ivaylo Petrov 10Stanimir Svilianov 3Indje Kromidov 3Irina Balabanova 4END | 3 - Stanimir Svilianov, Indje Kromidov4 - Irina Balabanova10 - Ivaylo Petrov |

1. Problem 11.\* Students Joined to Specialties

Create a new class **StudentSpecialty** that holds **specialty name** and **faculty number**. Create a **Student** class that holds **student name** and ** faculty number**. Create a list of **student specialties,** where each specialty corresponds to a certain student (via the faculty number). Print all student names alphabetically along with their faculty number and specialty name.

You will recieve several specialties in format :

 {specialty name} {specialty name} {faculty number}

Until you reach &quot;Students:&quot; , you should add specialties to the collection. After you reach &quot;Students:&quot;, you should start reading students in format :

 {faculty number} {student&#39;s first name} {student&#39;s second name}

You should add the students untill you recieve &quot;END&quot; command.

### Examples

| **Student Specialties** | **join** | **Students** | **→** | **Result (Joined Students with Specialties)** |
| --- | --- | --- | --- | --- |
| **SpecialtyName** | **FacNum** | **FacNum** | **Name** | **Name** | **FacNum** | **Specialty** |
| Web Developer | 203314 | 215314 | Milena Kirova | Asya Manova | 203314 | Web Developer |
| Web Developer | 203114 | 203114 | Stefan Popov | Asya Manova | 203314 | QA Engineer |
| PHP Developer | 203814 | 203314 | Asya Manova | Diana Petrova | 203914 | PHP Developer |
| PHP Developer | 203914 | 203914 | Diana Petrova | Diana Petrova | 203914 | Web Developer |
| QA Engineer | 203314 | 203814 | Ivan Ivanov | Ivan Ivanov | 203814 | PHP Developer |
| Web Developer | 203914 |   |   | Stefan Popov | 203114 | Web Developer |

| **Input** | **Output** |
| --- | --- |
| Web Developer 203314Web Developer 203114PHP Developer 203814PHP Developer 203914QA Engineer 203314Web Developer 203914Students:215314 Milena Kirova203114  Stefan Popov203314 Asya Manova203914 Diana Petrova203814 Ivan IvanovEND | Asya Manova 203314 Web DeveloperAsya Manova 203314 QA EngineerDiana Petrova 203914 PHP DeveloperDiana Petrova 203914 Web DeveloperIvan Ivanov 203814 PHP DeveloperStefan Popov 203114 Web Developer |

1. Problem 12.\* Little John

**This problem is originally from the PHP Basics Exam (3 May 2015). You may check your solution** [**here**](https://judge.softuni.bg/Contests/Practice/Index/84#10) **.**

As you probably know Little John is the right hand of the famous English hero - Robin Hood.A little known fact isthat Little John can&#39;t handle Math very well. Before Robin Hood left to see Marry Ann, he asked John to **count** his hay of arrowsand send him an **encrypted** message containing thearrow&#39;s count **.** The message should be encrypted since it can be intercepted by the Nottingham&#39;s evil Sheriff. Your task is to help Little John before it is too late (0.10 sec).

You are given **4 input** strings(hay). Those strings **may or may not** contain arrows. The arrows can be of different type as follows:

- &quot;&gt;-----&gt;&quot; – a small arrow
- &quot;&gt;&gt;-----&gt;&quot; – a medium arrow
- &quot;&gt;&gt;&gt;-----&gt;&gt;&quot; – a large arrow

Note that the **body** of each arrow will always be **5 dashes long**. The **difference** between the arrows is in their **tip** and **tail**. The given 3 types are the only ones you should count, the **rest should be ignored** (Robin Hood does not like them). You should start searching the hays **from the largest** arrow type down **to the smallest** arrow type.

After you find the **count** of each arrow type you should **concatenate** them into one number in order: small,medium, large arrow(even if the arrow count is 0). Then you **convert** the number in **binary** representation, **reverse** it and **concatenate it again** with the initial binary representation of the number. You **convert** the final binary number again **back to decimal**. This is the encrypted message you should send to Robin Hood.

### Input

The input will be read from the console. The **data** will be received from 4 input **lines containing strings**.

### Output

The output should be a decimal number, representing the encrypted count of arrows.

### Constraints

- The input strings will contain any ASCII character.
- Allowed working time: 0.1 seconds. Allowed memory: 16 MB.

### Examples

| **Input** | **Output** |
| --- | --- |
| &gt;&gt;&gt;-----&gt;&gt;abc&gt;&gt;&gt;-----&gt;&gt;&gt;&gt;&gt;-----&gt;&gt;&gt;-----&gt;s&gt;&gt;-----&gt; | 14535 _The count is: 1 small, 1 medium and 3 large arrows__113(dec) = 1110001(bin) -&gt; reversed is 1000111(bin)__1110001 __1000111__ (bin) = 14535(dec)_ |

1. Problem 13. \* Office Stuff

**This problem is from the Java Basics Exam (21 Sept 2014 Evening). You can test your solution** [**here**](https://judge.softuni.bg/Contests/Practice/Index/34#3) **.**

You are given a sequence of **n** companies in format | **&lt;company&gt; - &lt;amount&gt; - &lt;product&gt;|**. Example:

- |SoftUni - 600 - paper|
- |Vivacom - 600 - pen|
- |XS - 20 - chair|
- |Vivacom - 200 - chair|
- |SoftUni - 40 - chair|
- |XS - 40 - chair|
- |SoftUni - 1 - printer|

Write a program that prints **all companies** in **alphabetical** order. For each company print the product type and their aggregated ordered amounts. Order the products by **order of appearance**. **Print** the result in the following format: **&lt;company&gt;: &lt;product&gt;-&lt;amount&gt;, &lt;product&gt;-&lt;amount&gt;,…** For the orders above the output should be:

- SoftUni: paper-600, chair-40, printer-1
- Vivacom: pen-600, chair-200
- XS: chair-60

### Input

The input comes from the console. At the first line the number **n** stays alone. At the next **n** lines, we have **n** orders in format | **&lt;company&gt; - &lt;amount&gt; - &lt;product&gt;|**.

The input data will always be valid and in the format described. There is no need to check it explicitly.

### Output

Print **one line for each company**. Company lines should be ordered in **alphabetical**** order **. For each company print the** products **ordered by this company in** order **of** appearance **, along with the total amount for the given product. Each line should be in format** &lt;company&gt;: &lt;product&gt;-&lt;amount&gt;, &lt;product&gt;-&lt;amount&gt;, … &lt;product&gt;-&lt;amount&gt;**

### Constraints

- The **count** of the lines **n** will be in the range [1 … 100].
- The **&lt;company&gt;** and **&lt;product&gt;** will consist of only of **Latin characters** , with length of [1 … 20].
- The **&lt;amount&gt;** will be an integer number in the range [1 … 1000].
- Time limit: 0.1 sec. Memory limit: 16 MB.

### Examples

| **Input** | **Output** |   | **Input** | **Output** |
| --- | --- | --- | --- | --- |
| 7|SoftUni - 600 - paper||Vivacom - 600 - pen||XS - 20 - chair||Vivacom - 200 - chair||SoftUni - 40 - chair||XS - 40 - chair||SoftUni - 1 - printer| | SoftUni: paper-600, chair-40, printer-1Vivacom: pen-600, chair-200XS: chair-60 | 5|SoftUni - 200 - desk||SoftUni - 40 - PC||SoftUni - 200 - desk||SoftUni - 600 - paper||SoftUni - 600 - textbook| | SoftUni: desk-400, PC-40, paper-600, textbook-600 |

1. Problem 14.\*\* Export to Excel

Write a program to create an Excel file like the one below using an external library. Such as [Apache POI](http://howtodoinjava.com/apache-commons/readingwriting-excel-files-in-java-poi-tutorial/) for Java.

You are given as **input** course data about **1000 students** in a **.txt** file (tab-separated values). Each line in the input holds **ID** , **first name** , **last name** , **email** , **gender** , **student type** , **exam result** , **homework sent** , **homework**** evaluated **,** teamwork score **,** attendances count **,** bonus**.

