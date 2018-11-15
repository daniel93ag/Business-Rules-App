# BusinessRules
La idea de esta aplicación, es para ser usada por 
una cadena hotelera que esté construyendo un sistema de 
reservas, y quiera brindar a sus clientes una aplicación 
para buscar y reservar cuartos.

Los dueños del hotel necesitan definir varias políticas
para calcular las tarifas de las reservas, entre las cuales
se encuentran: descuentos por reservas tempranas, ofertas 
de último minuto, entre otras.

Ellos posiblemente tengan que modificar estas políticas para
adaptarlas a las diferentes temporadas de viaje, eventos o
incluso agregar políticas para futuras ofertas o programas 
para clientes frecuentes.

Para lograr esto, se realizó una aplicación usando Node.js
y el servicio 'Bussiness Rules', que ofrece facilidades 
para definir las políticas además de la escalabilidad que 
ofrece la plataforma Bluemix.

Pasos:
- Definir XOM (Execution Object Model)
Es el runtime contra el cual las reglas son ejecutadas.
ODM soporta modelos construidos en: XML o Java, en este
caso se usó JAVA.

- Se definieron 2 clases: Hotel y Result
Hotel, cuenta con información básica como: nombre, ciudad
y tarifa de los hoteles.

Result define la respuesta que obtendrá el cliente ante una
solicitud, cuenta con datos como: Hotel, fecha de reserva,
fecha de salida y tarifa de reserva (será calculada por
el servicio de reglas del negocio).

- Especificar el Business Object Model (BOM)
Es el modelo donde las reglas son autorizadas, normalmente
se crea el modelo BOM de un XOM existente.

- Crear las reglas necesarias para dar a la aplicación la
lógica del negocio deseada.
En este caso se tiene la tabla de decisión: 'advanceReduction'
y el flujo 'bluebooking' 
la tabla 'advanceReduction' tiene los descuenteos dependiendo
de cuan temprano se hace una reservación.

Si es hecha al día siguiente de día actual se brinda un dscto
del 50% por oferta de úlimo minuto, si es hecha con una
anticipación de 7 días o más se brinda un dscto del 30% del
valor, mientras que si la reservación se hace entre 5 a 7
días no se ofrece ningún descuento adicional al precio.

el flujo 'bluebookin´define la ejecución como 2 tareas 
secuenciales 'initReservation' 'pricing', el primero 
inicializa una instancia de 'Result' que será retornada
después de qe las reglas sean ejecutadas.

el segundo, es decir, 'pricing', dispara la ejecución del 
descuento a ser aplicado.

- Definir el contrato entre la lógica del negocio y la
aplicación.
Los parámetros del Ruleset especifican esta relación.
Se tienen 3 parámetros de entrada:
instancia de 'Hotel', fecha entrada, fecha salida.

Un parámetro de salida que es pasado a la aplicación después
de la ejecución de la lógica, se pasa una instancia de la
clase 'Result'.

- Desplegar la lógica del negocio realizada al servicio de
Business Rules en Bluemix
En Eclipse elegir la perspectiva: 'Rule'.
Ir a la pestaña Rule Explorer, la cual guarda las reglas del
proyecto.
Ir a la sección Deploy and Integrate y pulsar el primer item
'Create RuleApp proyect'.
Se abrirá un asistente para agregar nuevos proyectos de reglas
Le damos un nombre al proyecto, y pulsamos finalizar.
Con esto, se creará un nuevo proyecto en el workspace
conteniendo las reglas (la carpeta se llama rules).

Para desplegar las reglas al servicio, seleccionamos
el proyecto con las 'RuleApp' click derecho, RuleApp, Deploy
En el asistente, seleccionar las opciones por defecto.
En el siguiente asistente elegir la creación de un servidor
temporal, y llenar la información con los datos proveídos
por el servicio Business Rules en Bluemix.
Clic en Finalizar y vemos el progreso en la Consola.

Con eso ya tendremos las reglas del negocio en el servicio
en Bluemix.

## Run the app locally

1. [Install Node.js][]
2. Download and extract the starter code from the Bluemix UI
3. cd into the app directory
4. Run `npm install` to install the app's dependencies
5. Run `npm start` to start the app
6. Access the running app in a browser at http://localhost:6001

[Install Node.js]: https://nodejs.org/en/download/


