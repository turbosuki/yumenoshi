build:
	mvn clean verify -DskipTests=true

test-local:
	mvn clean test -q -e -Dsurefire.suiteXmlFiles=local-testng.xml

test-browserstack:
	mvn clean test -Dsurefire.suiteXmlFiles=browserstack-testng.xml

init:
	printf '%s\n' 'implicitWait=' 'baseUrl=' 'browserStackUsername=' 'browserStackKey=' 'defaultLocalBrowser='> src/main/resources/application.properties