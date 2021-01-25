package JavaProjekt.Organisms;

import JavaProjekt.Action;
import JavaProjekt.ActionEnum;
import JavaProjekt.World;
import JavaProjekt.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Organism {

    int power;
    int initiative;
    Position position;
    int liveLength;
    int powerToReproduce;
    char sign;
    World world;
    int isFreezed;

    public int getIsFreezed() {
        return isFreezed;
    }

    public void setIsFreezed(int isFreezed) {
        this.isFreezed = isFreezed;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getLiveLength() {
        return liveLength;
    }

    public void setLiveLength(int liveLength) {
        this.liveLength = liveLength;
    }

    public int getPowerToReproduce() {
        return powerToReproduce;
    }

    public void setPowerToReproduce(int powerToReproduce) {
        this.powerToReproduce = powerToReproduce;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public abstract void initParams();

    public abstract List move();

    public abstract List action();

    public interface ABCClone{
        Organism cloned();
    }


    public Organism(int power, int initiative, Position position, int liveLength, int powerToReproduce, char sign, World world) {
        this.power = power;
        this.initiative = initiative;
        this.position = position;
        this.liveLength = liveLength;
        this.powerToReproduce = powerToReproduce;
        this.sign = sign;
        this.world = world;
        this.isFreezed = 0;
    }

    public List<Action> consequences(Organism attackingOrganism) {

        List<Action> result = new ArrayList<>();
        if(this.power > attackingOrganism.getPower()) {
            result.add(new Action(0, ActionEnum.A_REMOVE, attackingOrganism, new Position(-1, -1)));
        }
        else {
            result.add(new Action(0, ActionEnum.A_REMOVE, this, new Position(-1, -1)));
        }
        return result;
    }

    public boolean ifReproduce(){
        boolean result = false;
        if(this.power >= this.powerToReproduce){
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        String name = this.getClass().getSimpleName();
        return String.format("%s: power %s initiative: %s liveLength: %s position: %s", name, this.power,
                this.initiative, this.liveLength,  this.position);
    }
}
