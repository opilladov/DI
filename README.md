<!-- # DI -->
<h1 align="center"> Spotify </h1>

![Spotify_Logo](https://github.com/user-attachments/assets/16a71a22-fd40-4c86-829f-430caf54a423)

![GitHub release (latest by date)](https://img.shields.io/github/v/release/usuario/proyecto)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/usuario/proyecto/main.yml)
![GitHub last commit](https://img.shields.io/github/last-commit/usuario/proyecto)
![GitHub contributors](https://img.shields.io/github/contributors/usuario/proyecto)
![GitHub license](https://img.shields.io/github/license/usuario/proyecto)

## Índice

1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Estado del Proyecto](#estado-del-proyecto)
3. [Demostración de Funciones y Aplicaciones](#demostración-de-funciones-y-aplicaciones)
4. [Acceso al Proyecto](#acceso-al-proyecto)
5. [Tecnologías Utilizadas](#tecnologías-utilizadas)
6. [Personas Contribuyentes](#personas-contribuyentes)
7. [Personas Desarrolladoras del Proyecto](#personas-desarrolladoras-del-proyecto)
8. [Licencia](#licencia)

---

## Descripción del Proyecto
Spotify es una plataforma de streaming de música, podcasts y videos que permite a los usuarios acceder a una extensa biblioteca de contenido multimedia de forma instantánea. Fundada en 2006, ofrece tanto un servicio gratuito con anuncios como opciones de suscripción premium sin publicidad, permitiendo la reproducción de música a demanda, la creación de playlists personalizadas, y la descarga de contenido para escuchar sin conexión. Con millones de canciones y podcasts disponibles, Spotify es una de las plataformas líderes a nivel mundial en la industria del streaming musical.

## Estado del Proyecto
El proyecto de integración de Spotify se encuentra en la Fase 2 de desarrollo, con un 70% completado. Hasta ahora, se ha logrado la autenticación de usuarios con cuentas de Spotify y la funcionalidad de búsqueda de canciones está al 80%, mientras que la reproducción básica de música está al 60%. Aún se están ajustando los controles avanzados de reproducción y la creación de listas de reproducción dentro de la aplicación.

Los próximos pasos incluyen completar la funcionalidad de búsqueda y reproducción, además de comenzar con las recomendaciones personalizadas y características avanzadas, como la sincronización de listas de reproducción. Aunque el proyecto está progresando según el cronograma, uno de los desafíos principales es la limitación de la API de Spotify en cuanto a la tasa de solicitudes, lo que afecta las pruebas de carga. La fecha estimada para el lanzamiento beta es el 10 de octubre de 2024, con el lanzamiento oficial planeado para el 30 de octubre.

## Demostración de Funciones y Aplicaciones


## Acceso al Proyecto
Para acceder al proyecto de integración de Spotify, sigue estos pasos:

1. **Registro y Autenticación**
   - **Cuenta de Spotify**: Asegúrate de tener una cuenta de Spotify activa. Puedes crear una [aquí](https://www.spotify.com/signup/).
   - **Acceso al Repositorio**: Solicita acceso al repositorio del proyecto a través de [GitHub](https://github.com/). Asegúrate de proporcionar tu dirección de correo electrónico asociada con tu cuenta de GitHub para recibir una invitación.

2. **Clonación del Repositorio**
   - Una vez que recibas acceso, clona el repositorio usando el siguiente comando en tu terminal:
     ```bash
     git clone https://github.com/tu-usuario/nombre-del-repositorio.git
     ```

3. **Configuración del Entorno de Desarrollo**
   - **Instalación de Dependencias**: Navega al directorio del proyecto y ejecuta:
     ```bash
     npm install
     ```
   - **Configuración de Variables de Entorno**: Crea un archivo `.env` en la raíz del proyecto y añade tus credenciales de Spotify y otras configuraciones necesarias. Puedes usar el archivo `.env.example` como referencia.

4. **Ejecución del Proyecto**
   - **Iniciar el Servidor**: Para iniciar el servidor de desarrollo, utiliza:
     ```bash
     npm start
     ```
   - **Verificación**: Accede a la aplicación en tu navegador en `http://localhost:3000` para verificar que todo esté funcionando correctamente.

5. **Documentación Adicional**
   - Revisa la documentación en la carpeta `/docs` del repositorio para detalles específicos sobre el uso de la API de Spotify y la integración del proyecto.

6. **Soporte y Contribuciones**
   - Si encuentras problemas o tienes preguntas, dirígete a la sección de [Issues](https://github.com/tu-usuario/nombre-del-repositorio/issues) del repositorio o contacta a los mantenedores del proyecto a través del correo electrónico proporcionado en la documentación.

Para más detalles sobre el proyecto y actualizaciones, sigue las instrucciones en el repositorio y las comunicaciones del equipo de desarrollo.

## Tecnologías Utilizadas
El proyecto de integración de Spotify emplea **React.js** para el desarrollo del frontend, aprovechando su arquitectura basada en componentes para crear una interfaz dinámica y responsiva. Tecnologías como **HTML5/CSS3** y librerías como **Material UI** o **Tailwind CSS** ayudan a lograr un diseño atractivo y funcional. **Redux** se utiliza para la gestión del estado global de la aplicación.

En el backend, **Node.js** y **Express.js** permiten crear una API RESTful que interactúa con la **Spotify Web API**. El protocolo **OAuth 2.0** garantiza la autenticación segura de los usuarios. Además, **MongoDB** se utiliza como base de datos NoSQL para almacenar información de usuarios y listas de reproducción, con **Mongoose** como ODM para facilitar las operaciones.

A nivel de infraestructura, se emplea **Docker** para la contenedorización, lo que facilita el despliegue en entornos consistentes, y **AWS** para el hosting y la escalabilidad. Finalmente, se usan herramientas como **Jenkins** o **GitLab CI** para automatizar los procesos de integración y despliegue continuo, mientras que **Jest** y **Postman** se utilizan para asegurar la calidad del código a través de pruebas unitarias y de API.

## Personas Contribuyentes
El proyecto de Spotify ha sido desarrollado y mejorado por un equipo diverso de ingenieros, diseñadores y líderes desde sus inicios. Algunas de las personas clave que contribuyeron a su creación son:

- **Daniel Ek** - Cofundador y CEO de Spotify. Daniel Ek es uno de los principales impulsores de la visión de Spotify como una plataforma de música en streaming. Su liderazgo ha sido fundamental en el crecimiento y expansión de la empresa.

- **Martin Lorentzon** - Cofundador y ex presidente de Spotify. Junto con Daniel Ek, Lorentzon ayudó a fundar Spotify en 2006 y desempeñó un papel clave en su financiación y crecimiento inicial.

- **Andreas Ehn** - Primer CTO de Spotify. Ehn fue uno de los primeros miembros del equipo técnico, ayudando a desarrollar la infraestructura y la arquitectura técnica de Spotify en sus primeras etapas.

- **Lars "Lash" Bengtsson** - Ingeniero de software sénior en los primeros días de Spotify, contribuyó al desarrollo de la plataforma y las tecnologías subyacentes.

- **Oskar Stål** - CTO de Spotify en el pasado, responsable de la evolución tecnológica y la expansión global de la plataforma.

Además del liderazgo, miles de ingenieros, desarrolladores de software, diseñadores UX/UI, expertos en datos y personal de operaciones han trabajado en el crecimiento y la mejora de Spotify a lo largo de los años, desde su creación hasta la actualidad.

## Personas Desarrolladoras del Proyecto
El desarrollo del proyecto de Spotify fue impulsado por un equipo clave de ingenieros y desarrolladores que contribuyeron al éxito de la plataforma desde sus primeras etapas. Algunas de estas personas clave incluyen:

- **Andreas Ehn** - Fue el primer CTO de Spotify y desempeñó un papel crucial en la creación de la infraestructura técnica de la plataforma. Lideró el equipo de ingeniería en las fases iniciales, ayudando a establecer las bases tecnológicas que permitieron a Spotify escalar rápidamente.

- **Lars "Lash" Bengtsson** - Ingeniero de software sénior en los primeros días de Spotify. Bengtsson ayudó a construir gran parte de la tecnología detrás de la plataforma, enfocándose en la arquitectura técnica y las operaciones backend.

- **Oskar Stål** - Posteriormente CTO de Spotify, Stål fue responsable de la evolución técnica de la plataforma, liderando los equipos que implementaron soluciones escalables a medida que la plataforma crecía a nivel global.

- **Niklas Ivarsson** - Lideró el equipo de licencias de música, un aspecto crucial del desarrollo de la plataforma, asegurando que Spotify pudiera ofrecer contenido legal y relevante a los usuarios.

- **Mattias Arrelid** - Ingeniero sénior en infraestructura, ayudó a optimizar la plataforma para manejar grandes volúmenes de datos y usuarios, lo cual fue vital para el crecimiento masivo de Spotify.

- **Erik Huggers** - Fue responsable de la expansión técnica y estratégica, ayudando a dirigir el enfoque en tecnologías avanzadas y nuevas funcionalidades en la plataforma.

A lo largo de los años, un equipo creciente de desarrolladores e ingenieros ha trabajado en diversos aspectos del backend, frontend, infraestructura y experiencia de usuario, mejorando continuamente la plataforma que conocemos hoy como Spotify.

## Licencia
1. Spotify Free
Es la opción gratuita, que permite a los usuarios escuchar música con anuncios y ciertas limitaciones, como la necesidad de estar conectados a internet para escuchar música en modo aleatorio.

2. Spotify Premium
Es la suscripción de pago que elimina los anuncios y permite la descarga de música para escuchar offline, además de ofrecer una calidad de audio mejorada. Hay varias modalidades de Premium:
    - **Individual**: Para un solo usuario.
    - **Duo**: Para dos personas que viven en la misma dirección.
    - **Family**: Para hasta seis miembros de la misma familia, con cada uno teniendo su propia cuenta.
    - **Student**: Ofrece un descuento para estudiantes universitarios.

3. Spotify Premium for Business
Este es un servicio dirigido a empresas y locales comerciales para ofrecer música en lugares públicos. Necesita una licencia especial para cumplir con las regulaciones de derechos de autor.

4. Spotify for Artists
Esta es una plataforma que permite a los artistas gestionar sus perfiles, ver estadísticas de reproducción y promocionar su música.
