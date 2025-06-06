# patient-management-system
This is a patient management system.
Coded with java spring boot, provides different microservices for the specific usecases.

Patient Service -> Provides APIs to for clients to interact with system. Provided APIs are to add, update, remove and get the details of patients. Also provides a grpc client to interact with Billing service. Also acts as a kafka producer for the events that are generated to be consumed by Analytics service.

Billing Service -> It gets grpc request from patient service based on which it generates bill for the patients.

Analytics Service -> provides a kafka consumer that consumes events from kafka broker produced by patient-service. The cosumer java files are generated using the proto file. The logic for consuming the events is present in KafkaConsumer.java, where KafkaListner is configured to consume event from patient topic.

Auth Service -> This microservice provides funtionality for authantication and authorization of the api request reaching to patient service. Provides funstionality to login as well, where it generates a JWT token for each user which is appicable for some specific period. Also provides functionality to validate token for each requests.

API Gateway -> It acts as the entry point for the application, where all the request comes and based on the api url, the gateway calls specific service i.e. Auth service, Patient Service, or specific docs. It also provides JwtValidationGatewayFilterFactory to validate the request with authorization token, that are directly trying to access Patient service.

Key Components used in the project:-
Java, Spring Boot ,API gateway configuration acting as a single point of entry to application, Authorization of request using JWT tokens, REST APIs, GRPC, KAFKA, POSTGRESS DBs (2 , for storing patient data and User data(username, password)).
