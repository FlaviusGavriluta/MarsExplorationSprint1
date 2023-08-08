

Before we begin with the actual construction of the tests, let's analyze how we apply the OOP principles
and concepts that you wish to learn in this task:

## Single Responsibility Principle (SRP)
The `MapConfigurationValidatorImpl` class has the sole responsibility of validating the map configuration,
thus fulfilling this principle.

## Open/Closed Principle (OCP)
Since `MapConfigurationValidator` is an interface, you can add other implementations in the future without
modifying the existing code.

## Dependency Injection
This principle allows objects to receive their dependencies, instead of creating them themselves.
In this context, it is not directly applied but can be explored in other parts of the application.

## Practice Interface Segregation Principle
The `MapConfigurationValidatorImpl` class implements a simple and clear interface, which means that it is not
obliged to depend on methods it does not use.

## Data-Driven Testing
The unit tests that we will create will use different data sets to validate various scenarios and use cases.

## Multidimensional Arrays
Although this aspect is not directly involved in creating validation tests, it was used in other parts of the project,
such as representing the elements on the map.

## Work with Random
Similar to multidimensional arrays, working with random values is an aspect that could be explored in other parts
of the application but not directly in testing the validation class.