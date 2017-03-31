# Snake

A desktop java application implementing a popular Snake game.


![sampleOutput](https://sc-cdn.scaleengine.net/i/554964ca61d3ff3d4f7e9cc3f214a5a93.png)

Use `W` | `S` | `A` | `D` keyboard buttons to move a snake on the drawing field, up, down, left, right respectively.
 
Control the Snake Speed slider to adjust game's speed. 

With every consumed brick the game increments Points value displayed on the right edge of the screen as well as snake's length. Amount of points is correlated linearly with the snake's current speed.

Under the `Points` label, there is a list which stores all games' results which took place during the session.
Beneath, there is a label informing the user about a mean amount of points calculated from all the games the user played since the start of the application.

Current game can be stopped by pressing `Stop Game` button, or by the snake's collision with the wall or the snake itself.

The application's gameplay:

![sampleOutput](https://media.giphy.com/media/3oriNXLE0PBI5AguRi/source.gif)

---

To generate a jar file of this project using maven:
  1. after cloning the project, open terminal in the project's main directory and type:

  `mvn package`
  
  2. Next go to generated `target` directory:
  
  `cd target`
  
  3. To run generated jar file enter:
  
  `java -jar Snake.jar`