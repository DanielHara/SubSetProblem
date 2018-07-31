# SubSetProblem
This project solves the SubSet problem for positive numbers as a practical application for financial markets.
The idea for this program came from my internships in the financial industry.

Given `n` fixed interest rate assets (a bond contract or similar), each with an interest rate of `r` one wants to choose `p <= N` such as the effective interest rate of the set of the chosen assets is
as near as possible of a desired average. This is useful for building combined assets of bonds. There is also interest to restrict the obtained average to be greater or equal, or lower or equal than the desired average,
or indifferent to this (ie, as near as possible to the desired average).

This is a specific version of the SubSet problem for positive numbers, which admits a solution (implemented here) in time `O(n*T(1+p))`, where `T` is the number of intervals the interval `[0, desiredAverage * p]` is divided to be able to convert the problem to the SubSet problem with integers.
This differs from the trivial solution, which consumes time `O(n!/(p!(n-p)!))` (which would be far too expensive).

To build the project, enter `mvn clean install`.
To test the project, enter `mvn test`.
To run the project, place input.txt in `src/` and enter `mvn package && java -cp SubSetSolver-1.0-SNAPSHOT.jar`.

The input and output of the program are by .txt. files.

First, we write the desired mode of operation: `BEST_AVERAGE` (goes as near as possible of the desired average),
                                               `ABOVE_AVERAGE` (goes as near as possible of the desired average, but must be greater or equal the desired average).
                                               `BELOW_AVERAGE` (goes as near as possible of the desired average, but must be less or equal the desired average).

input.txt:
```
<MODE_OPERATION>
<Total number of available contracts> <Number of contracts to choose from> <Desired Average>
n contracts:
<Number of contracts available with> <Rate>
```
Here are examples of input and output files:

input1.txt:
```
BEST_AVERAGE
5 2 9.5
2 9.0
2 10.0
1 20.0
```

output1.txt
```
1 contracts with rate 10.0
1 contracts with rate 9.0
Average: 9.5
```

input2.txt:
```
BEST_AVERAGE
600 50 9.33
100 9.23
200 9.32
300 9.49
```

output2.txt:
```
3 contracts with rate 9.49
47 contracts with rate 9.32
Average: 9.330199
```

input3.txt:
```
ABOVE_AVERAGE
800 160 11.23
1 11.13
20 11.86
79 11.01
200 11.65
350 11.90
150 10.99
```

output3.txt:
```
32 contracts with rate 10.99
56 contracts with rate 11.65
72 contracts with rate 11.01
Average: 11.2300005
```

input4.txt:
```
BELOW_AVERAGE
1000  300 30.14
200 30.09
400 30.24
100 31.9
300 29.98
```
output4.txt:
```
100 contracts with rate 30.24
199 contracts with rate 30.09
1 contracts with rate 29.98
Average: 30.139635
```


