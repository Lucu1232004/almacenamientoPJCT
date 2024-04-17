-- Tabla Usuario
CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    genero ENUM ('H', 'M') NOT NULL,
    edad INT NOT NULL
);

-- Tabla Compra
CREATE TABLE Compra (
    idCompra INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT NOT NULL,
    fechaCompra VARCHAR(50) NOT NULL,
    costoTotal VARCHAR(50) NOT NULL,
    FOREIGN KEY (IdUsuario) REFERENCES Usuario(idUsuario)
);

-- Tabla Producto
CREATE TABLE Producto (
    idProducto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    precio INT NOT NULL,
    imagen BLOB, 
    tipo ENUM ('Articulo', 'Diseño') NOT NULL,
    esPaquete BOOLEAN NOT NULL,
    articulos JSON,
    cantidad INT NOT NULL,
    seccion ENUM ('Hombre', 'Mujer', 'Ninio') NOT NULL,
    atuendos ENUM ('Sudaderas','Camisetas','Calcetines','Pantalonetas','Leggins','Zapatillas') NOT NULL,
    tallas JSON
);


-- Tabla Contiene
CREATE TABLE Contiene (
	idContiene INT NOT NULL AUTO_INCREMENT,
    idCompra INT NOT NULL,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (idContiene),
    FOREIGN KEY (idCompra) REFERENCES Compra(idCompra),
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
);

-- Tabla Paquete
CREATE TABLE Paquete (
    idPaquete INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idProducto INT NOT NULL,
    precio INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
);

-- Tabla Resena
CREATE TABLE Resena (
    idResena INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idProducto INT NOT NULL,
    idUsuario INT NOT NULL,
    comentarios VARCHAR(225),
    valoraciones ENUM ('MuyMala','Mala','Neutra','Buena','MuyBuena') DEFAULT 'Neutra' NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
);
-- Tabla Municipios
CREATE TABLE Municipio (
    codigoPostal INT NOT NULL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla Envio
CREATE TABLE Envio (
	idEnvio INT NOT NULL AUTO_INCREMENT,
    idCompra INT NOT NULL,
    codigoPostal INT NOT NULL,
    fechaEnvio DATE NOT NULL,
    direccion VARCHAR(50) NOT NULL,
    PRIMARY KEY (idEnvio),
    FOREIGN KEY (idCompra) REFERENCES Compra(idCompra),
    FOREIGN KEY (codigoPostal) REFERENCES Municipio(codigoPostal)
);

-- Tabla Registros
CREATE TABLE Registro (
    idRegistro INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT NOT NULL,
    username VARCHAR(225) NOT NULL UNIQUE,
    contrasena VARCHAR(20) NOT NULL,
    rol ENUM ('administrador', 'cliente') NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

ALTER TABLE Producto
ADD CONSTRAINT check_cantidad CHECK (cantidad >= 0);

