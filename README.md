# Foro Alura 

Este proyecto funciona como back-end para una aplicación web tipo foro online. La aplicación considera diferentes endpoints con las cuales se pueden realizar diversas acciones tales como registrar usuarios, crear y consultar topicos, y agregar respuestas a los mismos. 

La aplicación back-end atendera las solicitudes en 4 diferentes controllers, uno para cada tabla de la base de datos.

## Tecnologías utilizadas:

  - [Eclipse](https://www.eclipse.org/)
  - [MySql](https://www.mysql.com/)
  - [Java](https://www.java.com/en/)

  - [Spring Security](https://start.spring.io/)
  - [Token JWT](https://jwt.io/)

## Diagrama entidad-relación de la base de datos

![Base de datos](./imagenes/Foro_alura_Diagrama.png)


La base de datos consta de 4 diferentes tablas. 

La tabla usuarios almacena la información relacionada al login del usuario y sirve como referencia de la pertenencia de una respuesta o un topico. 

La tabla cursos solamente contiene información de los cursos existentes dentro de la plataforma a la que pertenece el foro, de los cuales se pueden habrir topicos para solventar dudas o par ampliar la información existente. 

La tabla topicos es el punto central de la aplicación, ya que esta contendra la funcionalidad central del foro, al albergar los diferentes topicos abiertos por los usuarios. Los topicos pertenecen a un usuario, son sobre un curso de la plataforma y pueden contener respuestas a partir de las cuales puede cambiar el estado del topico (estos estados se explicaran mas adelante).

La tabla respuestas corresponde a las respuestas de los topicos, separadas gracias a su referencia al element topico correspondiente de cada respuesta.

En su conjunto, estas cuatro tablas permiten el uso de la aplicación web de forma funcional.

## Estados de un topico

Los topicos pueden estar en 4 diferentes estados:

 - NO_RESPONDIDO
 - NO_SOLUCIONADO
 - SOLUCIONADO
 - CERRADO

El estado NO_RESPONDIDO lo tendra cuandoel topico no tenga respuestas.
 
El estado NO_SOLUCIONADO lo tendra un topico cuando ya cuente con al menos una respuesta pero que ninguna de estas sea una solución al problema.
 
El estado SOLUCIONADO lo tendra un topico cuando este contenga alguna respuesta que este señañada como solución al topico.
 
El estado CERRADO solo lo tendra un topico cuando despues de un determinado tiempo el topico no haya sido solucionado ó a petición del autor del topico. Esta acción para esta aplicación solo estara disponible para realizarse de forma manual a traves del correspondiente endpoint de topicos.

## Uso de la aplicación

Para hacer uso de la aplicación en su entorno de desarrollo se recomienda el uso del IDE Eclipse. La aplicación al momento de ejecutarse despliega al mismo tiempo la documentación del proyecto, la cual hace uso de una url para ser desplegada al usuario.

La documentación por su naturaleza esta disponible en dos url segun su uso:

* Formato Swagger UI 

http://server:port/context-path/swagger-ui.html

* Formato Json

http://server:port/context-path/v3/api-docs

La documentacion por su naturaleza en su versión de interfaz de usuario permite la interacción con los endpoints de la aplicación, sin embargo se recomienda el uso de postman u otro software similar.