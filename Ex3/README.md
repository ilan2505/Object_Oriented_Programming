# OOP_2021_Ex3
Fourth Assignment in OOP course in Python @Ariel_University_2021

# Authors :
* Souffir Ilan Meir : 342615648
* Cohen Ben : 207029786  

# Topic of the Assignment number 4 :
This assignment 4 is dedicated to the design and implementation of data structures and algorithms on graphs (directed and weighted) but this time in python.
The shortest route (path) is realized using Dykstra algorithme (DFS).

# Analysis of the performance of our algorithms on size graphs :


# UML of the project :
<p align="center">
  <img align="center" width=60% src = "https://user-images.githubusercontent.com/55143087/147593396-7e749d9a-2d9c-4f5b-a976-3cdc17fb3e3c.png"/>
</p>

# Ex3 Contains 2 Interfaces :
1) GraphAlgoInterface
2) GraphInterface

# Ex3 Contains 2 implementations :
1) GraphAlgo
2) DiGraph

# 1) GraphAlgo
 This class is an implementation of GraphAlgoInterface.
 
 We are going to explains each methods of GraphAlgo :
 * get_graph(self) : return the directed graph on which the algorithm works on.
 * load_from_json(self, file_name: str) -> bool : Loads a graph from a json file.
 * save_to_json(self, file_name: str) -> bool : Saves the graph in JSON format to a file.
 * shortest_path(self, id1: int, id2: int) -> (float, list) :  Returns the shortest path from node id1 to node id2 using Dijkstra's Algorithm
   * We are using Dijkstra_shorted_path(graph: DiGraph, src, dest) in the class Dijkstra_Algorithm.py
 * TSP(self, node_lst: List[int]) -> (List[int], float) : Finds the shortest path that visits all the nodes in the list.
   * We are using find_tsp(self.graph, node_lst) in the class find_tsp.py
 * centerPoint(self) -> (int, float) : Finds the node that has the shortest distance to it's farthest node.
 * plot_graph(self) -> None : Plots the graph. If the nodes have a position, the nodes will be placed there. Otherwise, they will be placed in a random but elegant manner.

# 2) DiGraph
 This class is an implementation of GraphInterface.
 
 We are going to explains each methods of DiGraph :
 * e_size(self) -> int : Returns the number of edges in this graph.
 * get_mc(self) -> int : Returns the current version of this graph, on every change in the graph state - the MC should be increased.
 * add_edge(self, id1: int, id2: int, weight: float) -> bool : Adds an edge to the graph.
 * add_node(self, node_id: int, pos: tuple = None) -> bool : Adds a node to the graph.
 * remove_node(self, node_id: int) -> bool : Removes a node from the graph.
 * remove_edge(self, node_id1: int, node_id2: int) -> bool : Removes an edge from the graph.
 * v_size(self) -> int : Returns the number of vertices in this graph.
 * get_all_v(self) -> dict : return a dictionary of all the nodes in the Graph, each node is represented using a pair(node_id, node_data).
 * all_in_edges_of_node(self, id1: int) -> dict : return a dictionary of all the nodes connected to (into) node_id, each node is represented using a   pair(other_node_id, weight).
 * all_out_edges_of_node(self, id1: int) -> dict : return a dictionary of all the nodes connected from node_id , each node is represented using a   pair(other_node_id, weight).

# Ex3 Contains also these classes :
  * main : where we are runing the codes with check0, check1, check2, check3.
  * Dijkstra_Algorithm : the Dijkstra Algorithm where we have :
    * value(pos: str)
    * Dijkstra_shorted_path(graph: DiGraph, src, dest)
    * Dijkstra_center(graph: DiGraph, src, dist2) 
  * OrderedSet : it's to sort the array in time of O(n) instead of O(n log n).
  * find_tsp : Finds the shortest path that visits all the nodes in the list. we have in this class :
    * find_closest(src, lst, graph, dist)
    * find_tsp(graph: DiGraph, node_lst: List[int])
  
# Tests that we are running :
  * TestDiGraph.py
    * test_e_size()
    * test_get_mc()
    * test_add_edge()
    * test_add_node()
    * test_remove_node()
    * test_remove_edge()
    * test_v_size()
    * test_get_all_v()
    * test_all_in_edges_of_node()
    * test_all_out_edges_of_node()
  * TestGraphAlgo.py
    * test_center()
    * test_TSP()
    * test_shorted_path():
  * comparre_Test.py
    * test_center()
    * test_TSP()
    * test_shorted_path()

# Graphs that we have created :
A0 :
<p align="center">
  <img align="center" width=60% src = "https://user-images.githubusercontent.com/55143087/147609749-40fc3ccc-e5a4-48ba-bf3a-e26b828c3388.png"/>
</p>
A1 :
<p align="center">
  <img align="center" width=60% src = "https://user-images.githubusercontent.com/55143087/147609792-79dd7c7a-0eae-4b70-8ddc-22e662b4ed9c.png"/>
</p>
A2 :
<p align="center">
  <img align="center" width=60% src = "https://user-images.githubusercontent.com/55143087/147609818-2c2db755-f69e-46ad-b2bb-3744ba569f60.png"/>
</p>
A3 :
<p align="center">
  <img align="center" width=60% src = "https://user-images.githubusercontent.com/55143087/147609841-8801c741-f982-4515-af53-469b7d7a4488.png"/>
</p>
A4 :
<p align="center">
  <img align="center" width=60% src = "https://user-images.githubusercontent.com/55143087/147609878-0714f51c-b95a-40cc-80b8-d5c220e358d4.png"/>
</p>
A5 :
<p align="center">
  <img align="center" width=60% src = "https://user-images.githubusercontent.com/55143087/147609904-6942e887-99c0-484f-bc6c-5e2a60bbbafa.png"/>
</p>
