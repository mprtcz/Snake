# Snake

A desktop java application implementing a popular Snake game.
Use w,s,a,d to move a snake round the drawing field, control the Snake Speed slider to adjust game's speed. 
With every consumed brick the game increments Points value displayed on the right edge of the screen.

The application's sample output:

![sampleOutput](https://media.giphy.com/media/3o7TKNs4WfRWDcKZQ4/giphy.gif)


To generate a jar file of this project using maven:
  1. after cloning the project, open terminal in the project's main directory and type:

  `mvn package`
  
  2. Next go to generated `target` directory:
  
  `cd target`
  
  3. To run generated jar file enter:
  
  `java -jar Snake.jar`