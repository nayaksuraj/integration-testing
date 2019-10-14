## Get started

### 1. Set an API Key as Environment Variable
In order to run the service, you need to set the `WEATHER_API_KEY` environment variable to a valid API key retrieved from [darksky.net](http://darksky.net).

A simple way is to rename the `env.sample` file to `.env`, fill in your API key from `darksky.net` and source it before running your application:

```bash
source .env
```

### 2. Start a PostgreSQL database
The easiest way is to use the provided `startDb.sh` script. This script starts a Docker container which contains a database with the following configuration:
    
  * port: `15432`
  * username: `postgres`
  * password: `postgres`
  * database name: `postgres`
  
### 3. Run the Application
Once you've provided the API key and started a PostgreSQL database you can run the application using

```bash
mvn spring-boot:run
mvn test

```

The application will start on port `8080` so you can send a sample request to `http://localhost:8080/hello` to see if you're up and running.

### 4. Start Standalone WireMock server
To start WireMock standalone use the provided `startWireMockServer.sh`. This script starts a Wiremock Standalone server and post some data so later we can run test against this.
  * port: `8091`
  * endpoint: `localhost:8091/weathher`