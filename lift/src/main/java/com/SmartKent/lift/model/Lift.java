package com.SmartKent.lift.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name="lift")
@Component
public class Lift {

//pickAndDrop method is implemented at last in this class

    @Id
    private Long liftId;
    private String state;
    private String direction;
    private String person;
    private int floor;

    public Lift() {
        this.state = "IDLE";
        this.direction = "NAN";
        this.person = "0";
        this.floor =   1;
    }



    public Long getLiftId() {
        return liftId;
    }

    public void setLiftId(Long liftId) {
        this.liftId = liftId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Lift{" +
                "liftId=" + liftId +
                ", state='" + state + '\'' +
                ", direction='" + direction + '\'' +
                ", person='" + person + '\'' +
                ", floor=" + floor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lift)) return false;
        Lift lift = (Lift) o;
        return getFloor() == lift.getFloor() &&
                getLiftId().equals(lift.getLiftId()) &&
                Objects.equals(getState(), lift.getState()) &&
                Objects.equals(getDirection(), lift.getDirection()) &&
                Objects.equals(getPerson(), lift.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLiftId(), getState(), getDirection(), getPerson(), getFloor());
    }

    //Sleep method is used inorder to show the time delay
    //caused by movement of lift and pause of lift.
    public int pickAndDrop(int fromFloor, int toFloor) throws InterruptedException {
        if (fromFloor < 1 && fromFloor > 10){
            System.out.println("Floor number should be in between 1 and 10");
        }
        if (toFloor < 1 && toFloor > 10 ) {
            System.out.println("Floor number should be in between 1 and 10");
        }


        int EST = Math.abs(fromFloor - toFloor);//Value is assigned regardless of the sign

        if (this.state.equals("IDLE")) {
            int floorsToPass = Math.abs(fromFloor - this.floor);
            System.out.println("The lift would be in " + floorsToPass * 3 + " seconds to the requester in floor " + fromFloor);
            this.setState("TO_PICKUP");
            this.setDirection(this, fromFloor);
            System.out.println(this.toString());

            Thread.sleep(floorsToPass * 1000 *3);
            this.setFloor(fromFloor);
            this.setState("PICKUP");
            this.setPerson("1");
            this.setDirection(this, floor);
            System.out.println(this.toString());

            Thread.sleep(4 * 1000);
            floorsToPass = Math.abs(toFloor - this.floor);
            this.setState("TO_DROPOFF");
            this.setDirection(this, toFloor);
            System.out.println(this.toString());

            Thread.sleep(floorsToPass * 1000 *3);
            this.setFloor(toFloor);
            this.setState("DROPOFF");
            this.setPerson("0");
            this.setDirection(this, floor);
            System.out.println(this.toString());

            Thread.sleep(4 * 1000);
            this.setState("IDLE");
            this.setDirection("NAN");
            System.out.println(this.toString());
        }
        return EST * 3;
    }


    //Method to Detect and set the change in direction.
    // Each time the state changes.
    public void setDirection(Lift lift,int reachFloor) {
        if (reachFloor - lift.floor == 0 ) {
            lift.setDirection("NAN");
        }
        if (reachFloor - lift.floor > 0 ) {
            lift.setDirection("UP");
        }
        if (reachFloor - lift.floor < 0 ) {
            lift.setDirection("DOWN");
        }
    }


}
