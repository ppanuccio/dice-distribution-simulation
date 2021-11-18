##

The application use MongoDB as database listening on `localhost:27017`. If you don't have already installed on your
machine you can run an instance using Docker:

`docker run -d -p 27017:27017 --name my-mongodb mongo:latest`

After you have started the database you can the application with the following command:

`./mvnw spring-boot:run`

You can create a dice distribution simulation with the following command:

`curl "http://localhost:8080/dice-distribution-simulation?numberOfDice=3&diceSides=6&numberOfRolls=10"`

And you can get a relative dice distribution based on the simulations with:

`curl "http://localhost:8080/dice-relative-distribution"`

