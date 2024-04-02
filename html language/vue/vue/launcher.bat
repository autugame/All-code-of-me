@echo OFF
color 0a
Title music launcher
Mode con cols=109 lines=30
:START
ECHO.
Echo                  ==========================================================================
ECHO.
Echo                                         autumn-music launcher tool
ECHO.
Echo                                         The author: autumn qq number 3452896431
ECHO.
Echo                                         QQ group(no)
ECHO.
Echo                  ==========================================================================
Echo.
echo.
echo.
:TUNNEL
set /p clientid= music launcher, press enter to continue:
echo.
start "C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe" .\indexForVue3.html
.\jre1.8.0_211\bin\java -jar music-1.0.0.jar
goto TUNNEL



