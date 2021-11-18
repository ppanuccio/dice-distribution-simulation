## Dice Distribution Simulation

The following application simulates several rolls of the dice and provide a relative distribution grouped by dice number
and dice sides. Dice number, dice sides and number of rolls for each simulation can be specified as parameters.

### Technical Note

I tried to model the domain as object entities. In such a way I'm able to test domain business logics without worrying
about infrastructure's stuff and I was able to test my entities in isolation. The `domain` package includes all the main
business logic. The `infrastructure` package it contains mainly frameworks' stuff and in general everything that isn't
related to business logic.

I decided to use MongoDB as database since it is particularly suitable for this use case, where there isn't a fixed
schema because every dice distribution simulation can vary depending on input parameters like `diceNumber`
,`numberOfRolls`
and `diceSides`. Moreover, in this implementation, I aggregated the data by `diceNumber` and `diceSides` within the
application; using `MongoDB` allows me in future to leverage on MongoDB features like filtering and aggregations to
performs such aggregation directly within the database improving the performance of the application.

### How to run

This is a Spring application with Maven and use MongoDB as database listening on `localhost:27017`. If you don't have
already installed MongoDB on your machine, you can run an instance using Docker:

`docker run -d -p 27017:27017 --name my-mongodb mongo:latest`

After you have started the database you can run the application with the following command:

`./mvnw spring-boot:run`

You can create a dice distribution simulation with the following command:

`curl "http://localhost:8080/dice-distribution-simulation?numberOfDice=3&diceSides=6&numberOfRolls=10"`

And you can get a relative dice distribution based on the simulations with:

`curl "http://localhost:8080/dice-relative-distribution"`