INSERT INTO User (id, email, password, type) VALUES(100, 'idar@email.com', 's0m3pasSw0rD', 'student');
INSERT INTO User (id, email, password, type) VALUES(102, 'fredrick@email.com', 'freddyBoy13', 'student');
INSERT INTO User (id, email, password, type) VALUES(103, 'hans@email.com', 'HighP3rf0rmace', 'student');
INSERT INTO User (id, email, password, type) VALUES(104, 'kim@email.com', 'k1mmeL1mme', 'student');
INSERT INTO User (id, email, password, type) VALUES(105, 'tomas@email.com', 'ruud1337', 'student');
INSERT INTO User (id, email, password, type) VALUES(106, 'test@email.com', 'test!4747JH4!.', 'student');
INSERT INTO Course(id, name) VALUES(100, 'Avansert Javaprogrammering 01');
INSERT INTO Course(id, name) VALUES(101, 'Embedded Systems');
INSERT INTO Course(id, name) VALUES(102, 'C for Linux');
INSERT INTO Course(id, name) VALUES(103, 'Entrepreneurship');
INSERT INTO Location(id, room, building) VALUES(100, '81', 'Schweigaardsgate 14');
INSERT INTO Location(id, room, building) VALUES(101, 'Vrimle', 'Schweigaardsgate 14');
INSERT INTO Location(id, room, building) VALUES(102, 'Auditoriet', 'Schweigaardsgate 14');
INSERT INTO Location(id, room, building) VALUES(103, '41', 'Schweigaardsgate 14');

-- add together som tables
-- USR_SUB
INSERT INTO USR_SUB VALUES(100, 100);
INSERT INTO USR_SUB VALUES(101, 100);
INSERT INTO USR_SUB VALUES(102, 100);
INSERT INTO USR_SUB VALUES(103, 100);