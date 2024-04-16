DeMorgan's Complaint System

In our time at Ashesi, we have noticed that whenver a student has a complaint concerning a proble, the student usually has to go to adminstration to report their complaint. After reporting the complaint, the student has no was of knowing when their complaint will be answered unless they go back to the office. We though this is an inefficient way to report the problem and hence we came up with with DeMorgan's Complaint Sytem to solve this problem.

With out system, student's will be able to log in and send their complaint to any department from their current location. When sending a complaint, the student would be able to select the department to receive the complaint and also the category of the department. The complaint would then be send to deparment office for a response

Our System makes use of 8 classes each with its own function
    -Complaint: This class extends the Message class and it represents a specific type of message used for making complaints. It possess the attributes below:
category: The category of the complaint (e.g., water, electricity).
status: Tracks the status of the complaint (e.g., "open", "responded"). It also inherits methods from the Message class but has getters and setters for category and status. It has the markResponded() method to update the complaint's status to "responded".
    -DeMorganComplaint: This class 
    -History: This class represents the storage of a user's message (whether complaints or responses). 
    -Message:This class represents the messages sent and received by a user within the complaint system. It also possess the attributes below:
text- The textual content of the message.
image- An optional image attachment.
category- The category of the message (using an enum for organization).
sender- The UserComplaints object who sent the message.
recipient- The UserComplaints object intended to receive the message.
ID- A unique identifier for the message.
date- The timestamp when the message was created. It has getter and setter methods for its attributes and the displayMessage() method to print a message in a readable format.
    -Office: This class extends the UserComplaint class and it represents the office that receives the complaint from the student. 
    -Student: This class extends the UserComplaint class and is more specific to students in Ashesi. It includes two instance variables: major and studentID. 
    -UserComplaint: This class creates the user who is sending a complaint to the chosen department office. It include four instance variables: userName, email, password and history.
