# Desafio Fuego de Quasar MercadoLibre

Desafio tecnico realizado para Mercadolibre, consiste en la toma, manejo y devolucion de mensajes codificados para el 
rescate de la flota de carga que se encuentra a la deriva y cuyo material a bordo es de vital importancia para la
Alianza Rebelde

Consideraciones:
* La unidad de distancia en los parámetros de GetLocation es la misma que la que se
utiliza para indicar la posición de cada satélite.
* El mensaje recibido en cada satélite se recibe en forma de arreglo de strings.
* Cuando una palabra del mensaje no pueda ser determinada, se reemplaza por un string
en blanco en el array.
* * Ejemplo: [“este”, “es”, “”, “mensaje”]
* Considerar que existe un desfasaje (a determinar) en el mensaje que se recibe en cada
satélite.

## Comenzando 🚀

El proyeto podra ejecutarse localmente o ejecutando un contenedor de Docker

### Pre-requisitos 📋

Se debe tener instalado Maven y JDK

### Instalación 🔧

Basta con ejecutar las siguientes lineas en consola

```
git clone https://github.com/kevinpineda222/FuegoQuasarMeli.git
cd quasarFireChallenge
mvn clean
mvn spring-boot:run
```

## Ejecución ⚙️

Para la ejecucion local del servicio se ha expuesto la siguiente API en Swagger

* [FUEGO QUASAR ML](http://localhost:8080/swagger-ui.html#/) - API DOCUMENTATION SWAGGER

### Ejecucion Nivel 2 del Challenge

Para ejecutar el endpoint "/topsecret" correspondiente al Nivel 2, se tendra que utilizar una request con el siguiente formato :

```
{
    "satellites": [
                {
            "name": "kenobi",
            "distance": 100.0,
            "message": [
                "este",
                "",
                "",
                "mensaje",
                ""
            ]
        },
        {
            "name": "skywalker",
            "distance": 115.5,
            "message": [
                "",
                "es",
                "",
                "",
                "secreto"
            ]
        },
        {
            "name": "sato",
            "distance": 142.7,
            "message": [
                "este",
                "",
                "un",
                "",
                ""
            ]
        }
    ]
}
```
### Ejecucion Nivel 3 del Challenge

Para ejecutar el endpoint "/topsecret_split/{satellite_name}" correspondiente al metodo POST del Nivel 3, se tendra que utilizar una request con el siguiente formato :

```
{
    "distance": 115.5,
    "message": [
        "",
        "es",
        "",
        "",
        "secreto"
    ]
}

satellite_name : (sato,skywalker,kenobi)
```

Para ejecutar el endpoint "/topsecret_split" basta con seleccionar y ejecutar

## Herramientas Utilizadas 🛠️

Se utilizaron las siguientes herramientas para el desarrollo :

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)


## Autor ✒️

**Kevin Pineda**
* [Linkedin](https://www.linkedin.com/in/kevin-pineda-05a30b19a?lipi=urn%3Ali%3Apage%3Ad_flagship3_profile_view_base_contact_details%3BZCWMjLqKTfeD5SDA05jlKQ%3D%3D)
