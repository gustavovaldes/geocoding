**Geocoding**

Simple proxy for google maps api.

SpringBoot+Camel


**Prerequisites**

`Java8`
`Maven`

**Use**

`http://localhost:8090/geocoding/?address={SomeAddresHere}`

**Run Service**

`mvn spring-boot:run `

**Unit Test**

`mvn test`

**Api Test**

Start Service using Stub Endpoint: 

`mvn spring-boot:run -Drun.profiles=iso`

In other terminal run the api tests (stubs server is started with the apiTest module):

`cd apitest`

 `mvn test`
