# Content
1. General Information
    - Configuration
    - Design
    - APIs access and security
    - RestTemplate data access and implementation
    - JSON parsing and building
2. Deployment Instructions
3. How To Use
4. Testing

## 1. GENERAL INFORMATION
Here the most important considerations regarding how this app was design and deployed:

### Configuration:
The application was built as a standalone app and supposed to be run via the "main" method in the "App" class (package "rest"). Such approach has the following advantages:
- Standalone jars/wars are easy to assemble and deploy, they may be easily redeployed. It provides great benefits for scalable infrastructures (like SOA systems);
- They are easy to configure, so the team work require less control.
In some situation common server deployment may be preferable, it will be easy to deploy the same WAR/Jar inside the container.

### Design:

There are simple 2-tier application with a set of RESTful controllers and two beans(package "beans"),
one of them is responsible for storing ApplicationContext (this is ApplicationContexProvider class) and another one - the singleton bean with the method storing the authentication keys for users (Singleton class). Beans are being instantiated in AppConfig class. Application Context is injectable via @Autowired annotation, beans may be extracted from Application Context using getBean (<Params>) method. Also there is "model" package containing 2 POJO classes with the related interfaces.

Remark: for the same purpose the JavaEE APIs may be considered (like EJBs etc). May be the most efficient solution would be to combine the functionality of Spring Boot (to design the end points) and JavaEE for building of the business logic and DB layer.

### APIs access and security:

To allow the access to these APIs from other platforms, @CrossOrigin annotation is used (no origin restrictions are currently declared). At the same time, the APIs consumption is available only for the users who have their unique keys. To confirm the access, client has to set the unique key as a value for the "authorization" header. If header has not been found while processing the client request or the value of this header can not be found, client receives the status 401 "unauthorized". 

### RestTemplate data access and implementation:

- > As soon as there is a requirement to create a Restful client within one of our controllers, looks like that it is necessary to avoid the situation when the client will be waiting for the response too long. One solution (which is implemented in this app) is to set timeouts for RequestFactory object. Another option may be to send the request in a separate thread and to run it on a background. It may make sense if the result does not to be returned immediately to UI.
- > Despite https://www.developer.aero service was recommended, in this app http://api.timezonedb.com serice used instead. The reason is that the first web site does not supply API keys automatically.

### JSON parsing and building:
To process and build JSON objects gson library is used. The separated GsonUtils clas s has been created for this purpose.

## 2. DEPLOYMENT INSTRUCTIONS
Please refer to the document "CloudDeployment".

## 3. HOW TO USE
There are 3 REST resources, which may be called using the pseudo key "3.14". Key has to be set as a value of the header "authorization".

/time/now (get) - accept no parameters, returns the JSON object containing current date, time and timezone abbreviation. Returns status 500 if the internal data provider is not available. Returns status 401 if the key is not provided/wrong key provided.

/math/add (get) - accept 2 numeric parameters with keys "n1"and "n2". They must be provided as a part of URL. Returns status 422 if the provided parameters can not be converted to Integers. Returns status 401 if the key is not provided/wrong key provided. In case of success, returns status 200 and the result of operation.

/math/add (post) - accept 2 numeric parameters with keys "n1"and "n2". They must be encapsulated in the request body, header "application/x-www-form-urlencoded" should be set as a value of the header "Content-type". Returns status 422 if the provided parameters can not be converted to Integers. Returns status 401 if the key is not provided/wrong key provided. In case of success, returns status 200 and the result of operation.

## 4. TESTING 
We are providing a JSON file (WestJetTests.postman_collection, located in the root folder of the app) with a set of the integration postman tests. Tests can be easily imported into Postman IDE and to run. Also the file with JUNIT tests can be found in the "test" folder.