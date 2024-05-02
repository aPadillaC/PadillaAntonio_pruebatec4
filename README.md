# AgenciaTurismo
<p dir="auto">Este proyecto es una aplicación para una agencia de turismo que desea gestionar las solicitudes de reservas para los diferentes tipos de paquetes que ofrece. La api está desarrollada en Java utilizando el framework Spring Boot y Maven para la gestión de dependencias. Está diseñada para proporcionar funcionalidades completas para la gestión de hoteles y vuelos, incluyendo la administración de habitaciones, reservas de habitaciones/vuelos, y la información de los clientes.</p>

<!-- Tecnologías Utilizadas -->

<h2 dir="auto">Tecnologías utilizadas</h2>
<ul dir="auto">
	<li>Back-end: <a target="_blank" rel="noopener noreferrer nofollow" href="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTHiXC1J0Tu16Edwsnf83qnm-O3DfPLxYmJw&usqp=CAU"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTHiXC1J0Tu16Edwsnf83qnm-O3DfPLxYmJw&usqp=CAU" alt="Java" data-canonical-src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSTHiXC1J0Tu16Edwsnf83qnm-O3DfPLxYmJw&usqp=CAU" style="max-width: 3%;"></a><a target="_blank" rel="noopener noreferrer nofollow" href="https://www.qindel.com/wp-content/uploads/2023/04/spring-boot.jpeg"><img src="https://www.qindel.com/wp-content/uploads/2023/04/spring-boot.jpeg" alt="Java JSP" data-canonical-src="https://www.qindel.com/wp-content/uploads/2023/04/spring-boot.jpeg" style="max-width: 3%; padding-left: 5px"></a></li>
	<br>
	<li>Base de datos: <a target="_blank" rel="noopener noreferrer nofollow" href=""><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSnO0xHQrNDbCdgefmnjSjUPAMIKBx2F-NOww&usqp=CAU" alt="MySQL" data-canonical-src="" style="max-width: 3%;"></a></li>
</ul>


<!-- Estructura del Proyecto -->

<h2 dir="auto">Estructura del Proyecto</h2>

<p>Los archivos de código fuente se encuentran en el directorio `src/main/java/com/hackaboss/agenciaTurismo`.</p<>

<p>El proyecto se divide en varios paquetes:<p>

<ul dir="auto">
    <li><strong>model: </strong> Contiene las clases de modelo que representan las entidades de la base de datos.</li>
	<br>
    <li><strong>dto: </strong>Los DTOs (Data Transfer Objects) son objetos que se utilizan para encapsular datos y enviarlos de un sistema a otro. En este proyecto, se utilizan varios DTOs para representar los diferentes componentes de la aplicación.</li>
	<br>
    <li><strong>controller: </strong> Contiene las clases controladoras que manejan las solicitudes HTTP.</li>
	<br>
    <li><strong>service: </strong> Contiene las clases de servicio que implementan la lógica de negocio.</li>
	<br>
    <li><strong>repository: </strong>Contiene las interfaces de repositorio que se utilizan para interactuar con la base de datos. Estas interfaces extienden JpaRepository o CrudRepository de Spring Data JPA, lo que proporciona métodos para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en las entidades.  </li>
	<br>
    <li><strong>security: </strong>Contiene las clases que implementan la seguridad y la autenticación en la aplicación. Esto incluye la configuración de seguridad, los filtros de autenticación, etc.</li>
	<br>
    <li><strong>exception: </strong>Contiene las clases de excepción personalizadas que se utilizan para manejar errores y excepciones en la aplicación. Estas excepciones personalizadas permiten proporcionar mensajes de error más descriptivos y específicos al usuario.</li>
</ul>

<!-- Funcionalidades Detalladas -->

<h2 dir="auto">Funcionalidades Detalladas</h2>
<br>

<h3>* Hoteles </h3>

<p>El controlador HotelController es la interfaz entre el cliente y el sistema. Maneja las solicitudes HTTP y delega la lógica de negocio al servicio IHotelService.</p>

