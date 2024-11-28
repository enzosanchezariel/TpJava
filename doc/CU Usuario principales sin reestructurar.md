# CU 01: Inscribirse a una Sala

Dimensiones:
- Alcance: Sistema  
- Nivel: Usuario  
- Estructura: Sin Estructurar

Meta: Inscribirse a una sala  
Actor primario: Usuario  
Otros actores: \-  
Precondiciones:
- El usuario debe estar logueado.

Disparador: El usuario desea inscribirse a una sala.

Camino básico:
1. El usuario elige la opción “Crear sala”.El sistema muestra el formulario para crear una sala.  
2. El usuario ingresa los datos requeridos:  
    * Nombre de la sala.  
    * Cantidad máxima de personas en la sala.  
    *  Código de la sala.  
    * Fecha de finalización de la sala.

    y confirma la creación de la sala. El sistema valida que:

    * Los caracteres ingresados sean válidos.  
    * El código de la sala no existía previamente.
3. El sistema registra la sala, asigna al usuario como administrador de la sala y agrega al usuario como participante de la sala. El sistema muestra “Sala creada con éxito”.  
		  
Caminos alternativos:  
1.a\<previo\>El usuario no está registrado:  
	1.a.1: El usuario ingresa los datos personales al sistema. El sistema registra al nuevo cliente. Vuelve al paso 1\.  

2.a. Los caracteres no son válidos.  
	2.a.1 El sistema muestra: “Caracteres inválidos, debe ingresar caracteres válidos para registrar la sala”. Vuelve al paso 1\.  

2.b El código de la sala ya existe.  
	2.b.1 El sistema muestra: “La sala con ese código ya existe, ¿Quiere unirse?”.  
		2.b.1.a el usuario selecciona la opción “aceptar”.  
			2.b.1.a.1 El sistema agrega al usuario como participante de la sala. FCU.  
			2.b.1.a.2 La sala se encuentra expirada. El sistema muestra “no es posible unirlo a la sala”.FCU  
		2.b.1.b El usuario selecciona la opción “Cancelar”. FCU

Postcondiciones:

- Sistema:  
  - Éxito: Sistema registra nueva sala.  
  - Éxito alternativo:  
    a: Nueva Sala Registrada \+ Nuevo Usuario Registrado  
    b: Usuario se une a una sala existente
  - Fracaso: No aplica   
- Negocio:  
  - Éxito: Sala nueva.  
  - Éxito alternativo: Nueva Sala \+ Nuevo Usuario  
  - Fracaso: Sala no creada.

Diccionario de datos:  
| Paso | Tipo \[E-S\] | Nombre | Descripción |  
| :- | :- | :- | :- |  
| 1 | FS | muestraFormulario |{}|  
| 2 | FE | respuestaForm | nomSala \+ cantMaxPart \+ codSala \+ fechaFin |  
| 2 | FS | respuestaSist | mensaje(d) |

mensaje(d) \= {“Sala creada con éxito”/“La sala con ese código ya existe”/”Caracteres inválidos, debe ingresar caracteres válidos para registrar la sala”}

# CU 02: Responder un Cuestionario

Dimensiones:

- Alcance: Sistema  
- Nivel: Usuario  
- Estructura:

Meta:   
Actor primario: Usuario  
Otros actores: \-  
Precondiciones:

- Sistema: El usuario debe estar logueado.

     El usuario debe pertenecer a la sala.

- Negocio: -  

Disparador: El usuario selecciona el cuestionario que quiere responder.  
Camino básico:

1. El usuario entra a la sala. El sistema le muestra todos los cuestionarios disponibles.  
2. El usuario selecciona el cuestionario que desea realizar. El sistema:  
    * Valida que el usuario haya iniciado sesión.  
    * Verifica que haya encontrado el Quiz, que el mismo no esté dado de baja y que su Room tampoco  
    * Verifica que el usuario tenga acceso a la Room.  
    * Verifica si el usuario ya contestó el Quiz y no tiene un intento del mismo vigente o de otro Quiz  
    
    El sistema muestra el listado de preguntas pertenecientes a ese cuestionario.  
