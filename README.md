
# Bubble Sort Algorithm Libraries: A Performance Evaluation

This Java application was created to experiment with three different bubble sort algorithms. A detailed explanation of the experiment's design, implementation, findings, and replicability can be found in the [report](https://github.com/erickgarro/EE-Bubble-Sort-Experiments/blob/main/report/Garro%20and%20Guerrero%20-%20Bubble%20Sort%20-%20Performance%20Evaluation%20Report.pdf).

The artifacts to replicate the expertiment are available in this repository.

## Procedure

To run the experiment, we created an application that automates the initialization of our data and the execution of the measurements, results collection, and data processing.

The program allows the experiments to be fully customized by parameters-passing on invocation within the restriction of a fixed set of *data types*.

The user can set the following variables declaring flags for:

-   the number of iterations to run each instance of source data for each Bubble Sorting algorithm to evaluate

-   the seed to initialize the randomized data generator

-   the length of the strings to assess, the array sizes to use

-   the last *n* number of results from the executed iterations to be
    considered for the data evaluation

The usage of the application is as follows:

```
java -jar BubbleSortExperiments.jar [-i <iterations>] [-s <seed>] [-l <string length>] [-a <array sizes>] [-n <last number of results>] [-h] [-r]
```

If the user does not provide any of those values, the system will execute with these default values:

-   iterations: 1,000

-   seed: Java standard random seed

-   string length: 5 characters

-   last number of results: 30

The user can also call the program with the *-r* flag to get a basic overview of the statistics of every iteration, or if needed, with the *-h* flag to get the usage details.

When invoked, the program looks on the disk for all pre-filtered files available, and if they match the string length, it will load it into memory. If no matching file exists, it will process an external file containing 370,103 unique strings and generate the corresponding filtered version. The files is located in `data/all_strings.txt`  inside the working directory. The original file is publicly available[^1].

The program then initializes the arrays that will hold the data to sort for each array size and type and populate them with randomly generated data. In the case of the strings, it will randomly take the words from the pre-filtered data.

After all randomized data arrays are generated according to the current array size and data type, the program is ready to enter the next phase, where data ordering comes to play.

One ordering type at a time, the program will create clones of the corresponding random array and run them sequentially through the provided Bubble Sort algorithms. Because the Enums are also sorted by default, the ascending array is processed first, the descending second, and the random third.

When finished, it continues to the following data type. When all data types for that array size are done, it will iterate again with the next array size if there is more than one.

[^1]: <https://github.com/dwyl/english-words/blob/master/words_alpha.txt>

Before the sorter in evaluation processes each configuration, an arbitrary total of seven garbage collection invocations are performed, aiming at clearing up the memory heap of unused objects .

Then a timer in nanoseconds precision is started and stopped right after the sorter finishes. Execution time is then calculated and collected in a Result class instance, which is pushed into a Result Stack. The measurements are collected for further processing to extract statistical data in the next stage.

The entire process concludes when a CSV file is generated and persisted on the hard drive.

### Project structure

The source code of the experimentation program is structured inside the main package `ch.usi.ee`, where the *Main.java* class resides.

<!-- insert image https://github.com/erickgarro/EE-Bubble-Sort-Experiments/raw/main/report/project_packages_structure.jpg-->

<img src="https://github.com/erickgarro/EE-Bubble-Sort-Experiments/raw/main/report/project_packages_structure.jpg" alt="Project package structure" style="
  width:40%;
  display: block;
  margin-left: auto;
  margin-right: auto;"/>

The rest of the project was structured in six sub packages, considering future extensibility and maintainability. Here is their high level description:

-   **bubble_sort**: contains the *sort* interface and the three implementations provided of Bubble Sort.

-   **data**: contains the strings source file for the String processor, and the data generators and pre-sorter

-   **enums**: contains Enum classes to describe the algorithms that can be tested, and the available data orderings and data types

-   **experiments**: holds the class that executes the experiments

-   **io**: manages all input and output data from and to the hard disk, as well as the creation of CSV files

-   **statistics**: handles result holders and statistics generation
