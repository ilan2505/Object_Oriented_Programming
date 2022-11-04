# OOP_2021_Ex1
Second Assignment in OOP course in Python @Ariel_University_2021

# Authors :
* Souffir Ilan Meir : 342615648
* Cohen Ben : 207029786  

# Topic of the Matala Num 1 :
In this assigment number 2 : we want to solve the problem of the call attribution to the elevators with the language Python.

# Ex1 Contain 3 classes :
* Mycalls.py :
  * timeCall
  * src -> source floor
  * dest -> destination floor
  * elev -> elevator num
  * time_get_src -> time to get to source
  * time_get_dest -> time to get to destination
  * time_wait = time_get_dest - timeCall   -> time you are waiting for an elevator
* MyBuilding.py :
  * NOE -> number of elevator
  * floorTime
  * speed 
  * max_floor 
  * min_floor 
  * TOTAL_TIME_WAIT -> time total you are waiting for an elevator 
* Ex1.py (this is our algo) :
  * def Betstop -> This func count how many stop will be with allocate to elevator number 'i'
  * def checkIfCost -> This func check if will be costing waiting time if we allocate the call to elevator number 'i'
  * def Checklaststop -> This func checks where is the last stop and how much is good to allocate to this elevator
  * def addHeaders -> This func is add header to svc file
  * def removeHeaders -> This func is remove header to svc file
  * def allocatecalls -> This func is allocate the call to the svc
  * def check -> This func check the waiting time and elevator
  * def allocate -> it's for our main : we are taking the Building, calls and the name of my output
  * if __name__ == '__main__':  our main

# UML :
<p align="center">
  <img align="center" width=100% src = "https://user-images.githubusercontent.com/55143087/142608757-f5ebc791-2c3a-41b4-92ee-df11e5b28db8.png"/>
</p>
  
# Algorithm offline :
* 1.1 We pass on each call i going through all the elevators in the building to assign the best, then we check:
* 1.2 We are calculating where is the elevator
* 1.3 If :
  * 1.3.1 elevator is going UP or Down 
  * 1.3.2 AND the source floor is BELOW or ABOVE the position of the elevator 
  * 1.3.3 AND if it's the shortest time for a call -> take the elevator.
* 1.4 If there is any call for the elevator :
  * 1.4.1 check if the call time of the elevator is the shortest
  * 1.4.2 give the elevator
* 1.5 At the End update all calls in the program's out file.

# The Elevator-Algorithm as implemented in the jar is defined as follows:
* 1.1 Each elevator has a “flag” of UP/DOWN, and its start position is floor 0.
* 1.2 At the beginning, once the first call arrives, the flag is defined to the value UP/DOWN according to the src value (is below 0 → DOWN, above 0 → UP).
* 1.3 In case the elevator’s flag==True (UP), it will “go to” the closest floor above its current position. If none - it will change the flag = DOWN.
* 1.4 In case the elevator’s flag==False (DOWN), it will “go to” the closest floor below its current position. If none - it will change the flag = UP.
* 1.5 If the list of calls is Empty it will remain in LEVEL - state.

The expected time the elevator will travel from floor_a to floor_b is:
CloseTime + StartTime + df/speed + StopTime + OpenTime (df == |floor_a-floor_b).
