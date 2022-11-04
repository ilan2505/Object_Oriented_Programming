package ex0.algo;
import ex0.Building;
import ex0.simulator.Builging_A;
import ex0.simulator.Call_A;
import ex0.simulator.ElevetorCallList;
import ex0.simulator.Simulator_A;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    class Smart_ElevatorTest {


        Call_A a1 = new Call_A(1.0, 0, 6);
        Call_A a2 = new Call_A(5.0, 9, 4);
        Call_A a3 = new Call_A(8.0, -1, 6);
        Call_A a4 = new Call_A(10.0, 5, 9);
        Call_A a5 = new Call_A(15.0, 6, 4);
        ElevetorCallList y = new ElevetorCallList();
        Building b;
        Smart_Elevator Smart_Elevator;


        public Smart_ElevatorTest() {
            Simulator_A.initData(1, null);
            b = (Builging_A) Simulator_A.getBuilding();
            Smart_Elevator = new Smart_Elevator(b);
            ElevatorAlgo alg = new Smart_Elevator(Simulator_A.getBuilding());
            Smart_Elevator.cmdElevator(0);
            Simulator_A.initAlgo(Smart_Elevator);


        }

        @Test
        void CheckFast() {
            Simulator_A.initData(7, null);
            b = Simulator_A.getBuilding();
            Smart_Elevator = new Smart_Elevator(b);
            int x = Smart_Elevator.CheckFast(b.numberOfElevetors());
            assertEquals(x, -1);
            Simulator_A.initData(9, null);
            b = Simulator_A.getBuilding();
            Smart_Elevator = new Smart_Elevator(b);
            x = Smart_Elevator.CheckFast(b.numberOfElevetors());
            assertEquals(x, 3);


        }

        @Test
        void timeW() {
            double x = Smart_Elevator.timeW(4, 0);
            assertTrue(x == 6);
        }

        @Test
        void allocateAnElevator() {
            Simulator_A.initData(2, null);
            b = Simulator_A.getBuilding();
            Smart_Elevator = new Smart_Elevator(b);
            assertTrue(Smart_Elevator.allocateAnElevator(a1) == 0);
            assertTrue(Smart_Elevator.allocateAnElevator(a2) == 1);
            assertTrue(Smart_Elevator.allocateAnElevator(a3) == 0);
        }

        @Test
        void cmdElevator() throws InterruptedException {
            b.getElevetor(0).goTo(7);
            Smart_Elevator.cmdElevator(0);
            assertEquals(b.getElevetor(0).getState(), 1);

        }

        @Test
        void getDirection() {
            Simulator_A.initData(2, null);
            b = (Builging_A) Simulator_A.getBuilding();
            Smart_Elevator = new Smart_Elevator(b);
            assertEquals(Smart_Elevator.getDirection(), 1);

        }
    }