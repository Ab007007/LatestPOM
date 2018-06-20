set projectLocation=D:\QSP\LatestPOM
cd %projectLocation% 
set classpath=%projectLocation%\bin;%projectLocation%\allinonejar\*
java org.testng.TestNG %projectLocation%\testNG.xml
pause