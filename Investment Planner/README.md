# Investment Tracker

## About the project:

This application would be useful for people interested in investing, the app currently supports investments in **stocks,
cryptocurrency or real estate.**
The way it works is simple, the users create a list, pick a number of potential investments that they'd like to add to
the list. The investments can also be organized by type (i.e Crypto), amount (i.e $500), and specific names *(i.e
Bitcoin, TSLA)*, which helps the user keep track of various types of investments in one place. I picked this project
because I am interested in learning more about investing and stocks, as I plan to make some investments in the future,
this would be a good opportunity to research the topic in depth.

## User stories:

- As a user, I want to be able to create a list of investments
- As a user, I want to be able to add an investment to the list
- As a user, I want to be able to check the total amount of money I have invested
- As a user, I want to be able to view (load) the entire list of investments
- As a user, I want to be able to edit(update) the details of the investment in the list
- As a user, I want to be able to reset/clear my investment list.
- As a user, I want to be able to save my investment list to file
- As a user, I want to be able to be able to load my saved investment list from file
- As a user, I want to be able to be able to hide certain selected investments form the list

## Phase 4: Task 2

Fri Nov 26 15:29:55 PST 2021 New investment added to list: Bitcoin

Fri Nov 26 15:30:13 PST 2021 An investment was updated in the list

Fri Nov 26 15:30:19 PST 2021 Calculated the total money invested: $200

Fri Nov 26 15:30:26 PST 2021 All investments were saved

Fri Nov 26 15:30:46 PST 2021 Investment list was reset

Fri Nov 26 15:30:48 PST 2021 New investment added to list: House

## Phase 4: Task 3

- Split the InvestmentPlanner class into two classes, where one of the classes would contain the methods that build the
  JFrame components, and the other would contain the behaviour for the application, this make the code more easily
  readable and searching for methods quicker.
- Abstract the methods that are long and have repetitive behaviour. like initializeRadioButtons().
- Extract short methods from the code in initializeSplash() to make  
  it clean and easy to read and understand its behaviour.
- I would abstract all the short, similar JFrame methods (button creators, component adders)
  into a more compact form since the code is too repetitive.

*REFERENCES*:
Phase 2:
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

Phase 3+4:

https://stackoverflow.com/questions/33553881/java-vertically-align-buttons

https://www.youtube.com/watch?v=1JjTAxbsDqs

https://stackoverflow.com/questions/50545296/java-gui-shows-blank-until-resize

https://stackoverflow.com/questions/8176965/how-to-add-element-to-existing-jlist/19511390

https://stackoverflow.com/questions/201287/how-do-i-get-which-jradiobutton-is-selected-from-a-buttongroup

https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution

https://stackoverflow.com/questions/7080205/popup-message-boxes

https://stackoverflow.com/questions/15258467/java-how-can-i-popup-a-dialog-box-as-only-an-image

https://stackoverflow.com/questions/16134549/how-to-make-a-splash-screen-for-gui

https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed