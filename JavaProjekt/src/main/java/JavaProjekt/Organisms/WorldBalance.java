package JavaProjekt.Organisms;

import JavaProjekt.Action;
import JavaProjekt.ActionEnum;
import JavaProjekt.Position;
import JavaProjekt.World;

import java.util.Random;

public class WorldBalance{

    World world;

    public WorldBalance(World world) {
        this.world = world;
    }

    public void woldBalance() {
        int sheeps = 0;
        int wolfs = 0;
        int grasses = 0;
        int dandelions = 0;
        int toadstools = 0;
        for (Organism organism : this.world.getOrganisms()) {
            if (organism instanceof Wolf) {
                wolfs += 1;
            } else if (organism instanceof Sheep) {
                sheeps += 1;
            } else if (organism instanceof Grass) {
                grasses += 1;
            } else if (organism instanceof Dandelion) {
                dandelions += 1;
            } else if (organism instanceof Toadstool) {
                toadstools += 1;
            }

        }


        for (Organism organism : this.world.getOrganisms()) {
                while (sheeps > 8 || wolfs > 8 || dandelions > 7 || toadstools > 5 || grasses > 8) {
                    if (organism instanceof Sheep) {
                        if (sheeps > 8) {
                            new Action(0, ActionEnum.A_WORLDBALANCE, organism, new Position(-1, -1));
                            System.out.printf("%s WAS KILLED AT %s\n%n", organism.getClass().getSimpleName(), organism.getPosition());
                            sheeps--;
                            break;
                        }
                        else break;
                    }
                    if (organism instanceof Wolf) {
                        if (wolfs > 8) {
                            new Action(0, ActionEnum.A_WORLDBALANCE, organism, new Position(-1, -1));
                            System.out.printf("%s WAS KILLED AT %s\n%n", organism.getClass().getSimpleName(), organism.getPosition());
                            wolfs--;
                            break;
                        }
                        else break;
                    }
                    if (organism instanceof Dandelion) {
                        if (dandelions > 7) {
                            new Action(0, ActionEnum.A_WORLDBALANCE, organism, new Position(-1, -1));
                            System.out.printf("%s WAS KILLED AT %s\n%n", organism.getClass().getSimpleName(), organism.getPosition());
                            dandelions--;
                            break;
                        }
                        else break;
                    }
                    if (organism instanceof Grass) {
                        if (grasses > 8) {
                            new Action(0, ActionEnum.A_WORLDBALANCE, organism, new Position(-1, -1));
                            System.out.printf("%s WAS KILLED AT %s\n%n", organism.getClass().getSimpleName(), organism.getPosition());
                            grasses--;
                            break;
                        }
                        else break;
                    }
                    if (organism instanceof Toadstool) {
                        if (toadstools > 5) {
                            new Action(0, ActionEnum.A_WORLDBALANCE, organism, new Position(-1, -1));
                            System.out.printf("%s WAS KILLED AT %s\n%n", organism.getClass().getSimpleName(), organism.getPosition());
                            toadstools--;
                            break;
                        }
                        else break;
                    }
                }
            }

            Random random = new Random();

                while (sheeps <= 2) {
                    Organism org = new Sheep(new Position(random.nextInt(10), random.nextInt(10)), this.world);
                    System.out.printf("%s WAS BORN AT %s\n%n", org.getClass().getSimpleName(), org.position);
                    this.world.addOrganism(org);
                    sheeps++;
                }
                while (wolfs <= 2) {
                    Organism org = new Wolf(new Position(random.nextInt(10), random.nextInt(10)), this.world);
                    System.out.printf("%s WAS BORN AT %s\n%n", org.getClass().getSimpleName(), org.position);
                    this.world.addOrganism(org);
                    wolfs++;
                }
                while (dandelions  <= 1 ) {
                    Organism org = new Dandelion(new Position(random.nextInt(10), random.nextInt(10)), this.world);
                    System.out.printf("%s WAS BORN AT %s\n%n", org.getClass().getSimpleName(), org.position);
                    this.world.addOrganism(org);
                    dandelions++;
                }
                while (toadstools  <= 4) {
                    Organism org = new Toadstool(new Position(random.nextInt(10), random.nextInt(10)), this.world);
                    System.out.printf("%s WAS BORN AT %s\n%n", org.getClass().getSimpleName(), org.position);
                    this.world.addOrganism(org);
                    toadstools++;
                }
                while (grasses <= 4) {
                    Organism org = new Grass(new Position(random.nextInt(10), random.nextInt(10)), this.world);
                    System.out.printf("%s WAS BORN AT %s\n%n", org.getClass().getSimpleName(), org.position);
                    this.world.addOrganism(org);
                    grasses++;
                }
            }
        }
