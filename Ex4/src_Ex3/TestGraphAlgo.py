import unittest
from GraphAlgo import GraphAlgo


class getGraph:
    def __init__(self):
        self.graph = GraphAlgo(({}, {}, {}, 0, 0))
        self.graph.load_from_json('../data/A5.json')

    def get_Algo(self):
        return self


class MyTestCase(unittest.TestCase, GraphAlgo):

    def test_center(self):
        self.Algo = getGraph().get_Algo()

        self.assertEqual(self.Algo.graph.centerPoint(), (8, 9.925289024973141))

    def test_TSP(self):
        self.Algo = getGraph().get_Algo()

        self.assertEqual(self.Algo.graph.TSP([1, 2, 3]), ([1, 2, 3], 2.8647559158521916))

    def test_shorted_path(self):
        self.Algo = getGraph().get_Algo()

        self.assertEqual(self.Algo.graph.shortest_path(1, 8), (6.204771159825874, [1, 2, 6, 7, 8]))


if __name__ == '__main__':
    MyTestCase()
    pass
