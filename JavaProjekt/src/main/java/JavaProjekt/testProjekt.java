package JavaProjekt;

import JavaProjekt.Organisms.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static JavaProjekt.ActionEnum.A_MOVE;
import static JavaProjekt.ActionEnum.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testProjekt {

    private World world;
    private Action action;

    @Before
    public void setup() {
        world = new World(5, 5);
    }


    @Test
    public void TestAlienAppearAndOrganisms() {

        List<Organism> appearedOrganisms = new ArrayList<>();

        Organism sheep = new Sheep(new Position(4, 0), world);
        Organism wolf = new Wolf(new Position(3, 3), world);
        Organism grass = new Grass(new Position(2, 3), world);
        Organism toadstool = new Toadstool(new Position(1, 4), world);
        Organism dandelion = new Dandelion(new Position(4, 1), world);

        appearedOrganisms.add(sheep);
        appearedOrganisms.add(wolf);
        appearedOrganisms.add(grass);
        appearedOrganisms.add(toadstool);
        appearedOrganisms.add(dandelion);


        for (Organism organism : appearedOrganisms) {
            world.addOrganism(organism);
        }

        assertEquals(appearedOrganisms.size(), world.getOrganisms().size());

        for (int i = 0; i < 7; i++) {
            world.makeTurn();
        }
        boolean alienAppear = false;
        for (Organism organism : world.getOrganisms()) {
            if (organism.getSign() == 'K') {
                alienAppear = true;
            }
        }
        assertTrue(alienAppear);
    }

    @Test
    public void TestSheepOnWolf() {

        Position wolfPosition = new Position(2, 2);
        Organism wolf = new Wolf(wolfPosition, world);
        world.addOrganism(wolf);
        Position sheepPosition = new Position(2, 2);
        Organism sheep = new Sheep(sheepPosition, world);
        world.addOrganism(sheep);

        world.makeTurn();

        char result = world.getOrganisms().get(1).getSign();
        assertEquals('W', result);
    }

    @Test
    public void TestOrganismMove(){

        Position wolfPosition = new Position(2, 2);
        Organism wolf = new Wolf(wolfPosition, world);
        world.addOrganism(wolf);

        List<Action> action = new ArrayList<>();
        action.add(new Action(0, ActionEnum.A_MOVE, wolf, wolfPosition));
        world.makeTurn();

        List result = world.getOrganisms().get(0).move();


        assertEquals(action, result);
    }
}
