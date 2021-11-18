import  json

class Elevator:
    elevDetails = []
    NOE = 0  # number of elevator
    floorTime = 0
    speed = 0
    max_floor = 0
    min_floor = 0
    stopList = []
    TOTAL_TIME_WAIT = 0

    def __init__(self, Build):
        self.stopList = []

        json_file = open(Build, encoding="utf-8")
        thisBuild = json.load(json_file)
        json_file.close
        self.NOE = len(thisBuild['_elevators'])  # number of elevator
        self.max_floor = thisBuild['_maxFloor']
        self.min_floor = thisBuild['_minFloor']
        self.TOTAL_TIME_WAIT = 0
        for i in thisBuild['_elevators']:
            self.elevDetails.append([float(i['_speed']),
                                     float(i['_startTime']) + float(i['_stopTime']) + float(i['_closeTime']) + float(
                                         i['_openTime'])])  # speed, floorTime
            self.stopList.append([])
