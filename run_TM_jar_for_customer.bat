@echo off
IF EXIST TaskManagerNetcracker-1.0-SNAPSHOT.one-jar.jar GOTO LAUNCH
@echo ARTIFACT NOT FOUND! Put this bat file into similar package with TaskManagerNetcracker-1.0-SNAPSHOT.one-jar.jar
PAUSE
GOTO END
:LAUNCH
java -jar TaskManagerNetcracker-1.0-SNAPSHOT.one-jar.jar
:END
