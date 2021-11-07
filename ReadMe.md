# Invoice-Management-Service
Invoice data management service

##How to build
1. Run the script.sql and create the database/table[MySql] and insert raw data

2. Update application-local.properties file with your database credentials(endpoint, dbName, username, password)


3. Open terminal and execute the following commands

    Linux:
    ``./gradlew build``
    
    Windows:
    ``gradlew.bat build``
`


4. Now the build bundle you can see in the build folder

     i. move to build folder -> ``cd build/``

    ii. Run jar app -> ``java -jar invoice-management-service-0.0.1.jar``


5. App will up and run in 8080 port. By default, it will take properties from local.properties file. Make sure to update the properties with your properties

6. You can test the endpoints using the postman.json or using the frontend app



Since this app is running in local environment we can save our properties in local properties file. But when we deploy we need to maintain environment specific properties, In that case we need to store secure properties in a cloud store(ssm) and can access through spring-cloud
