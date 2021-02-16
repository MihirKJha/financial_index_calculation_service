#FINANCIAL_INDEX_CALCULATION_SERVICE
----------------------------------------------------------------
Solactive, as one of the leading providers of financial indexes, consumes and uses real-time trading prices
of tens of thousands of financial instruments from more than 100 exchanges over the world.

In order to ensure integrity of our index calculation and proper input data, our operations team needs a
restful API to monitor the incoming prices. The main use case for that API is to provide real-time price
statistics from the last 60 seconds (sliding time interval).

Technology used
----------------------------------------
Java 8
REST API
Spring Boot
Swagger 
Lombok
Docker container
Maria DB
Maven


How to run the project
----------------------------------------
To build and run -->  mvn install -DskipTests

To create and start docker container --> docker-compose up -d --build --force-recreate -t 0 

Now application is up and running .

Swagger URL -- http://localhost:8081/swagger-ui.html#

DB tables 
------------
INSTRUMENT_TICKS  to keep ticks information 

INSTRUMENT_INDEX to keep instrument index calculation

INSTRUMENT_MEDIAN_INDEX to keep all instrument index calculation

Use below endpoints

[API-1]POST -  http://localhost:8081/ticks 

Request Body :- 
		{
		  "instrument": "IBM.N",
		  "price": 143.43,
		  "timestamp": 1613453228746
		}

[API-2] GET  - http://localhost:8081/statistics
[API-3] GET  - http://localhost:8081/statistics/{InstrumentCode}


To access DB use Hedis SQL with below credenials -
Hostname/IP: localhost
port: 3306
User: user
password: user


To kill container after use - 
Docker-compose down

Assumptions
-----------------------------
These three endpoint will be executed and used in parallel

To make Statistics API contant time I did all calculation while saving ticks  with ealrier assumption in mind.

Improvements
-----------------------------
Code coverage and improing design part
Jenkins PCF integrations 
