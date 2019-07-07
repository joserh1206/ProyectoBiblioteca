# ProyectoBiblioteca
Herramienta que permite la administración de los libros, revistas y clientes de las bibliotecas. Así como un conjunto de consultas útiles para la toma de decisiones. 

La Biblioteca cuenta con un conjunto de clientes que podrán pedir literatura prestada y comprar algunas revistas que vende la Biblioteca. La biblioteca tiene un nombre específico, una dirección física, un teléfono, la dirección del sitio en Internet y el nombre del bibliotecario a cargo. Al crearla se le debe especificar estos datos. Una vez creada, se podrá registrar el resto de la información. Cada uno de los libros y revistas (venta y préstamo) son registradas en el sistema y obtienen un identificador único y consecutivo. A partir de estos datos se podrán realizar ventas y préstamos de la literatura registrada en el sistema. 
El registro de los libros y revistas se podrá realizar de dos maneras: manual y automática. La forma manual será a través del sistema y la forma automática será a través de la carga de un archivo Excel que contendrá la información de libros y revistas de la biblioteca. El sistema debe llevar mucho control de los préstamos realizados de manera que se pueda cobrar las multas correspondientes e identificar clientes morosos para no prestarles más libros o revistas.  
Por defecto todos los libros o revistas que se registran en el sistema tienen el estado de “disponibles”, de manera que cualquier cliente puede pedirlos prestados o comprarlos, si es el caso de una revista para venta. Si se realiza alguna acción sobre un artículo el sistema debe saber el estado actual de este, ya sea vendida o prestada. De esta forma no se podrá realizar otra transacción a este artículo, a menos que sea devuelto. El sistema debe permitir configurar algunos parámetros importantes. Entre estos se encuentra el registro de la fecha actual del sistema y la cantidad de días de préstamos para los libros y las revistas. Para permitir una comunicación con los clientes, el sistema podrá enviar un mensaje electrónico a aquellos clientes que no hayan devuelto algún artículo y su fecha de vencimiento del préstamo ya haya pasado. Todos los datos generados a partir de la ejecución de la tarea programada deben ser gestionados en memoria principal, no será requerida la persistencia de datos.
El Sistema de Biblioteca deberá ofrecer las siguientes transacciones al usuario:
1) Registro de clientes (todos los datos son requeridos)
a. Los datos a solicitar son: Nombre, correo, cédula de identificación y teléfono.
b. Se debe validar el número telefónico del cliente (valida únicamente la cantidad de dígitos, será responsabilidad del cliente ingresar un número telefónico que sí exista)
c. Se debe validar que el formato de la dirección de correo cumpla con las condiciones que toda cuenta de correo debe cumplir. En caso que todos los datos requeridos hayan sido ingresados de forma correcta, el Sistema debe proceder a crear el cliente.
2) Registro Manual de libros (todos los datos son requeridos)
a. Los datos a solicitar son: Nombre, autor, año, editorial, tipo (novela, teatro, poesía, infantil, ensayos). 
b. Cada Libro al ser registrado recibirá un identificador del sistema. En el caso de los libros es con el siguiente formato: L- XXX donde XXX es un consecutivo.
c. Cada libro tendrá un estado: disponible o prestado. Al registrarse quedará con estado disponible. En caso que todos los datos requeridos hayan sido ingresados de forma correcta, el Sistema debe proceder a crear el libro.
3) Registro Automático de libros
El sistema debe cargar el archivo y registrar todos los registros.
4) Registro manual de Revistas (todos los datos son requeridos)
a. Los datos a solicitar son: Nombre, número, año, tipo (venta o préstamo).
b. Cada Revista al ser registrada recibirá un identificador del sistema. En el caso de las Revistas es con el siguiente formato: R- XXX donde XXX es un consecutivo.
c. En el caso de las revistas de venta se debe registrar su costo en colones. d. Cada revista tendrá un estado: disponible o prestado/vendido. Al registrarse quedará con estado disponible.
5) Registro Automático de revistas
6) Definir Parámetros
a. El usuario puede modificar los siguientes parámetros: fecha actual, días de
préstamo de libros y días de préstamo de revistas.
b. Si el usuario no ha cambiado la fecha actual, debe salir por defecto el día según la
fecha del sistema
c. Por defecto los días de préstamo de libros son 10 y los días de préstamo de
revistas son 5.
7) Registrar Préstamo
a. El Sistema solicita el identificador del cliente
b. EL SISTEMA verifica que el identificar sea de un cliente previamente registrada en el sistema.
c. El SISTEMA permite que el usuario registre una lista de libros o revistas para
préstamo.
d. El préstamo se debe registrar con la fecha actual del sistema de biblioteca y se le
debe aplicar los días establecidos en los parámetros.
e. El sistema no debe prestar ningún tipo de literatura a clientes que tienen deudas o
no han devuelto libros donde su fecha de devolución ya pasó.
En caso que todos los datos requeridos hayan sido ingresados de forma correcta,
el SISTEMA debe responder al usuario dando un listado de los artículos prestados
para comprobación del usuario.
8) Compra de Revista
a. El Sistema solicita el identificador del cliente
b. EL SISTEMA verifica que el identificar sea de un cliente previamente registrada en
el sistema.
c. El SISTEMA permite que el usuario registre una lista revistas para venta. Debe
validar que los códigos de identificación sean válidos para este tipo de transacción.
d. La venta se debe registrar con la fecha actual del sistema de biblioteca.
e. El sistema sí debe permitir vender revistas aunque el cliente tenga deudas. En caso que todos los datos requeridos hayan sido ingresados de forma correcta, el SISTEMA debe responder al usuario dando un listado de los artículos vendidos para comprobación del usuario con el monto de cada revista.
9) Devolver Literatura.
a. El Sistema solicita el identificador del cliente
b. EL SISTEMA verifica que el identificar sea de un cliente previamente registrada en
el sistema.
c. El SISTEMA permite que el usuario registre una lista de literatura. Debe validar que los códigos de identificación sean válidos para este tipo de transacción y que hayan sido prestados a ese cliente.
d. La devolución se debe registrar con la fecha actual del sistema de biblioteca.
e. En el caso que haya libros con multa el sistema debe calcular las Multas para cada uno de los artículos “atrasados”. Estas multas deben ser registradas en el sistema.
f. El sistema debe actualizar el estado de la literatura devuelta.  g. El sistema una vez realizada la transacción y en el caso que haya multas debe preguntar si desea pagar. En el caso que si, automáticamente debe ir a la siguiente funcionalidad. En caso que todos los datos requeridos hayan sido ingresados de forma correcta, el SISTEMA debe responder al usuario dando un listado de los artículos devueltos y la información de las multas, en los casos que aplique.
10) Pagar Multa.
a. El Sistema solicita el identificador del cliente. En el caso que se desee pagar la multa desde la funcionalidad anterior no es necesario que pregunte el identificador del cliente.
b. EL SISTEMA verifica que el identificar sea de un cliente previamente registrada en el sistema.
c. El SISTEMA despliega una lista de todos los artículos y multas no pagadas por el cliente.
d. El sistema debe desplegar el monto para cada una de las deudas así como el total.
e. El usuario debe pagar la totalidad de multas activas.
f. El sistema debe actualizar el estado de las multas
11) Calcular Multas
a. El SISTEMA calcula a partir de los préstamos activos a todos los clientes, cuáles deberían haber devuelto algún libro y no lo han hecho.
b. El sistema enviar un correo electrónico a todos los clientes que tendrán multas.
c. A cada cliente le enviará un desglose de los artículos que debería haber devuelto. Con la fecha en que lo debería haber devuelto y la multa al día en que se está enviando el correo, según la fecha del sistema de biblioteca.
d. El sistema no debe registrar estas multas en el sistema, es solo un cálculo.
e. El sistema debe desplegar al usuario una lista de las personas a las que se les envió
el correo electrónico.
12) Consultar Libros
a. El usuario puede escoger entre listar todos los libros, los disponibles o los prestados.
b. El sistema lista todos los libros según la categoría seleccionada.
c. Debe desplegar toda la información del libro, su estado y en el caso de los prestados el nombre del cliente que tiene el libro.
d. La lista debe ser ordenada por el identificador del Libro.
13) Consultar Revistas
a. El usuario puede escoger entre listar todas las revistas, las disponibles, los prestados o las vendidas. 
b. El sistema lista todas las revistas según la categoría seleccionada. 
c. Debe desplegar toda la información de las revista, su estado y en el caso de los prestados o vendidos el nombre del cliente que tiene la revista.
d. La lista debe ser ordenada por el identificador de la revista.
14) Consultar Prestamos
a. El sistema solicita si se desea ver la lista de préstamos de revistas o de libros.
b. El sistema lista las revistas o libros prestados.
c. Debe listar toda la información del artículo, la fecha del préstamo, el cliente al que se le prestó, la fecha de la devolución (en el caso de los que ya hayan sido devueltos) y un indicador de si tuvo o tiene multa.
15) Consultar Deudas de un cliente:
a. El Sistema solicita el identificador del cliente
b. EL SISTEMA verifica que el identificar sea de un cliente previamente registrada en el sistema.
c. El sistema lista todas las deudas relacionadas al cliente.
d. Debe listar tanto las deudas pagadas o las que no ha pagado. Debe ser claro para el usuario saber cuáles son pagadas y cuales no.
e. El sistema debe listar el id del artículo, el nombre del artículo, los días en que se atraso, el monto en colones y el monto en dólares y si ya se pagó o no.
16) Salir
