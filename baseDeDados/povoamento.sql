USE MC;

#admin
#INSERT INTO Administrador(idAdministrador, email, nome, password) VALUES (1110, 'mariajbp00@gmail.com', 'Maria', 'password');

#users
INSERT INTO UtilizadorRegistado(idUtilizador, email, nome, password, idBibliotecaMusica,idBibliotecaVideo) VALUES (2220, 'user1@gmail.com', 'User1', 'password', 300,300);

INSERT INTO UtilizadorRegistado(idUtilizador, email, nome, password, idBibliotecaMusica, idBibliotecaVideo) VALUES (2120, 'e', 'User2', 'p',300,300);

select * from UtilizadorRegistado;