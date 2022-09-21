# java-reactive
Java reactive project

Things to be covered-

1. The "default" blocking programming model and its limitation which is conventional programming.
2. Understanding Reactive programming.
3. Build scalable reactive code using Project Reactor.
4. Learn operators and transform reactive streams.
5. Learn patterns, common best practices and pitfalls with reactive programming.
6. Reactive programming + Spring Boot application development architecture.

Prerequisites-
1. Some Java knowledge including Collections and stream basics.
2. A computer with Java 11 + installed (Java 17+ preferred).
3. A Java IDE like Intellij IDEA (Free version should do).

What is reactive programming?
=> Reactive programming is a declarative programming paradigm concerned with data streams and the propogation of change.
With this paradigm, it's possible to express static (e.g. arrays) or dynamic (e.g. event emitters) data streams with ease, and also communicate that an inferred dependency within the associated execution model exists, which facilities that automatic propagation of the changes data flow.

Traditional use cases-
1. User events
    - When  user clicks a button
    - When I/O response happens
2. React to something
    - When user clicks this button, run this function

Why do we care?
1. Server side web development
    - Request comes in
    - We do processing
    - We return response
2. A case of reactive programming
    - Modern application developement
        - High data scale
        - High usage scale
        - Cloud based costs
    - How do you scale up?
        - Vertical scaling
        - Horizontal scaling

What's the problem here?

@GetMapping("/users/{userid}")
public User getUserDetails(@PathVariable String userId){
    User user = userService. getUser(userId);
    UserPreferences prefs = userPreferenceService.getPreference(userId);
    user.setPreference(prefs);
    return user;
}

Below is problem-
User user = userService. getUser(userId);
UserPreferences prefs = userPreferenceService.getPreference(userId);

It's waiting at both the calls for the response and 2nd call is dependent on 1st call completion.

1. Unnecessarily sequential
1.1 Cost
       - Performance
2. Idling threads
2.1 Cost
        - Wasted hardware

Spoilt backend developers
- Never had to think about concurrency
- It's single request
- Multiple simultaneous users abstracted out
- Delays abstracted out
- We pay with sequential blocking operations
- We pay with idling threads

There are concurrency APIs which can be used instead of Reactive Java.

Future and Completable Future
- CompletableFuture<User> userAsync = CompletableFuture.supplyAsync(() -> userService.getUser(userId)));
- It'll not block and will give Future.
- It's avaiable since Java 8
- Implements 'Future' and goes beyond it.
- Works with 'CompletionStage' to coordinate async operation.

Using CompletableFuture
- Call userService
- Call userProfileService in parallel
- When both return, merge the data structures
- Return merged objects

- CompletableFuture<User> userAsync = CompletableFuture.supplyAsync(() -> userService.getUser(userId));
- CompletableFuture<UserPreference> userProfileAsync = CompletableFuture.supplyAsync(() -> userService.getUserPreference(userId));
- CompletableFuture<Void> bothFutures = CompletableFuture.allOf(userAsync,userProfileAsync);
- bothFutures.join();

The problem
- Too much for the dev to do
- Error handling is messy
- It's still sync after all
- We need a new paradigm
- The framework needs to support it

Reactive Programming sample for what was done above-
@GetMapping("/users/{userId}")
public Mono<User> getUserDetails)(@PathVariable String userId){
    return userService.getUser(userId).zipWith(userPreferenceService.getPreference(userId))
                .map(tuple -> {
                    User user = tuple.getT1();
                    user.setUserPreference(tuple.getT2());
                    return user;
                });
}

What's different-
- Much simpler than the manual concurrent way
- Few resuable flexible functions
- Combine and reuse in powerful ways

Reactive Programming
- Declarative code to build asynchronous processing pipelines
- Different way of thinking about flow of data
- Different way of thinking about data
- Integrated with Java! 'Flow' interface
- It needs to click
- It has learning curve
- It's not worth it for small projects
- Familiar yet different
- It looks similar to Collections streams

Loops
- Coding without loops. We can't deal with collections with loop.

Java Streams referesher-
- Represent a sequence of data
- Focus on computations
- vs collections which focus on storage
- Internal iteration

Example-
List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

numbers.stream().forEach(numer -> System.out.println(number));

Stream operators-
- map
- filter
- flatMap
- findFirst
- so many more

Design Patterns-
- The "Gang of Four"
- Object Oriented Design Patterns

Two Patterns
- Iterator Pattern
  - The iterator pattern is a design pattern in which an iterator is used to traverse a container and access the container's elements
  - The iterator pattern decouples algorithm from containers
  - It's gives use consistent behavior. Iterator -> Consumer
  - Container gives Iterator Impl ->Data-> Consumer <-Next<-
- Observer Pattern
  - The observer pattern is a design pattern where the subject maintains a list of its dependents, called observers, and notifies then automatically
  of any state changes, usually by calling one of their methods.
  - Source <- Observer <- Consumer
  - I've new Data -> Source ->Data-> Observer

Assembly line analogy is true for Reactive Programming. True assembly line. Items come over time.



