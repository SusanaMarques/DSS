USE MC;

#admin
INSERT INTO Administrador(idAdministrador, email, nome, password) VALUES (1110, 'admin@gmail.com', 'Admin', 'password');

#users
INSERT INTO UtilizadorRegistado(idUtilizador, email, nome, password, idBibliotecaMusica, idBibliotecaVideo) VALUES (2220, 'maria@gmail.com', 'Maria', 'password', 300, 400);
INSERT INTO UtilizadorRegistado(idUtilizador, email, nome, password, idBibliotecaMusica, idBibliotecaVideo) VALUES (2221, 'hugo@gmail.com', 'Hugo', 'password', 301, 401);
INSERT INTO UtilizadorRegistado(idUtilizador, email, nome, password, idBibliotecaMusica, idBibliotecaVideo) VALUES (2222, 'susana@gmail.com', 'Susana', 'password', 302, 402);



select * from musica; 
#delete from Musica where idMusica = -689926072;

#select * from Video;
#delete from Video where idVideo = 937131542;

select * from CategoriaVideo;

#select * from PlaylistVideo;

select * from ProprietariosMusica;

insert into CategoriaVideo(idVideo, idUtilizador, categoria) values (123,432, 'asd');