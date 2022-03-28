# santahelper
A Rest API to help Santa and his ageing elves

## Prerequisites to running this application
- Gradle v6.4
- Java 8 

## Steps to run the application:
- git clone https://github.com/pulkitguptajmi/santahelper.git
- git switch master
- gradle clean build
- gradle docker
- docker run -p 8085:8085 santahelper

## Sample curl request and response:
### Request
curl --location --request POST 'localhost:8085/hoodfiller' \
--form 'hood_capacity="45"' \
--form 'present_weights="2,5,10,50,100"'

### Response
[
    5,
    10,
    10,
    10,
    10
]
Status:200 OK



