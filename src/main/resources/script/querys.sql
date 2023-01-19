/*PREGUNTA 1:
Obtener cantidad de empleados dentro de los siguientes segmentos de sueldo:
SEGMENTO A: menor a USD 3.500
SEGMENTO B: mayor o igual a USD 3.500 y menor que USD 8.000
SEGMENTO C: mayor o igual a USD 8.000.
*/
SELECT
s.SEGMENTO,
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




/*PREGUNTA 2:
Utilizando la tabla “departments” se requiere realizar la misma agrupación
de segmentos de sueldo, pero por departamento.
*/
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

/*PREGUNTA 3:
Información del empleado con mayor sueldo de cada departamento.
*/
SELECT 
d.DEPARTMENT_ID,
d.DEPARTMENT_NAME,
concat_ws(' ', e.FIRST_NAME, e.LAST_NAME) AS EMPLOYEE,
max(e.SALARY) AS SALARY
FROM departments d
INNER JOIN employees e ON d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID
ORDER BY d.DEPARTMENT_ID;

/*PREGUNTA 4:
Información de los gerentes que hayan sido contratados hace más de 15
años.
*/
SELECT 
e.EMPLOYEE_ID, 
concat_ws(' ', e.FIRST_NAME, e.LAST_NAME) AS EMPLOYEE,
e.HIRE_DATE, 
e.JOB_ID,
timestampdiff(YEAR, e.hire_date, CURDATE()) AS 'AÑOS' 
FROM employees e
WHERE e.JOB_ID IN ('FI_MGR', 'AC_MGR', 'SA_MAN', 'PU_MAN', 'ST_MAN', 'MK_MAN')
AND timestampdiff(YEAR, e.hire_date, CURDATE()) > 15;

/*PREGUNTA 5:
Salario promedio de todos los departamentos que tengan más de 10
empleados.
*/
SELECT 
d.DEPARTMENT_ID,
d.DEPARTMENT_NAME,
count(e.EMPLOYEE_ID) AS CANTIDAD,
AVG(e.SALARY) AS PROMEDIO
FROM departments d 
INNER JOIN employees e on d.DEPARTMENT_ID = e.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME
HAVING count(e.EMPLOYEE_ID) > 10;

/*PREGUNTA 6:
Obtener la siguiente información agrupada por país: cantidad empleados,
salario promedio, salario más alto, salario más bajo, promedio años
antigüedad.
*/
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


set sql_mode='';
SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));