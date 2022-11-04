package ex0.algo;

import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;
import java.util.TreeSet;

/**
 * A bit better than ShabatElev2Algo: randomly spread the elevators in the init stage, allocate the "closest" elevator..
 */
public class Smart_Elevator implements ElevatorAlgo {
    public static final int UP=1, DOWN=-1,LEVEL=0;
    private int _direction;
    private Building _building;
    private boolean[] _firstTime;
    int[] NS;
    private TreeSet<Integer>[] UDF;// stop list if elevator go up
    private TreeSet<Integer>[] destUpB;//back up of up stops
    private TreeSet<Integer>[] DDF;//stop list if elevator go down
    private TreeSet<Integer>[] destDownB;// back up of down stop

    public Smart_Elevator(Building b) {
        NS=new int[b.numberOfElevetors()];
        UDF=new TreeSet[b.numberOfElevetors()];
        destUpB=new TreeSet[b.numberOfElevetors()];
        DDF=new TreeSet[b.numberOfElevetors()];
        destDownB=new TreeSet[b.numberOfElevetors()];
        _building = b;
        _firstTime=new boolean[_building.numberOfElevetors()];
        _direction = UP;
        _firstTime = new boolean[_building.numberOfElevetors()];
        for(int i=0;i<_firstTime.length;i++) {// restart all trees and array
            _firstTime[i] = true;
            DDF[i]=new TreeSet<>();
            UDF[i]=new TreeSet<>();
            destDownB[i]=new TreeSet<>();
            destUpB[i]=new TreeSet<>();
        }


    }

    @Override
    public Building getBuilding() {
        return _building;
    }

    @Override
    public String algoName() {
        return "Ex0_OOP_Smart_Elevator";
    }

    @Override
    public int allocateAnElevator(CallForElevator c) {
        int src=c.getSrc();
        int dest=c.getDest();
        int min =_building.minFloor();
        int max=_building.maxFloor();
        int elevNum = _building.numberOfElevetors();
        int ans = 0 ;
        int fastest = CheckFast(elevNum);//check the fastest elevator
        if (elevNum > 1) {
            if (max - min > 20 && elevNum>3) {// if there are more than 20 floors :
                if (src > (max - min) * 0.7 && dest > (max - min) * 0.7) {// if the src and dest are in the top 30% of building :
                    ans = 0;
                } else {
                    ans = 1;
                    if (Math.abs(src-dest) > (max - min) * 0.8 && fastest != -1) {// if there is faster elevator and (src-dest) is very big :
                        ans = fastest;
                    } else {
                        for (int i = 2; i < elevNum; i++) {
                            if (i != fastest) {
                                if (timeW(src, i) < timeW(dest, ans)) {// check time to src from every elevator
                                    ans = i;

                            }
                            }
                        }
                    }
                }
            } else {// if there are less than 20 floors or fewer than 3 elevators
                if (Math.abs(src-dest) > (max - min) * 0.8 && fastest != -1) {
                    ans = fastest;
                } else {
                    for (int i = 1; i < elevNum; i++) {
                        if (i != fastest) {
                            if (timeW(c.getSrc(), i) < timeW(c.getSrc(), ans)) {
                                ans = i;
                            }
                        }
                    }
                }
            }
        }
        OrderElev(ans,src,dest);//
        return ans;
    }

    /**
     * check if there is faster elevator, if not return -1.
     */

    int CheckFast(int NumOE) {
        int x=-1;
        double sp=_building.getElevetor(0).getSpeed();
        for (int i=1;i<NumOE;i++){
            Elevator x1=_building.getElevetor(i);
            if (x1.getSpeed()>sp){
                sp=x1.getSpeed();
                x=i;
            }
        }
        return x;
    }

    /**
     *count how long the elevator get to src
     */

