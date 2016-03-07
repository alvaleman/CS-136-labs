Alvaro Aleman
CS 136
Prof.Yorgey
3/9/2015


---------------------------------
Though Questions
---------------------------------
5.5- Using the rule of thumb that each loop that performs n iterations multiplies the complexity of each iteration by a factor of n, the square matrix multiplication method run time is O(n^3)


5.12- Log base 2 is useful particularly for determining the runtime or methods that use the divide and conquer implementation strategy. Since log is a ratio based on the base using log base 2 does not make much of a difference from a complexity viewpoint. Plus, if we wanted the log of a number we would need to write the method, in which we can use a given base. Abstraction is useful so users do not need to know what type of log base we use as long as it is effective.


5.16- We can iterate through the list, keep track of the numbers that have been checked and check the next one. Worst case would be when no numbers match, going through the whole list and checking each one and finding it does not match. The function will thus have to have two loops, having a growth rate of O(n^2) 


6.4- In bubble sort each pass of the bubbling phase performs n-1 comparisons and as many as n-1 exchanges. Thus the worst case cost of performing bubble sort is O((n-1)^2) or O(n^2) operations. In the best case, none of the comparisons leads to an exchange so bubble sort would run in O(n) time. We would expect to see some exchanges in the average case, leading to a quadratic run time. 


6.13- Selection sort is stable since equal values are just placed an index below the current one. Insertion sort is stable since the values are added to form a “hand” of sorted values so equal values are not swapped. Merge sort is not stable since equal value between data and temp lead to the temp item being chosen to put into the sorted data. Quick sort is stable since a swap only occurs if the value to the left is less than the one in the right- equal values are not swapped. 




---------------------------------
Class Descriptions
---------------------------------
Main.java- This class reads in the phonebook data, creates the student objects, and sorts the objects in different ways using comparators in order to answer particular questions


Student.java-  Student object that will be used to hold information for a particular student. Also includes comparators to sort data in different manners


MyVector.java - This class is an extension of the vector class in which we implement an insertionSort method that will be used to sort the vector based on the camparator parameter that is passed


AreaFrequency- This class creates associations between the area codes and the number of occurrences for each area code


----------------------------------------
Data description questions
----------------------------------------
A) Student that appears first in the printed phone book if the book is arranged in alphabetical order: Aalayah Rasheed

B) Largest SU Box number belongs to Grant L Sanders with #3334
smallest SU Box number belongs to Charles P Abba with #1028

C) The person with the most vowels in his or her name is Olufunmilayo Olosunde with 11
The person with the least vowels in his or her name is Chin H Ho with 2

E) Top ten most common area codes for student home phone number
1. 718 with 89 people with this area code.
2. 413 with 71 people with this area code.
3. 781 with 71 people with this area code.
4. 203 with 69 people with this area code.
5. 978 with 67 people with this area code.
6. 212 with 62 people with this area code.
7. 617 with 52 people with this area code.
8. 508 with 51 people with this area code.
9. 914 with 51 people with this area code.
10. 610 with 47 people with this area code.