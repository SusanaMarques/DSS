USE MC;

#admin
INSERT INTO Administrador(idAdministrador, email, nome, password) VALUES (1110, 'admin@gmail.com', 'Admin', 'password');

#users
INSERT INTO UtilizadorRegistado(idUtilizador, email, nome, password, idBibliotecaMusica, idBibliotecaVideo) VALUES (2220, 'maria@gmail.com', 'Maria', 'password', 300, 400);
INSERT INTO UtilizadorRegistado(idUtilizador, email, nome, password, idBibliotecaMusica, idBibliotecaVideo) VALUES (2221, 'hugo@gmail.com', 'Hugo', 'password', 301, 401);
INSERT INTO UtilizadorRegistado(idUtilizador, email, nome, password, idBibliotecaMusica, idBibliotecaVideo) VALUES (2222, 'susana@gmail.com', 'Susana', 'password', 302, 402);


select * from musica; 
#delete from Musica where idMusica = 1734882928;

select * from Video;
#delete from Video where idVideo = -197838234;

select * from ProprietariosVideo;


#select * from CategoriaMusica; 

#select * from CategoriaVideo;

#select * from PlaylistMusica

