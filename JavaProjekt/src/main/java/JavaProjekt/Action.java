package JavaProjekt;

import JavaProjekt.Organisms.Organism;

public class Action {

    public int getValue() {
        return value;
    }

    public ActionEnum getAction() {
        return action;
    }

    public Organism getOrganism() {
        return organism;
    }

    public Position getPosition() {
        return position;
    }

    int value;
    public ActionEnum action;
    public Organism organism;
    public Position position;

    public Action(int value, ActionEnum action, Organism organism, Position position) {
        this.value = value;
        this.action = action;
        this.organism = organism;
        this.position = position;
    }

    @Override
    public String toString(){

        String className = this.organism.getClass().getSimpleName();

        switch (action){
            case A_ADD:
                return String.format("%s was added at %s\n", className, this.position);
            case A_MOVE:
                return String.format("%s moved to %s from %s\n", className, this.position, this.organism.getPosition());
            case A_REMOVE:
                return String.format("%s was killed at %s\n", className, this.organism.getPosition());
            case A_INCREASEPOWER:
                return String.format("%s strength set to %s at %s\n", className, this.organism.getPower(), this.position);
            case A_TIMEFREEZE:
                return String.format("%s is grounded by Alien at %s\n", className, this.position);
            case A_WORLDBALANCE:
                return String.format("%s WAS KILLED AT %s\n", className, this.position);
            case A_BORN:
                return String.format("%s WAS BORN AT %s\n", className, this.position);
            default:
                return null;
        }
    }
}
