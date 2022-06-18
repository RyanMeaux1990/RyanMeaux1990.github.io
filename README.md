# Ryan Meaux E-portfolio

## Professional Self Assesment
Taking the Computer Science course has helped to shape my professional goals, by giving me a foundation to understand and practice Computer Science. The development of the Eportfolio provided me with an opportunity to demonstrate some of the things that I’ve managed to learn up to this point within the Computer Science program, thus allowing me to be able to shine a light on the areas that I feel are some of my most employable and best areas.  

Working in a team environment is almost inevitable within the computer science field, because of this, it’s very important to understand how to properly use products like GitHub. From the Computer Science program I have gotten the knowledge to be able to properly navigate and use GitHub. 

Communicating with the stakeholders in a project is important in project’s that use the waterfall or Agile Method. The importance of communication with the stakeholders provides the developers with a chance fully understand the requirements of what they’re developing. During the Computer Science program I’ve learned the basics of Agile or Waterfall and how to use each.  

Data Structures and algorithms are important to developers because they are the brains and engine of the software. During the computer sciences program, I’ve learned the basics of how to use, build, and deploy, both algorithms and days data structures. 

Database’s are important because they provide a location to store and pull data from they also serve as a medium where the UI communicates with the code. Within the computer sciences program I’ve learned how to properly manage and create one to one and one to many databases, both mongo and MySQL. 

The Artifacts fit together and demonstrate my knowledge and skill within all of the following areas; improving software, improving data structures, improving databases, improving security, and using data mining. Each section of the required upgrades requires the use of the other pieces of the upgrades. For example, when updating the app to use a 2FA method, it required the updating of data structures to hold other required data, it also required the updating of the databases to add columns to tables, and the expansion of the UI for use.  

## Code Review

[![Code Review](http://img.youtube.com/vi/SLWc6VTsulM/0.jpg)](https://m.youtube.com/watch?v=SLWc6VTsulM&feature=youtu.be)

## Code 

### Original Code
https://github.com/RyanMeaux1990/Weight_Watcher.git

### Two Factor Authentication
https://github.com/RyanMeaux1990/RyanMeaux1990.github.io.git

### Data Structures
https://github.com/RyanMeaux1990/RyanMeaux1990.github.io.git

### Data Mining 
https://github.com/RyanMeaux1990/RyanMeaux1990.github.io.git

## Artifact Description
For all three the artifacts that I will be submitting come from an app that I created for my IT-365 class, known as Operating System Environments. The app that I created is called Weight Watcher. Originally, the app tracked the weight of a person, by the input the user puts in, and it allows the user to be able edit and delete any previous inputs, once the user gets to their desired weight, it will give them a notification. What my intentions are for this class, are to extend the app to be able to track body measurements, so the user's can do data mining on their own weights and body measurements.

## Justification For Inclusion
The reason I chose to include this one artifact for use in all three sections of this class is because it allows me the ability to showcase all of my abilities. Because I created the entire app from the ground up, from the UI design, to the database design and creation, and implementing everything, it  allows me the leverage to be able to add onto it in anyway that I see fit.

## Artifact One: Engineering

### List of upgrades
1. Updating SQL Table to include a column for users phone number. 
2. Created a new view for the execution of the Two Factor Authentication methods.
3. Created new methods in the Database Controller class to execute the Two Factor Authentication. 
4. Added a new text box to the registration page to capture the phone number. 

### Demonstrated Skills
1. Create and design creative solutions to address security flaws. 
2. Demonstrate the ability to develop solutions to solve logic problems. 
3. Updating and managing databases.
4. Using, updating, and managing data structures.

### Course Outcome: 
I developed a security mindset that anticipates adversarial exploits in software architecture and designs to expose potential vulnerabilities, mitigate design flaws, and ensure privacy and enhanced security of data and resources by completing the following enhancement. This was done by ensuring that there was two forms of authenticaon involved in the logging in of the app. 

### Course Objectives
For the first step I proposed to extend the app by creating and building a form of two factor authentication.

### Reflection
For modifying the artifact the first thing week I learned a few things. The main thing that I learned is that if your code is too difficult to understand, adding anything to it is almost impossible. I also learned that if you do not write good comments it will also be impossible to fully understand how everything will work. The challenge that I faced was adding a new column within the database.

## Artifact Two (Algorithms and Data Structure): 

### List of upgrades
1. Updating SQL Table to include new columns to hold all data from the Measurements class. 
2. Created a new class called Measurements. 
3. Updated the User class to require an object of Measurements.
4. Created A new view called UserInput that takes the users input from measurements they take. 
5. Created new database functions to create/update/delete specific meaurement entries.   

### Demonstrated Skills
1. Demonstrated the ability to develop solutions to solve logic problems. 
3. Updating and managing databases.
4. Using, updating, and managing data structures.
5. Creating and updating User Interfaces.
6. Adding app functionality and complexity. 

### Course Outcome 
1: I employed strategies for building collaborative environments that enable diverse audiences to support organizational decision making in the field of computer science by completing the following enhancement. This was done by building data structures that was explandable and usable by others. 

2: I designed, developed, and delivered professional-quality oral, written, and visual communications that are coherent, technically sound, and appropriately adapted to specific audiences and contexts by completing the following enhancement. This was completed by ensuring that all of the necessary comments were added to the program. 

Outcome 4: I demonstrated an ability to use well-founded and innovative techniques, skills, and tools in computing practices for the purpose of implementing computer solutions that deliver value and accomplish industry-specific goals by completing the following enhancement. This was done by expanding the data structures to allow for the implementation of data mining. 


### Reflection
For modifying the artifact this week I learned that if you do not have a really modular piece of software, adding anything to it will be hard to do. During the second week I continued to have difficulties extending the database but eventually ended up getting it, after reading the documentation, I learned that all I had to do was upgrade the database version, and the sqlUpgrade function would fire and upgrade the database. Once the database upgraded, I was able to store the new data structures, which would make way for the next upgrade data mining.

## Artifact Three (Databases):

### List of upgrades
1. Added a new view to hold the chart.
2. Incorporated AnyChart API.
3. Connected the API to display data for data mining. 

### Demonstrated Skills
1. Demonstrated the ability to develop solutions to solve logic problems. 
3. Incorporating an API.
4. Using, updating, and managing data structures.
5. Creating and updating User Interfaces.
6. Adding app functionality and complexity. 
7. Providing data mining functionality. 

###Course Outcome 
3: I designed and evaluated computing solutions that solve a given problem using algorithmic principles and computer science practices and standards appropriate to its solution, while managing the trade-offs involved in design choices by completing the following enhancement. By using data mining we are able to solve the problem of visualizing their weight loss and muscle growth. 

### Reflection
For this week I struggled to use the Gradle system to incorporate the data mining package but through the use of trial and error I ended up getting it to finally download the API that I needed to bring in the charts for the data mining. Once I was able to get the package installed, I next had the chance to learn about creating charts within the app.
