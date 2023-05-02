# Maze-Solver
A ASCII Maze solver 

This is a ASCII Maze solve that will use recurions to traverse through a maze that has been decoded from a TXT File.
With the letters indiating what each spaace means. 

X = A Wall 
S = Start Point 
E = Exit of Maze
Blank Space = Possible Path 
* = The solved path

After finding a path to the exit it will output a soultion path indicating the dirrections in a N/S/E/W format 

When creating the map indicating the width and length of the maze in the first like as see in maze1.txt. 

Y-Length+TAB+X-length  

12  11

---------- Sample Output -------------
Enter maze filename: maze1.txt
Start at 1,1.
File transcription complete!

Initial State:
XXXXXXXXXXX
XS        X
XXXXX XXXXX
X   X X   X
XXX     XXX
X XXX XXX X
X   X X   X
X   X X   X
X X X X X X
X X     X X
X  XX XXE X
XXXXXXXXXXX

Final Maze:
XXXXXXXXXXX
X#****    X
XXXXX*XXXXX
X   X*X   X
XXX  *  XXX
X XXX*XXX X
X   X*X** X
X   X*X***X
X X X*X*X*X
X X  ***X*X
X  XX XXO*X
XXXXXXXXXXX
Solution Path: E, E, E, E, S, S, S, S, S, S, S, S, E, E, N, N, E, N, W, 
