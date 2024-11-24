
# Plataforma Web de Rehabilitación a Distancia

## Descripción

La **Plataforma Web de Rehabilitación a Distancia** es una aplicación desarrollada como parte del trabajo de la asignatura *Ingeniería Web*. El objetivo de esta plataforma es ofrecer un sistema de rehabilitación físico y seguimiento de pacientes a través de una interfaz web accesible tanto para **pacientes** como para **profesionales médicos**. 

La plataforma permite realizar seguimientos remotos de los pacientes, enviar ejercicios personalizados, realizar sesiones de terapia a distancia y mantener un flujo de comunicación entre pacientes y médicos. La aplicación es responsiva y accesible desde dispositivos móviles y de escritorio.

### Funcionalidades principales:
- **Autenticación y autorización** de usuarios (pacientes y médicos).
- **Registro y gestión de pacientes** y profesionales médicos.
- **Interacción de pacientes con ejercicios** asignados por los profesionales médicos.
- **Seguimiento de progreso** de pacientes a través de gráficos y estadísticas.
- **Notificaciones** para avisos y recordatorios de ejercicios.
- **Panel de administración** para médicos para gestionar pacientes y sus ejercicios.

## Tecnologías Utilizadas

- **Frontend:**
  - **Angular**: Framework de desarrollo web para crear una interfaz dinámica y responsiva.
  - **HTML/CSS**: Lenguajes de marcado y estilos para la estructura y diseño de la aplicación.
  - **Bootstrap**: Framework de CSS para facilitar el diseño responsivo y componentes reutilizables.
  - **RxJS**: Para la gestión de eventos asincrónicos en Angular.

- **Backend:**
  - **Spring Boot**: Framework basado en Java para el desarrollo de aplicaciones empresariales, que proporciona una configuración rápida y sencilla para crear microservicios RESTful.
  - **Spring Security**: Para gestionar la autenticación y autorización de los usuarios (pacientes y médicos).
  - **JPA (Java Persistence API)**: Para gestionar la base de datos de manera eficiente.
  - **H2 Database**: Base de datos en memoria utilizada durante el desarrollo.

- **Otros:**
  - **Maven**: Herramienta de gestión de dependencias para el proyecto backend (Spring Boot).
  - **Node.js y npm**: Para gestionar las dependencias de Angular y ejecutar el servidor de desarrollo.
  
## Instalación

### 1. Configuración del Backend (Spring Boot)

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/tu_usuario/rehabilitacion-a-distancia.git
   cd rehabilitacion-a-distancia/backend
   ```

2. **Compila el proyecto**:
   Si tienes Maven instalado, ejecuta el siguiente comando en la terminal:
   ```bash
   mvn clean install
   ```

3. **Ejecuta el servidor de Spring Boot**:
   Ejecuta la aplicación con el siguiente comando:
   ```bash
   mvn spring-boot:run
   ```

   El servidor se levantará en `http://localhost:8080`.

### 2. Configuración del Frontend (Angular)

1. **Clona el repositorio**:
   Si aún no has clonado el repositorio, hazlo en una carpeta separada:
   ```bash
   git clone https://github.com/tu_usuario/rehabilitacion-a-distancia.git
   cd rehabilitacion-a-distancia/frontend
   ```

2. **Instala las dependencias de Angular**:
   Asegúrate de tener **Node.js** y **npm** instalados. Luego, ejecuta el siguiente comando:
   ```bash
   npm install
   ```

3. **Ejecuta el servidor de desarrollo de Angular**:
   ```bash
   ng serve
   ```

   La aplicación frontend estará disponible en `http://localhost:4200`.

## Integrantes del Grupo

- **Cuevas Rodríguez, Marta**
- **de Pablo, Diego**
- **Silva Rodríguez, Alejandro**
- **Soriano Muñoz, Juan Ignacio**

## Estructura del Proyecto

### Backend (Spring Boot)
- `src/main/java/com/rehabilitacion`: Código fuente principal de la aplicación backend.
- `src/main/resources`: Archivos de configuración como `application.properties`.
- `src/test/java/com/rehabilitacion`: Pruebas unitarias y de integración para el backend.

### Frontend (Angular)
- `src/app`: Código fuente de la aplicación frontend.
  - `auth`: Módulo de autenticación (login, registro, etc.).
  - `dashboard`: Panel principal para pacientes y médicos.
  - `shared`: Componentes reutilizables (como formularios, botones, etc.).
  - `services`: Servicios para interactuar con el backend.

## Autenticación y Autorización

La plataforma utiliza **Spring Security** para gestionar la autenticación de usuarios y controlar el acceso a las rutas protegidas en función de los roles de usuario (Paciente o Médico). Los usuarios deben iniciar sesión para acceder a sus respectivos paneles.

- **Médico**: Accede al panel de administración, donde puede gestionar los pacientes y asignarles ejercicios.
- **Paciente**: Accede al panel personal donde puede ver los ejercicios asignados y su progreso.

## Contribución

Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork de este repositorio.
2. Crea una nueva rama (`git checkout -b feature-nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadida nueva funcionalidad'`).
4. Envía un pull request para revisar tus cambios.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT.

## Contacto

Para cualquier duda o pregunta, por favor contacta a los integrantes del grupo a través de sus respectivos correos electrónicos.

- **Cuevas Rodríguez, Marta**: [email@example.com](mailto:email@example.com)
- **de Pablo, Diego**: [email@example.com](mailto:email@example.com)
- **Silva Rodríguez, Alejandro**: [email@example.com](mailto:email@example.com)
- **Soriano Muñoz, Juan Ignacio**: [email@example.com](mailto:email@example.com)
