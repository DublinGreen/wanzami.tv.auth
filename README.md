# Spring Boot + GraphQL + MySQL (Wazami tv auth)
Wanzami tv auth service to handle login and registration of new users. Using Apollo client (). Java doc has been generated and unit test

## Run Spring Boot application
```
mvn spring-boot:run
```
# convert graphql query to json
https://datafetcher.com/graphql-json-body-converter

# Add Custom domain to service
Add a new record to hosted host on aws and create ssl for the custom domain

Record name
auth.wanzami.tv

Record type
A

Value
[Public IPv4 address]

Alias
No

TTL (seconds)
300

Routing policy
Simple

#install ssl on subdomain aws ec2
ssh into ec2 install and navigate into app directory [/var/app/current/]

sudo yum install -y epel-release
sudo yum install -y certbot python3-certbot-nginx
sudo certbot --nginx -d auth.wanzami.tv

Use this to test, if the ssl install was succesful
curl -v https://auth.wanzami.tv


#add cors url
The application using this application, add it to the cors in application.properties and CustomCorsConfig.java files

------------------------------------------

##Use postman or graphiql to inspect and test

## Note, any deployment after, you need to reinstall the ssl

sudo yum install -y certbot python3-certbot-nginx
sudo certbot --nginx -d auth.wanzami.tv