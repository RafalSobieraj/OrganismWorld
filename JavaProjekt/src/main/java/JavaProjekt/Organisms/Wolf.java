package JavaProjekt.Organisms;

import JavaProjekt.Action;
import JavaProjekt.Position;
import JavaProjekt.World;

import java.util.ArrayList;
import java.util.List;

public class Wolf extends Animal{


    public Wolf(Position position, World world) {
        super(6,
                5,
                position,
                15,
                12,
                'W',
                world);
    }

    @Override
    public void initParams() {
        this.setPower(6);
        this.setInitiative(5);
        this.setLiveLength(15);
        this.setPowerToReproduce(12);
        this.setSign('W');

    }

    @Override
    public Organism cloned() {
        return new Wolf(this.getPosition(), this.getWorld());
    }


    public List<Action> consequences(Organism attackingOrganism){

        List<Action> result = new ArrayList<>();

        Organism meetOrganism = this.world.getOrganismFromPosition(this.position);
        if (!(meetOrganism instanceof Wolf || meetOrganism instanceof Plant)){
            result.addAll(meetOrganism.consequences(this));
        }
        else if (meetOrganism instanceof Toadstool){
            result.addAll(meetOrganism.consequences(this));
        }
        return result;
    }
}
