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
* [Contact](#contact)

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

## Room for Improvement

Áreas de mejora:
- Expansión de la API para manejar más tipos de consultas relacionadas con precios.
- Load Balancing.
- Añadir monitoring
- Reactivo

Pendiente:
- Implementación de seguridad y autenticación de API.


## Contact
Creado por [@joancesar](https://www.joancesar.com/) - ¡no dudes en contactarme!

