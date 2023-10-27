# Nisum API usuario

Api RestFull de creación de usuario.

## Requisitos Previos

Asegúrarse de tener instalados los siguientes requisitos previos en tu sistema:

- Java 17 o superior
- Maven
- Base de datos H2
- Git Bash
- Postman

## Pasos para Probar la Aplicación

Sigue estos pasos para probar la aplicación:

1. Clona este repositorio en tu máquina local con GitBash:

   ```shell
   git clone https://github.com/david-ortiz-94/nisum-api-usuario.git
2. Navega al directorio de la aplicación descargada:
   ```shell
   cd nisum-api-usuario

3. Compila y empaqueta la aplicación utilizando Maven:
   ```shell
   mvn clean install

4. Ejecuta la aplicación Spring Boot:
   ```shell
   mvn spring-boot:run
   
5. Accede a la API REST utilizando la herramienta Postman. Utiliza el siguiente endpoint:

Registro de Usuario: POST http://localhost:8080/api/usuarios

Envía una solicitud JSON con los detalles del usuario para registrar un nuevo usuario.