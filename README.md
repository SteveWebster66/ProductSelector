
This project has been implemented as an mvc Spring Boot application, with a JSON backed catalogue.

To build: mvn clean install

To run from IDE: launch ProductSelectorApplication

To run from command line: java -jar target/sky-product-selector-1.0.jar

Once the web service is started it can be accessed from any browser via:

http://localhost:8080/selector?customerId=dave

Note: by default any none null customerId is given a location of LONDON, dave is a LIVERPOOL user.

If no customer param is passed or no cookie is present the page will 404 - Not Found.
