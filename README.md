## Exercise Repo
### Author : greyamigo (Georgy Babu Abraham)

### Development Environment Setup

Requirements:
  - Java 8 
  - SBT
  
### Building

Checkout and build the repository:

```
git clone https://github.com/greyamigo/exercise.git
cd exercise
sbt compile             # compile all files to class files
sbt test                # run all tests (Sample Data is loaded in tests)
```  

#### Employee Exercise


Q1. Employee Associates : 
   Organisation is a group of Employees and each employee has name, direct manager and list of projects
   
Q2.2 Create a function that for a given organisation and employee will return all their project
team mates (employees that work on a common project).

   ```getMates(org:Organisation, emp:Employee):List[Employee]```

Q2.3 Create a function that for a given organisation and two employees will return their first
common manager.

   ```getClosestManager(organisation: Organisation, emp1:Employee, emp2: Employee):Option[Employee]```
   
Q2.4 Create a function that for a given organisation and project will provide a guess on the
closest people manager responsible for the project based on the number of direct reports working on it
   
   ```guessWhoseResponsible(organisation: Organisation, project: Project):Employee```
    
    
    
### URL Pinger

Q2.1 - Define an object with the minimal set of properties to save to the DB to represent the
response to a web page request. 

```case class ResponseObject(uri:URL,body:String,responseCode:Int,cookie:Map[String,String])```


Q2.2 - Write a script/class that given 4 urls, it will send a request to each one of them and
store the result in the database.

```def ping4URlsandSaveToDB(urls: List[URL])```


Q2.3 - Now, if a given url is in the test.com domain, also print the results to stdout.

```def ping4URlsandSaveToDB(urls: List[URL])```

Q2.4 - Write a function that will find all urls in the database that tried to set a cookie when
requested.

```def findJSessionID()```