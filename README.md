# BankStatementProcessor
Rabo bank statement processor

This is Springboot project which is done with both front end and backend.
welcome.jsp - will get a bank customer statement as input xml file.
result.jsp - will display the failed records ( account number and description ) which doesnt satisfy the given condition.

Record.java - comprises the basic record object for each records in the input xml.
App.java - main class of this application
AppController.java - act as a request handler class of this application
AppService.java - act as service which validates the input xml and return the failed records
