:seteamos el path
set PATH=D:\Program Files\Java\jdk1.8.0_60\bin;%PATH%

: compilamos todas las clases
:javac Model\*.java Controlador\*.java  Vista\*.java FACILIDADES\*.java
javac -cp xstream-1.4.9.jar;xpp3_min-1.1.4c.jar Model\*.java Controlador\*.java  Vista\*.java FACILIDADES\*.java diablito\Diablito.java