<p>El controlador expone los siguientes endpoints:</p>
<ul dir="auto">
	<li><strong>POST</strong> /agency/hotels/new: Añade un nuevo hotel.</li>
    <li><strong>GET</strong> /agency/hotels: Obtiene una lista de todos los hoteles.</li>
    <li><strong>GET</strong> /agency/hotels/{hotelId}: Busca un hotel por su ID.</li>
    <li><strong>POST</strong> /agency/hotels/{hotelId}/rooms/new: Añade una nueva habitación a un hotel.</li>
    <li><strong>PUT</strong> /agency/hotels/edit/{hotelId}: Actualiza la información de un hotel.</li>
    <li><strong>PUT</strong> /agency/hotels/{hotelId}/rooms/edit/{roomId}: Actualiza la información de una habitación en un hotel.</li>
    <li><strong>GET</strong> /agency/hotels/{hotelId}/rooms/{roomId}: Busca una habitación por su ID en un hotel.</li>
    <li><strong>DELETE</strong> /agency/hotels/delete/{hotelId}: Elimina un hotel.</li>
    <li><strong>DELETE</strong> /agency/hotels/{hotelId}/rooms/delete/{roomId}: Elimina una habitación de un hotel.</li>
    <li><strong>GET</strong> /agency/hotels/rooms: Busca habitaciones por ciudad y rango de fechas.</li>
    <li><strong>POST</strong> /agency/hotels/{roomId}/rooms-booking/new: Añade una nueva reserva de habitación.</li>
    <li><strong>GET</strong> /agency/hotels/rooms-booking: Obtiene una lista de todas las reservas de habitaciones.</li>
    <li><strong>DELETE</strong> /agency/hotels/rooms-booking/delete/{roomBookingId}: Elimina una reserva de habitación.</li>
    <li><strong>PUT</strong> /agency/hotels/rooms-booking/edit/{roomBookingId}: Actualiza una reserva de habitación.</li>
    <li><strong>PUT</strong> /agency/hotels/rooms-booking/complete/{roomBookingId}: Completa una reserva de habitación.</li>
    <li><strong>POST</strong> /agency/hotels/new-list: Añade una lista de hoteles. (solo para precargar la BBDD)</li>
    <li><strong>POST</strong> /agency/hotels/{hotelId}/rooms/new-list: Añade una lista de habitaciones a un hotel. (solo para precargar la BBDD)</li>
    <li><strong>GET</strong> /agency/hotels/{hotelId}/rooms-booking: Obtiene una lista de reservas de habitaciones por ID de hotel.</li>
</ul>

<br>

<h3>* Vuelos </h3>

<p>El controlador FlightController es la interfaz entre el cliente y el sistema. Maneja las solicitudes HTTP y delega la lógica de negocio al servicio IFlightService.</p>

<p>El controlador expone los siguientes endpoints:</p>

<ul dir="auto">
    <li><strong>POST</strong> /agency/flights/new: Añade un nuevo vuelo.</li>
    <li><strong>GET</strong> /agency/flights: Obtiene una lista de todos los vuelos.</li>
    <li><strong>GET</strong> /agency/flights/{flightId}: Busca un vuelo por su ID.</li>
    <li><strong>PUT</strong> /agency/flights/edit/{flightId}: Actualiza la información de un vuelo.</li>
    <li><strong>DELETE</strong> /agency/flights/delete/{flightId}: Elimina un vuelo.</li>
    <li><strong>GET</strong> /agency/flights/search: Obtiene un vuelo por destino, origen, fecha ida y fecha vuelta.</li>
    <li><strong>POST</strong> /agency/flights/{flightId}/flight-booking/new: Añade una nueva reserva de vuelo.</li>
    <li><strong>GET</strong> /agency/flights/{flightId}/flight-booking: Obtiene las reservas de un vuelo por su ID.</li>
    <li><strong>PUT</strong> /agency/flights/flight-booking/edit/{flightBookingId}: Actualiza una reserva de vuelo.</li>
    <li><strong>DELETE</strong> /agency/flights/flight-booking/delete/{flightBookingId}: Elimina una reserva de vuelo.</li>
    <li><strong>POST</strong> /agency/flights/newList: Añade una lista de vuelos.</li>
