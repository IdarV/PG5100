INSERT INTO User (id, email, password, usertype) VALUES(100, 'idar@email.com', 's0m3pasSw0r!D', 'STUDENT');
INSERT INTO User (id, email, password, usertype) VALUES(102, 'fredrick@email.com', 'freddyBo!y13', 'STUDENT');
INSERT INTO User (id, email, password, usertype) VALUES(103, 'hans@email.com', 'HighP3rf0rmace!', 'STUDENT');
INSERT INTO User (id, email, password, usertype) VALUES(104, 'kim@email.com', 'k1mmeL1mme', 'STUDENT');
INSERT INTO User (id, email, password, usertype) VALUES(105, 'tomas@email.com', 'ruud1337', 'STUDENT');
INSERT INTO User (id, email, password, usertype) VALUES(106, 'test@email.com', 'test!4747JH4!.', 'STUDENT');

INSERT INTO Location(id, room, building) VALUES(100, '81', 'Schweigaardsgate 14');
INSERT INTO Location(id, room, building) VALUES(101, 'Vrimle', 'Schweigaardsgate 14');
INSERT INTO Location(id, room, building) VALUES(102, 'Auditoriet', 'Schweigaardsgate 14');
INSERT INTO Location(id, room, building) VALUES(103, '41', 'Schweigaardsgate 14');

INSERT INTO Course(id, name, FK_LOCATION) VALUES(100, 'Avansert Javaprogrammering 01', 100);
INSERT INTO Course(id, name, FK_LOCATION) VALUES(101, 'Embedded Systems', 103);
INSERT INTO Course(id, name, FK_LOCATION) VALUES(102, 'C for Linux', 102);
INSERT INTO Course(id, name, FK_LOCATION) VALUES(103, 'Entrepreneurship', 101);

INSERT INTO Event(id, eventtype, title, description, FK_COURSE) VALUES(100, 'LECTURE', 'JPA Basics', 'Learning the basics', 100);
INSERT INTO Event(id, eventtype, title, description, FK_COURSE) VALUES(101, 'LECTURE', 'IO Basics', 'Learning the basics', 101);
INSERT INTO Event(id, eventtype, title, description, FK_COURSE) VALUES(102, 'LECTURE', 'Pointers basics', 'Learning the basics', 102);
INSERT INTO Event(id, eventtype, title, description, FK_COURSE) VALUES(103, 'LECTURE', 'LEAN basics', 'Learning the basics', 103);

-- USR_SUB(course, user) are *-* table of users int courses.
INSERT INTO USR_SUB VALUES(100, 100);
INSERT INTO USR_SUB VALUES(101, 100);
INSERT INTO USR_SUB VALUES(102, 100);
INSERT INTO USR_SUB VALUES(103, 100);

INSERT INTO USR_SUB VALUES(100, 102);
INSERT INTO USR_SUB VALUES(102, 102);
INSERT INTO USR_SUB VALUES(103, 102);

INSERT INTO USR_SUB VALUES(101, 103);
INSERT INTO USR_SUB VALUES(101, 104);

INSERT INTO USR_SUB VALUES(102, 104);
INSERT INTO USR_SUB VALUES(103, 104);

INSERT INTO USR_SUB VALUES(100, 105);
INSERT INTO USR_SUB VALUES(101, 105);

-- Event_Details(endTime, startTime, eventID)
INSERT INTO Event_Details VALUES('2015-12-05 12:00:00.000', '2015-12-05 11:20:00.000', 100);
INSERT INTO Event_Details VALUES('2015-12-06 15:20:00.000', '2015-12-06 11:30:00.000', 101);
INSERT INTO Event_Details VALUES('2015-12-08 18:00:00.000', '2015-12-08 17:00:00.000', 102);
INSERT INTO Event_Details VALUES('2015-12-09 10:40:00.000', '2015-12-09 08:10:00.000',  103);
