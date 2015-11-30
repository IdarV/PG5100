INSERT INTO User (id, email, password, type) VALUES(100, 'idar@email.com', 's0m3pasSw0r!D', 'student');
INSERT INTO User (id, email, password, type) VALUES(102, 'fredrick@email.com', 'freddyBo!y13', 'student');
INSERT INTO User (id, email, password, type) VALUES(103, 'hans@email.com', 'HighP3rf0rmace!', 'student');
INSERT INTO User (id, email, password, type) VALUES(104, 'kim@email.com', 'k1mmeL1mme', 'student');
INSERT INTO User (id, email, password, type) VALUES(105, 'tomas@email.com', 'ruud1337', 'student');
INSERT INTO User (id, email, password, type) VALUES(106, 'test@email.com', 'test!4747JH4!.', 'student');

INSERT INTO Location(id, room, building) VALUES(100, '81', 'Schweigaardsgate 14');
INSERT INTO Location(id, room, building) VALUES(101, 'Vrimle', 'Schweigaardsgate 14');
INSERT INTO Location(id, room, building) VALUES(102, 'Auditoriet', 'Schweigaardsgate 14');
INSERT INTO Location(id, room, building) VALUES(103, '41', 'Schweigaardsgate 14');

INSERT INTO Course(id, name, FK_LOCATION) VALUES(100, 'Avansert Javaprogrammering 01', 100);
INSERT INTO Course(id, name, FK_LOCATION) VALUES(101, 'Embedded Systems', 103);
INSERT INTO Course(id, name, FK_LOCATION) VALUES(102, 'C for Linux', 102);
INSERT INTO Course(id, name, FK_LOCATION) VALUES(103, 'Entrepreneurship', 101);

-- add together som tables
-- USR_SUB
INSERT INTO USR_SUB VALUES(100, 100);
INSERT INTO USR_SUB VALUES(101, 100);
INSERT INTO USR_SUB VALUES(102, 100);
INSERT INTO USR_SUB VALUES(103, 100);

INSERT INTO USR_SUB VALUES(100, 101);
INSERT INTO USR_SUB VALUES(101, 101);

INSERT INTO USR_SUB VALUES(100, 102);
INSERT INTO USR_SUB VALUES(102, 102);
INSERT INTO USR_SUB VALUES(103, 102);

INSERT INTO USR_SUB VALUES(101, 103);

INSERT INTO USR_SUB VALUES(101, 104);
INSERT INTO USR_SUB VALUES(102, 104);
INSERT INTO USR_SUB VALUES(103, 104);
