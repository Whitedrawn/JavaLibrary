@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  JAVA2 startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and JAV_A2_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\JAVA2-1.0-SNAPSHOT.jar;%APP_HOME%\lib\sqlite-driver-1.5.4.jar;%APP_HOME%\lib\jdbc-driver-1.5.4.jar;%APP_HOME%\lib\runtime-jvm-1.5.4.jar;%APP_HOME%\lib\junit-4.12.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.7.10.jar;%APP_HOME%\lib\Sql4Ddriver-4.1.0.jar;%APP_HOME%\lib\HikariCP-5.0.1.jar;%APP_HOME%\lib\slf4j-nop-2.0.4.jar;%APP_HOME%\lib\Sql4DCompiler-4.1.0.jar;%APP_HOME%\lib\scala-library-2.11.12.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.7.10.jar;%APP_HOME%\lib\kotlin-stdlib-1.7.10.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.7.10.jar;%APP_HOME%\lib\hamcrest-core-1.3.jar;%APP_HOME%\lib\fluent-hc-4.3.6.jar;%APP_HOME%\lib\httpclient-4.3.6.jar;%APP_HOME%\lib\mysql-connector-java-5.1.34.jar;%APP_HOME%\lib\spring-jdbc-4.1.2.RELEASE.jar;%APP_HOME%\lib\jackson-databind-2.5.0.jar;%APP_HOME%\lib\jackson-core-2.5.0.jar;%APP_HOME%\lib\jackson-annotations-2.5.0.jar;%APP_HOME%\lib\commons-pool-1.6.jar;%APP_HOME%\lib\kryo-2.24.0.jar;%APP_HOME%\lib\slf4j-log4j12-1.7.12.jar;%APP_HOME%\lib\slf4j-api-2.0.4.jar;%APP_HOME%\lib\apache-log4j-extras-1.2.17.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\sqlite-jdbc-3.34.0.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\joda-time-2.3.jar;%APP_HOME%\lib\antlr-runtime-3.5.2.jar;%APP_HOME%\lib\json-20140107.jar;%APP_HOME%\lib\commons-io-2.4.jar;%APP_HOME%\lib\guava-18.0.jar;%APP_HOME%\lib\httpcore-4.3.3.jar;%APP_HOME%\lib\spring-tx-4.1.2.RELEASE.jar;%APP_HOME%\lib\spring-beans-4.1.2.RELEASE.jar;%APP_HOME%\lib\spring-core-4.1.2.RELEASE.jar;%APP_HOME%\lib\commons-logging-1.1.3.jar;%APP_HOME%\lib\commons-codec-1.6.jar;%APP_HOME%\lib\minlog-1.2.jar;%APP_HOME%\lib\objenesis-2.1.jar;%APP_HOME%\lib\log4j-1.2.17.jar


@rem Execute JAVA2
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %JAV_A2_OPTS%  -classpath "%CLASSPATH%" coursework.gui.App %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable JAV_A2_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%JAV_A2_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
