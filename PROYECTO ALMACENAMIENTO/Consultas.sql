-- ¿Qué usuario tiene más compras? 

SELECT u.Nombre AS NombreUsuario, COUNT(c.idCompra) AS TotalCompras
FROM Usuario u
INNER JOIN Compra c ON u.idUsuario = c.idUsuario
GROUP BY u.Nombre
ORDER BY TotalCompras DESC
LIMIT 1;

-- Reseñas de todos los productos

SELECT idProducto, Valoraciones, Comentarios FROM Resena;

-- Compras con su detalle y nombre del usuario que realizó cada compra

SELECT c.idCompra, c.FechaCompra, c.CostoTotal, u.Nombre AS NombreUsuario 
FROM Compra c 
INNER JOIN Usuario u ON c.idUsuario = u.idUsuario;

-- Envíos con su detalle y quién realizó el envío

SELECT e.idEnvio, e.Codigopostal, e.Direccion, u.Nombre AS NombreUsuario
FROM Envio e
INNER JOIN Compra c ON e.idCompra = c.idCompra
INNER JOIN Usuario u ON c.idUsuario = u.idUsuario;

-- Total de Compras por Ciudad

SELECT u.Nombre AS Ciudad, COUNT(e.idCompra) AS TotalCompras
FROM Envio e
INNER JOIN Ubicacion u ON e.Codigo = u.Codigo
GROUP BY u.Nombre
ORDER BY TotalCompras DESC;

-- Cantidad de Atuendos Vendidos y Cantidad Total por Atuendo

SELECT P.idProducto, P.Atuendos, SUM(C.cantidad) AS Cantidad_Vendida, SUM(C.cantidad * P.Precio) AS Total_Vendido
FROM  Producto AS P
JOIN Contiene AS C ON P.idProducto = C.idProducto
GROUP BY P.idProducto, P.Atuendos
ORDER BY Total_Vendido DESC;

-- Promedio de Valoraciones por Atuendo

SELECT R.idProducto, P.Atuendos, AVG(R.Valoraciones) AS PromedioValoraciones
FROM Resena as R, Producto as P
WHERE R.idProducto = P.idProducto
GROUP BY R.idProducto;

-- Total Dinero Gastado por Persona

SELECT u.Nombre AS NombreUsuario, SUM(c.CostoTotal) AS TotalGastado
FROM Usuario u
INNER JOIN Compra c ON u.idUsuario = c.idUsuario
GROUP BY u.Nombre;
