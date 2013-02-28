SETUP & EXECUTION
===============================================================================

On using Eclipse create a new empty project first and select 'File/Import...' --> ArchiveFile to import the content of the 
PaymentProcess.zip archive. Convert the project to a Maven project on right clicking the project root and select 
'Configure/Convert to Maven Project'.

Build the source on right clicking the project root and select 'Run As/Maven install'. In the target folder there should be
a payment-process-1.0.war file which needs to be copied to the webapps directory of your Tomcat server.

Before invoking http://your-webapps-servers-url/payment-process.1.0/ start the mocked services on right clicking wfm.AllServers
and selecting 'Run As/Java Application'. This will start the mocked web services the workflow process will use and invoke.

GENERAL INFO
===============================================================================

This example uses an in-memory H2 database for Activiti which does not export the workflow process to the Activiti explorer. 

To export the process to the Activiti explorer a couple of steps need to be done:
1) Install an H2 database and configure it like explained in http://www.h2database.com/html/quickstart.html
   Using a JDBC URL like jdbc:h2:tcp://localhost/activiti does make sense
2) Change the jdbc.url inside WEB-INF/classes/db.properties of the activiti-explorer to match your H2 JDBC URL you set before
   f.e. jdbc.url=jdbc:h2:tcp://localhost/activiti
3) In wfm.web.ActivitiEngine comment out the StandaloneInMemoryProcessEngine and remove the comment for the StandaloneProcessEngine
   
Now the process gets deployed within a central H2 database an can be seen by the payment-process web application and the 
Activiti explorer. Note however that the H2 database has to be started before invoking either the Activiti explorer or the
web application.

VERSION
===============================================================================

0.3 
*) Removed PaymentService - doBankTransferPayment(...) and doCreditCardPayment(...) moved in the corresponding service classes as doPayment(...)
*) BankTransferValidationService(Impl) and CreditCardValidationService(Impl) renamed to BankTransferService(Impl) and CreditCardService(Impl)
*) Server classes for BankTransferService and CreditCardService renamed accordingly
*) Removed 'service' fieldName in the workflow model as the corresponding serviceString is set via execution.setVariable("paymentService", serviceString) 
   in BankTransferValidationWSBehavior or CreditCardValidationWSBehavior
*) Adapted JUnit-tests accordingly

0.2
*) Upgraded from Activiti 5.9 to 5.11
*) Upgraded CXF from 2.3.2 to 2.6.6 which allows the JVM environment to be > Java 6 but also requires an execution environment of at least
   Java 6!
*) Added CouponValidationWSBehavior again as the experimental SOAP invocation in the bpmn-file should be removed in future versions (not 100% sure though)
*) Created a new free eMail-account and removed individual-related data from former team-members like email-addresses and names

0.1
*) WFM2012_MR3 version