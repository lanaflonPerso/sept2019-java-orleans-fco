INSERT INTO sponsor VALUES
(1,'Umbro',1,'https://www.umbro.fr/','/img/photoSponsor/umbro.png'),
(2,'St Jean de la Ruelle',2,'http://www.ville-saintjeandelaruelle.fr/','/img/photoSponsor/logo-st-jean.png'),
(3,'ooredoo',3,'http://www.ooredoo.tn/','/img/photoSponsor/ooredoo-alt.png'),
(4,'accor live limitless',4,'https://all.accor.com/geoloc/selectdisplayzone/index.gb.shtml','/img/photoSponsor/all.png'),
(5,'QNB',5,'https://www.qnb.com/sites/qnb/qnbglobal/page/en/enqnbglobalnetwork.html','/img/photoSponsor/qnb-secondary.png'),
(6,'rouwanda',7,'https://www.visitrwanda.com/','/img/photoSponsor/visit-rwanda.png'),
(7,'soir DE match',6,'www.soirdematch.fr','/img/photoSponsor/sponsoFCO.png'),
(8,'renault',8,'https://www.renault.fr/','/img/photoSponsor/renault.png'),
(9,'deliveroo',9,'https://deliveroo.fr/en/','/img/photoSponsor/deliveroo.png');

INSERT INTO encounter VALUES (1, "Régionale 1", "Equipe Futsal", 1577228400000, "/img/chateauroux.jpg", "Chateauroux Etoile");

INSERT INTO message VALUES
(1, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ex urna, scelerisque quis varius a, fermentum at mi. Phasellus ac enim eget arcu pharetra pharetra.", "2019-12-08 16:25:32", "blabla@gmail.com", "Quentin", "Liger"),
(2,"Morbi enim enim, sagittis eget sapien et, aliquam ultrices justo. Mauris interdum enim urna, nec rutrum risus venenatis nec. Aenean vitae ante id est rhoncus vestibulum. In quis rhoncus orci. Quisque viverra sed nisi ac blandit. Vestibulum egestas libero augue, in elementum elit sodales non. Proin ut erat a augue rutrum scelerisque.", "2019-12-09 09:56:12", "emailunpeutroplong@gmail.com", "Jean-Michel-Pierre", "Dupont");

INSERT INTO pole VALUES (1, "seniors"), (2, "formation"), (3, "pre-formation"), (4, "ecole-de-foot");

INSERT INTO team (id, name, pole_id) VALUES
(14, "Seniors 1", 1), (15, "Seniors 2", 1), (16, "Futsal", 1), (17, "Féminines", 1),
(1, "U19", 2), (2, "U17", 2), (3, "U16", 2), (4, "U15", 2), (5, "U14", 2),
(6, "U13", 3), (7, "U12", 3), (8, "U11", 3), (9, "U10", 3),
(10, "U9", 4), (11, "U8", 4), (12, "U7", 4), (13, "U6", 4);