</ul>


<!-- DTOs -->


<!-- Instalación y configuración -->

<h2 dir="auto">Instalación y configuración</h2>
<b>Para Windows</b>
<ul dir="auto"><b></b>
	<li>Visita el sitio web oficial de Oracle, e instala  <a href="https://www.oracle.com/java/technologies/downloads/#java17">JDK</a> en el equipo.</li>
	<li>Una vez completada la instalación, verifica que el JDK esté correctamente configurado ejecutando java -version en la línea de comandos de la terminal de su PC.</li>
</ul>
<br>
<b>Para macOS</b>
<ul dir="auto">
<li>Abre la Terminal.</li>
<li>Instala Homebrew si aún no lo tienes: /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"</li>
<li>Usa Homebrew para instalar el JDK: brew install openjdk</li>
<li>Verifica la instalación ejecutando java -version en la Terminal.</li>
</ul>
<br>
<b>Instalar Mysql y mysql workbench para la Base de Datos</b>
   <ul>
    <li><a href="https://dev.mysql.com/doc/refman/8.0/en/windows-installation.html">Instalación Windows</a></li>
    <li><a href="https://dev.mysql.com/doc/refman/5.7/en/macos-installation-pkg.html">Instalación Mac</a></li>
    <li>En la carpeta del proyecto incluimos el script para crear la base de datos, si está Workbench instalado correctamente solo hay que importar el archivo que contiene estructura y los datos de la BBDD predeterminados para las pruebas.</li>
  </ul>



<!-- EJECUCIÓN -->

<h2 dir="auto">Abrir y Ejecutar Proyecto</h2>
<ul dir="auto">
    <li>Clona el repositorio</li>
  	<li>Abrir proyecto en su IDE</li>
	<li>Actualiza dependencias si fuera necesario</li>
	<li>Ejecuta el programa mediante el archivo `AgenciaTurismoApplication.java`
</ul>



<!-- BBDD -->

<h2 dir="auto">Información de la Base de Datos y tablas</h2>
<ul dir="auto">
	<li><b>Hotel:</b> La entidad Hotel representa un hotel en la aplicación. Cada hotel tiene un ID único, un nombre, una ciudad, un código de hotel y una lista de habitaciones. El código del hotel se genera a partir del nombre del hotel, la ciudad y un número. Cada hotel puede tener varias habitaciones (Relación OneToMany con Room)</li>
	<br> 
	<li><b>Room:</b> La entidad Room representa una habitación en un hotel. Cada habitación tiene un ID único, un tipo de habitación, un precio de habitación, un código de habitación, fechas de disponibilidad y una lista de reservas de habitaciones. El código de la habitación se genera a partir del código del hotel y un número. Cada habitación puede tener varias reservas. (Relación OneToMany con RoomBooking)</li>
	<br> 
	<li><b>RoomBooking:</b> La entidad RoomBooking representa una reserva de habitación. Cada reserva de habitación tiene un ID único, un código de reserva, fechas de reserva, y está asociada a una habitación y a un cliente. El código de reserva se genera a partir del código de la habitación y un número.</li>
	<br> 
	<li><b>Flight:</b> La entidad Flight representa un vuelo en la aplicación. Cada vuelo tiene un ID único, un código de vuelo, un origen, un destino, una fecha, asientos disponibles y una lista de reservas de vuelos. El código de vuelo se genera a partir del origen, el destino y un número. Cada vuelo puede tener varias reservas (Relación OneToMany con FlightBooking). </li>
	<br> 
	<li><b>FlightBooking:</b> La entidad FlightBooking representa una reserva de vuelo. Cada reserva de vuelo tiene un ID único, un código de reserva, un tipo de asiento, un precio de asiento y está asociada a un vuelo y a una lista de clientes (Relación ManyToMany con Client). El código de reserva se genera a partir del código de vuelo y un número. </li>
	<br> 
	<li><b>Client:</b> La entidad Client representa un cliente en la aplicación. Cada cliente tiene un ID único, un nombre, un apellido, un NIF, un correo electrónico y listas de reservas de habitaciones (Relación OneToMany) y vuelos (Relación ManyToMany). </li>

