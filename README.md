# BookSystem
A Simple Java program that uses a dynamic CVS file to access/write/edit. 
You can interact with the program via the integrated GUI working with JavaFX. The CSV is updated in real time as you edit/add/delete
books. The page that displays all the books in the inventory ( CSV file ) is dynamically made depending on how many books there are.
There's other options like a search bar. The GUI is very easy to use and intiutive. Inside the program we used an ArrayList of Books
to make a temporary inventory.

Something to add is that only the Admin has the power to edit/add or remove books from the inventory. The methods actually update the
file. It's a simple way to update in real time, we make copy of the original CSV file, delete the old one and rename the new one.
All this is in a method for efficency.

NOTE: All the GUI is on one window that changes depending on the button you click so you can technically say it's dynamic

Authors:Vlad Crihan, Adit Tandon and  Brydon Parsons 
Grade Received A+
