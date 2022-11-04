import unittest

from src.GraphAlgo import GraphAlgo


class getGraph:
    def __init__(self):
        self.graph = GraphAlgo(({}, {}, {}, 0, 0))
        self.graph.load_from_json('../data/1000Nodes.json')

    def get_Algo(self):
        return self


class MyTestCase(unittest.TestCase):

    def test_center(self):
        self.Algo = getGraph().get_Algo()
        self.Algo.graph.centerPoint()

    def test_TSP(self):
        self.Algo = getGraph().get_Algo()
        self.Algo.graph.TSP([1, 2, 300])

    def test_shorted_path(self):
        self.Algo = getGraph().get_Algo()
        self.Algo.graph.shortest_path(1, 8)


if __name__ == '__main__':
    unittest.main()
