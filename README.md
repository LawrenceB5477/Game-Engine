#Game Engine 

This is a test game engine running on a single thread. 
*The engine revolves around a render loop and an update loop 
*Graphics are rendered as fast as the CPU will allow, game objects are updated 60 times a second 
*The main "brain" of the engine is the Game.java class, the above game logic is in the "run" method. 

The engine uses the standard swing library for its window, and it uses the standard graphics library 
to draw. 
*All moving graphics stem from the "GameObject" class. 

The implementation of the engine has two enemies. 
*The first enemy simply changes its velocity when it reaches the edge of the screen. 
*The second enemy is a turret that strafes horizontally, and creates bullet objects. 
*Bullet objects calculate the cosine and sine of the right angle formed between the x and y coordinates 
between themselves and the player, and set their own velocity accordingly. 