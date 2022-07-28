# Coding Assessment



### qn1 - hashcode() & equals() override
Consider these two simple classes
``` 
public class Person {
    private final int age;
    private final String name;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class Employee extends Person {
    private final String role;
    
    public Employee(String name, int age, String role) {
    super(name, age);
    this.role = role;
    }
}
```
The task is to write appropriate hashCode() and equals() methods for both classes.

### qn2 - Source file reintegration problem - file copy

There are three directories in that tarball (“source”, “dest” and “model”)
The “source” directory contains a small number of java files. The task is to copy/move them
from the “source” directory into the appropriate subdirectory of the “dest” based on
information found in “model” directory

As an example consider this file from the “source” folder:
`       source/com/tc/text/Banner.java`

Searching the “model” directory there exists a Banner.java at this path:
`       model/common-api/src/com/tc/text/Banner.java`

Therefore the file should be moved from “source” to “dest” with the path discovered in “model”
`        dest/common-api/src/main/java/com/tc/text/Banner.java`

There doesn’t need to be any particular interface for this program (ie. paths can be hard
coded so as to not require any command line arguments). There is no specification for any
output either, the task of moving the files to the appropriate location is only requirement. Any
error/exceptional conditions (if any) can simply be printed in any format desired to System.err

### qn3 - Statistic Calculator
The task here is to implement this interface. If you want to write multiple implementations then
that is good, or you can focus down on writing just one. Some notes regarding
implementations:
+ Implementations of this class must be 'thread-safe'. The definition of the term
'thread-safe' is left up to you.
+ Implementations may choose to prioritize (or not) various aspects of the performance
and or behavior of their instances - this is acceptable as long as the compromise can
be explained and justified.
+ You are free to choose appropriate behavior for any corner cases but are expected to
be able to justify those behaviors.
+ If you want to optimize for ‘performance’ (whatever you take performance to mean)
here at the expense of code readability then that is fine, but you’ll need to be able to
explain the code to us. If something appears counter-intuitive or overly complex then
code comments can be useful.

```
package statistics;

    /**
    * Tracks the statistics of a continual stream of values.
      */
    
    public interface Statistic {
    
      /*
    * Called to update the statistic with a new sample value.
      */
    void event(int value);
    
      /*
    * Returns the mean of the received sample values.
      */
    float mean();
    
      /*
    * Returns the minimal received sample value.
      */
    int minimum();
    
      /*
    * Returns the maximal received sample value.
      */
    int maximum();
    
      /*
    * Returns an estimate of the variance of the total population
    * given the received sample values.
      */
    float variance();
    
  }

```

Solutions are there in this project.

This is simple maven project with junit dependency.
