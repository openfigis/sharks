
# Environment Setup

Here some instruction on how to setup the environment to contribute to Sharks project.

## Eclipse setup

1.  Install and download Eclipse IDE for Java Developers (current used Luna)
2.  From Luna update site install the following plugins:
  *   Eclipse Java Web Developer Tools 3.6.3.v201502041526
  *   Eclipse Web Developer Tools 3.6.3.v201501312139
  *   JavaScript Development Tools 1.6.100.v201410221502
  *   m2e-wtp - Maven Integration for WTP 1.1.0.20140611-1646
  *   JST Server Adapters 3.2.301.v201410241731
  *   JST Server Adapters Extensions 3.3.302.v201501040116
  *   JST Server UI 3.4.101.v201410241731
  *   WST Server Adapters 3.2.401.v201410241731
3. Install angularjs support (from http://oss.opensagres.fr/angularjs-eclipse/0.8.0/):
  *   AngularJS Eclipse 0.8.0.201501282321
  *   AngularJS Eclipse Developer Resources 0.8.0.201501282321
  *   Embedded nodejs 0.9.0.201501272204
  *   Tern feature 0.9.0.201501272204
  *   Tern JSDT feature 0.9.0.201501272204
  *   Eclipse WTP HTML-Web resources 0.9.0.201501272159
4. Install Tomcat (from http://tomcat.apache.org/download-80.cgi)
5. in preferences move up "angularjs plugin" suggestion in "Content Assist for html editor"
6. Install [lombok](https://projectlombok.org/ "lombok project"): 
  *   download the lombok jar
  *   run the jar
  
  
## Eclipse Tomcat setup  
1.	Configure the Tomcat 8 Java 8 server
2.	Go to Run/Run Configurations.. and add an environment variable with name SHARKS_CONFIG and for a value something like 	C:\Users\vaningen\git\sharks\sharks-deploy\conf\dev\sharks.properties
  
  
[See more info for the Sharks Client setup here](/sharks-client/README)
  
  

