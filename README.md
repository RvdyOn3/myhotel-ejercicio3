
# Prueba Técnica

En este repositorio vamos a encontrar las soluciones a las preguntas 2 (Script) y 3 (Srping WebFlux) de la prueba técnica.

## Pregunta 2 - Se deben construir las querys que permitan resolver los problemas presentados :
Para ejecutar las querys se debe realizar la creación de las tablas mediante el archivo ```dump.sql``` ubicado en la ruta ```src -> main -> resource -> script```.

- Obtener cantidad de empleados dentro de los siguientes segmentos de sueldo:
  ```bash
    SELECT s.SEGMENTO, 
    count(s.EMPLOYEE_ID) AS CANTIDAD
    FROM (
    SELECT
    e.EMPLOYEE_ID,
    e.FIRST_NAME,
    e.LAST_NAME,
    e.SALARY,
    CASE
    WHEN e.SALARY < 3500.00 then 'SEGMENTO A'
    WHEN e.SALARY >= 3500.00 AND e.SALARY < 8000.00  then 'SEGMENTO B'
    WHEN e.SALARY >= 8000.00 then 'SEGMENTO C'
    END AS SEGMENTO
    FROM employees e) as s
    GROUP BY s.segmento;
  ```
- Utilizando la tabla “departments” se requiere realizar la misma agrupación de segmentos de sueldo, pero por departamento:
  ```bash
    SELECT 
    d.DEPARTMENT_ID,
    d.DEPARTMENT_NAME,
    CASE
    WHEN SUM(e.SALARY) < 3500.00 then 'SEGMENTO A'
    WHEN SUM(e.SALARY) >= 3500.00 AND SUM(e.SALARY) < 8000.00  then 'SEGMENTO B'
    WHEN SUM(e.SALARY) >= 8000.00 then 'SEGMENTO C'
    END AS SEGMENTO
    FROM departments d
    INNER JOIN employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
    GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME
    ORDER BY e.DEPARTMENT_ID;
  ```
- Información del empleado con mayor sueldo de cada departamento:
  ```bash
    SELECT 
    d.DEPARTMENT_ID,
    d.DEPARTMENT_NAME,
    concat_ws(' ', e.FIRST_NAME, e.LAST_NAME) AS EMPLOYEE,
    max(e.SALARY) AS SALARY
    FROM departments d
    INNER JOIN employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
    GROUP BY d.DEPARTMENT_ID
    ORDER BY d.DEPARTMENT_ID;
  ```
- Información de los gerentes que hayan sido contratados hace más de 15 años.:
   ```bash
     SELECT 
     e.EMPLOYEE_ID,
     concat_ws(' ', e.FIRST_NAME, e.LAST_NAME) AS EMPLOYEE,
     e.HIRE_DATE,
     e.JOB_ID,
     timestampdiff(YEAR, e.hire_date, CURDATE()) AS 'AÑOS'
     FROM employees e
     WHERE e.JOB_ID IN ('FI_MGR', 'AC_MGR', 'SA_MAN', 'PU_MAN', 'ST_MAN', 'MK_MAN')
     AND timestampdiff(YEAR, e.hire_date, CURDATE()) > 15;
    ```
- Salario promedio de todos los departamentos que tengan más de 10 empleados:
  ```bash
    SELECT 
    d.DEPARTMENT_ID,
    d.DEPARTMENT_NAME,
    count(e.EMPLOYEE_ID) AS CANTIDAD,
    AVG(e.SALARY) AS PROMEDIO
    FROM departments d
    INNER JOIN employees e on d.DEPARTMENT_ID = e.DEPARTMENT_ID
    GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME
    HAVING count(e.EMPLOYEE_ID) > 10;
  ```
- Obtener la siguiente información agrupada por país: cantidad empleados, salario promedio, salario más alto, salario más bajo, promedio años antigüedad:
  ```bash
    SELECT 
    c.COUNTRY_ID,
    c.COUNTRY_NAME,
    COUNT(e.EMPLOYEE_ID) AS EMPLOYEE,
    IFNULL(AVG(e.SALARY), 0) AS SALARY,
    IFNULL(max(e.SALARY), 0) AS SALARY_MAX,
    IFNULL(min(e.SALARY), 0) AS SALARY_MIN,
    IFNULL(AVG(TIMESTAMPDIFF(YEAR, e.hire_date, CURDATE())), 0) AS AVGYEAR
    FROM countries c
    LEFT JOIN locations l ON c.COUNTRY_ID = l.COUNTRY_ID
    LEFT JOIN departments d ON l.LOCATION_ID = d.LOCATION_ID
    LEFT JOIN employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
    GROUP BY c.COUNTRY_ID, c.COUNTRY_NAME;
  ``` 
