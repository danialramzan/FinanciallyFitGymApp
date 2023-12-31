﻿# FinanciallyFit.

## Project Overview

FinanciallyFit is the implementation of a new business idea that completely revolutionizes the way gym memberships work in Java. You might've heard the phrase: "It pays to be fit!" but what if it actually paid* to be fit? That is what FinanciallyFit is. The main selling point of this gym is that along with a lower than average base fee, when you first sign up you set up a gym plan which you stick to. Whenever you attend the gym you have to log your check-in and check-out times. Every time you deviate from this plan you get charged extra in order to give you a financial incentive to stick to your routine. If you stick to your schedule, you end up getting access to a gym for a very low price. At the end of each month, the system generates a bill for each member, displaying the total amount owed based on their attendance record for the month.

## Who and Why?
This billing structure is suitable for anyone who like me, struggles to effectively commit to things and follow through with them as part of a regular routine. I had this idea for a gym billing system which would solve the problem and would me go "Hey, I'm going to lose money if I don't go to the gym today!". I believe there are many others like me who would benefit from such a fee based structure because it aligns the best interests of both your health, and your wealth. This model has the potential to make fitness more realistic to a wider audience, and I would like to implement the billing system in Java as a proof of concept to demonstrate to people how the idea can work practically.

## User Stories:

- **As a user, I want to sign up for a a cheaper gym membership that also helps me stay committed to a schedule**

-  **As a user, I want to be able to add an account/profile for myself into a list of gym members so my stats and information can be documented.** -X's in Y

-  **As a user,  I want to be able to choose the number of days I am allowed to miss in a week/month**
    
- **As a user, I want to be able to log my daily attendance, to document my progress and hours put in**
    
- **As a user, I want to be incentivized (in this case, financially) to attend the gym regularly, so something like laziness does not derail me and I am motivated to maintain a consistent workout routine.**
    
-  **As a user, I want to have the option to select and view my information and statistics since the start of the month until the current date out of a list of gym members e.g. amount due.** - viewing list of items

-  **As a user, at the end of the month I want to receive a monthly bill that shows the total amount I owe based on my attendance records, making it easy to track my expenses.**

-  **As a user, I want to be able to be able to save the state and information of all of my Gym Members to a file.**

-  **As a user, I want to be able to be able to load the state and information of all of my Gym Members from a pre-saved file so I dont have to reenter it every time I launch the program.**

## Instructions for Grader

-  **You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking register method and adding the details, then clicking okay.**
- **You can generate the second required action related to the user story "adding multiple Xs to a Y" by checking check attendance by date on the main menu, this tells you a list of everyone who attended on a certain day (filter).**
- **You can locate my visual component when the app opens, there is a splash screen, and every subsequent panel in the app contains the logo.**
- **You can save the state of my application by clicking the save button.**
- **You can reload the state of my application by clicking the load button.**

## Phase 4: Task 2 (sample of EventLog output upon system exit):

<pre>
Mon Nov 27 23:56:46 PST 2023
**EVENT** Member: Danial Ramzan registered on 2023-11-17 with allowed missed days: 2


Mon Nov 27 23:56:54 PST 2023
**EVENT** Attendance was just logged for member Danial Ramzan: 3.0 hours logged on 2023-11-18, changing number of days attended to 1


Mon Nov 27 23:57:04 PST 2023
**EVENT** Attendance was just logged for member Danial Ramzan: 4.0 hours logged on 2023-11-19, changing number of days attended to 2


Mon Nov 27 23:57:15 PST 2023
**EVENT** Member: Paul Carter registered on 2023-11-18 with allowed missed days: 4


Mon Nov 27 23:57:23 PST 2023
**EVENT** The attendance record for 2023-11-17 was requested, the members who attended are: Nobody!


Mon Nov 27 23:57:31 PST 2023
**EVENT** The attendance record for 2023-11-18 was requested, the members who attended are: +Danial Ramzan


Mon Nov 27 23:58:03 PST 2023
**EVENT** The attendance record for 2023-11-19 was requested, the members who attended are: +Danial Ramzan


Mon Nov 27 23:58:34 PST 2023
**EVENT** Attendance was just logged for member Paul Carter: 4.5 hours logged on 2023-11-19, changing number of days attended to 1


Mon Nov 27 23:58:57 PST 2023
**EVENT** The attendance record for 2023-11-19 was requested, the members who attended are: +Danial Ramzan +Paul Carter

</pre>

## Phase 4: Task 3 (opportunities for refactoring): 

My prime candidate for refactoring would probably be my FinanciallyFitModel class; it was born out of a stage where I realised making all my methods public and static did not comply with the rubric, and I needed to rectify the situation by making public getter methods which could called by the UI. It is just a collection of code that can very easily be integrated into MembersManager, and removing it from my program entirely with all its methods put into MembersManager would probably be a good idea.
