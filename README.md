# Created This Note making App for Android device
# The complete app is built on Android Studio(Jelly-Fish)
# To maintain the code readability and maintainabilty I have implemented MVVM architecture,MVVM is an most popular architecuture used for designing Android apps.
# MVVM Stands for Model-View-ViewModel,where we separate the logic from the UI to enhace the code readability and maintainability:
View is the Collection of Visible elements which also accepts User input.This includes user interfaces (UI), animations and text. The content of View is not interacted with directly to change what is presented.
ViewModel is located between the View and Model layers. This is where the controls for interacting with View are housed, while binding is used to connect the UI elements in View to the controls in ViewModel.
Model take care of the logic for the program, which is retrieved by the ViewModel upon its own receipt of input from the user through View.
# While working on this project I learned how to store the data entered by user locally on your phone,to achieve this I have used Room Database:
The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
# There Are three Major Components in Room:
1) The database class that holds the database and serve as the main access point.
2) Data Entities that represent the table in your Phone Application
3) Data Access Objects(DAO) that provides Method that your app can use to query,update,insert,delete,sort etc.
