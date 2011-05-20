URL: http://localhost:8080/MyRichMyFacesProject/pages/login.jsf

ADDITIONAL INFO:
Application server: Geronimo Tomacat v2.2.1
RichFaces v3.3.3 Final
JSF MyFaces v1.2.8


GERONIMO INSTALLATION NOTES:
1) DOWNLOAD geronimo v 2.2.1
	- installation: http://www.apache.org/dyn/closer.cgi/geronimo/2.2.1/geronimo-tomcat6-javaee5-2.2.1-bin.zip
	- source: http://www.apache.org/dyn/closer.cgi/geronimo/2.2.1/geronimo-2.2.1-source-release.zip
2) EXTRACT geronimo-tomcat6-javaee5-2.2.1-bin.zip
3) ADD JAVA_HOME = i.e. C:\Progra~2\Java\jdk1.6.0_13\
4) ADD GERONIMO_HOME = i.e. C:\geronimo-tomcat6-javaee5-2.2.1\
5) OPEN %GERONIMO_HOME%\var\activemq\conf\activemq.xml
	- find "broker" tag and add attributes:
	- useShutdownHook="false" 
	- start="false" 
	- schedulerSupport="false" 
6) START geronimo server using %GERONIMO_HOME%\bin\startup.bat
7) RUN Geronimo Admin. Console
	- URL: http://localhost:8080/console
	- Username: system
	- Password: manager
8) UNINSTALL myfaces
	- Admin console -> Applications -> System modules
	- check "Expert User(enable all actions on Geronimo Provided Components)"
	- Ctrl + F, find all modules with keyword "myfaces"
	- uninstall all myfaces modules
9) STOP geronimo server using %GERONIMO_HOME%\bin\stop-server.bat
10) CREATE new server in eclipse 
	- click "Add new server"
	- click "Download Additional Adapters"
	- select Adapter for Geronimo v 2.2
	- install server
	

HOT SPOTS:
%GERONIMO_HOME%\var\config\config.xml
%GERONIMO_HOME%\var\repository\default
%GERONIMO_HOME%\var\activemq\conf\activemq.xml


GERONIMO SETTINGS:
0)stop app. server
1)delete all content of %GERONIMO_HOME%\var\repository\default
2)remove last lines containing name of your applications 

