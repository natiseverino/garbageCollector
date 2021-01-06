# GarbageCollector - Trabajo Final
### Introducción a la programación de dispositivos móviles

![app_icon](/screenshots/app-icon.png)

En este trabajo se implementó una aplicación en android llamada Garbage Collector , para utilizar los
servicios de la API garbage recycler, provista por la cátedra, la cual permite manejar los reciclados de un usuario. 

La API provee los servicios para registrar a un usuario junto con sus datos personales y registrar sus reciclados, asi como tambien consultar los mismos y con consultar el total.

```
// Datos personales del usuario
{
  "firstName": "Bautista",
  "lastName": "Carpintero",
  "mail": "--------@gmail.com",
  "username": "BautistaCRP",
  "address": {
    "department": "----------",
    "number": ---,
    "streetAddress": "----------",
    "city": "----------",
    "state": "----------",
    "zipCode": "----------"
    }
}

// reciclado:
{
  "tons": 2,
  "bottles": 3,
  "tetrabriks": 6,
  "glass": 4,
  "paperboard": 4,
  "cans": 5
}
```

Cabe destacar que los datos que se dan de alta en la API se guardan en una base de datos
en memoria. Por lo tanto se decidió tomar ciertas medidas para guardar los datos de forma
local en el dispositivo y establecer convenciones de sincronización con la API.

#### Screenshots
![screenshot-1](/screenshots/screenshot-1.png)
![screenshot-2](/screenshots/screenshot-2.png)
