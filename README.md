### Example for running java application and selenium/standalone-chrome
### in two separate container but in one network


### Step 1. Build the Application

`mvn clean install`


### Step 2. Create an image from the Jar file

`docker image build -t my-app .`

### Step 3. pull selenium/standalone-chrome image from docker hub

`docker pull selenium/standalone-chrome`


### Step 4. Creat a docker network
`docker network create my-app-net`

### Step 5. Add app container to the network
`docker container run --network my-app-net --name my-app-container -p 8080:8080 -d my-app`


### Step 6. Add chrome container to the network
`docker container run --network my-app-net --name my-chrome -p 4444:4444 -d selenium/standalone-chrome
`


#### Step. 7 You can see the output as:
 `localhost:8080`
##### and you will see
 `Hello Hamzeh`
#### and
 `localhost:8080/shot/`
##### and you will see
 `2045562868`
##### where this numer is the hashcode of the created remotewebdriver