# KeepCoding-IVEdicion-KeepcodingMasterBootcamp-Android-Avanzado


A continuación se describen las partes que se han desarrollado (con el check marcado).

## Elementos de la práctica
- [X] Si no hay conexión, mostrar un mensaje indicándolo (se ha añadido que también desaparezcan los botones de los que no haya datos en BDD cuando no haya conexión a internet - No sé si sería más correcto que se deshabilitasen, pero he creído que si aparecían los botones y no se podía pulsar en ellos puede confundir al usuario)
- [X] Poner todo en caché.
- [X] Si han pasado más de 7 días, descargar toda la información de nuevo.
- [X] La app tendrá una pantalla de menú principal donde habrá un logo y dos botones (Shops y Activities).
- [X] Tanto la lista de Shops como la de actividades, mostrarán:
  - [X] Mapa. Se encontrará en la parte superior y ocupará el 50% y contendrá pines con la localización de cada uno de los elementos (Shops o Activities).
  - [X] Listado de actividades. Se encontrará en la parte inferior y ocuperá el 50%. Cada celda de la lista contendrá:
  - - [X] Logo a la izquierda. 
  - - [X] Imagen de fondo con toda la tienda.
  - - [X] Nombre de la actividad en el frente.
- [X] Al pinchar en un pin del mapa, se mostrará:
  - [X] Logo + nombre de la actividad o tienda. (Esto se encuentra realizado al 50%, ya que en este momento no se está mostrando el logo)
  - - [X] Nombre de la tienda en la parte delantera.
- [X] El mapa se centrará en Madrid y mostrará la ubicación del usuario
- [ ] Filtro de actividades y tiendas mediante una barra de búsqueda. Las actividades o tiendas se borrarán se la lista y del mapa.
- [X] Todos los datos se tienen que mostrar en inglés o en español dependiendo de las preferencias de idioma del dispositivo.

## Enlaces de interes
Solución a cómo comprobar los permisos de localización: [Stackoverflow](https://stackoverflow.com/questions/37986082/android-googlemaps-mylocation-permission)

Solución a cómo comprobar si hay conexión a internet: [Stackoverflow](https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out)
