from Ex4.client_python.src_Ex3.DiGraph import DiGraph

from Ex4.client_python.src_Ex3.OrderedSet import OrderedSet

MAX = float('inf')


def value(pos: str):
    t = list(pos)
    r = int(t.index(','))
    x = pos[0:r]
    le = len(t)
    t = list(pos[r + 1:le])
    pos = pos[r + 1:le]
    r = int(t.index(','))
    y = pos[0:r]
    le = len(t)
    pos = pos[r + 1:le]
    z = pos[0:le]
    return float(x), float(y), float(z)


def Dijkstra_shorted_path(graph: DiGraph, src, dest):
    if src == dest:
        return 0, [src]
    Q = list(graph.Nodelist)
    dist = OrderedSet()
    dist1 = OrderedSet()
    prev = {}

    for i in range(len(Q)):
        dist.add(Q[i], MAX)
        dist1.add(Q[i], MAX)
        prev[i] = None
    dist1.dec(Q[src], 0)
    dist.dec(Q[src], 0)
    while Q.__contains__(dest) and len(Q) > 1:
        u = list(dist.dic)[0]
        Q.remove(u)
        x = graph.all_out_edges_of_node(u)
        sort = tuple(x.values())
        for j in range(len(sort)):

            alt = dist1.dic[u] + x[sort[j][0]][1]
            if alt < dist1.dic[sort[j][0]]:
                dist1.dec(sort[j][0], alt)
                dist.dec(sort[j][0], alt)
                prev[sort[j][0]] = u
        del dist.dic[u]
    t = [dest]
    if dist1.dic[dest] == MAX:
        return -1, None
    prev1 = prev[dest]
    while prev1 != src:
        t.append(prev1)
        prev1 = prev[prev1]
    t.append(src)
    t.reverse()
    return dist1.dic[dest], t


def Dijkstra_center(graph: DiGraph, src, dist2):
    Q = list(graph.Nodelist)
    dist = OrderedSet()
    dist1 = OrderedSet()
    prev = {}

    for i in range(len(Q)):
        dist.add(Q[i], MAX)
        dist1.add(Q[i], MAX)
        prev[i] = None
    dist.dec(src, 0)
    dist1.dec(src, 0)
    while len(Q) != 0 and len(Q) > 1:
        u = list(dist.dic)[0]
        xx = dist.dic[u]
        if xx > dist2:
            return float('inf')
        Q.remove(u)
        x = graph.all_out_edges_of_node(u)
        if x is None:
            return MAX
        sort = tuple(x.values())
        for j in range(len(sort)):
            alt = dist1.dic[u] + sort[j][1]
            if alt < dist1.dic[sort[j][0]]:
                dist1.dec(sort[j][0], alt)
                dist.dec(sort[j][0], alt)
                prev[sort[j][0]] = u
        del dist.dic[u]
    return dist1.dic[Q[0]]
