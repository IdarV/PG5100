# PG5100 - Java Enterprise Programming Exam
By Idar Vassdal

#### Setup
1) Install Wildfly and run ```./standalone.sh```<br/>
2) Open project in root and run ```mvn clean package``` <br/>
3) In root, run ```mvn wildfly:deploy``` <br/>
4) Given that Wildfly runs on the default port 8080, navigate to: http://localhost:8080/tjoida-1.0-SNAPSHOT/index.xhtml

#### About
The program is a management program for a typical school, with models for users, courses, events and locations. The user view is a little more advanced than the rest of the views, allowing CRUD operations on the same page. I like to think of this view as a teacher-view, where they can add students to courses, add manage their students. I added the extra functionality as a challenge for myself.
