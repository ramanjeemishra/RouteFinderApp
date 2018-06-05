## Route finding problem

This solution uses Uniform cost search and Depth first search
for finding the optimal and all possible routes. Depending on constraints defined in route command search will stop. Breadth First search implementation can be used for shortest depth/level path identification.

- For shortest path Uniformm Cost Search algorithm is used.
- To represent the graph, Adjacency List algorithm is used.
- Test cases were developed using JUNIT 5.2.0-M1 version.
- Checkstyle, FindBugs, Jacoco was used for quality check, but build was not marked as failed.
- To run the program gradle or simple java code can be used.

Using gradle command is, Arguments to the program( please refer to build.properties)
- Input Routes - 'input-route.txt',
- Set of commands to execute - 'input-command.txt'
- Output wll be generated in - 'output.txt'

```
gradle run
```


#### To run using simple java (Java 8)
```
com.rjm.app.RouteFinderApp 'input-route.txt' 'input-command.txt' 'output.txt'
```

### Opportunity for optimization
- Improve naming convention for ADT
- Run Commands in parallel and improve exception handling of command file
- Refactor GraphSerachTemplate method to make apply SRP
- Provide java docs wherever name is not self exmplanatory
