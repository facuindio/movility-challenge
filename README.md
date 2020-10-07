# movility-challenge
This project is my solution for a job interview challenge. As there was no specific requirement for the input, I created an API, because I think is easier to test and clearer than a console input, a file reader, etc.

# Running
For executing the application you just need to run it.

The solution is serving under the context http://localhost:8080/api/v1/positionServiceImpl

Please consider that the verb is a POST, so you can execute it successfully with this body: 
{
"input" : "5 5\r\n1 2 N\r\nLMLMLMLMM\r\n3 3 E\r\nMMRMMRMRRM"
} 

Please keep in mind that the LINE_SEPARATOR can change depending on the system, so for windows is \r\n (as teh example I copied), but you can change it depending on the System you are using (the application is ready to support any System).

# Notes
I calculate when a mower leaves the plateau, and I inform that, but I don't handle it. I also son't handle collisions between mowers.

In the pdf you sent me, the second line of "Output Test Case #2" was 5 1 N. I assumed this was a mistake, so in the test case givenChallengeInput_shouldSolveTheProblem(), where I deal with the solution of the propose input, I correct the output to be consistent (x=1, y=5 E instead of x=5, y=1 E).