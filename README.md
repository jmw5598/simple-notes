# Simple Notes

*Simple Notes* is a REST api built with Spring Boot and an Angular 4 frontend.  This is a simple application for organizing your notes.

### Demo Setup

Prerequisite: A working installation of Pandoc is required for exporting.

1. Clone the github repository `git clone https://github.com/jmw5598/simple-notes.git`
2. Run `npm install` on the `interface` directory.
3. Serve the Angular frontend with `ng serve` while in `~/simple-notes/interface`.
4. Serve the Spring Boot backend with `mvn spring-boot:run` while in `~/simple-notes/simple-notes`
5. Navigate to `http://localhost:4200`

Default Credentials

Username | Password | Description
| - | - | - |
| admin | admin | Has USER and ADMIN roles |
| user | password | Has USER only role |
| disabled | password | User that is disabled |


### Video

[Video Demonstration][2]

### Screenshots

![Image of login](https://image.ibb.co/chA7Tc/sn_login.png)

![Image of topic list](https://image.ibb.co/fyh48c/sn_topics.png)

![Image of topic details](https://image.ibb.co/gZ96Mx/sn_sections.png)

![Image of section editor](https://image.ibb.co/kDHFEH/sn_editor.png)


[Link to screenshots](https://photos.app.goo.gl/Ck7ImjFwUX8SijaW2)

### Other
Security based on Stephan Zerhusen's [Jwt Spring Security Demo][1]

[1]: https://github.com/szerhusenBC/jwt-spring-security-demo
[2]: https://youtu.be/fgSkne3gfzk
