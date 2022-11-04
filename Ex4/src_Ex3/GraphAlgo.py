import json
from random import random
from typing import List

from matplotlib import pyplot as plt

from Ex4.client_python.src_Ex3.DiGraph import DiGraph
from Ex4.client_python.src_Ex3.Dijkstra_Algorithm import value, Dijkstra_shorted_path, Dijkstra_center
from Ex4.client_python.src_Ex3.find_tsp import find_tsp

MAX = float('inf')


class GraphAlgo:

    def __init__(self, g: DiGraph = ({}, {}, {}, 0, 0)):
        if g == ({}, {}, {}, 0, 0) or g == ():
            self.graph = DiGraph()
        else:
            n = (g.Nodelist, g.EdgeList, g.EdgeListIn, g.mc, g.esize)
            self.graph = DiGraph(n)

    def get_graph(self):  # ->GraphInterface:
        return self.graph

    def load_from_json(self, file_name: str) -> bool:
        with open(file_name, 'r') as file:
            read = json.load(file)  # , object_hook=lambda json_dict: SimpleNamespace(**json_dict))
        x = tuple(read["Edges"])
        y = tuple(read["Nodes"])
        for i in range(len(y)):
            id = int(y[i]['id'])
            try:
                pos = (y[i]['pos'])

                pos = tuple(value(pos))
            except:
                pos = (random() * 1000, random() * 1000, 0.0)
            self.graph.add_node(id, pos)
        for i in range(len(x)):
            src = int(x[i]['src'])
            w = float(x[i]['w'])
            dest = int(x[i]['dest'])
            self.graph.add_edge(src, dest, w)
        return True

    def save_to_json(self, file_name: str) -> bool:
        try:
            obj = open(file_name, 'w')
            x = self.graph.__repr__()
            obj.write(x)
            obj.close()
        except IOError:
            print("Error")
            return False
        return True

    def shortest_path(self, id1: int, id2: int) -> (float, list):
        return Dijkstra_shorted_path(self.graph, id1, id2)

    def TSP(self, node_lst: List[int]) -> (List[int], float):
        return find_tsp(self.graph, node_lst)

    def centerPoint(self) -> (int, float):

        center = 0
        dist = MAX
        nodelist = tuple(self.graph.Nodelist)
        for i in range(len(nodelist)):
            dist1 = Dijkstra_center(self.graph, i, dist)
            if dist1 < dist:
                dist = dist1
                center = i

        return center, dist

    def plot_graph(self) -> None:
        Nodes = self.graph.get_all_v()
        fig, ax = plt.subplots(1, 1)

        for i in range(len(Nodes)):
            try:
                Edges = tuple(self.graph.all_out_edges_of_node(i).keys())
            except:
                Edges = ()
            ax.scatter(Nodes[i][1][0], Nodes[i][1][1], 15, color='b')
            for j in range(len(Edges)):
                dest = Nodes.get(Edges[j])
                plt.plot([Nodes[i][1][0], dest[1][0]], [Nodes[i][1][1], dest[1][1]], color='yellow')
                x1 = Nodes[i][1][0] * 0.1 + dest[1][0] * 0.9
                y1 = Nodes[i][1][1] * 0.1 + dest[1][1] * 0.9
                plt.annotate(text='', xy=(x1, y1), xytext=(dest[1][0], dest[1][1]), arrowprops=dict(arrowstyle='<-'))

        plt.show()
