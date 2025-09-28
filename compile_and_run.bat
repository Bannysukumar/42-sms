@echo off
echo Compiling Java SMS Application...

REM Create classes directory
if not exist "classes" mkdir classes

REM Download required JAR files if they don't exist
if not exist "lib" mkdir lib

REM Compile with classpath
javac -cp "lib/*" -d classes src/main/java/com/fastglobal/sms/*.java src/main/java/com/fastglobal/sms/model/*.java src/main/java/com/fastglobal/sms/service/*.java src/main/java/com/fastglobal/sms/config/*.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo Running SMS Application...
    java -cp "classes;lib/*" com.fastglobal.sms.SmsApplication
) else (
    echo Compilation failed!
)

pause
