package JavaProjekt.Organisms;

import JavaProjekt.Action;
import JavaProjekt.ActionEnum;
import JavaProjekt.Position;
import JavaProjekt.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Alien extends Animal{
    public Alien(Position position, World world) {
        super(0, 10, position, 5, 50, 'K', world);
        this.initParams();
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(10);
        this.setLiveLength(5);
        this.setPowerToReproduce(50);
        this.setSign('K');
    }

    @Override
    public Organism cloned() {
        return new Alien(this.getPosition(), this.getWorld());
    }


    public List getNeighbouringPositions(){
        return this.world.filterFreePositions(this.world.getNeighbouringPositions(this.position, 1));
    }

    public List<Action> consequences(Organism attackingOrganism){
        List<Action> result = new ArrayList<>();
        if(this.getPower() > attackingOrganism.getPower()) {
            result.add(new Action(0, ActionEnum.A_MOVE, attackingOrganism, getLastPosition()));
        }
        else {
            result.add(new Action(0, ActionEnum.A_MOVE, this, getLastPosition()));
        }
        return result;
    }

    public List<Action> action(){
        List<Action> result = new ArrayList<>();
        List<Position> freezedOrganisms = this.world.occupiedPositions(this.world.getNeighbouringPositions(this.position, 2));

        for (Position positions : freezedOrganisms){
            result.add(new Action(2, ActionEnum.A_TIMEFREEZE, this.world.getOrganismFromPosition(positions), positions));
        }
        return result;
    }
}
