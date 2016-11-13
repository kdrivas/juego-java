set PATH=D:\Program Files\Java\jdk1.8.0_60\bin;%PATH%
:creamos paquetes
jar cvf entidad.jar Model\Entidad.class Model\Enemigo.class Model\Avatar.class Model\ObjetoDeJuego.class
jar cvf artefactos.jar Model\Artefacto.class Model\Pocion.class Model\Arma.class Model\Armadura.class Model\Saco.class
jar cvf mundo.jar Model\Laberinto.class Model\Celda.class Controlador\*.class FACILIDADES\*.class
jar cvf interfaz.jar Vista\*.class
jar cvfm ejecutable.jar manifiest.txt diablito\Diablito.class

