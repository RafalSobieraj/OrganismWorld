package JavaProjekt.Organisms;

import JavaProjekt.Action;
import JavaProjekt.ActionEnum;
import JavaProjekt.Position;
import JavaProjekt.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Animal extends Organism implements Organism.ABCClone {

    public Position getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Position lastPosition) {
        this.lastPosition = lastPosition;
    }

    public Position lastPosition;

    public Animal(int power, int initiative, Position position, int liveLength, int powerToReproduce, char sign, World world) {
        super(power, initiative, position, liveLength, powerToReproduce, sign, world);
        this.lastPosition = position;
    }

    public List move(){
        List<Action> result = new ArrayList<>();
        List<Position> pomPositions = this.getNeighbouringPositions();
        Position newPosition;

        if (!pomPositions.isEmpty()){
            Random random = new Random();
            int index = random.nextInt(pomPositions.size());
            newPosition = (Position) pomPositions.get(index);
            result.add(new Action(0, ActionEnum.A_MOVE, this , newPosition));
            this.lastPosition = this.position;
            Organism metOrganism = this.world.getOrganismFromPosition(newPosition);
            if (metOrganism != null){
                for (Action meet : metOrganism.consequences(this))
                    result.add(meet);
            }
        }
        return result;
    }

    public List action(){
        List<Action> result = new ArrayList<>();
        Organism newAnimal = null;

        List birthPositions = this.getNeighbouringBirthPositions();
        if (this.ifReproduce() && !birthPositions.isEmpty()){
            newAnimal = this.cloned();
            newAnimal.initParams();
            Random random = new Random();
            int index = random.nextInt(birthPositions.size());
            Position newAnimalPosition = (Position) birthPositions.get(index);
            newAnimal.position = newAnimalPosition;
            this.setPower(this.getPower()/2);
            result.add(new Action(0, ActionEnum.A_ADD, newAnimal, newAnimalPosition));
        }
        return result;
    }

    public List getNeighbouringPositions(){
        return this.world.getNeighbouringPositions(this.position, 1);
    }

    public List getNeighbouringBirthPositions(){
        return this.world.filterFreePositions(this.world.getNeighbouringPositions(this.position, 1));
    }
}
