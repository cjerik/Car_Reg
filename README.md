# CarReg
INDA Project DD1349. 

## Idea 

Our idea is to develop a system, where one can input a picture of a car and our program will calculate with the help of the "Mean Value Theorem" if the car has been exceeding the speedlimit. 

### Detailed description
There will be a GUI where a user firstly can decide a distance between two checkpoints and any speedlimit inbetween. Then the user can submit any number of pictures of cars crossing the checkpoint. If it's the first time the system will store the cars registration number, and the time that the car crossed the checkpoint. If the same car is later spotted at the second checkpoint the system will check if the car has been over the speedlimit during its travel. If the car hasn't been speeding, nothing will happen. But if the car has been speeding, the user will be alerted with the average speed, the car registration number and the time of the crossing. 

### API
For the recognintion of registrationplates of the cars "openalpr" will be used, and for the GUI we will use Javas own AWT. In order to use "openalpr" we need OkHttp and OkIO (for sending to and receiving from openalpr) and GSON for parsing response.

#### Contributors 
Aleksandar Mitic and Carl-Johan Eriksson

