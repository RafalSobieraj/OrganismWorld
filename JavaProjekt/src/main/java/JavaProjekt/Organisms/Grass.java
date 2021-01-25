package JavaProjekt.Organisms;

import JavaProjekt.Position;
import JavaProjekt.World;

public class Grass extends Plant{
    public Grass(Position position, World world) {
        super(0, 0, position, 6, 3, 'G', world);
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(10);
        this.setPowerToReproduce(8);
        this.setSign('G');
    }

    @Override
    public Organism cloned() {
        return new Grass(this.getPosition(), this.getWorld());
    }
}
