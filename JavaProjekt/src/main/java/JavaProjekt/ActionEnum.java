package JavaProjekt;

public enum ActionEnum {

    A_MOVE(1),
    A_REMOVE(2),
    A_ADD(3),
    A_INCREASEPOWER(4),
    A_TIMEFREEZE(5),
    A_WORLDBALANCE(6),
    A_BORN(7);

    int value;

    public int getValue() {
        return value;
    }

    private ActionEnum(int value){
        this.value = value;
    }

}
