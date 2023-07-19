# Test para Inditex

## Resumen
Se trata de un pequeño microservicio para la consulta de Precios para una marca, producto y fecha determinados. Para ello se toma como base una tabla en una BBDD H2 en memoria autocargada en el arranque de la aplicación.

## Detalles
La aplicación esta desarrollada usando Java 17 + Spring como framework y usa un patrón MVC típico con:

  - Controller para la recepción de las peticiones. En ellos sólo se recibe la petición y se validan parámetros dejando la lógica de negocio para la capa Service.
  - Service para la ejecución de la lógica de negocio usando componentes Repository.
  - Repository como componente para la obtención de los datos solicitados.

## Tecnologías
Las tecnologías utilizadas son las siguientes:

  - Java 17
  - Spring Boot 3.0.4
  - Spring Data JPA 3.0.3
  - OpenAPI 3.0 usando la libreria springdoc-openapi v2.1.0
  - BBDD en memoria H2
  

 
