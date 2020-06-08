# Devices Sensor Reports 
- Small app for Develop REST API endpoint that get data from devices in hexdecimal format and save it in decmial format. 
with CRUD functionality on Cars data.can list cars, delete car, update car, add new car.
- Web view and dashboard for devices temprature reports.
## Installation

### Requirements
* Java 
* Maven
* WebSocket
* Angular

### Build and Run
* Build with Maven.

Download the app. Extract the zip file. go inside project folder and run this maven commend.

`$ mvn spring-boot:run`

* Angular
`$ npm install`

`$ ng s`	

## Built With
* SpringBoot
* H2 simple in memory DB
* WebSocket
* Angular

## Using the endpoints
* Once the app is up and running. you can check API documentation from this url
[Swagger API Documentation](http://localhost:1010/swagger-ui.html)
* You can check DeviceTempController
* Ex: http://localhost:1010/v1.0/devicetemp/{value}       GET     parse hex value into decmail, extract data and save it
* Ex: http://localhost:1010/v1.0/devicetemp/latest 		  GET     get all devices with the latest temp degree
* Ex: http://localhost:1010/v1.0/devicetemp/device/{deviceId}     GET   get all temp for a specific device by device id
* Ex: http://localhost:1010/v1/devicetemp  				  Get     get all data in DB

## DB using H2
* The project using H2 simple in memory DB.
* You can access the H2 console [HERE](http://localhost:8080/h2).
* username: sa password:

## API Documentation
* [Swagger API Documentation](http://localhost:8080/swagger-ui.html)

## Actuator
* The project uses spring actuator for monitoring.
* [actuator metrics](http://localhost:1010/actuator/metrics)
* [actuator env](http://localhost:1010/actuator/env)
* [actuator health](http://localhost:1010/actuator/health)



