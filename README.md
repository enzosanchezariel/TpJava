# Propuesta TP Java

## Integrantes:
- Abuin, Leonel
- Duarte, Lautaro
- Sanchez, Enzo
- Hidalgo, Tomás

## Descripción del Sistema:
El sistema es una aplicación donde los usuarios podrán responder cuestionarios de diversos temas. Además, podrán competir en salas y ganar puntos dentro de ellas; completar desafíos; y subir de rango.

## Modelo de Dominio:
![Modelo de dominio](/ModeloDeDominioEntregaInicial.png)

## CRUD simples:
- CRUD usuario.
- CRUD tema.
- CRUD rango.
- CRUD preguntas.
- CRUD desafío.

## CRUD dependientes:
- Creación de sala.
- Creación de cuestionarios.

## CU no ABMC:
- Inscribirse a una sala
- Responder un cuestionario
- ~~Modificar el proceso de respuesta para calcular los desafíos y rangos alcanzados~~
- Obtener resultados de un cuestionario por mail.
- Ver ganador/es de la sala.

## Listados complejos:
- Ranking de usuarios filtrado por puntajeTotal.
- ~~Listado de preguntas por tema.~~
- Modificar el proceso de respuesta para calcular los desafíos y rangos alcanzados

## Listados simples:
- Listado de salas.
- Listado de temas.
- Listado de cuestionarios.

## Requerimiento Extra:
Los usuarios recibirán el resultado de su participación en la sala por email.