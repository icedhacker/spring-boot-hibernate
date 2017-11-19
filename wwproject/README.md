**Part 1**

First of all you have to create entities using Hibernate for the following UML diagram. The data has to be
stored in MySQL.

![Alt text](codingtask_part1.jpg?raw=true "Coding Task - Part 1")

A **Person** has a **House** and can have multiple **Children**. Every **Child** has favorite meals which are sorted (first
is the one it likes most).

Now, write a **Spring** application which provides two endpoints:

* **/house** This endpoint returns the House for a given person

* **/child/info** This endpoint returns the Parent and the most favorite meal of a child

The frontend that will use these endpoints will perform ~100,000 requests per hour and 70% of those will be **/h
ouse** and 30% will be **/child/info**. All responses have to be in JSON format.

**Part 2**

Now imagine that **Child** is a super class of **Daughter** and **Son**

![Alt text](codingtask_part2.jpg?raw=true "Coding Task - Part 2")

Write an additional endpoint **/color** that returns, for a given Child, the **hairColor** if the Child is a Daughter or
the **bicycleColor** if the Child is a Son.

**Part 3**

Imagine that you have around 100,000,000 Persons and 300,000,000 Children in your database and you want
to provide a new endpoint **/persons/children** which returns an array in which the element n is the amount of
people having n children. To speed up this request, you want to write a job which runs every 15 minutes,
calculates those numbers and puts them into a new entity **ParentSummary**.

![Alt text](codingtask_part3.jpg?raw=true "Coding Task - Part 3")

