package JavaProjekt;

import JavaProjekt.Organisms.Alien;
import JavaProjekt.Organisms.Organism;
import JavaProjekt.Organisms.Plant;
import JavaProjekt.Organisms.WorldBalance;

import java.util.*;

public class World {

    private final int worldX;
    private final int worldY;
    private int turn;
    private List<Organism> organisms;
    private List<Organism> newOrganisms;
    private char separator;

    public World(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.turn = 0;
        this.organisms = new ArrayList<Organism>();
        this.newOrganisms = new ArrayList<Organism>();
        this.separator = ' ';
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public List<Organism> getOrganisms() {
        return organisms;
    }

    public void setOrganisms(List<Organism> organisms) {
        this.organisms = organisms;
    }

    public List<Organism> getNewOrganisms() {
        return newOrganisms;
    }

    public void setNewOrganisms(List<Organism> newOrganisms) {
        this.newOrganisms = newOrganisms;
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }


    public void makeTurn() {

        List<Action> actions;
        if (this.getTurn() % 6 == 0 && this.getTurn() != 0) {
            System.out.println("Alien is here!");
            this.addOrganism(this.alienAppear());
        }

        for (Organism org : this.organisms) {
            if (this.positionOnBoard(org.getPosition())) {
                if (org.getIsFreezed() >= 1)
                    org.setIsFreezed(org.getIsFreezed() - 1);
                if (org.getIsFreezed() < 1) {
                    actions = org.move();
                    for (Action a : actions) {
                        this.makeMove(a);
                    }
                    actions.clear();
                    if (this.positionOnBoard(org.getPosition())) {
                        actions = org.action();
                        for (Action a : actions) {
                            this.makeMove(a);
                        }
                        actions.clear();
                    }
                }
            }
        }

        if (this.getTurn() % 8 == 0 && this.getTurn() != 0){
            alienDisappear();
        }

        List<Organism> aliveOrganisms = new ArrayList<>();
        for (Organism organism : this.getOrganisms()) {
            if (this.positionOnBoard(organism.getPosition())) {
                aliveOrganisms.add(organism);
            }
        }

        this.setOrganisms(aliveOrganisms);

        for (Organism o : aliveOrganisms) {
            if (o.getIsFreezed() < 1) {
                o.setLiveLength(o.getLiveLength() - 1);
                o.setPower(o.getPower() + 1);
            }
        }

        this.setOrganisms(aliveOrganisms);

        Iterator<Organism> organismIterator = aliveOrganisms.iterator();
        while (organismIterator.hasNext()){

            Organism organism = organismIterator.next();

            if (organism.getLiveLength() < 1){
                System.out.println(organism.getClass().getSimpleName()+ " died of old age at " + organism.getPosition());
                organismIterator.remove();
            }
        }

        for (Organism organism : this.getNewOrganisms()){
            if (this.positionOnBoard(organism.getPosition())){
                aliveOrganisms.add(organism);
            }
            else {
                aliveOrganisms.remove(organism);
            }
        }
        this.setOrganisms(aliveOrganisms);
        this.getOrganisms().sort(Comparator.comparingInt(Organism::getInitiative));
        this.turn += 1;
    }

    public Alien alienAppear(){
        List<Position> positions = this.filterFreePositions(this.alienPositions());

        Random random = new Random();
        int index = random.nextInt(positions.size());
        Position alien = positions.get(index);
        return new Alien(alien, this);
    }

    public void alienDisappear(){

        Iterator<Organism> organismIterator = this.getOrganisms().iterator();
        while (organismIterator.hasNext()){
            Organism organism = organismIterator.next();

            if (organism.getSign() == 'K'){
                organismIterator.remove();
                System.out.println("Alien was hidden. He will return soon");
            }
        }
    }

    public void makeMove(Action action){
        System.out.print(action);
        if(action.action == ActionEnum.A_ADD || action.action == ActionEnum.A_BORN)
            this.newOrganisms.add(action.organism);
        else if(action.action == ActionEnum.A_INCREASEPOWER)
            action.organism.setPower(action.organism.getPower() + action.getValue());
        else if(action.action == ActionEnum.A_MOVE)
            action.organism.setPosition(action.position);
        else if(action.action == ActionEnum.A_REMOVE || action.action == ActionEnum.A_WORLDBALANCE)
            action.organism.setPosition(new Position(-1, -1));
        else if(action.action == ActionEnum.A_TIMEFREEZE)
            action.organism.setIsFreezed(action.getValue());
    }

    public boolean addOrganism(Organism newOrganism){
        Position newOrgPosition = new Position(newOrganism.getPosition().getX(), newOrganism.getPosition().getY());

        if(positionOnBoard(newOrgPosition)){
            getOrganisms().add(newOrganism);
            getOrganisms().sort(Comparator.comparingInt(Organism::getInitiative));
            return true;
        }
        return false;
    }

    public boolean positionOnBoard(Position position){
        return position.getX() >= 0 && position.getY() >= 0 && position.getX() < this.getWorldX() && position.getY() < this.getWorldY();
    }

    public Organism getOrganismFromPosition(Position position){
        Organism pomOrganism = null;

        for(Organism org : this.getOrganisms()){
            if (org.getPosition().equals(position)) {
                pomOrganism = org;
                break;
            }
        }
        if(pomOrganism == null){
            for (Organism org : this.getNewOrganisms()){
                if (org.getPosition().equals(position))
                    pomOrganism = org;
                    break;
            }
        }
        return pomOrganism;
    }

    public List<Position> filterFreePositions(List<Position> fields){
        List<Position> result = new ArrayList<>();

        for(Position field: fields){
            if (this.getOrganismFromPosition(field) == null)
                result.add(field);
        }
        return result;
    }

    public List<Position> filterPositionsWithoutAnimals(List<Position> fields){
        List<Position> result = new ArrayList<>();
        Organism pomOrg = null;

        for (Position filled : fields){
            pomOrg = this.getOrganismFromPosition(filled);
            if (pomOrg == null || pomOrg instanceof Plant)
                result.add(filled);
        }
        return result;
    }


    public List<Position> occupiedPositions(List<Position> fields){
        List<Position> result = new ArrayList<>();
        for (Position field : fields){
            if (this.getOrganismFromPosition(field) != null)
                result.add(field);
        }
        return result;
    }

    public List<Position> alienPositions(){
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < this.worldX; i++){
            for (int j = 0; j < this.worldY; j++){
                positions.add(new Position(i,j));
            }
        }
        return positions;
    }

    public List<Position> getNeighbouringPositions(Position position, int range){
        List<Position> result = new ArrayList<>();
        Position pomPosition;

        for (int y = -1; y <= range; y++){
            for (int x = -1; x <= range; x++){
                pomPosition = new Position(position.getX() + x, position.getY() + y);
                if (this.positionOnBoard(position)){
                    result.add(pomPosition);
                }
                if (pomPosition.getX() <= -1 || pomPosition.getY() <= -1 || pomPosition.equals(position)){
                    result.remove(pomPosition);
                }
            }
        }
        return result;
    }


    @Override
    public String toString(){
        String result = "\nturn : " + this.turn + "\n";
        for (int wY = 0; wY < worldY; wY++){
            for (int wX = 0; wX <  worldX; wX++){
                Organism org = this.getOrganismFromPosition(new Position(wX, wY));
                if (org != null){
                    result += org.getSign();
                }
                else {
                    result += this.separator;
                }
            }
            result += "\n";
        }
        return result;
    }
}
