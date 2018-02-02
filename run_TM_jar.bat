@echo off
IF EXIST  target\TaskManagerNetcracker-1.0-SNAPSHOT.one-jar.jar GOTO LAUNCH
@echo ARTIFACT NOT FOUND! EXECUTE generate_TM_artifact.bat FIRST!
set /P c=YOU WANT TO EXECUTE IT NOW[Y/N]?
if /I "%c%" EQU "Y" goto :GEN
if /I "%c%" EQU "N" goto :END
PAUSE
GOTO END
:GEN
call generate_TM_artifact.bat
:LAUNCH
java -jar target\TaskManagerNetcracker-1.0-SNAPSHOT.one-jar.jar
:END
