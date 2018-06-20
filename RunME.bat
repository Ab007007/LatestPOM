start cmd /k echo Welcome to Regression Test Execution
cd d:\QSP\LatestPOM
d:
set classpath=D:\QSP\LatestPOM\allinonejar\*;D:\QSP\LatestPOM\bin;.
java org.testng.TestNG TestNG.xml
pause