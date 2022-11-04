import unittest

from src.DiGraph import DiGraph


class TestDiGraph(unittest.TestCase):

    def test_e_size(self):
        g = DiGraph()
        for i in range(4):
            g.add_node(i)
        g.add_edge(1, 2, 1)
        g.add_edge(2, 3, 4)
        g.add_edge(1, 0, 2)
        g.add_edge(1, 3, 3)
        self.assertEqual(g.e_size(), 4)

    def test_get_mc(self):
        g = DiGraph()
        g.add_node(0)
        g.add_node(2)
        g.add_node(1)
        g.add_edge(0, 1, 2)
        g.add_edge(1, 2, 4)
        g.add_edge(0, 2, 3)

        g.add_edge(1, 0, 4)
        g.remove_node(0)
        self.assertEqual(g.mc, 8)

    def test_add_edge(self):
        g = DiGraph()
        g.add_node(0)
        g.add_node(1)
        g.add_edge(1, 0, 2.0)
        self.assertEqual(list(g.all_in_edges_of_node(0).values()), ([(1, 2.0)]))
        return True

    def test_add_node(self):
        g = DiGraph(({}, {}, {}, 0, 0))
        node_id = 1
        g.remove_node(1)
        pos: tuple = (1, 2, 3)
        g.add_node(node_id, pos)
        self.assertEqual(g.get_all_v(), {1: (1, (1, 2, 3))})

    def test_remove_node(self):
        g = DiGraph(({}, {}, {}, 0, 0))
        for n in range(4):
            g.add_node(n)
        g.remove_node(3)
        self.assertEqual(g.v_size(), 3)
        self.assertEqual(list(g.get_all_v().keys()), [0, 1, 2])

        return True

    def test_remove_edge(self):
        g = DiGraph(({}, {}, {}, 0, 0))
        g.add_node(0)
        g.add_node(1)
        g.add_edge(1, 0, 2.0)
        g.add_edge(0, 1, 1.1)
        g.remove_edge(1, 0)
        self.assertEqual(g.e_size(), 1)
        self.assertEqual(list(g.all_out_edges_of_node(0).values()), [(1, 1.1)])

    def test_v_size(self):
        g = DiGraph(({}, {}, {}, 0, 0))
        for i in range(100):
            g.add_node(i)
        x = 0
        for x in range(100):
            if x % 2 == 0:
                g.remove_node(x)
        self.assertEqual(g.v_size(), 50)

    def test_get_all_v(self):
        g = DiGraph(({}, {}, {}, 0, 0))
        for i in range(3):
            g.add_node(i, (i, i * 2, i * 3))
        self.assertEqual(tuple(g.get_all_v().values()), ((0, (0, 0, 0)), (1, (1, 2, 3)), (2, (2, 4, 6))))

    def test_all_in_edges_of_node(self):
        g = DiGraph(({}, {}, {}, 0, 0))
        for i in range(4):
            g.add_node(i)
        g.add_edge(1, 2, 1)
        g.add_edge(2, 3, 4)
        g.add_edge(1, 0, 2)
        g.add_edge(1, 3, 3)
        self.assertEqual(list(g.all_out_edges_of_node(1).values()), [(2, 1), (0, 2), (3, 3)])

    def test_all_out_edges_of_node(self):
        g = DiGraph(({}, {}, {}, 0, 0))
        for i in range(4):
            g.add_node(i)
        g.add_edge(1, 2, 1)
        g.add_edge(2, 3, 4)
        g.add_edge(1, 0, 2)
        g.add_edge(1, 3, 3)
        self.assertEqual(list(g.all_in_edges_of_node(3).values()), [(2, 4), (1, 3)])


if __name__ == '__main__':
    unittest.main()
    TestDiGraph.test_add_node()
