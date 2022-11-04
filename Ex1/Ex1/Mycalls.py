import csv

import Ex1
import MyBuilding


class Calls:
    timeCall = 0
    src = 0
    dest = 0
    elev = -1
    time_get_src = 0
    time_get_dest = 0
    time_wait = time_get_dest - timeCall
    callsdet = []

    def __init__(self, calls, build,):
        self.callsdet = []
        self.b= MyBuilding.Elevator(build)
        self.stoplist = self.b.stopList
        with open(calls) as csv_file:
            reader = csv.reader(csv_file)
            for row in reader:
                x = row.copy()
                self.timeCall = float(x[1])
                self.src = int(x[2])
                self.dest = int(x[3])
                w, y, z = Ex1.check(self.callsdet, [float(x[1]), int(x[2]), int(x[3])], self.stoplist, build)
                self.elev = w
                self.time_get_src = y
                self.time_get_dest = z
                self.time_wait = self.time_get_dest - self.timeCall
                self.callsdet.append(
                    [self.timeCall, self.src, self.dest, self.elev, self.time_get_src, self.time_get_dest,
                     self.time_wait])
                # self.callsdet=nm.array(self.callsdet)