Tambien puede ejecutar las querys mediante el archivo ```querys.sql``` ubicado en la ruta ```src -> main -> resource -> script```.


## Pregunta 3 - Implementar la API (endpoints):
Entregar la información solicitada en cada uno de los problemas propuestos en la pregunta 2.
Este proyecto se realizó utilizando **Spring WebFlux** para la programación reactiva.

### Indice

 - [Estructura](#Estructura)
 - [Configuracion](#Configuracion)
 - [Ejecucion](#Ejecucion)
 - [APIs](#APIs)
 - [Recursos](#Recursos)
 - [Tecnologías](#Tecnologias)

### Estructura

- **config:** Paquete donde se encuentran las clases de configuración (Config, Handler, etc).
- **controller:** Paquete donde se encuentran las clases controladoras o los endpoints que van hacer expuestos para su consumo.
- **dto:** Paquete donde se encuentran las clases para transmitir información entre el cliente y el servidor.
- **model:** Paquete donde se encuentran las clases que hacen referencia a nuestro modelo de datos.
- **repository:** Paquete donde se encuentran las clases que establecen comunicación con la BD.
- **services:** Paquete donde se encuentran las clases que realizan la lógica de negocio o comercial.
- **Main.java:** Clase .java para el arranque del proyecto.

### Configuracion

#### Requisitos
- Java 11 (JDK completo, no JRE)
- Herramienta de línea de comando git
- IDE preferido (yo utilizo IDEA IntelliJ)
- MySql Server instalado localmente o con Docker 

#### Pasos
##### **Configurando la BD**
- Puede iniciar MySQL instalado localmente que se conectará a través del puerto 3306.
- Puede descargar MySQL vía docker e iniciarlo, para este ejemplo estamos exponiendo el puerto 33060.
```bash
  docker pull mysql
  docker run -d -p 33060:3306 --name=mysql -e MYSQL_ROOT_PASSWORD=mysql mysql:latest
```
##### **Spring Administrar Vehiculos**
- Clonar el proyecto en tu ordenador
```bash
  git clone
```
- Dentro de IntelliJ ```File-> Open ``` y seleccionas la ruta donde se encuentra el proyecto que descargaste en el paso anterior. Haz click en el botón ```Ok```
- Una vez que el proyecto este abierto dirígete al archivo ```pom.xml``` en la raiz de la estructura del proyecto.
- Selecciona click derecho en el archivo, se abrirá una ventana de opciones, dirígete a ```Maven -> Reload project```. Lo que hara con esta selección es descargar todos las librerías que se utilizarán en el proyecto.

### Ejecucion
##### **Ejecución de la BD**
Podemos generar el modelo de la BD de 2 formas:
- Mediante el script de creación ```dump.sql``` ubicado en la carpeta ```src -> main -> resource -> script```

##### **Ejecución Spring Administrar Vehículos**
- Nos ubicamos en el archivo ```application.properties``` ubicado en ```src -> main -> resource```. Aquí podemos validar los valores para la configuración y ejecución del proyecto:
    ```bash
        ### Puerto en donde se ejecutará el proyecto
        server.port=8080

        ### JDC donde se encuentra la BD
        spring.datasource.url=jdbc:mysql://localhost:33060/myhotel_test
        
        ### usuario y password BD
        spring.datasource.username=root
        spring.datasource.password=mysql
    ```
- Nos ubicamos en la clase ```Main.java``` ubicado en ```src -> main -> java -> com -> myhotel```, seleccionamos click derecho ```Run```.
- Ingresamos a la siguiente URL ```http://localhost:8080/swagger-ui.html``` ya que tenemos Swagger incorporado en nuestro proyecto podemos ver la interfaz de usuario.
## APIs

#### Retorna la agrupación por segmentos 

```http
  GET /employee/segment
```

#### Retorna gerentes con mas de 15 años trabajando

```http
  GET /employee/managers-old
```

#### Retorna agrupación de segmentos por departamentos

```http
  GET /department/segment-salary
```
#### Retorna empleado con mayor sueldo de cada departamento

```http
  GET /department/max-salary
```
#### Retorna salario promedio de todos los departamentos que tengan más de 10 empleados

```http
  GET /department/avg-salary
```

#### Retorna agrupación por pais de cantidad empleados, salario promedio, salario más alto, salario más bajo, promedio años antigüedad

```http
  GET /country/employee-info
```

Para mayor información consulte ```http://localhost:8080/swagger-ui.html```

#### Recursos
- Script de BD ubicado en la carpeta ```src -> main -> resource -> script``` con nombre ```dump.sql```

#### Tecnologias

- [Spring boot](#) 
- [Spring Data](#)
- [Spring WebFlux](#)
- [OpenApi](#)
- [Lombok](#) 
- [ModelMapper](#) 
- [Maven](#) 
- [Java 11](#) 
- [MySql](#) 
