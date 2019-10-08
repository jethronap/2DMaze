# 2DMaze

>An application that finds the shortest path given a maze, a start point and a goal. 
>It uses two different algorithmic flavours Dijkstra and AStar Search.
>
>You can use one of the mazes provided in [test_data](https://github.com/jethronap/2DMaze/tree/master/src/test_data) folder. Or try making one yourself.
>
>#####**Note**: If invalid filename is given the application exits.
>
>Acceptable maze format:
>
>                       n_rows = your number of rows
>                       n_cols = your number of cols
>                       0, O
>                       1, X
>                       2, X
>                       3, O
>                       ...
>
>On the left column is the zero-based numbering of cells and should start from the bottom left corner of your maze and continue upwards.
>
>Whereas on the right the blocked flag. Use `O` for open `X` for closed.
>
>See example below to understand how cell numbering is supposed to be.
>
| 6 | 7 | 8 |
|---|:-:|:-:|
| 3 | 4 | 5 |
| 0 | 1 | 2 |


### References:

**Dijkstra**:
* [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)
* [Dijkstra's Shortest Path Algorithm](https://brilliant.org/wiki/dijkstras-short-path-finder/)
* [Finding The Shortest Path, With A Little Help From Dijkstra](https://medium.com/basecs/finding-the-shortest-path-with-a-little-help-from-dijkstra-613149fbdc8e)
* [Calculate shortest paths in Java by implementing Dijkstra’s Algorithm](https://medium.com/@ssaurel/calculate-shortest-paths-in-java-by-implementing-dijkstras-algorithm-5c1db06b6541)

**AStar**:
* [A* search algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm)
* [A* Search](https://brilliant.org/wiki/a-star-search/)
* [Introduction to A*](http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html)
* [Easy A* (star) Pathfinding](https://medium.com/@nicholas.w.swift/easy-a-star-pathfinding-7e6689c7f7b2)

#### Books on Algorithms:
* [Introduction to Algorithms](https://en.wikipedia.org/wiki/Introduction_to_Algorithms)
* [Algorithms Unlocked](https://en.wikipedia.org/wiki/Algorithms_Unlocked)
* [The Algorithms design Manual](http://algorist.com)

This is a Maven project and can be run as is. Just clean and build and give it a go.