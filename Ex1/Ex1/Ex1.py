import pandas as pd
import json
import csv

import MyBuilding
import Mycalls


# this func count how many stop will be with allocate to elevator number 'i'
def Betstop(elev, stoplist, mycall, lastStop, length):
    stopBef = 0
    if len(stoplist[elev]) == length:
        return 0

    if stoplist[elev][length][2] == True and mycall[1] > lastStop:
        while length < len(stoplist[elev]) and stoplist[elev][length][2] == True and stoplist[elev][length][0] < mycall[1]:
            stopBef = stopBef + 1
            length = length + 1
    elif stoplist[elev][length][2] == True and mycall[1] < lastStop:
        while stoplist[elev][length][2] == True and length < len(stoplist[elev]):
            length = length + 1
            stopBef = stopBef + 1
        while length < len(stoplist[elev]) and stoplist[elev][length][2] == False and stoplist[elev][length][0] > \
                mycall[1]:
            stopBef = stopBef + 1
            length = length + 1
    elif stoplist[elev][length][2] == False and mycall[1] > lastStop:
        while length < len(stoplist[elev]) and stoplist[elev][length][2] == False:
            length = length + 1
            stopBef = stopBef + 1
        while length < len(stoplist[elev]) and stoplist[elev][length][2] == True and stoplist[elev][length][0] < mycall[
            1]:
            stopBef = stopBef + 1
            length = length + 1
    else:
        while length < len(stoplist[elev]) and stoplist[elev][length][2] == False and stoplist[elev][length][0] > \
                mycall[1]:
            length = length + 1
            stopBef = stopBef + 1

    return stopBef, length


# this func check if will be costing waiting time if we allocate the call to elevator number 'i'
def checkIfCost(laststop, mycall):
    print(laststop)
    if (laststop[1] < mycall and laststop[2] == True) or (laststop[1] > mycall and laststop[2] == False):
        return 0
    elif (laststop[1] > mycall and laststop[2] == True) or (laststop[1] > mycall and laststop[2] == True):
        return abs(laststop[1] - mycall)
    else:
        return 0


# this func checks where is the last stop and how much is good to allocate to this elevator
def Checklaststop(b1, elev, stoplist, mycall):
    costing = 0
    timetosrc = 100000
    t = 0
    floor = float(b1[elev][1])
    spe = float(b1[elev][0])
    length = 0
    if stoplist[elev][len(stoplist[elev]) - 1][0] <= mycall[0]:
        size1 = abs(stoplist[elev][len(stoplist[elev]) - 1][1] - mycall[1])
        TTD = size1 / spe
        if TTD < timetosrc:
            timetosrc = TTD
            elev = elev
            t = len(stoplist[elev])
    else:
        for i in range(len(stoplist[elev])):
            if stoplist[elev][i][0] > mycall[0]:
                lastStop = stoplist[elev][i - 1][0]
                costing = 4#checkIfCost(stoplist[elev][i-1], mycall[1])
                size1 = abs(stoplist[elev][i - 1][1] - mycall[1])
                stoptosrc, lastStop = Betstop(elev, stoplist, [mycall[0], mycall[1]], lastStop, i)
                TTD = size1 / b1[elev][1] + stoptosrc * floor
                if TTD < timetosrc:
                    timetosrc = TTD
                t = i
                break
    return len(stoplist[elev])-t, costing, timetosrc


# This func is add header to svc file
def addHeaders(call, Headers):
    with open(call) as file:
        reader = csv.DictReader(file, fieldnames=Headers)
        with open('help.csv', 'w', newline='') as csvF:
            writer = csv.DictWriter(csvF, fieldnames=reader.fieldnames)
            writer.writeheader()
            next(reader)
            writer.writerows(reader)
    return 'help.csv'


# This func is remove header to svc file

def removeHeaders(calls, output):
    with open(calls) as f1:
        with open(output, 'w') as f2:
            next(f1)
            for line in f1:
                f2.write(line)


# This func is allocate the call to the svc
def allocatecalls(elev, helpsvc, callsdit):
    df = pd.read_csv(helpsvc)
    NOC = (int(df.size / len(df.columns)))
    for i in range(len(callsdit)):
        t = i % elev
        df.iloc[i:i + 1, 5:6] =callsdit[i][3]
    df.to_csv(helpsvc, index=False)


# This func check the waiting time and elevator
def check(callsdet, mycall, stoplist, build):
    timeSrc = 0
    timeDest = 0
    mytime = mycall[0]
    b1 = MyBuilding.Elevator(build)
    elev = 0
    if len(callsdet) < b1.NOE:
        elev = len(callsdet)
        timeSrc = mytime + (mycall[1] - b1.min_floor) / b1.elevDetails[elev][0]
        timeDest = timeSrc + abs(mycall[1] - mycall[2]) / b1.elevDetails[elev][0] + b1.elevDetails[elev][1]
        stoplist[elev].append([ timeSrc,mytime,mycall[1], False])
        stoplist[elev].append([ timeDest,mytime, mycall[2], False])
    else:
        lowestCosting = 100000
        for i in range(b1.NOE):
            t, cost, TTsrc = Checklaststop(b1.elevDetails, i, stoplist, [mycall[0], mycall[1]])
            timeSrc = mytime + TTsrc
            t, cost, TTD = Checklaststop(b1.elevDetails, i, stoplist, [timeSrc, mycall[2]])
            timeDest = mytime + TTD
            costing =cost * t*b1.elevDetails[elev][1]+timeDest- mytime
            if costing < lowestCosting:
                lowestCosting = costing
                elev = i
        stoplist[elev].append([ timeSrc, mycall[1], False])
        stoplist[elev].append([ timeDest, mycall[2], False])

        for i in range(t):
            stoplist[elev][i+len(stoplist[elev]) - t][0]=stoplist[elev][i+len(stoplist[elev]) - t][0]+cost * b1.elevDetails[elev][1]

        for j in range(len(stoplist[elev]) - 1):
            if stoplist[elev][j][0] < stoplist[elev][j + 1][0]:
                stoplist[elev][j][2] = True
        stoplist[elev].sort()

    return (len(callsdet)-1)%b1.NOE, timeSrc, timeDest


def allocate(Building, calls, output):
    c1 = Mycalls.Calls(calls, Building)
    json_file = open(Building, encoding="utf-8")
    thisBuild = json.load(json_file)
    json_file.close()
    elev = len(thisBuild['_elevators'])
    Headers = ['Call', 'time', 'src', 'dest', 'flag', 'elev', 'time_src', 'time_dest']
    helpsvc = addHeaders(calls, Headers)  # add header to svc
    allocatecalls(elev, helpsvc, c1.callsdet)  # allocate calls to svc
    removeHeaders(helpsvc, output)  # remove headers from svc


#if __name__ == '__main__':
    #allocate('B1.json', 'calls_a.csv', 'B1100.csv')