3. El usuario responde las preguntas y confirma sus respuestas. El sistema registra las respuestas del usuario, calcula las respuestas correctas y manda un mail al usuario con sus respuestas.

Caminos alternativos:  
2.a El usuario no inició sesión.  
	2.a.1 El sistema muestra “Debe iniciar sesión para entrar a un formulario”. FCU.

2.b La sala está dada de baja.  
	2.b.1 El sistema muestra “El formulario no existe o fue eliminado o su sala fue eliminada”. FCU

2.c La sala a la que pertenece el formulario está dada de baja.  
	2.c.1 El sistema muestra “El formulario no existe o fue eliminado o su sala fue eliminada”. FCU

2.d No se encuentra el formulario .  
	2.d.1 El sistema muestra “El formulario no existe o fue eliminado o su sala fue eliminada”. FCU

2.e El usuario no tiene acceso a la Room.  
	2.e.1 El sistema muestra “Usted no tiene acceso a la sala del formulario”. FCU

2.f El usuario tiene una participación de otro cuestionario vigente.   
	2.f.1 El sistema muestra “Usted tiene un intento vigente de otro cuestionario” Se redirige al otro cuestionario.

2.g El usuario ya intentó el formulario.  
	2.g.1 El sistema muestra “Usted ya intentó este cuestionario” FCU

3.a El usuario no responde todas las preguntas.  
	3.a.1 El sistema muestra “Respuesta no registrada”. FCU

3.b El tiempo del formulario expiró.  
	3.b.1 El sistema muestra “El intento expiró y no se registró su respuesta” FCU

Postcondiciones:

- Sistema:  
  - Éxito: Respuesta registrada.  
  - Éxito alternativo:  
  - Fracaso:  
- Negocio:  
  - Éxito: Nueva respuesta obtenida.  
  - Éxito alternativo:  
  - Fracaso:

Diccionario de datos:  
| Paso | Tipo \[E-S\] | Nombre | Descripción |  
| :- | :- | :- | :- |  
| 1 | FS | cuestionarios |1{Quiz}n|  
| 2 | FE | selecCuest | Quiz |  
| 2 | FS | preguntas |Question \+ 1{rta}4 |  
| 3 | FE | respPreg | 1{rta}n |

\> “CU: Obtener resultados de un cuestionario por mail” forma parte del “CU 02: Responder un Cuestionario”

# CU 03: Ver ganador/es de la sala

Dimensiones:

- Alcance: Sistema.  
- Nivel: Usuario.  
- Estructura: Sin Estructurar.

Meta: Ver ganador o ganadores de la sala.  
Actor primario: Usuario  
Otros actores: \-  
Precondiciones:

- El usuario debe estar logueado.  
- La sala debe haber finalizado. Es decir que ha pasado la fecha de vencimiento.

Disparador: El usuario ingresa a una sala finalizada.  
Camino básico:

1. El usuario ingresa a una sala finalizada desde el menú de inicio del sistema.  
2. El sistema muestra un listado con el o los usuarios que obtuvieron la mayor puntuación.

Caminos alternativos:  
2.a No hay mayor puntuación porque no hay respuestas registradas para ningún cuestionario de la sala.  
	2.a.1 El sistema muestra “No hay ganadores registrados en esta sala.” en lugar de mostrar el listado.  
Postcondiciones:

- Sistema:  
  - Éxito: \-  
  - Éxito alternativo: \-  
  - Fracaso: \-  
- Negocio:  
  - Éxito: Listado de ganadores de la sala.  
  - Éxito alternativo: \-  
  - Fracaso: Mensaje indicando que no hay ganadores.

Diccionario de datos:  
| Paso | Tipo \[E-S\] | Nombre | Descripción |  
| :- | :- | :- | :- |  
| 1 | FE | selecSala | Sala |  
| 2 | FS | ganadores | \[1{nombre \+ apellido \+ puntuación}n \\| “No hay ganadores registrados en esta sala.”\] |