import unittest

from Ex4.client_python import Agent
from Ex4.client_python.pokemon import pokemon
from Ex4.client_python.src_Ex3.GraphAlgo import GraphAlgo


class MyTestCase(unittest.TestCase):
    def test_Agent(self):
        a = Agent.agent(0, 0, 1, 1, (100, 200, 0))
        self.assertEqual(a.speed, 1)
        a.src = 2
        self.assertEqual(a.src, 2)
        self.assertEqual(a.pos[0], 100)

    def test_Pokemon(self):
        Algo = GraphAlgo()
        Algo.load_from_json("t1.json")
        p = pokemon()
        p.value = 5
        p.src = 10
        p.dest = 11
        self.assertEqual(p.value, 5)
        self.assertEqual(p.target, False)
        self.assertEqual(p.src, 10)
        self.assertEqual(p.dest, 11)

    def test_graph(self):
        Algo = GraphAlgo()
        Algo.load_from_json("t1.json")
        self.assertEqual(Algo.shortest_path(0,1)[1],[0,1])


if __name__ == '__main__':
    unittest.main()