</ul>


<!-- Uso de Swagger -->

<h2>Uso de Swagger</h2>

<p>Para ver la documentación de la API en Swagger hay que acceder a la url <a href="http://localhost:8080/doc/swagger-ui/index.html">http://localhost:8080/doc/swagger-ui/index.html</a>. Para las rutas protegidas con la autenticación es necesario validarse, para ello en el archivo applicatio.properties aparece un usuario y contraseña por defecto para ello.</p>


<!-- SUPUESTOS -->

<h2 dir="auto">Supuestos aplicados por el desarrollador</h2>
<ul dir="auto">
	<li> No se ha considerado necesario implantar un borrado/edicion de un cliente ya que según se interpreta en la consigna no se aprecia la necesidad al enfocarse esta principalmente en las entidades de reservas así como las de Hotel, Habitación y Vuelos.</li>
	<br>
	<li> Se ha implementado dos rutas Controller principales (FlightController y HotelController) por considerarse que por un lado Room y RoomBooking guarda estrecha relación con Hotel y ver más legible la ruta fija "/agency/hotels" para implementar todos los endPoint/métodos correspondientes a esas entidades. Y por otro lado FlightBooking y Flight en la ruta fija "/agency/flights/.</li>
	<br>
	<li> Para la reserva de una habitación, se ha supuesto que solo aparezca como cliente la persona que reserva como tal y no los posibles acompañantes.</li>
	<br>
    <li> Para la autenticación se ha implementado un código que simule más la funcionalidad real utilizando JWT. (en el siguiente apartado explicaremos mejor la forma de autenticarse y poder acceder a las rutas protegidas). Se ha tenido que crear un AdminController, un modelo de User, así como, una carpeta security con los archivos de configuración.</li>
	<br>
	<li>Para la busqueda de vuelos por por fecha, origen y destino, solo se muestra los resultados que cumplen con la busqueda. Para hacer el viaje de "vuelta" sería necesario invertir el orden de origen y destino tal y como indica la consigna.</li>
	<br>
	<li>Se ha desarrollado borrado lógico (mediante el campo isDeleted) para toda la aplicación para no perder ningun dato necesario en el futuro a modo de plan de negocio. Cuando se intenta borrar un Hotel, habitación o vuelo y existe una reserva activa en ese momento, se notifica mediante una exceptión. En este caso habría que borrar, o marcar como completada antes de proceder con el borrado lógico.</li>
	<br>
	<li>Cuando se edita un hotel cambia automáticamente el codigo de la habitación y del hotel. Lo mismo pasa cuando se edita una habitación que cambia su código. Los códigos de reserva no se ven afectados ya que es un código que posee el cliente y no sería una buena práctica estar informándole de esos cambios que no le corresponde.</li>
	<br>
	<li>De las habitaciones solo podemos editar el precio y el tipo, la fecha no está disponible para su edición ya que se consideran que solo estarán disponibles en determinados momentos predefinidos.</li>
	<br>
	<li>Para la edición de una reserva de habitación, se ha supuesto que el cliente solamente pueda modificar en este caso las fechas de la reserva. Y para una reserva de un vuelo se podría modificar el tipo de asiento así como el precio del mismo porque cambiaría de categoría. El cliente se ha optado por no poder modificar sus datos y tener que optar por ello a cancelar/borrar la reserva y volver a realizarla correctamente.</li>
	<br>
	<li>Para las habitaciones se ha utilizado los campos "complete" y "isBooked" respectivamente para especificar que una reserva se ha llevado a cabo y esta finalizada, y para indicar que la habitación tiene reserva todos los días que se encuentra disponible.</li>
	<br>
	<li>Para la realización de las reservas (tanto de habitaciones como de vuelos) se le pasa por el body un DTO del Booking correspondiente con los datos propios de la reserva ya que el resto de parametros lo obtenemos a partir del Id pasado por el PathVariable.</li>
	<br>
	<li>Se ha desarrollado consultas a la BBDD propias a parte de las proporcionadas por JPA para realizar una mejor optimización.</li>
	<br>
