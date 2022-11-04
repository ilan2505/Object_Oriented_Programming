import FindPocEdge
from Ex4.client_python import Agent
from src_Ex3.DiGraph import DiGraph


class pokemon:
    def __init__(self, value=0, Type=1,pos1=(), pos= (0, 0, 0), graph =DiGraph()):
        self.value = value
        self.pos = pos1
        self.type = Type
        if pos !=(0,0,0):
            find = FindPocEdge.PocEdge((pos[0], pos[1], pos[2]),Type)
            self.src, self.dest = find.findEdge(graph)
        else:
            self.src,self.dest=0,0
        self.target = False
        self.Agent=Agent
        self.killed=False

