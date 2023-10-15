CREATE TABLE Jugador
(
    cedula INTEGER PRIMARY KEY,
    nombre TEXT NOT NULL,
    email  TEXT NOT NULL
);

CREATE TABLE Configuracion
(
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    configuracion TEXT NOT NULL,
    CONSTRAINT uk_configuracion UNIQUE (configuracion)
);

INSERT INTO Configuracion (configuracion)
VALUES ('Jugar en X');
INSERT INTO Configuracion (configuracion)
VALUES ('Cuatro esquinas');
INSERT INTO Configuracion (configuracion)
VALUES ('Carton lleno');
INSERT INTO Configuracion (configuracion)
VALUES ('Jugar en Z');

CREATE TABLE Juego
(
    id               INTEGER PRIMARY KEY AUTOINCREMENT,
    fecha            TEXT    NOT NULL,
    hora             TEXT    NOT NULL,
    configuracion_id INTEGER NOT NULL,
    CONSTRAINT fk_configuracion FOREIGN KEY (configuracion_id)
        REFERENCES configuracion (id)
);

CREATE TABLE NumerosCantados
(
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    numero   INTEGER NOT NULL,
    juego_id INTEGER NOT NULL,
    CONSTRAINT fk_juego FOREIGN KEY (juego_id)
        REFERENCES Juego (id),
    CONSTRAINT uk_numero_juego UNIQUE (numero, juego_id)
);

CREATE TABLE GanadoresJuego
(
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    juego_id INTEGER NOT NULL,
    cedula   INTEGER NOT NULL,
    CONSTRAINT fk_juego FOREIGN KEY (juego_id)
        REFERENCES Juego (id),
    CONSTRAINT fk_jugador FOREIGN KEY (cedula)
        REFERENCES Jugador (cedula),
    CONSTRAINT uk_juego_jugador UNIQUE (juego_id, cedula)
)