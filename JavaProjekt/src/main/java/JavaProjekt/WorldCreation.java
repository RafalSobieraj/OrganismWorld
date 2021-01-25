package JavaProjekt;

import JavaProjekt.Organisms.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorldCreation {

    public void worldCreation() {

        World world = new World(10, 10);

        List<Organism> appearedOrganisms = new ArrayList<>();

        Organism sheep = new Sheep(new Position(4, 0), world);
        Organism wolf = new Wolf(new Position(3, 3), world);
        Organism grass = new Grass(new Position(6, 6), world);
        Organism toadstool = new Toadstool(new Position(1, 5), world);
        Organism dandelion = new Dandelion(new Position(8, 9), world);

        appearedOrganisms.add(sheep);
        appearedOrganisms.add(wolf);
        appearedOrganisms.add(grass);
        appearedOrganisms.add(toadstool);
        appearedOrganisms.add(dandelion);

        for (Organism organism : appearedOrganisms) {
            world.addOrganism(organism);
        }

        System.out.println(world);
    }
}
