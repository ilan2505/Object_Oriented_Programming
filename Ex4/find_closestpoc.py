from src_Ex3.GraphAlgo import GraphAlgo


def findClosest(pokList: dict, Algo: GraphAlgo, agent):

    dist = float('inf')
    list1 = []
    j = -1
    count = 0

    for i in range(len(pokList)):
        if not pokList[i].target:
            tempDist, tempList = Algo.shortest_path(agent, pokList[i].src)
            if tempDist < dist:
                j = i
                dist = tempDist
                list1 = tempList
                list1.append(pokList[i].dest)
    if j != -1:
        pokList[j].target = True
        return dist, list1, pokList[j]
    else:
        return 0,[],pokList[0]

    print("#############")


