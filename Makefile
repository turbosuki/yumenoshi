build:
	mvn clean verify -DskipTests=true

test-local:
	mvn clean test -Dsurefire.suiteXmlFiles=local-testng.xml

test-browserstack:
	mvn clean test -Dsurefire.suiteXmlFiles=browserstack-testng.xml

init:
	printf '%s\n' 'implicitWait=' 'baseUrl=' 'seleniumHubUrl=' 'browserStackUsername=' 'browserStackKey=' 'defaultLocalBrowser='> src/main/resources/application.properties