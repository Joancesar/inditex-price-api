# inditex-price-api
> Capa API para la gestión de precios en Inditex

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Arquitecture](#arquitecture)
* [Screenshots](#screenshots)

## General Information
- La `inditex-price-api` es una API diseñada para gestionar y recuperar información sobre precios de productos para las tiendas Inditex.
- El objetivo principal es proporcionar una manera rápida y eficiente de consultar precios, teniendo en cuenta diferentes criterios como fechas, producto y marca.

## Technologies Used
- Java - versión 17
- Spring Boot - versión 3.1.5
- Caffeine Cache - versión 3.1.8
- JUnit - versión 5.9.3
- Flyway - versión 9.16.3

## Features
Listo para usar características:
- Consulta de precios por fecha, ID de producto y ID de marca.
- Caché eficiente para mejorar el rendimiento de las consultas frecuentes.
- Arquitectura basada en microservicios para facilitar la escalabilidad y el mantenimiento.
- Arquitectura hexagonal.

## Setup
Este proyecto utiliza Maven para la gestión de dependencias. 
Puedes configurar tu entorno local clonando el repositorio y ejecutando `mvn install` para instalar las dependencias necesarias.

## Usage
Para iniciar la aplicación, ejecuta:
```
mvn spring-boot:run
```
Luego, puedes realizar solicitudes a endpoints como:
```
curl -X GET 'http://localhost:8080/v1/brands/1/products/35455/prices?date=2022-12-24T10:00:00'
```

## Project Status
El proyecto está: _completed_

## Arquitecture
#### Domain
Esta capa es el corazón de la aplicación, donde reside la lógica de negocio. 
Define los modelos de dominio (como Brand, Currency, Price, y Product) 
que representan las entidades con las que opera la aplicación. 

Interacciones:
Con Application: La capa de dominio es utilizada por la capa de aplicación para llevar a cabo operaciones. 
Los servicios de la capa de aplicación invocarán métodos del dominio para ejecutar lógica de negocio cuando se procesan las solicitudes.
Con Infrastructure: La capa de dominio no debería depender de la infraestructura. En su lugar, la infraestructura dependerá de los modelos de dominio para realizar tareas como la persistencia de datos.

#### Application
La capa de aplicación actúa como mediadora entre la infraestructura (y las interfaces de usuario) y el dominio. Contiene los 'puertos', que son interfaces que definen operaciones de alto nivel que se pueden realizar. 
Estos puertos serán implementados por los adaptadores en la capa de infraestructura.
Interacciones:
Con Domain: La capa de aplicación utiliza las entidades y la lógica de negocio definida en la capa de dominio para realizar operaciones. Por ejemplo, para obtener un precio aplicable, 
la capa de aplicación ejecutará la lógica de dominio que determina cuál es el precio correcto en función de las reglas de negocio.
Con Infrastructure: Los servicios de la capa de aplicación dependen de los adaptadores definidos en la infraestructura para acceder y manipular datos externos. Por ejemplo, para guardar o recuperar información de la base de datos.

#### Infrastructure
En esta capa se encuentran todos los adaptadores necesarios para conectar la aplicación con el mundo exterior. 
Esto incluye la implementación de la persistencia de datos (como PriceRepositoryAdapter), la comunicación con otras APIs, y la configuración necesaria para que la aplicación se ejecute en un entorno de producción.

Interacciones:
Con Application: Los adaptadores en la infraestructura implementan los puertos definidos en la capa de aplicación. Por ejemplo, PriceRepositoryAdapter implementará PriceRepositoryPort para proporcionar los detalles de cómo se recuperan los datos de la base de datos.
Con Domain: Los adaptadores utilizarán las entidades de dominio para leer o escribir en la base de datos y realizar cualquier otra operación que requiera interacción con los modelos de dominio.


### Decisiones de Diseño y Herramientas Utilizadas:
ControllerAdvice:
Permite manejar de manera centralizada las excepciones de toda la aplicación. 
Facilita la implementación de un manejo de errores consistente y reduce la duplicación de código de manejo de errores.

Jacoco:
Es una herramienta de informes de cobertura de código que se utiliza para medir qué tan bien están cubiertos los códigos fuente por las pruebas. 
La decisión de utilizar Jacoco fue para garantizar que se mantenga un alto estándar de calidad del código y que las funcionalidades críticas estén debidamente probadas.

## Room for Improvement

Áreas de mejora:
- Expansión de la API para manejar más tipos de consultas relacionadas con precios.
- Load Balancing.
- Añadir monitoring
- Reactivo
- Pruebas de rendimiento ej: Gatling

## Screenshots
<!---
![img.png](img.png)
-->