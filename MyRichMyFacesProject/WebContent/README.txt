URL: http://localhost:8080/MyRichMyFacesProject/pages/login.jsf

ADDITIONAL INFO:
Application server: Geronimo Tomacat v2.2.1
RichFaces v3.3.3 Final
JSF MyFaces v1.2.8




HOT SPOTS:
%GERONIMO_HOME%\var\config\config.xml
%GERONIMO_HOME%\var\repository\default
%GERONIMO_HOME%\var\activemq\conf\activemq.xml


GERONIMO SETTINGS:
0)stop app. server
1)delete all content of %GERONIMO_HOME%\var\repository\default
2)remove last lines containing name of your applications
3)check %GERONIMO_HOME%\var\activemq\conf\activemq.xml: 
-useShutdownHook="false" 
-start="false" 
-schedulerSupport="false" 