</ul>


<!-- Paso a paso autenticación -->

<h2 dir="auto">Capturas y Demostración (Login)</h2>

<ul dir="auto">
	<li>Vamos a la ruta "http://localhost:8080/agency/admin/login" y pasamos por el body el user y password: 
		<a target="_blank" rel="noopener noreferrer" href="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso1.jpg"><img src="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso1.jpg" alt="Paso 1" style="max-width: 100%;"></a>   
	</li>
	<li>Obtenemos el token generado con JWT: 
		<a target="_blank" rel="noopener noreferrer" href="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso2.jpg"><img src="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso2.jpg" alt="Paso 2" style="max-width: 100%;"></a>   
	</li>
	<li>Vamos a una de las rutas protegidas y compromamos que sin meter el token no me deja realizar la petición solicitada
		<a target="_blank" rel="noopener noreferrer" href="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso3.jpg"><img src="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso3.jpg" alt="Paso 3" style="max-width: 100%;"></a>   
	</li>
	<li>Pegamos en headers -> Key(Authorization) -> Value(token obtenido) 
		<a target="_blank" rel="noopener noreferrer" href="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso4.jpg"><img src="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso4.jpg" alt="Paso 4" style="max-width: 100%;"></a>   
	</li>       
	<li>Le damos a enviar y comprobamos que ahora si se realiza la gestión solicitada: 
		<a target="_blank" rel="noopener noreferrer" href="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso5.jpg"><img src="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso5.jpg" alt="Paso 5" style="max-width: 100%;"></a>    
	</li>    
	<li>Dejamos pasar unos minutos y volvemos a intentar repetir la misma acción. En este caso no nos permitiría realizarla de nuevo ya que la vida del token expiró. Habría que volver a realizar el login y seguir los mismos pasos. 
		<a target="_blank" rel="noopener noreferrer" href="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso6.jpg"><img src="https://github.com/aPadillaC/PadillaAntonio_pruebatec4/blob/master/screenshots/Paso6.jpg" alt="Paso 6" style="max-width: 100%;"></a>   
	</li>    
</ul>



<!-- DESARROLLADOR -->

<h2 dir="auto">Desarrollador</h2>
<p dir="auto">Esta aplicación ha sido desarrollada por: </p>
<ul dir="auto">
	<li><a href="https://www.linkedin.com/in/antonio-padilla-carrillo" rel="nofollow">Antonio Padilla</a></li>
</ul>

<h2 dir="auto"><a id="user-content-licencia" class="anchor" aria-hidden="true" href="#licencia"><svg class="octicon octicon-link" viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true"><path fill-rule="evenodd" d="M7.775 3.275a.75.75 0 001.06 1.06l1.25-1.25a2 2 0 112.83 2.83l-2.5 2.5a2 2 0 01-2.83 0 .75.75 0 00-1.06 1.06 3.5 3.5 0 004.95 0l2.5-2.5a3.5 3.5 0 00-4.95-4.95l-1.25 1.25zm-4.69 9.64a2 2 0 010-2.83l2.5-2.5a2 2 0 012.83 0 .75.75 0 001.06-1.06 3.5 3.5 0 00-4.95 0l-2.5 2.5a3.5 3.5 0 004.95 4.95l1.25-1.25a.75.75 0 00-1.06-1.06l-1.25 1.25a2 2 0 01-2.83 0z"></path></svg></a>Licencia de uso</h2>

<p>El código del proyecto aquí alojado se encuentra bajo licencia <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/" rel="nofollow"><img alt="Licencia Creative Commons" src="https://camo.githubusercontent.com/f05d4039b67688cfdf339d2a445ad686a60551f9891734c418f7096184de5fac/68747470733a2f2f692e6372656174697665636f6d6d6f6e732e6f72672f6c2f62792d6e632d73612f342e302f38387833312e706e67" data-canonical-src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" style="max-width: 100%;"></a><br> <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/" rel="nofollow">Licencia Creative Commons Atribución-NoComercial-CompartirIgual 4.0 Internacional</a></p>




