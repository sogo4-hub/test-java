# Hello Servlet - Java Web Application

Una aplicación web Java que implementa un servlet que responde a peticiones GET con un mensaje JSON.

## Características

- ✅ Servlet Java que responde en la ruta `/hello`
- ✅ Respuesta en formato JSON con el mensaje "Hola desde el servlet!"
- ✅ Pruebas unitarias con JUnit y Mockito
- ✅ Configuración para despliegue en Apache Tomcat
- ✅ Configuración mediante anotaciones y web.xml

## Estructura del Proyecto

```
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/servlet/
│   │   │       └── HelloServlet.java        # Servlet principal
│   │   └── webapp/
│   │       └── WEB-INF/
│   │           └── web.xml                  # Configuración del servlet
│   └── test/
│       └── java/
│           └── com/example/servlet/
│               └── HelloServletTest.java    # Pruebas unitarias
└── pom.xml                                  # Configuración Maven
```

## Requisitos

- Java 11 o superior
- Maven 3.6+
- Apache Tomcat 9.0+ (para despliegue)

## Compilación y Pruebas

### Compilar el proyecto
```bash
mvn clean compile
```

### Ejecutar las pruebas
```bash
mvn test
```

### Generar el archivo WAR
```bash
mvn package
```

El archivo WAR se generará en `target/hello-servlet.war`

## Despliegue en Tomcat

1. Compila el proyecto y genera el WAR:
   ```bash
   mvn package
   ```

2. Copia el archivo WAR a la carpeta webapps de Tomcat:
   ```bash
   cp target/hello-servlet.war /path/to/tomcat/webapps/
   ```

3. Inicia o reinicia Tomcat:
   ```bash
   /path/to/tomcat/bin/catalina.sh run
   ```

4. Accede al servlet en:
   ```
   http://localhost:8080/hello-servlet/hello
   ```

## Respuesta del Servlet

El servlet responde con un objeto JSON:

```json
{
  "message": "Hola desde el servlet!"
}
```

## Pruebas Unitarias

Las pruebas verifican:
- ✅ Que el servlet devuelve JSON con el formato correcto
- ✅ Que se configura correctamente el Content-Type (`application/json`)
- ✅ Que el mensaje es el esperado ("Hola desde el servlet!")

## Tecnologías Utilizadas

- **Java Servlet API 4.0.1** - API de servlets
- **Gson 2.8.9** - Serialización/deserialización JSON
- **JUnit 4.13.2** - Framework de pruebas
- **Mockito 4.11.0** - Mocking para pruebas
- **Maven** - Gestión de dependencias y construcción