    double timeW(int src, int elev) {
        double time1 = -1;
        Elevator thisElev = this._building.getElevetor(elev);
        int pos = thisElev.getPos();
        double speed = thisElev.getSpeed();
        double floorTime = speed+thisElev.getStopTime()+thisElev.getStartTime()+thisElev.getTimeForOpen()+thisElev.getTimeForClose();  //time of a stop in a floor
            if (_direction == LEVEL) {
                time1 = Math.abs(src - pos) * speed;
            } else if (_direction == UP) {
                if (src>=pos+speed){// if elevator go up and I above the elevator
                    if (!UDF[elev].isEmpty()) {
                        time1 = (betweenStop(src, UDF[elev], 1) * floorTime + (src - pos) * speed);//between stops flor time+ travel time.
                    } else {
                        time1 = (src - pos) * speed;
                    }
                } else {// if elevator go up and I behind the elevator
                    if (!destDownB[elev].isEmpty() && !UDF[elev].isEmpty()) {// if the backup isn't empty and the up list isn't empty
                        time1 = (UDF[elev].size() + betweenStop(src, destDownB[elev], -1)) * floorTime + (2 * UDF[elev].last() - src - pos) * speed;// time1=all up stop+down between stop *floor time+travel time.
                    } else if (!UDF[elev].isEmpty()) {
                        time1 = (UDF[elev].size() * floorTime + (2 * UDF[elev].last() - pos - src) * speed);
                    } else {
                        time1 = (betweenStop(src, destDownB[elev], -1)) * floorTime + (2 * NS[elev] - pos - src) * speed;
                    }
                }
            } else {
                if (src<pos+speed){// elevator go down and I behind.
                    if (!DDF[elev].isEmpty()) {
                        time1 = (betweenStop(src, DDF[elev], 1) * floorTime + (pos - src) * speed);
                    } else {
                        time1 = (pos - src) * speed;
                    }
                } else {
                    if (!destUpB[elev].isEmpty() && !DDF[elev].isEmpty()) {// if the backup isn't empty and the up list isn't empty
                        time1 = (DDF[elev].size() + betweenStop(src, destUpB[elev], 1)) * floorTime + (pos - 2 * DDF[elev].first() + src) * speed;// time1=all up stop+down between stop *floor time+travel time.
                    } else if (!DDF[elev].isEmpty()) {
                        time1 = ((DDF[elev].size()) * floorTime + (pos - 2 * DDF[elev].first() + src) * speed);
                    } else {
                        time1 = (betweenStop(src, destUpB[elev], 1)) * floorTime + (pos - 2 * NS[elev] + src) * speed;
                    }
                }
            }
        return time1;
    }

    /**
     * order elev check where to put the src and the dest(UDF,DDF,destUPB,destDownB).
     *
     */

    private void OrderElev(int ans, int src, int dest) {
        boolean callUp=src<dest;
        Elevator elevator=_building.getElevetor(ans);
        int pos=elevator.getPos();
        if ((elevator.getState()==UP)||!UDF[ans].isEmpty()){
            if (callUp){// I go up and elev Up
                if (pos<src){
                    UDF[ans].add(src);
                    UDF[ans].add(dest);
                }else {
                    destDownB[ans].add(src);
                   destUpB[ans].add(dest);
                }
            }else{//I go down and elev UP
                if (pos<src){
                    UDF[ans].add(src);
                   destDownB[ans].add(dest);
                }else {
                    destDownB[ans].add(src);
                    destDownB[ans].add(dest);
                }
            }
        }else if (elevator.getState()==DOWN||!DDF[ans].isEmpty()){
            if (callUp){//elev go down and I go up
             if (pos>=src){
                 DDF[ans].add(src);
                destUpB[ans].add(dest);
            }else {
                 destUpB[ans].add(src);
                 destUpB[ans].add(dest);
             }
        }else{//I go down and elev Down
            if (pos>src){
                DDF[ans].add(src);
                DDF[ans].add(dest);
            }else {
                destUpB[ans].add(src);
                destDownB[ans].add(dest);
            }
        }
        }else {
                    if (UDF[ans].isEmpty()&&DDF[ans].isEmpty()) {
                        if (callUp) {//I go up

                            if (pos <= src) {
                                UDF[ans].add(src);
                                UDF[ans].add(dest);
                            } else {
                                DDF[ans].add(src);
                                destUpB[ans].add(dest);
                            }
                        } else {// I GO DOWN
                            if (pos >= src) {
                                DDF[ans].add(src);
                                DDF[ans].add(dest);
                            } else {
                                UDF[ans].add(src);
                                destDownB[ans].add(dest);
                            }
                        }
                    }
            }
    }


