from src_Ex3.DiGraph import DiGraph

EPS = 0.00001


class PocEdge:
    def __init__(self, p: (0.0, 0.0, 0), type1: int):
        self.x = (float)(p[0])
        self.y = (float)(p[1])
        self.Type = type1

    def findEdge(self, Graph: DiGraph):
        EdgeList = Graph.EdgeList
        NodeList = Graph.Nodelist
        for i in range(len(EdgeList)):
            N1 = NodeList[EdgeList[i][0]]
            N2 = NodeList[EdgeList[i][1]]
            x1 = N1[1][0]
            y1 = N1[1][1]
            x2 = N2[1][0]
            y2 = N2[1][1]
            m = (float)(y1 - y2) / (x1 - x2)
            # print(self.y - y1 - m * (self.x - x1))
            if abs(self.y - y1 - m * (self.x - x1)) < EPS:
                if self.Type == 1:
                    return min(N1[0], N2[0]), max(N1[0], N2[0])
                else:
                    return max(N1[0], N2[0]), min(N1[0], N2[0])


"""""""""
            print(EdgeList[i])
            dests = tuple(EdgeList[i])
            for j in range(len(dests)):
                N1 = NodeList[i]
                N2 = NodeList[dests[j]]
                x1 = N1[1][0]
                y1 = N1[1][1]
                x2 = N2[1][0]
                y2 = N2[1][1]
                m = (float)(y1 - y2) / (x1 - x2)
                # print(self.y - y1 - m * (self.x - x1))
                if abs(self.y - y1 - m * (self.x - x1)) < EPS:
                    if self.Type == 1:
                        return min(N1[0], N2[0]), max(N1[0], N2[0])
                    else:
                        return max(N1[0], N2[0]), min(N1[0], N2[0])

    """""""""
