# Report
## Process
Not a lot needed to be done here. The code itself was simple to create, and adding synchronization was more of an "experimental" process than proper programming. We implemented the function to launch the application in different modes so that it would be easy to compare the different synchronization modes.
## Observations
### No synchronization
| Situation | Result |
|-----------|--------|
| Order of purchases | Inconsistent |
| Non-existent ticket sales | None |
| Correct available seats printed | Never |
| Correct remaining seats printed | Rarely |
|

Without synchronization, the application seems to work as intended, although the printed values are off. We suspect that on slower systems, the system might break down and accidentally hand out too many tickets. This warrants further testing in the future.

### Synchronized function
| Situation | Result |
|-----------|--------|
| Order of purchases | Inconsistent |
| Non-existent ticket sales | None |
| Correct available seats printed | Always |
| Correct remaining seats printed | Always |
|

When adding the `synchronized` keyword to the booking function, everything works as expected, although for slower operations (which are more likely to actually benefit from threads), the `synchronized` keyword will likely bottleneck the system.
### Volatile variable
| Situation | Result |
|-----------|--------|
| Order of purchases | Inconsistent |
| Non-existent ticket sales | None |
| Correct available seats printed | Never |
| Correct remaining seats printed | (seemingly) Always |
|

When defining the remaining seats as a `volatile` variable, the function acts more or less the same as without any synchronization, but more consistently is correct about the amount of remaining seats. The desynchronization for the correct available seats is likely due to the time taken to actually print the line taking longer than for another thread to mutate the variable, thus creating a desync between the "available seats" print and the "booked X tickets" print.

### Atomic variable
| Situation | Result |
|-----------|--------|
| Order of purchases | Inconsistent |
| Non-existent ticket sales | None |
| Correct available seats printed | Never |
| Correct remaining seats printed | Never |
|

For an atomic variable, it is hard to spot any difference from the unsynchronized function. Theoretically, this should guarantee that there are no errors, but from analyzing the printed results, there is no way of guaranteeing that this is actually the case.

## Theorising
A likely culprit for most of the synchronization modes being indistinguishable from the non-synchronized mode is the print function itself. Since printing tends to take a rather extended amount of time, it is likely that there are still prints in the progress of being performed while the variable is being mutated, which leads to the pipeline of:

1. Seats variable gets fetched for the "available seats" print
2. Other thread mutates variable
3. String combination for "booked tickets" print is completed and the result is printed
4. String combination for "available seats" print is completed and the result is printed.

This is especially likely for the atomic synchronization as the "available seats" print itself includes a function call to get the atomic variable, whereas the "booked tickets" print is composed entirely of thread-specific variables. The same logic would apply to volatile, as fetching the volatile variable would logically take longer than the thread-specific variables.