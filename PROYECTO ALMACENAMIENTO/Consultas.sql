-- Encontrar el usuario que ha realizado el mayor número de compras en cada sección (Hombre, Mujer, Niño), 
-- junto con la cantidad de compras realizadas.
SELECT U.nombre AS nombre_usuario, P.seccion, COUNT(C.idCompra) AS total_compras
FROM Usuario U
JOIN Compra C ON U.idUsuario = C.idUsuario
JOIN Contiene CO ON C.idCompra = CO.idCompra
JOIN Producto P ON CO.idProducto = P.idProducto
GROUP BY U.idUsuario, P.seccion
HAVING COUNT(C.idCompra) = (
    SELECT MAX(total_compras)
    FROM (
        SELECT COUNT(C2.idCompra) AS total_compras
        FROM Usuario U2
        JOIN Compra C2 ON U2.idUsuario = C2.idUsuario
        JOIN Contiene CO2 ON C2.idCompra = CO2.idCompra
        JOIN Producto P2 ON CO2.idProducto = P2.idProducto
        WHERE P2.seccion = P.seccion
        GROUP BY U2.idUsuario
    ) AS max_compras
);

-- Encontrar el nombre del producto que ha recibido la mejor valoración promedio, junto con la valoración promedio.
SELECT P.nombre AS nombre_producto, ROUND(AVG(
    CASE
        WHEN R.valoraciones = 'MuyMala' THEN 1
        WHEN R.valoraciones = 'Mala' THEN 2
        WHEN R.valoraciones = 'Neutra' THEN 3
        WHEN R.valoraciones = 'Buena' THEN 4
        WHEN R.valoraciones = 'MuyBuena' THEN 5
    END
), 2) AS valoracion_promedio
FROM Producto P
JOIN Resena R ON P.idProducto = R.idProducto
GROUP BY P.nombre
ORDER BY valoracion_promedio DESC
LIMIT 1;

-- Encontrar el nombre del producto más vendido en cada sección (Hombre, Mujer, Niño) junto con la cantidad total vendida.
SELECT P.nombre AS nombre_producto, 
       P.seccion, 
       SUM(CO.cantidad) AS cantidad_vendida
FROM Producto P
JOIN Contiene CO ON P.idProducto = CO.idProducto
JOIN Compra C ON CO.idCompra = C.idCompra
WHERE P.seccion IN ('Hombre', 'Mujer', 'Ninio')
GROUP BY P.seccion, P.idProducto
HAVING SUM(CO.cantidad) = (
    SELECT MAX(total_cantidad)
    FROM (
        SELECT SUM(CO2.cantidad) AS total_cantidad
        FROM Producto P2
        JOIN Contiene CO2 ON P2.idProducto = CO2.idProducto
        JOIN Compra C2 ON CO2.idCompra = C2.idCompra
        WHERE P2.seccion = P.seccion
        GROUP BY P2.idProducto
    ) AS max_cantidad
);

-- Obtener el nombre del usuario que ha realizado compras en todas las secciones disponibles 
SELECT U.nombre AS nombre_usuario, U.idUsuario
FROM Usuario U
JOIN Compra C ON U.idUsuario = C.idUsuario
JOIN Contiene CO ON C.idCompra = CO.idCompra
JOIN Producto P ON CO.idProducto = P.idProducto
GROUP BY U.idUsuario
HAVING COUNT(DISTINCT P.seccion) = (SELECT COUNT(DISTINCT seccion) FROM Producto);


