package JavaProjekt.Organisms;

import JavaProjekt.Action;
import JavaProjekt.ActionEnum;
import JavaProjekt.Position;
import JavaProjekt.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Plant extends Organism implements Organism.ABCClone {

    public Plant(int power, int initiative, Position position, int liveLength, int powerToReproduce, char sign, World world) {
        super(power, initiative, position, liveLength, powerToReproduce, sign, world);
    }

    public List move(){
        return new ArrayList<>();
    }

    public List action(){
        List<Action> result = new ArrayList<>();
        Organism newPlant;
        Position newPosition;

        if(this.ifReproduce()){
            List<Position> pomPositions = this.getFreeNeighbouringPosition(this.position);
            if(!pomPositions.isEmpty()) {
                Random random = new Random();
                int newPositionNumber = random.nextInt(pomPositions.size());
                newPosition = pomPositions.get(newPositionNumber);
                newPlant = this.cloned();
                newPlant.initParams();
                newPlant.setPosition(newPosition);
                this.setPower(this.getPower()/2);
                result.add(new Action(0, ActionEnum.A_ADD, newPlant, newPosition));
            }
        }
        return result;
    }

    public List getFreeNeighbouringPosition(Position position){
        return this.world.filterFreePositions(this.world.getNeighbouringPositions(position, 1));
    }

}
