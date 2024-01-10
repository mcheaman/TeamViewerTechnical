# TeamViewerTechnical 
## Java, SpringBoot, OpenAPI, and Docker Technical Challenge
### by Mason Heaman

## Prerequisites
Docker is installed and running - https://docs.docker.com/get-docker/

## Instructions

### Build & Run

#### Download source code   
    1. <> Code -> Download ZIP  
    2. Unzip file
#### Enter project directory through command line  
    cd path-to-directory/TeamViewerTechnical-main

#### Build the eccomerce docker image
    docker build . -t eccomerce:v1

#### Run the containerized application  
    docker-compose up

### Test
Navigate to http://localhost:8081/swagger-ui/index.html/

This page, utilizing swagger-ui, defines the OpenAPI 3.0 specification for the ecommerce API. Testing can be done directly from this page with the following steps:
1. Select the dropdown for the API endpoint to be tested
2. Click 'Try it out'
3. If the request requires a body, add it in the Request body section
4. Click Execute
5. Scroll down to Server response to see the response

#### Testing Notes
The database will be empty at creation. Therefore, testing the endpoints should begin by adding products and orders (POST api/products, POST api/orders). Otherwise, other methods will return 404s due to lack of data. 


