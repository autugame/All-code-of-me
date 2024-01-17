@echo OFF
color 0a
Title 音乐启动工具
Mode con cols=109 lines=30
:START
ECHO.
Echo                  ==========================================================================
ECHO.
Echo                                         autumn-music客户端启动工具
ECHO.
Echo                                         作者: autumn QQ：3452896431
ECHO.
Echo                                         QQ群（无）
ECHO.
Echo                  ==========================================================================
Echo.
echo.
echo.
:TUNNEL
set /p clientid= 音乐启动器，按下回车键确认：
echo.
start "C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe" .\indexForVue3.html
.\jre1.8.0_211\bin\java -jar music-1.0.0.jar
goto TUNNEL



