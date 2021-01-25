package JavaProjekt.Organisms;

import JavaProjekt.Position;
import JavaProjekt.World;

public class Dandelion extends Plant {
    public Dandelion(Position position, World world) {
        super(0,
                0,
                position,
                6,
                2,
                'D',
                world);
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(10);
        this.setPowerToReproduce(6);
        this.setSign('D');

    }

    @Override
    public Organism cloned() {
        return new Wolf(this.getPosition(), this.getWorld());
    }
}
