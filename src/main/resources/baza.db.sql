BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `grad` (
	`id`	INTEGER,
	`naziv`	TEXT,
	`broj_stanovnika`	INTEGER,
	`drzava`	INTEGER,
	`postanski_broj`	INTEGER,
	FOREIGN KEY(`drzava`) REFERENCES `drzava`,
	PRIMARY KEY(`id`)
);
INSERT INTO `grad` VALUES (1,'Pariz',2206488,1, 71000);
INSERT INTO `grad` VALUES (2,'London',8825000,2, 71000);
INSERT INTO `grad` VALUES (3,'Beč',1899055,3, 71000);
INSERT INTO `grad` VALUES (4,'Manchester',545500,2, 71000);
INSERT INTO `grad` VALUES (5,'Graz',280200,3, 71000);
CREATE TABLE IF NOT EXISTS `drzava` (
	`id`	INTEGER,
	`naziv`	TEXT,
	`glavni_grad`	INTEGER,
	PRIMARY KEY(`id`)
);
INSERT INTO `drzava` VALUES (1,'Francuska',1);
INSERT INTO `drzava` VALUES (2,'Velika Britanija',2);
INSERT INTO `drzava` VALUES (3,'Austrija',3);
CREATE TABLE IF NOT EXISTS `Znamenitost` (
      `id`	INTEGER,
      `naziv`	TEXT,
      `slika`	TEXT,
      `grad_id`	INTEGER,
      PRIMARY KEY(`id`)
);
INSERT INTO `Znamenitost` VALUES (1,'Belva' , 'C:\Users\HARUN\Desktop\Napredni RS\Tut\rpr19-2parc-1t\rpr19-2parc-1t\resources\img\img1.jpg',5);
COMMIT;