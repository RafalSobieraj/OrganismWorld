package JavaProjekt.Organisms;

import JavaProjekt.Action;
import JavaProjekt.ActionEnum;
import JavaProjekt.Position;
import JavaProjekt.World;

import java.util.ArrayList;
import java.util.List;

public class Toadstool extends Plant{
    public Toadstool(Position position, World world) {
        super(0, 0, position, 10, 5, 'T', world);
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(10);
        this.setPowerToReproduce(5);
        this.setSign('T');
    }

    @Override
    public Organism cloned() {
        return new Toadstool(this.getPosition(), this.getWorld());
    }

    public List<Action> consequences(Organism attackingOrganism){
        List<Action> result = new ArrayList<>();

        if (this.getPower() >= attackingOrganism.getPower()){
            result.add(new Action(0, ActionEnum.A_REMOVE, attackingOrganism, new Position(-1, -1)));
        }
        else{
            result.add(new Action(0, ActionEnum.A_REMOVE, attackingOrganism, new Position(-1, -1)));
            result.add(new Action(0, ActionEnum.A_REMOVE, this, new Position(-1, -1)));
        }
        return result;
    }
}
