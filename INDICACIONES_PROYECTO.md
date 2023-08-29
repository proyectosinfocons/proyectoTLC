En el proyecto se ha cargado cuatro registros en el h2.
1.- Paso 1:
POST: http://localhost:8080/signup
Se crea el usuario

{
"username":"123",
"password":"123"
}



2.- Paso 2:

POST: http://localhost:8080/login
Se logea con el usuario creado para generar el token
{
"username":"123",
"password":"123"
}


3.- Paso 3:

GET: http://localhost:8080/principal/listadotodos


Cambiar en la opcion de Autherization en type: pegar el token generado en el paso 2



4.- Paso 4:

POST: http://localhost:8080/principal/crear

Cambiar en la opcion de Autherization en type: pegar el token generado en el paso 2

{
"ruc":"476427742",
"razonSocial":"fdfdffd",
"direccion":"fsfdsfsd",
"estado":"fdssfdffffffffprueba"
}