import this
from collections import OrderedDict, abc
from random import random


class DiGraph:
    def __init__(self, graph: list = ({}, [], {}, 0, 0)):
        self.EdgeList = graph[1]
        self.mc = graph[3]
        self.Nodelist = graph[0]
        self.EdgeList1={}
        self.EdgeListIn = graph[2]
        self.esize = graph[4]

    def e_size(self) -> int:

        return self.esize

    def get_mc(self) -> int:
        return self.mc

    def add_edge(self, id1: int, id2: int, weight: float) -> bool:
        self.EdgeList.append((id1,id2,weight))
        if self.EdgeList1.__contains__(id1):
            self.EdgeList1[id1][id2] = (id2, weight)
        else:
            self.EdgeList1[id1] = {}
            self.EdgeList1[id1][id2] = (id2, weight)
        if self.EdgeListIn.__contains__(id2):
            self.EdgeListIn[id2][id1] = (id1, weight)
        else:
            self.EdgeListIn[id2] = {}
            self.EdgeListIn[id2][id1] = (id1, weight)
        self.mc = self.mc + 1
        self.esize = self.esize + 1
        return True

    def add_node(self, node_id: int, pos: tuple = None) -> bool:
        if pos == None:
            pos = (random() * 1000, random() * 1000, 0.0)
        self.Nodelist[node_id] = (node_id, pos)
        self.mc = self.mc + 1
        return True

    def remove_node(self, node_id: int) -> bool:
        if self.Nodelist.__contains__(node_id):
            del self.Nodelist[node_id]
            if self.EdgeListIn.__contains__(node_id):
                t = tuple(dict(self.EdgeListIn.get(node_id)).keys())
                del self.EdgeListIn[node_id]
                for i in range(len(t)):
                    del self.EdgeListIn[t[i]][node_id]
            if self.EdgeList.__contains__(node_id):
                del self.EdgeList[node_id]


        else:
            print("There aren't this node")
            return False
        self.mc = self.mc + 1
        return True

    def __repr__(self):
        return "Edges=" + str((self.EdgeList)) + " Nodes=" + str((dict(self.Nodelist)))

    def __str__(self):
        return "Edges=" + str(self.EdgeList) + " Nodes=" + str(self.Nodelist)

    def remove_edge(self, node_id1: int, node_id2: int) -> bool:
        try:
            del self.EdgeList[node_id1][node_id2]
            del self.EdgeListIn[node_id2][node_id1]
        except:
            print("There aren't this edge")
            return False
        self.esize = self.esize - 1
        return True

    def v_size(self) -> int:
        return len(self.Nodelist)

    def get_all_v(self) -> dict:
        return dict(self.Nodelist).values()

    def all_in_edges_of_node(self, id1: int) -> dict:
        return dict(self.EdgeListIn.get(id1))

    def all_out_edges_of_node(self, id1: int) -> dict:
        return self.EdgeList1.get(id1)
