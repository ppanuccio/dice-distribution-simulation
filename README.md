## Dice Distribution Simulation

The following application simulates several rolls of the dice and provide a relative distribution grouped by dice number
and dice sides. Dice number, dice sides and number of rolls for each simulation can be specified as parameters.

### Technical Note

I tried to model the domain as object entities. In such a way I'm able to test domain business logics without worrying
about infrastructure's stuff.

### How to run

This is a Spring application and use Maven to run and use MongoDB as database listening on `localhost:27017`. If you
don't have already installed on your machine you can run an instance using Docker:

`docker run -d -p 27017:27017 --name my-mongodb mongo:latest`

After you have started the database you can the application with the following command:

`./mvnw spring-boot:run`

You can create a dice distribution simulation with the following command:

`curl "http://localhost:8080/dice-distribution-simulation?numberOfDice=3&diceSides=6&numberOfRolls=10"`

And you can get a relative dice distribution based on the simulations with:

`curl "http://localhost:8080/dice-relative-distribution"`

