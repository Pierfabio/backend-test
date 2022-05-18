# MY SOLUTION

During this project I refactored the existing code and implemented some features in order to achieve the main goal of make the code more understandable and let the Rover to be able to run safetely on Mars.

The first thing was to write some basics tests for the simple rover operations and try to implement those features, and to split code responsibilities in more than just one class.

After a carefully research on the web for understand which approach could be the best for managing coordinates in limitated portion of a map, I decided to use a representation that includes the rover coordinate in the related axis with the max value that it can assume.
For example the 'RoverCoordinates' object contains two 'MapPoint', one for each axes, it means that 'MapPointX' contains the actual rover position in the x axis and also the maximum position for the rover in this axis.

The object 'RoverCoordinates', which is the 'MarsRover' one and only field, in his constructor has one important feature: the fields 'MapPoint' are built based on the information passed from 'MapPlanet', which represents planet's dimensions, and 'roverStartingCoordinates', that is, as is name suggest, the point where the rover is spawn the very first time. This class is also responsible for the rover movement and the obstacles detection.

Class 'MarsRover' at the beginning contained all the flow of the software, now is just able to receive the direction command that refers to the 'RoverCoordinates' methods.

This is an explanation of my solution and the way I worked on this task, I tried to divide responsibilities all over the code, when possible, trying also to keeping intact the project's core.



