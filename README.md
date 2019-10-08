# 2DMaze

>An application that finds the shortest path given a maze, a start point and a goal. 
>It uses two different algorithmic flavours Dijkstra and AStar Search.
>
>You can use one of the mazes provided in [test_data](https://github.com/jethronap/2DMaze/tree/master/src/test_data) folder. Or try making one yourself.
>Acceptable maze formats consist of two columns where on the first row `n_rows = ` is provided; on the second provide `n_cols = `. 
>On the following rows provide cell id and blocked flag. Please use `O` for open `X` for closed. 
>The numbering of cells should start from the bottom left corner of your maze and continue upwards.
>
>See example below to understand how cell numbering is supposed to be.
>
>| 6 | 7 | 8 |
|---|:-:|:-:|
| 3 | 4 | 5 |
| 0 | 1 | 2 |


###References:
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

####Books on Algorithms:
* [Introduction to Algorithms](http://web.karabuk.edu.tr/hakankutucu/CME222/MIT[1].Press.Introduction.to.Algorithms.2nd.Edition.eBook-TLFeBOOK.pdf)
* [Algorithms Unlocked](http://dahlan.unimal.ac.id/files/ebooks/2013%20Algorithms_Unlocked.pdf)
* [The Algorithms design Manual](http://mimoza.marmara.edu.tr/~msakalli/cse706_12/SkienaTheAlgorithmDesignManual.pdf)

This is a Maven project and can be run as is. Just clean and build and give it a go.