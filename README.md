# Solucionador de Laberintos

## Descripción

Este proyecto implementa un programa capaz de encontrar un camino entre una entrada y una salida dentro de un laberinto representado mediante un archivo de texto.

El programa lee el laberinto, construye una representación interna del mismo y utiliza algoritmos de búsqueda para encontrar un camino desde la entrada (`E`) hasta la salida (`S`). Si existe una solución, el camino encontrado se marca con el carácter `*`.

---

## Características

* Lectura de laberintos desde archivos de texto.
* Búsqueda de caminos entre dos puntos.
* Representación de paredes y espacios transitables.
* Marcado de la ruta encontrada sobre el laberinto original.
* Manejo de casos en los que no existe solución.
* Implementación utilizando las estructuras de datos desarrolladas durante el curso.

---

## Formato de Entrada

Los laberintos se representan mediante archivos de texto utilizando los siguientes caracteres:

| Carácter      | Significado        |
| ------------- | ------------------ |
| `█`           | Pared              |
| ` ` (espacio) | Camino transitable |
| `E`           | Entrada            |
| `S`           | Salida             |

El archivo debe contener exactamente una entrada y una salida.

---

## Estructura del Proyecto

| Clase               | Descripción                                          |
| ------------------- | ---------------------------------------------------- |
| `Proyecto3.java`    | Punto de entrada principal del programa.             |
| `Laberinto.java`    | Representación y validación del laberinto.           |
| `Solucionador.java` | Implementación del algoritmo de búsqueda de caminos. |
| `Coordenada.java`   | Representación de posiciones dentro del laberinto.   |

---

## Compilación

Compilar el proyecto:

```bash
mvn compile
```

Generar el archivo JAR:

```bash
mvn package
```

---

## Ejecución

### Linux y macOS

```bash
java -jar target/proyecto3.jar laberinto.txt
```

### Windows (PowerShell)

```powershell
java -jar target/proyecto3.jar laberinto.txt
```

La solución se imprime directamente en la salida estándar.

---

## Ejemplo de Entrada

Archivo `laberinto.txt`:

```text
█████████
█E█     █
█ █ ███ █
█ █   █S█
█ █████ █
█       █
█████████
```

---

## Ejemplo de Salida

```text
█████████
█*█     █
█*█ ███ █
█*█   █S█
█*█████*█
█*******█
█████████
```

El carácter `*` representa el camino encontrado entre la entrada y la salida.

---

## Créditos

Proyecto realizado por **Miguel Ángel Jiménez Ramírez** para la materia **Estructuras de Datos** de la Facultad de Ciencias de la UNAM.

La especificación y los requerimientos del proyecto fueron proporcionados por el profesor **Canek Peláez Valdés**.

La implementación presentada en este repositorio es de autoría propia.
