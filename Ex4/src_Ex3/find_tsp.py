from typing import List
from Ex4.client_python.src_Ex3.Dijkstra_Algorithm import Dijkstra_shorted_path

from Ex4.client_python.src_Ex3.DiGraph import DiGraph

MAX = 21943245


def find_closest(src, lst, graph, dist):
    checksum = 1
    close = -1
    distRet = MAX
    list2 = []
    for i in range(len(lst)):
        if src != lst[i]:
            dist1, list1 = Dijkstra_shorted_path(graph, src, lst[i])
            if dist1 < distRet:
                distRet = dist1
                close = lst[i]
                if list1 is not None:
                    list2 = list1[1:]
    dist = dist + distRet
    if distRet == MAX:
        checksum = 0
    return checksum, close, list2, dist


def find_tsp(graph: DiGraph, node_lst: List[int]):
    dist = 0
    lst = [node_lst[0]]
    src = node_lst[0]
    cur = src
    checksum = 1
    node_lst.remove(cur)
    while len(node_lst) > 0 and checksum == 1:
        checksum, cur, list1, dist = find_closest(cur, node_lst, graph, dist)
        node_lst.remove(cur)
        lst += list1
    return lst, dist
