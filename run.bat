@ECHO OFF

call gradle build
java -jar ./build/libs/server-logs-reader-0.0.1-SNAPSHOT.jar ./logfile.txt
pause