    @Override
    public void cmdElevator(int elev) {
        if (_firstTime[elev]){//At the first time put all the elevators in 0.
            _building.getElevetor(elev).goTo(0);
            _firstTime[elev]=false;
        }
        Elevator thisElev=_building.getElevetor(elev);
        Direction(elev);//check the direction
        if (thisElev.getState()==LEVEL){
            if (!(UDF[elev].isEmpty()&&DDF[elev].isEmpty()&&destUpB[elev].isEmpty()&&destDownB[elev].isEmpty())){//if the elevator doesn't move and there are order.
                NS[elev]=Checknext(elev);
                thisElev.goTo(NS[elev]);// elevator go to the next stop
            }
            else {//_direction stay LEVEL.
                _direction=LEVEL;
            }
        }else if (thisElev.getState()==UP&&!UDF[elev].isEmpty()){
            if (UDF[elev].first()<NS[elev]){// if there is new stop so the elevator stop
                thisElev.stop(UDF[elev].first());
            }

        }else if (thisElev.getState()==DOWN&&!DDF[elev].isEmpty()){
            if (DDF[elev].last()>NS[elev]){// if there is new stop so the elevator stop
                thisElev.stop(DDF[elev].last());
            }
        }
    }

    /**
     * checknext check what is the elevator's next stop.
     *
     */

    private int Checknext(int elev) {
        int pos=_building.getElevetor(elev).getPos();
        int x=0;
        if (!UDF[elev].isEmpty()){// if the elevator go UP take the next stop from the UDF
            x=UDF[elev].first();
            RemoveFirst(elev);
            _direction=UP;
        }else if (!DDF[elev].isEmpty()){// if the elevator go DOWN take the next stop from the DDF
            x=DDF[elev].last();
            RemoveLast(elev);
            _direction=DOWN;
        }else if (!destUpB[elev].isEmpty()&&destUpB[elev].first()>pos){// if UDF and DDF is empty and the up backup isn't empty and next stop above the pos.
            x=destUpB[elev].first();
            destUpB[elev].remove(destUpB[elev].first());
            _direction=UP;
            if (!destUpB[elev].isEmpty()){
            moveUP(elev);}
        }else if (!destDownB[elev].isEmpty()){// if UDF and DDF is empty and the down backup isn't empty and next stop behind the pos.
            x=destDownB[elev].last();
            destDownB[elev].remove(destDownB[elev].last());
            _direction=DOWN;
            if (!destDownB[elev].isEmpty()){
            MoveDown(elev);
            }
        }
        return x;
    }

    private void RemoveLast(int elev) {
        DDF[elev].remove(DDF[elev].last());
    }

    private void RemoveFirst(int elev) {
        UDF[elev].remove(UDF[elev].first());
    }

    /**
     * move up is moves all up Backup to UDF
     */

    private void moveUP(int elev) {
            while(!destUpB[elev].isEmpty()){
                UDF[elev].add(destUpB[elev].first());
            destUpB[elev].remove(destUpB[elev].first());
            }
        }
    /**
     * move down is moves all down Backup to DDF
     */
    private void MoveDown(int elev) {
            while ((!destDownB[elev].isEmpty())) {
                DDF[elev].add(destDownB[elev].last());
                destDownB[elev].remove(destDownB[elev].last());
            }
    }

    /**
     * check the direction of the elevator.
     */
    private void Direction(int elev1) {
        Elevator elev = _building.getElevetor(elev1);
        int curr=elev.getPos();
        if (_direction == LEVEL) {
            if (UDF[elev1].size() == 0 && DDF[elev1].size() == 0 && destUpB[elev1].size() > 0 && destDownB[elev1].size() > 0) {// if UDF&&DDF is empty and the backups isn't
                if (curr >= destDownB[elev1].last()) {
                    _direction = LEVEL;
                } else {
                    _direction = DOWN;
                }
            } else if (UDF[elev1].size() > 0) {
                _direction = UP;
            } else if (DDF[elev1].size() > 0) {
                _direction = DOWN;
            }
        }
        }

    public int getDirection() {
        return this._direction;
    }

    /**
     * between stop check how many stop there are between the elevator floor and me.
     *
     */

    private int betweenStop(int src,TreeSet<Integer> t,int dir) {
        int sum=0;
        Object[] x=t.toArray();
        for (int i=0;i<x.length;i++){
            if ((int)x[i]>src){
                break;
            }
            sum=i;
        }if (dir==UP) {
            return sum;
        }
        return  x.length-sum;
    }
}


