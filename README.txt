This Java program parses a web server access log file, loads the log to H2SQL and checks if a given IP makes more than a certain number of requests for the given duration. 

Before runnig this program make sure you have the DB created running schema.sql file.

You can execute this program using the following command line:
java -cp "parser.jar" com.ef.Parser --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100

or
java -cp "parser.jar" com.ef.Parser --accesslog=/path/to/file --startDate=2017-01-01.13:00:00 --duration=daily --threshold=250
