package JavaProjekt.Organisms;

import JavaProjekt.Action;
import JavaProjekt.Position;
import JavaProjekt.World;

import java.util.ArrayList;
import java.util.List;

public class Sheep extends Animal {

    public Sheep(Position position, World world) {
        super(3,
                3,
                position,
                13,
                15,
                'S',
                world);
        this.initParams();
    }

    @Override
    public void initParams() {
        this.setInitiative(3);
        this.setPower(3);
        this.setLiveLength(13);
        this.setPowerToReproduce(15);
        this.setSign('S');

    }

    @Override
    public Sheep cloned() {
        return new Sheep( this.getPosition(), this.getWorld());
    }

    public List getNeighbouringPositions(){
        return this.world.filterPositionsWithoutAnimals(this.world.getNeighbouringPositions(this.position, 1));
    }

    public List<Action> consequences(Organism attackingOrganism){

        List<Action> result = new ArrayList<>();

        Organism meetOrganism = this.world.getOrganismFromPosition(this.position);
        if (!(meetOrganism instanceof Sheep)){
            result.addAll(meetOrganism.consequences(this));
        }
        return result;
    }
}
