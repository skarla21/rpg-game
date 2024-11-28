import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;



public class Game {
    public static void main(String[] args) {
        if(args.length != 2 || (!Objects.equals(args[0], "warrior") && !Objects.equals(args[0], "wizard"))) {
            System.out.println("Wrong arguments");
            System.out.println("Please run as: java rogue.Game player-class player-name");
            System.out.println(" where player-class is either \"wizard\" or \"warrior\"");
            System.out.println(" and player-name is the character name");
            System.exit(0);
        }
        // Player class is in args[0]
        // Player name is in args[1]
        GameLog log = GameLog.getInstance();

        Hero hero;
        if (args[0].equals("warrior")) {
            var hl = new WarriorLevel();
            hero = new Warrior(args[1], hl);
            hl.setWarrior((Warrior) hero);
        } else {
            var hl = new WizardLevel();
            hero = new Wizard(args[1], hl);
            hl.setWizard((Wizard) hero);
        }
        log.addLog(hero.getName() + " the " + hero.getHT() + " opens his eyes, standing in a cold dark room...");
        log.addLog("It's time for him to try and escape...");

        final Room[] currentRoom = {initial()};
        currentRoom[0].heroEnter(hero);


        EventQueue.invokeLater( () -> {
            var frame = new JFrame();
            frame.setLayout(null);
            frame.setSize(1500, 1000);
            frame.setTitle(currentRoom[0].getName());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            var component = new MyShapeComponent(currentRoom[0]);
            component.setBounds(0, 0, 1500, 1000);
            frame.add(component);
            frame.revalidate();

            var gameLogLabel = new JTextArea(GameLog.getInstance().getGameLog());
            gameLogLabel.setBorder(BorderFactory.createTitledBorder("Game Log"));
            gameLogLabel.setFocusable(false);
            gameLogLabel.setEditable(false);
            gameLogLabel.setBounds(900, 450, 550, 500);
            JScrollPane sp = new JScrollPane(gameLogLabel);
            sp.setBounds(900, 450, 550, 500);
            frame.add(sp);
            frame.revalidate();

            //add key listener with all possible inputs
            frame.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                    switch (e.getKeyChar()) {
                        case 'a', 'A' -> {
                            if (currentRoom[0].moveHero(0)) {
                                if (currentRoom[0].heroOnExit() != null) {
                                    log.addLog(args[1] + " entered the Exit to " + currentRoom[0].heroOnExit().getName());
                                    log.addLog(currentRoom[0].heroOnExit().getName() + ": " + currentRoom[0].heroOnExit().getDescription());
                                    Room newRoom = currentRoom[0].heroOnExit();
                                    if (newRoom.isFinal()) {
                                        log.addLog(args[1] + " escaped the Tombs of Atuan! You won!");
                                        log.addLog("Press 'X' to quit.");
                                        gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                        frame.repaint();
                                        frame.removeKeyListener(this);
                                        break;
                                    }
                                    currentRoom[0].heroLeave();
                                    currentRoom[0] = newRoom;
                                    currentRoom[0].heroEnter(hero);
                                    component.changeRoom(currentRoom[0]);
                                    frame.setTitle(currentRoom[0].getName());
                                } else {
                                    currentRoom[0].updateRoom();
                                    if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                                }
                                gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                frame.repaint();
                            }
                        }
                        case 'w', 'W' -> {
                            if (currentRoom[0].moveHero(1)) {
                                if (currentRoom[0].heroOnExit() != null) {
                                    log.addLog(args[1] + " entered the Exit to " + currentRoom[0].heroOnExit().getName());
                                    log.addLog(currentRoom[0].heroOnExit().getName() + ": " + currentRoom[0].heroOnExit().getDescription());
                                    Room newRoom = currentRoom[0].heroOnExit();
                                    if (newRoom.isFinal()) {
                                        log.addLog(args[1] + " escaped the Tombs of Atuan! You won!");
                                        log.addLog("Press 'X' to quit.");
                                        gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                        frame.repaint();
                                        frame.removeKeyListener(this);
                                        break;
                                    }
                                    currentRoom[0].heroLeave();
                                    currentRoom[0] = newRoom;
                                    currentRoom[0].heroEnter(hero);
                                    component.changeRoom(currentRoom[0]);
                                    frame.setTitle(currentRoom[0].getName());
                                } else {
                                    currentRoom[0].updateRoom();
                                    if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                                }
                                gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                frame.repaint();
                            }
                        }
                        case 'd', 'D' -> {
                            if (currentRoom[0].moveHero(2)) {
                                if (currentRoom[0].heroOnExit() != null) {
                                    log.addLog(args[1] + " entered the Exit to " + currentRoom[0].heroOnExit().getName());
                                    log.addLog(currentRoom[0].heroOnExit().getName() + ": " + currentRoom[0].heroOnExit().getDescription());
                                    Room newRoom = currentRoom[0].heroOnExit();
                                    if (newRoom.isFinal()) {
                                        log.addLog(args[1] + " escaped the Tombs of Atuan! You won!");
                                        log.addLog("Press 'X' to quit.");
                                        gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                        frame.repaint();
                                        frame.removeKeyListener(this);
                                        break;
                                    }
                                    currentRoom[0].heroLeave();
                                    currentRoom[0] = newRoom;
                                    currentRoom[0].heroEnter(hero);
                                    component.changeRoom(currentRoom[0]);
                                    frame.setTitle(currentRoom[0].getName());
                                } else {
                                    currentRoom[0].updateRoom();
                                    if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                                }
                                gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                frame.repaint();
                            }
                        }
                        case 's', 'S' -> {
                            if (currentRoom[0].moveHero(3)) {
                                if (currentRoom[0].heroOnExit() != null) {
                                    log.addLog(args[1] + " entered the Exit to " + currentRoom[0].heroOnExit().getName());
                                    log.addLog(currentRoom[0].heroOnExit().getName() + ": " + currentRoom[0].heroOnExit().getDescription());
                                    Room newRoom = currentRoom[0].heroOnExit();
                                    if (newRoom.isFinal()) {
                                        log.addLog(args[1] + " escaped the Tombs of Atuan! You won!");
                                        log.addLog("Press 'X' to quit.");
                                        gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                        frame.repaint();
                                        frame.removeKeyListener(this);
                                        break;
                                    }
                                    currentRoom[0].heroLeave();
                                    currentRoom[0] = newRoom;
                                    currentRoom[0].heroEnter(hero);
                                    component.changeRoom(currentRoom[0]);
                                    frame.setTitle(currentRoom[0].getName());
                                } else {
                                    currentRoom[0].updateRoom();
                                    if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                                }
                                gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                frame.repaint();
                            }
                        }
                        case 'r', 'R' -> {
                            currentRoom[0].restHero();
                            currentRoom[0].updateRoom();
                            if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                            gameLogLabel.setText(GameLog.getInstance().getGameLog());
                            frame.repaint();
                        }
                        case 't', 'T' -> {
                            if (currentRoom[0].takeWpn()) {
                                currentRoom[0].updateRoom();
                                if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                                gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                frame.repaint();
                            }
                        }
                        case 'g', 'G' -> {
                            if (currentRoom[0].takeShield()) {
                                currentRoom[0].updateRoom();
                                if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                                gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                frame.repaint();
                            }
                        }
                        case 'b', 'B' -> {
                            if (currentRoom[0].takeRing()) {
                                currentRoom[0].updateRoom();
                                if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                                gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                frame.repaint();
                            }
                        }
                        case 'h', 'H' -> {
                            if (currentRoom[0].useHealthPotion()) {
                                currentRoom[0].updateRoom();
                                if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                                gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                frame.repaint();
                            }
                        }
                        case 'm', 'M' -> {
                            if (currentRoom[0].useManaPotion()) {
                                currentRoom[0].updateRoom();
                                if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                                gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                frame.repaint();
                            }
                        }
                        case 'p', 'P' -> {
                            if (currentRoom[0].useRestorationPotion()) {
                                currentRoom[0].updateRoom();
                                if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                                gameLogLabel.setText(GameLog.getInstance().getGameLog());
                                frame.repaint();
                            }
                        }
                        case ' ' -> {
                            if (currentRoom[0].heroAttack()) {
                                currentRoom[0].updateRoom();
                                if (Math.random() < 0.1) { currentRoom[0].addEnemy(); }
                            }
                            gameLogLabel.setText(GameLog.getInstance().getGameLog());
                            frame.repaint(); // outside if, in order to print if hero doesn't have mana or isn't in range
                        }
                        default -> {
                        }
                    }
                    if (currentRoom[0].heroDead()) {
                        log.addLog(args[1] + " took lethal damage... You lost!");
                        log.addLog("Press 'X' to quit.");
                        gameLogLabel.setText(GameLog.getInstance().getGameLog());
                        frame.repaint();
                        frame.removeKeyListener(this);
                    }
                }

                public void keyPressed(KeyEvent e) {}
                public void keyReleased(KeyEvent e) {}
            });

        });


    }


    public static Room initial() {
        Room thePit = new Room("The Pit", "A dark pit, where the voices of the damned echo", 0.4, 1);
        Room theRoomOfChains = new Room("The Room of Chains", "Chains rustle all around you", 0.4, 2);
        Room theRoomOfBones = new Room("The Room of Bones", "Bones rattle in the darkness", 0.45, 3);
        Room thePaintedRoom = new Room("The Painted Room", "A faint light reveals walls painted with winged creatures", 0.4, 4);
        Room theTreasureRoom = new Room("The Treasure Room", "Little lights sparkle in the darkness", 0.4, 5);
        Room theUndertomb = new Room("The Undertomb", "The Nameless Ones writhe in their sleep", 0.7, 6);
        Room theRedRockRoom = new Room("The Red Rock Room", "Noone has crossed the Red Rock Door, ever", 0.6, 7);
        Room theUndergroundGarden = new Room("The underground garden", "A Garden of Wanders in a place so dark", 0.60, 8);
        Room theHallOfThrone = new Room("The Hall of Throne", "The Great Hall of Throne", true);

        thePit.addExit(theRoomOfChains);
        thePit.addExit(theRoomOfBones);
        theRoomOfChains.addExit(theRoomOfBones);
        theRoomOfBones.addExit(thePit);
        theRoomOfBones.addExit(thePaintedRoom);
        thePaintedRoom.addExit(theTreasureRoom);
        theTreasureRoom.addExit(thePit);
        theTreasureRoom.addExit(theUndertomb);
        theUndertomb.addExit(thePit);
        theUndertomb.addExit(thePaintedRoom);
        theUndertomb.addExit(theRedRockRoom);
        theUndertomb.addExit(theUndergroundGarden);
        theRedRockRoom.addExit(thePit);
        theUndergroundGarden.addExit(theUndertomb);
        theUndergroundGarden.addExit(theHallOfThrone);

        return thePit;
    }
}




class MyShapeComponent extends JComponent {
    private Room room;
    private final JLabel playerStatusLabel;

    public MyShapeComponent(Room room) {
        this.room = room;

        playerStatusLabel = new JLabel(room.playerStatus());
        playerStatusLabel.setBounds(900, 0, 500, 280);
        playerStatusLabel.setBorder(BorderFactory.createTitledBorder("Player Status"));
        playerStatusLabel.setVerticalTextPosition(JLabel.TOP);
        playerStatusLabel.setHorizontalTextPosition(JLabel.LEFT);
        add(playerStatusLabel);

        String buttons = "<html>" + "Move: W / A / S / D" + "<br />" + "Attack: Space" + "<br />" +
                "Rest: R" + "<br />" + "Equip Weapon / Shield / Ring: T / G / B" + "<br />" +
                "Use Health / Mana / Restoration Potion: H / M / P" + "<br />"  + "</html>";
        JLabel buttons1 = new JLabel(buttons);
        buttons1.setBounds(900, 290, 300, 120);
        buttons1.setBorder(BorderFactory.createTitledBorder("How to Play"));
        buttons1.setVerticalTextPosition(JLabel.TOP);
        buttons1.setHorizontalTextPosition(JLabel.LEFT);
        add(buttons1);

        requestFocusInWindow();
    }

    public void changeRoom(Room room) {
        this.room = room;
    }
    public void paintComponent(Graphics g) {
        int TILE_SIZE = 15;
        MapTile[][] roomTiles = room.getRoomTiles();

        //draw map
        for (int x=0; x<roomTiles.length; x++) {
            for (int y=0; y<roomTiles[x].length; y++) {
                g.setColor(new Color(100, 60, 40));
                g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                if (roomTiles[x][y].getType() == TileType.EXIT) {
                    g.setColor(new Color(250, 238, 60));
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
                } else if (roomTiles[x][y].getType() == TileType.FLOOR || roomTiles[x][y].getType() == TileType.START){
                    if (roomTiles[x][y].getState() == TileState.VISIBLE) {
                        g.setColor(new Color(175, 107, 74));
                    } else {
                        g.setColor(new Color(96, 71, 63));
                    }
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
                }

                if (roomTiles[x][y].wasTrap()) {
                    g.setColor(new Color(184, 25, 189));
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
                }
            }
        }
        //draw hero
        g.setColor(new Color(20, 97, 234));
        g.fillRect(room.getHeroPosition().getX() * TILE_SIZE, room.getHeroPosition().getY() * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
        //draw enemies
        HashMap<Enemy, Position> enemies = room.getEnemies();
        g.setColor(new Color(217, 30, 30));
        for (var entry : enemies.entrySet()) {
            if (roomTiles[entry.getValue().getX()][entry.getValue().getY()].getState() == TileState.VISIBLE) {
                g.fillRect(entry.getValue().getX() * TILE_SIZE, entry.getValue().getY() * TILE_SIZE, TILE_SIZE - 1, TILE_SIZE - 1);
            }
        }
        //draw items
        g.setColor(new Color(94, 255, 0));
        for(int x=0; x<roomTiles.length; x++) {
            for (int y = 0; y < roomTiles[x].length; y++) {
                if(roomTiles[x][y].getType() == TileType.FLOOR && roomTiles[x][y].getItem() != null && roomTiles[x][y].getState() != TileState.UNKNOWN) {
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE/3, TILE_SIZE/3);
                }
            }
        }
        //update player status
        playerStatusLabel.setText(room.playerStatus());
    }
    public Dimension getPreferredSize() { return new Dimension(1500, 1000); }
}




class GameLog {
    private static GameLog single_instance = null;

    private final ArrayList<String> gameLog = new ArrayList<>();
    public String s;
    private GameLog()
    {
        s = "Hello I am a string part of Singleton class";
    }

    public void addLog(String s) {
        gameLog.add(s);
    }

    public String getGameLog() {
        StringBuilder glog = new StringBuilder();
        for (String log: gameLog) {
            glog.append(log).append("\n");
        }
        return glog.toString();
    }

    // Static method to create instance of Singleton class
    public static GameLog getInstance()
    {
        if (single_instance == null) { single_instance = new GameLog(); }
        return single_instance;
    }
}






enum TileType { WALL, FLOOR, START, EXIT }
enum TileState { VISIBLE, FOGGED, UNKNOWN }

class Position {
    private final int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() { return x; }
    public int getY() { return y; }

    public Position add(int d) {    //gets an integer between 0 and 3, 0 = left, 1 = up, 2 = right, 3 = down
        if (d == 0) { return new Position(this.x - 1, this.y); }
        else if (d == 1) { return new Position(this.x, this.y - 1); }   // y positive direction is downwards (so up means - )
        else if (d == 2) { return new Position(this.x + 1, this.y); }
        else { return new Position(this.x, this.y + 1); }
    }

    public int distance(Position otherPosition) { return (Math.abs(this.x - otherPosition.getX()) + Math.abs(this.y - otherPosition.getY())); }
}

class MapTile {
    private TileType type;
    private TileState state;
    private boolean occupied, isTrap, wasTrap;
    private Item item;
    private final Position position;

    public MapTile(TileType type, Position position) {
        this.item = null;
        this.type = type;
        this.position = position;
        this.occupied = false;
        this.state = TileState.UNKNOWN;
        this.isTrap = false;
        this.wasTrap = false;
    }

    public void setTrap() { this.isTrap = true; }
    public void resetTrap() {
        this.isTrap = false;
        this.wasTrap = false;
    }
    public boolean isTrap() { return isTrap; }
    public boolean wasTrap() { return wasTrap; }
    public void activateTrap() {
        this.isTrap = false;
        this.wasTrap = true;
    }
    public TileState getState() { return this.state; }
    public void setState(TileState state) { this.state = state; }
    public void setItem(Item item) { this.item = item; }
    public Item getItem() { return this.item; }
    public void setOccupied(boolean o) { this.occupied = o; }
    public boolean getOccupied() { return this.occupied; }
    public TileType getType() { return this.type; }
    public void setType(TileType type) { this.type = type; }
    public Position getPosition() { return this.position; }
}




class Room {
    private final String name, description;
    private boolean finalRoom = false;
    private final ArrayList<Position> exit = new ArrayList<>();
    private final ArrayList<Room> destination = new ArrayList<>();
    private MapTile[][] roomTiles;
    private Position startPosition, heroPosition;
    private Hero hero;
    private CreateEquippable createEquippable;
    private final HashMap<Enemy, Position> enemies = new HashMap<>();
    private final int minDim = 40, maxDim = 60, maxEnemies = 5;
    private double fillRate;
    private int width, height, roomLevel;
    private final Random rand = new Random();

    private final Map<Integer, List<Class>> levelEnemyMap = Map.of(1, List.of(GiantRat.class, Zombie.class),
            2, List.of(GiantRat.class, Zombie.class, MadGuard.class),
            3, List.of(Zombie.class, MadGuard.class, Skeleton.class),
            4, List.of(MadGuard.class, Skeleton.class, Werewolf.class, Vampire.class),
            5, List.of(Skeleton.class, Werewolf.class, Vampire.class, SkeletonLord.class),
            6, List.of(Werewolf.class, Vampire.class, SkeletonLord.class, Shade.class));



    public Room(String n, String d, double f, int l) {
        this.name = n;
        this.description = d;
        this.fillRate = f;
        this.roomLevel = l;
        initialize();
    }
    public Room(String n, String d, boolean fin) {
        this.name = n;
        this.description = d;
        this.finalRoom = fin;
    }


    public MapTile[][] getRoomTiles() { return roomTiles; }
    public Position getHeroPosition() { return heroPosition; }
    public HashMap<Enemy, Position> getEnemies() { return enemies; }
    public boolean heroDead() { return (hero.getHP() == 0); }
    public Room heroOnExit() {
        for (Position pos: exit) {
            if (heroPosition.getX() == pos.getX() && heroPosition.getY() == pos.getY()) {
                return destination.get(exit.indexOf(pos));
            }
        }
        return null;
    }
    public String getDescription() { return this.description; }
    public String getName() { return this.name; }
    public boolean isFinal() {
        return this.finalRoom;
    }

    public void initialize() {
        width = rand.nextInt((maxDim - minDim) + 1) + minDim;
        height = rand.nextInt((maxDim - minDim) + 1) + minDim;
        roomTiles = new MapTile[width][height];

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                roomTiles[x][y] = new MapTile(TileType.WALL, new Position(x, y));
            }
        }

        // startPosition is random, but not in the edge (we will make sure edges remain walls)
        startPosition = new Position(rand.nextInt(width-2) + 1, rand.nextInt(height-2) + 1);
        Position current = startPosition;
        roomTiles[current.getX()][current.getY()].setType(TileType.START);

        double calcFillRate = 0.0;
        int fillCount = 1;

        while(calcFillRate < fillRate) {
            Position newPosition  = current.add(rand.nextInt(4));
            // check bounds
            if((newPosition.getX() > width - 2) || (newPosition.getX() < 1) || (newPosition.getY() > height - 2) || (newPosition.getY() < 1)) { continue; }
            // change only walls
            if(roomTiles[newPosition.getX()][newPosition.getY()].getType() == TileType.WALL) {
                roomTiles[newPosition.getX()][newPosition.getY()].setType(TileType.FLOOR);
                fillCount += 1;
            }
            calcFillRate = fillCount / (double) (width * height);
            current = newPosition;
        }
    }

    public void addTrap() {
        Position candidate;
        do { candidate = new Position(rand.nextInt(width-2) + 1, rand.nextInt(height-2) + 1);
        } while ( roomTiles[candidate.getX()][candidate.getY()].getType() != TileType.FLOOR || roomTiles[candidate.getX()][candidate.getY()].isTrap());

        roomTiles[candidate.getX()][candidate.getY()].setTrap();
    }

    public void addExit(Room r) {
        Position candidate;
        do { candidate = new Position(rand.nextInt(width-2) + 1, rand.nextInt(height-2) + 1);
        } while ( roomTiles[candidate.getX()][candidate.getY()].getType() != TileType.FLOOR );

        roomTiles[candidate.getX()][candidate.getY()].setType(TileType.EXIT);
        exit.add(candidate);
        destination.add(r);
    }

    public void addUsable() {
        Position candidate;
        do { candidate = new Position(rand.nextInt(width-2) + 1, rand.nextInt(height-2) + 1);
        } while ( roomTiles[candidate.getX()][candidate.getY()].getType() != TileType.FLOOR || roomTiles[candidate.getX()][candidate.getY()].getItem() != null);

        int i = rand.nextInt(3);
        if (i == 0) { roomTiles[candidate.getX()][candidate.getY()].setItem(new HealthPotion()); }
        else if (i == 1) { roomTiles[candidate.getX()][candidate.getY()].setItem(new ManaPotion()); }
        else { roomTiles[candidate.getX()][candidate.getY()].setItem(new RestorationPotion()); }
    }

    public void addEquippable() {
        Position candidate;
        do { candidate = new Position(rand.nextInt(width-2) + 1, rand.nextInt(height-2) + 1);
        } while ( roomTiles[candidate.getX()][candidate.getY()].getType() != TileType.FLOOR || roomTiles[candidate.getX()][candidate.getY()].getItem() != null);

        int i = rand.nextInt(3);
        if (i == 0) { roomTiles[candidate.getX()][candidate.getY()].setItem(createEquippable.makeWeapon(5*roomLevel + 5)); }
        else if (i == 1) { roomTiles[candidate.getX()][candidate.getY()].setItem(createEquippable.makeShield(5*roomLevel + 5)); }
        else { roomTiles[candidate.getX()][candidate.getY()].setItem(createEquippable.makeRing(5*roomLevel + 5)); }
    }

    public void addEnemy() {
        if (enemies.size() < maxEnemies) {      // max of 5 enemies in the room
            Position candidate;
            do { candidate = new Position(rand.nextInt(width - 2) + 1, rand.nextInt(height - 2) + 1);
            } while (roomTiles[candidate.getX()][candidate.getY()].getType() != TileType.FLOOR || roomTiles[candidate.getX()][candidate.getY()].getOccupied() );

            List<Class> possibleEnemies = levelEnemyMap.get(hero.getLevel());
            Class enemyClass = possibleEnemies.get(rand.nextInt(possibleEnemies.size()));
            try {
                var enemy = (Enemy) enemyClass.getDeclaredConstructor().newInstance();
                enemies.put(enemy, candidate);
                roomTiles[candidate.getX()][candidate.getY()].setOccupied(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public void heroEnter(Hero h) {
        this.hero = h;
        this.createEquippable = new CreateEquippable(h.getHT());
        heroPosition = startPosition;
        roomTiles[heroPosition.getX()][heroPosition.getY()].setOccupied(true);
        updateVisibility();

        int x;
        for ( x = 0; x < 6; x++) { addUsable(); addEquippable(); }  // putting 6 potions and equippables around the room at the start
        addEnemy(); addEnemy();     // 2 enemies will spawn at the start, and then each round with some probability
        addTrap(); addTrap();       // 2 random traps in the room
    }

    public void heroLeave() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                roomTiles[x][y].setItem(null);
                roomTiles[x][y].setOccupied(false);
                roomTiles[x][y].setState(TileState.UNKNOWN);
                roomTiles[x][y].resetTrap();
            }
        }
        enemies.clear();
        hero = null;
        heroPosition = null;
        createEquippable = null;
    }

    public void updateVisibility() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if (roomTiles[x][y].getPosition().distance(heroPosition) <= hero.getFoV()) {    //in hero's field of view
                    roomTiles[x][y].setState(TileState.VISIBLE);
                } else {
                    if (roomTiles[x][y].getState() == TileState.VISIBLE) { roomTiles[x][y].setState(TileState.FOGGED); }
                }
            }
        }
    }

    public boolean moveHero(int direction) {
        Position newHeroPosition = heroPosition.add(direction);
        if (roomTiles[newHeroPosition.getX()][newHeroPosition.getY()].getType() != TileType.WALL &&
                !roomTiles[newHeroPosition.getX()][newHeroPosition.getY()].getOccupied() ) {
            roomTiles[heroPosition.getX()][heroPosition.getY()].setOccupied(false);
            heroPosition = newHeroPosition;
            roomTiles[heroPosition.getX()][heroPosition.getY()].setOccupied(true);
            updateVisibility();

            if (roomTiles[heroPosition.getX()][heroPosition.getY()].isTrap()) {
                roomTiles[heroPosition.getX()][heroPosition.getY()].activateTrap();
                GameLog.getInstance().addLog("Oh no... " + hero.getName() + " stepped on a trap and took damage equal to half of his HP...");
                hero.takeDmg(hero.getHP()/2);
            }

            if (roomTiles[heroPosition.getX()][heroPosition.getY()].getItem() != null) {
                if (roomTiles[heroPosition.getX()][heroPosition.getY()].getItem() instanceof HealthPotion) {
                    hero.addHealthPotion();
                    roomTiles[heroPosition.getX()][heroPosition.getY()].setItem(null);
                    GameLog.getInstance().addLog(hero.getName() + " found a Health Potion");
                }
                else if (roomTiles[heroPosition.getX()][heroPosition.getY()].getItem() instanceof ManaPotion) {
                    hero.addManaPotion();
                    roomTiles[heroPosition.getX()][heroPosition.getY()].setItem(null);
                    GameLog.getInstance().addLog(hero.getName() + " found a Mana Potion");
                }
                else if (roomTiles[heroPosition.getX()][heroPosition.getY()].getItem() instanceof RestorationPotion) {
                    hero.addRestorationPotion();
                    roomTiles[heroPosition.getX()][heroPosition.getY()].setItem(null);
                    GameLog.getInstance().addLog(hero.getName() + " found a Restoration Potion");
                }
                else {
                    GameLog.getInstance().addLog(hero.getName() + " stands above a: " + roomTiles[heroPosition.getX()][heroPosition.getY()].getItem().toString());
                }
            }
            return true;
        }
        return false;
    }

    public void restHero() {
        hero.rest();
        GameLog.getInstance().addLog(hero.getName() + " rested, gaining HP and MP.");
    }

    public boolean takeWpn() {
        if (roomTiles[heroPosition.getX()][heroPosition.getY()].getItem() instanceof Weapon) {
            Weapon oldWpn = hero.getWpn();
            hero.setWpn((Weapon) roomTiles[heroPosition.getX()][heroPosition.getY()].getItem());
            roomTiles[heroPosition.getX()][heroPosition.getY()].setItem(oldWpn);
            GameLog.getInstance().addLog(hero.getName() + " picked up a new Weapon");
            return true;
        }
        return false;
    }
    public boolean takeShield() {
        if (roomTiles[heroPosition.getX()][heroPosition.getY()].getItem() instanceof Shield) {
            Shield oldShield = hero.getShield();
            hero.setShield((Shield) roomTiles[heroPosition.getX()][heroPosition.getY()].getItem());
            roomTiles[heroPosition.getX()][heroPosition.getY()].setItem(oldShield);
            GameLog.getInstance().addLog(hero.getName() + " picked up a new Shield");
            return true;
        }
        return false;
    }
    public boolean takeRing() {
        if (roomTiles[heroPosition.getX()][heroPosition.getY()].getItem() instanceof Ring) {
            Ring oldRing = hero.getRing();
            hero.setRing((Ring) roomTiles[heroPosition.getX()][heroPosition.getY()].getItem());
            roomTiles[heroPosition.getX()][heroPosition.getY()].setItem(oldRing);
            GameLog.getInstance().addLog(hero.getName() + " picked up a new Ring");
            return true;
        }
        return false;
    }

    public boolean useHealthPotion() { return hero.useHealthPotion(); }
    public boolean useManaPotion() { return hero.useManaPotion(); }
    public boolean useRestorationPotion() { return hero.useRestorationPotion(); }

    public boolean heroAttack() {
        ArrayList<Enemy> enemiesInRange = new ArrayList<>();
        ArrayList<Integer> enemiesDistance = new ArrayList<>();

        for (var entry : enemies.entrySet()) {
            if (heroPosition.distance(entry.getValue()) <= hero.getAttackRange()) {     //checking if enemy is in attack range
                // code below has the goal to archive the closest enemy(-ies) (the one hero will eventually attack)
                if (enemiesDistance.isEmpty()) {
                    enemiesInRange.add(entry.getKey());
                    enemiesDistance.add(heroPosition.distance(entry.getValue()));
                } else {
                    if (enemiesDistance.get(0) < heroPosition.distance(entry.getValue())) { continue; }
                    else if (enemiesDistance.get(0) == heroPosition.distance(entry.getValue())) {
                        enemiesInRange.add(entry.getKey());
                        enemiesDistance.add(heroPosition.distance(entry.getValue()));
                    } else {
                        enemiesInRange.clear();
                        enemiesDistance.clear();
                        enemiesInRange.add(entry.getKey());
                        enemiesDistance.add(heroPosition.distance(entry.getValue()));
                    }
                }
            }
        }

        if (enemiesInRange.isEmpty()) {
            GameLog.getInstance().addLog("No enemies in Attack Range...");
            return false;
        } else {
            int damage = hero.attack();
            if (damage == -1) {
                GameLog.getInstance().addLog(hero.getName() + " does not have enough Mana to attack...");
                return false;
            }
            // now prioritize the lowest hp enemy (if there are multiple in the minimum distance)
            Enemy enemyToAttack = enemiesInRange.get(0);
            int minHP = enemiesInRange.get(0).getHitPoints();
            for (Enemy enemy: enemiesInRange) {
                if (enemy.getHitPoints() < minHP) {
                    enemyToAttack = enemy;
                    minHP = enemy.getHitPoints();
                }
            }

            enemyToAttack.takeDamage(damage);
            GameLog.getInstance().addLog(hero.getName() + " dealt " + damage + " damage to nearby " + enemyToAttack.getName());
            if (enemyToAttack.isDead()) {
                if (roomTiles[enemies.get(enemyToAttack).getX()][enemies.get(enemyToAttack).getY()].getItem() == null && Math.random() < 0.2 ) {  //20% to drop pot
                    int i = rand.nextInt(3);
                    if (i == 0) { roomTiles[enemies.get(enemyToAttack).getX()][enemies.get(enemyToAttack).getY()].setItem(new HealthPotion()); }
                    else if (i == 1) { roomTiles[enemies.get(enemyToAttack).getX()][enemies.get(enemyToAttack).getY()].setItem(new ManaPotion()); }
                    else { roomTiles[enemies.get(enemyToAttack).getX()][enemies.get(enemyToAttack).getY()].setItem(new RestorationPotion()); }
                }
                GameLog.getInstance().addLog(hero.getName() + " killed nearby " + enemyToAttack.getName());
                roomTiles[enemies.get(enemyToAttack).getX()][enemies.get(enemyToAttack).getY()].setOccupied(false);
                hero.slainedEnemy(enemyToAttack);
                enemies.remove(enemyToAttack);
            }

            return true;
        }
    }

    public void updateRoom() {
        for (var entry : enemies.entrySet()) {
            if (entry.getValue().distance(heroPosition) > 1) {
                // move enemy towards hero (enemy is not next to hero, so he can't attack him)
                Position newPosL = entry.getValue().add(0); // move left
                Position newPosU = entry.getValue().add(1); // move up
                Position newPosR = entry.getValue().add(2); // move right
                Position newPosD = entry.getValue().add(3); // move down

                if (entry.getValue().getX() < heroPosition.getX() && !roomTiles[newPosR.getX()][newPosR.getY()].getOccupied() &&
                        (roomTiles[newPosR.getX()][newPosR.getY()].getType() == TileType.FLOOR || roomTiles[newPosR.getX()][newPosR.getY()].getType() == TileType.START)) {
                    roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(false);
                    entry.setValue(newPosR);
                    roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(true);
                } else if (entry.getValue().getX() > heroPosition.getX() && !roomTiles[newPosL.getX()][newPosL.getY()].getOccupied() &&
                        (roomTiles[newPosL.getX()][newPosL.getY()].getType() == TileType.FLOOR || roomTiles[newPosL.getX()][newPosL.getY()].getType() == TileType.START)) {
                    roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(false);
                    entry.setValue(newPosL);
                    roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(true);
                } else if (entry.getValue().getY() < heroPosition.getY() && !roomTiles[newPosD.getX()][newPosD.getY()].getOccupied() &&
                        (roomTiles[newPosD.getX()][newPosD.getY()].getType() == TileType.FLOOR || roomTiles[newPosD.getX()][newPosD.getY()].getType() == TileType.START)) {
                    roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(false);
                    entry.setValue(newPosD);
                    roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(true);
                } else if (entry.getValue().getY() > heroPosition.getY() && !roomTiles[newPosU.getX()][newPosU.getY()].getOccupied() &&
                        (roomTiles[newPosU.getX()][newPosU.getY()].getType() == TileType.FLOOR || roomTiles[newPosU.getX()][newPosU.getY()].getType() == TileType.START)) {
                    roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(false);
                    entry.setValue(newPosU);
                    roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(true);
                } else {
                    // if we end up here it means that enemy is stuck (can't get closer to hero), so we'll move him to a random direction (that is available)
                    if (!roomTiles[newPosR.getX()][newPosR.getY()].getOccupied() && (roomTiles[newPosR.getX()][newPosR.getY()].getType() == TileType.FLOOR
                            || roomTiles[newPosR.getX()][newPosR.getY()].getType() == TileType.START)) {
                        roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(false);
                        entry.setValue(newPosR);
                        roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(true);
                    } else if (!roomTiles[newPosL.getX()][newPosL.getY()].getOccupied() && (roomTiles[newPosL.getX()][newPosL.getY()].getType() == TileType.FLOOR
                            || roomTiles[newPosL.getX()][newPosL.getY()].getType() == TileType.START)) {
                        roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(false);
                        entry.setValue(newPosL);
                        roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(true);
                    } else if (!roomTiles[newPosD.getX()][newPosD.getY()].getOccupied() && (roomTiles[newPosD.getX()][newPosD.getY()].getType() == TileType.FLOOR
                            || roomTiles[newPosD.getX()][newPosD.getY()].getType() == TileType.START)) {
                        roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(false);
                        entry.setValue(newPosD);
                        roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(true);
                    } else if (!roomTiles[newPosU.getX()][newPosU.getY()].getOccupied() && (roomTiles[newPosU.getX()][newPosU.getY()].getType() == TileType.FLOOR
                            || roomTiles[newPosU.getX()][newPosU.getY()].getType() == TileType.START)) {
                        roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(false);
                        entry.setValue(newPosU);
                        roomTiles[entry.getValue().getX()][entry.getValue().getY()].setOccupied(true);
                    }
                    // if we reach here it means that enemy is not able to move nowhere for some reason
                }

            } else {
                // attack hero
                int damage = entry.getKey().dealDamage();
                hero.takeDmg(damage);
                GameLog.getInstance().addLog(entry.getKey().getName() + " dealt " + damage + " damage to " + hero.getName());
            }
        }
    }

    public String playerStatus() {
        StringBuilder pstate = new StringBuilder("<html>");
        pstate.append(hero.getName()).append(" (").append(hero.getHT()).append("), Level: ").append(hero.getLevel()).append("<br />").append("<br />");
        pstate.append("HP: ").append(hero.getHP()).append("/").append(hero.getMaxHP()).append("<br />");
        pstate.append("MP: ").append(hero.getMP()).append("/").append(hero.getMaxMP()).append("<br />");
        pstate.append("Strength: ").append(hero.getStr()).append("<br />");
        pstate.append("Intelligence: ").append(hero.getInt()).append("<br />");
        pstate.append("Defense: ").append(hero.getDefense()).append("<br />").append("<br />");
        pstate.append("Weapon: ");
        if (hero.getWpn() != null) { pstate.append(hero.getWpn().toString()); }
        pstate.append("<br />");
        pstate.append("Shield: ");
        if (hero.getShield() != null) { pstate.append(hero.getShield().toString()); }
        pstate.append("<br />");
        pstate.append("Ring: ");
        if (hero.getRing() != null) { pstate.append(hero.getRing().toString()); }
        pstate.append("<br />").append("<br />");
        pstate.append("HealthPotions / ").append(hero.getHealthPotions()).append("<br />");
        pstate.append("ManaPotions / ").append(hero.getManaPotions()).append("<br />");
        pstate.append("RestorationPotions / ").append(hero.getRestorationPotions()).append("<br />");
        pstate.append("</html>");
        return pstate.toString();
    }
}





enum EffectType { HP_REPLENISH, MP_REPLENISH, HP_BOOST, MP_BOOST, STR_BOOST, INT_BOOST, DEFENSE }

class ItemEffect {
    private final EffectType Effect;
    private final int stat;

    public ItemEffect(EffectType e, int s) {
        this.Effect = e;
        this.stat = s;
    }

    public EffectType getEffect() { return Effect; }
    public int getStat() { return stat; }
}


class Item {
    private final ItemEffect MainEffect;
    private final ItemEffect SecondaryEffect;
    private final String Name;

    public Item (String name, ItemEffect me, ItemEffect se) {
        this.Name = name;
        this.MainEffect = me;
        this.SecondaryEffect = se;
    }

    public ItemEffect getMainEffect() { return MainEffect; }
    public ItemEffect getSecondaryEffect() { return SecondaryEffect; }
    public String getName() { return Name; }
}



class Equippables extends Item {
    public Equippables(String name, ItemEffect me, ItemEffect se) { super(name, me, se); }
    public String toString() {
        return getName() + ", " + getMainEffect().getEffect() + ": " + getMainEffect().getStat() +
                ", " + getSecondaryEffect().getEffect() + ": " + getSecondaryEffect().getStat();
    }
}

class Weapon extends Equippables {
    public Weapon(String name, ItemEffect me, ItemEffect se) { super(name, me, se); }
}

class Shield extends Equippables {
    public Shield(String name, ItemEffect me, ItemEffect se) { super(name, me, se); }
}

class Ring extends Equippables {
    public Ring(String name, ItemEffect me, ItemEffect se) { super(name, me, se); }
}



class CreateEquippable {
    private final String[] adjectives = {"Fierce", "Stubborn", "Pure", "Ancient", "Evil", "Holy", "Fearful", "Monstrous", "Mighty", "Divine",
            "Mysterious", "Cursed", "Heavy", "Dark", "Shiny", "Spiky", "Ferocious", "Laughing"};
    private final String[] warriorTypes = {"Sword", "Axe", "Mace", "Hammer", "Dagger", "Machete"};
    private final String[] wizardTypes = {"Staff", "Wand", "Rod", "Tome", "Gem", "Scepter", "Trident"};
    private final String[] defenseTypes = {"Shield", "Cloak", "Cape", "Armor", "Guard", "Buckler", "Aegis", "Cover"};
    private final String[] surnames = {"of the Champion", "of Erreth-Akbe", "of Rok", "of Arne", "of Bjørn", "of Frode", "of Halfdan", "of Armageddon",
            "of Achilles", "of Osiris", "of Seth", "of Anubis", "of Zeus", "of the Unknown", "of Inanna", "of Ra", "of Poseidon", "of Ares", "of Tenar"};
    private final Random rng = new Random();
    private final HeroType ht;

    public CreateEquippable(HeroType ht) {
        this.ht = ht;
    }

    public Weapon makeWeapon(int weaponLevel) {
        StringBuilder sb = new StringBuilder(adjectives[rng.nextInt(adjectives.length)]).append(" ");
        if (ht.equals(HeroType.Wizard)) {
            sb.append(wizardTypes[rng.nextInt(wizardTypes.length)]).append(" ");
        } else {
            sb.append(warriorTypes[rng.nextInt(warriorTypes.length)]).append(" ");
        }
        sb.append(surnames[rng.nextInt(surnames.length)]);
        String name = sb.toString();

        EffectType mainEffect;
        EffectType[] otherEffects;
        if (ht.equals(HeroType.Wizard)) {
            mainEffect = EffectType.INT_BOOST;
            otherEffects = new EffectType[] {EffectType.HP_BOOST, EffectType.MP_BOOST, EffectType.DEFENSE};
        } else {
            mainEffect = EffectType.STR_BOOST;
            otherEffects = new EffectType[] {EffectType.HP_BOOST, EffectType.DEFENSE};
        }

        int stat = rng.nextInt(weaponLevel) + 1;
        var ME = new ItemEffect(mainEffect, stat);

        stat = weaponLevel - stat;
        EffectType secondStat = otherEffects[rng.nextInt(otherEffects.length)];
        var SE = new ItemEffect(secondStat, stat);

        return new Weapon(name, ME, SE);
    }

    public Shield makeShield(int weaponLevel) {
        StringBuilder sb = new StringBuilder(adjectives[rng.nextInt(adjectives.length)]).append(" ");
        sb.append(defenseTypes[rng.nextInt(defenseTypes.length)]).append(" ").append(surnames[rng.nextInt(surnames.length)]);
        String name = sb.toString();

        EffectType mainEffect = EffectType.DEFENSE;
        EffectType[] otherEffects;
        if (ht.equals(HeroType.Wizard)) {
            otherEffects = new EffectType[] {EffectType.HP_BOOST, EffectType.MP_BOOST, EffectType.INT_BOOST};
        } else {
            otherEffects = new EffectType[] {EffectType.HP_BOOST, EffectType.STR_BOOST};
        }

        int stat = rng.nextInt(weaponLevel) + 1;
        var ME = new ItemEffect(mainEffect, stat);

        stat = weaponLevel - stat;
        EffectType secondStat = otherEffects[rng.nextInt(otherEffects.length)];
        var SE = new ItemEffect(secondStat, stat);

        return new Shield(name, ME, SE);
    }

    public Ring makeRing(int weaponLevel) {
        StringBuilder sb = new StringBuilder(adjectives[rng.nextInt(adjectives.length)]).append(" ");
        sb.append("Ring ").append(surnames[rng.nextInt(surnames.length)]);
        String name = sb.toString();

        EffectType mainEffect;
        EffectType[] otherEffects;
        if (ht.equals(HeroType.Wizard)) {
            mainEffect = EffectType.INT_BOOST;
            otherEffects = new EffectType[] {EffectType.HP_BOOST, EffectType.MP_BOOST, EffectType.DEFENSE};
        } else {
            mainEffect = EffectType.STR_BOOST;
            otherEffects = new EffectType[] {EffectType.HP_BOOST, EffectType.DEFENSE};
        }

        int stat = rng.nextInt(weaponLevel) + 1;
        var ME = new ItemEffect(mainEffect, stat);

        stat = weaponLevel - stat;
        EffectType secondStat = otherEffects[rng.nextInt(otherEffects.length)];
        var SE = new ItemEffect(secondStat, stat);

        return new Ring(name, ME, SE);
    }
}



class Usables extends Item {
    private int usesLeft = 2;
    public Usables(String name, ItemEffect me, ItemEffect se) { super(name, me, se); }

    public void use() { usesLeft -= 1; }
    public int getUsesLeft() { return usesLeft; }
}

class HealthPotion extends Usables {
    public HealthPotion() { super("Health Potion", new ItemEffect(EffectType.HP_REPLENISH, 25), null); }
}

class ManaPotion extends Usables {
    public ManaPotion() { super("Mana Potion", new ItemEffect(EffectType.MP_REPLENISH, 20), null); }
}

class RestorationPotion extends Usables {
    public RestorationPotion() { super("Restoration Potion", new ItemEffect(EffectType.HP_REPLENISH, 25), new ItemEffect(EffectType.MP_REPLENISH, 20)); }
}




class Dice {
    private final int N;    //number of throws
    private final int S;    //number of sides
    private final int m;    //bonus damage

    public Dice(int N, int S, int m) {
        this.N = N;
        this.S = S;
        this.m = m;
    }

    public int roll() {
        int sum = 0;
        Random rand = new Random();
        for (int i=1; i <= N; i++) {
            int randomNum = rand.nextInt(S) + 1;
            sum += randomNum;
        }
        return (sum + m);
    }

    public String toString() {
        return N + "d" + S + " + " + m;
    }
}



class Enemy {
    private int hitPoints;
    private final String Name;
    private final int XP;
    private final Dice dmg;

    public Enemy(String Name, int hp, int xp, int N, int S, int m) {
        this.hitPoints = hp;
        this.Name = Name;
        dmg = new Dice(N, S, m);
        this.XP = xp;
    }

    public String getName() { return Name; }
    public int getHitPoints() { return hitPoints; }
    public Dice getDamage() { return dmg; }
    public int getXP() { return XP; }
    public String toString() {
        return "Enemy Name: " + Name + ", HP: " + hitPoints + ", gives " + XP + " XP, and deals: " + dmg;
    }

    public void addHitPoints(int hp) { hitPoints += hp; }

    public int dealDamage() {
        return dmg.roll();
    }

    public void takeDamage(int d) {
        hitPoints = Math.max(hitPoints-d, 0);
    }
    public boolean isDead() { return (hitPoints == 0); }
}


class GiantRat extends Enemy {
    public GiantRat () {
        super("Giant Rat", 15, 25, 1, 6, 1);
        GameLog.getInstance().addLog("A mighty 'Giant Rat' spawns...");
    }
}

class Zombie extends Enemy {
    public Zombie () {
        super("Zombie", 20, 30, 1, 6, 3);
        GameLog.getInstance().addLog("A stumbling 'Zombie' is nearby...");
    }
}

class MadGuard extends Enemy {
    public MadGuard () {
        super("Mad Guard", 25, 40, 2, 6, 2);
        GameLog.getInstance().addLog("A really mad 'Mad Guard' is born...");
    }
}

class Skeleton extends Enemy {
    public Skeleton () {
        super("Skeleton", 30, 70, 2, 6, 4);
        GameLog.getInstance().addLog("A frightening 'Skeleton' rises...");
    }
}

class Werewolf extends Enemy {
    public Werewolf () {
        super("Werewolf", 40, 90, 3, 6, 1);
        GameLog.getInstance().addLog("A furious 'Werewolf' approaches...");
    }
}

class Vampire extends Enemy {
    public Vampire () {
        super("Vampire", 45, 100, 3, 6, 2);
        GameLog.getInstance().addLog("An evil 'Vampire' crawls in the shadows...");
    }

    public int dealDamage() {
        int d = getDamage().roll();
        addHitPoints(d/3);
        return d;
    }
}

class SkeletonLord extends Enemy {
    public SkeletonLord () {
        super("Skeleton Lord", 50, 120, 3, 6, 4);
        GameLog.getInstance().addLog("A terrifying 'Skeleton Lord' lurks...");
    }
}

class Shade extends Enemy {
    public Shade () {
        super("Shade", 60, 150, 4, 6, 6);
        GameLog.getInstance().addLog("Oh no... Is that a 'Shade'???");
    }
}




enum HeroType { Warrior, Wizard }

abstract class Hero {
    int baseHP, curHP, baseMP, curMP, baseStr, baseInt, AttackRange, FoV, Level;
    HeroType HT;
    private final String Name;
    private Weapon wpn;
    private Shield shield;
    private Ring ring;
    private final ArrayList<HealthPotion> healthPotions;
    private final ArrayList<ManaPotion> manaPotions;
    private final ArrayList<RestorationPotion> restorationPotions;
    Map<EffectType, Integer> Bonuses = new HashMap<>();

    public Hero(String Name) {
        this.Name = Name;
        this.wpn = null;
        this.shield = null;
        this.ring = null;
        this.healthPotions = new ArrayList<>();
        this.manaPotions = new ArrayList<>();
        this.restorationPotions = new ArrayList<>();
        Bonuses.put(EffectType.HP_BOOST, 0);
        Bonuses.put(EffectType.MP_BOOST, 0);
        Bonuses.put(EffectType.STR_BOOST, 0);
        Bonuses.put(EffectType.INT_BOOST, 0);
        Bonuses.put(EffectType.DEFENSE, 0);
    }

    public int getAttackRange() { return AttackRange; }
    public int getFoV() { return FoV; }
    public int getHP() { return curHP; }
    public int getMaxHP() { return baseHP + Bonuses.get(EffectType.HP_BOOST); }
    public int getMP() { return curMP; }
    public int getMaxMP() { return baseMP + Bonuses.get(EffectType.MP_BOOST); }
    public int getStr() { return baseStr + Bonuses.get(EffectType.STR_BOOST); }
    public int getInt() { return baseInt + Bonuses.get(EffectType.INT_BOOST); }
    public int getDefense() { return Bonuses.get(EffectType.DEFENSE); }
    public Weapon getWpn() { return wpn; }
    public Shield getShield() { return shield; }
    public Ring getRing() { return ring; }
    public String getName() { return Name; }
    public HeroType getHT() { return HT; }
    public int getHealthPotions() { return healthPotions.size(); }
    public int getManaPotions() { return manaPotions.size(); }
    public int getRestorationPotions() { return restorationPotions.size(); }

    public void setLevel(int level) { Level = level; }
    public int getLevel() { return Level; }

    abstract public int attack();
    abstract public void rest();
    abstract public void slainedEnemy(Enemy e);

    public void alterCurrentStat(EffectType et, int stat) {
        if (et.equals(EffectType.HP_BOOST)) { curHP = Math.min(Math.max(curHP + stat, 1), baseHP + Bonuses.get(EffectType.HP_BOOST)); }
        if (et.equals(EffectType.MP_BOOST)) { curMP = Math.min(Math.max(curMP + stat, 0), baseMP + Bonuses.get(EffectType.MP_BOOST)); }
    }   // h function alterCurrentStat allazei antistoixa to curHP/curMP tou player analoga me tis allages sta bonuses

    public void takeDmg(int d) { curHP = Math.max(curHP - Math.max(d - Bonuses.get(EffectType.DEFENSE), 0), 0); }

    public void setWpn(Weapon newwpn) {
        if (this.wpn != null) {
            Bonuses.put(wpn.getMainEffect().getEffect(), Bonuses.get(wpn.getMainEffect().getEffect()) - wpn.getMainEffect().getStat());
            Bonuses.put(wpn.getSecondaryEffect().getEffect(), Bonuses.get(wpn.getSecondaryEffect().getEffect()) - wpn.getSecondaryEffect().getStat());
            alterCurrentStat(wpn.getSecondaryEffect().getEffect(), -wpn.getSecondaryEffect().getStat());
        }
        this.wpn = newwpn;
        Bonuses.put(wpn.getMainEffect().getEffect(), Bonuses.get(wpn.getMainEffect().getEffect()) + wpn.getMainEffect().getStat());
        Bonuses.put(wpn.getSecondaryEffect().getEffect(), Bonuses.get(wpn.getSecondaryEffect().getEffect()) + wpn.getSecondaryEffect().getStat());
        alterCurrentStat(wpn.getSecondaryEffect().getEffect(), wpn.getSecondaryEffect().getStat());
    }

    public void setShield(Shield newshield) {
        if (this.shield != null) {
            Bonuses.put(shield.getMainEffect().getEffect(), Bonuses.get(shield.getMainEffect().getEffect()) - shield.getMainEffect().getStat());
            Bonuses.put(shield.getSecondaryEffect().getEffect(), Bonuses.get(shield.getSecondaryEffect().getEffect()) - shield.getSecondaryEffect().getStat());
            alterCurrentStat(shield.getSecondaryEffect().getEffect(), -shield.getSecondaryEffect().getStat());
        }
        this.shield = newshield;
        Bonuses.put(shield.getMainEffect().getEffect(), Bonuses.get(shield.getMainEffect().getEffect()) + shield.getMainEffect().getStat());
        Bonuses.put(shield.getSecondaryEffect().getEffect(), Bonuses.get(shield.getSecondaryEffect().getEffect()) + shield.getSecondaryEffect().getStat());
        alterCurrentStat(shield.getSecondaryEffect().getEffect(), shield.getSecondaryEffect().getStat());
    }

    public void setRing(Ring newring) {
        if (this.ring != null) {
            Bonuses.put(ring.getMainEffect().getEffect(), Bonuses.get(ring.getMainEffect().getEffect()) - ring.getMainEffect().getStat());
            Bonuses.put(ring.getSecondaryEffect().getEffect(), Bonuses.get(ring.getSecondaryEffect().getEffect()) - ring.getSecondaryEffect().getStat());
            alterCurrentStat(ring.getSecondaryEffect().getEffect(), -ring.getSecondaryEffect().getStat());
        }
        this.ring = newring;
        Bonuses.put(ring.getMainEffect().getEffect(), Bonuses.get(ring.getMainEffect().getEffect()) + ring.getMainEffect().getStat());
        Bonuses.put(ring.getSecondaryEffect().getEffect(), Bonuses.get(ring.getSecondaryEffect().getEffect()) + ring.getSecondaryEffect().getStat());
        alterCurrentStat(ring.getSecondaryEffect().getEffect(), ring.getSecondaryEffect().getStat());
    }

    public void addHealthPotion() { healthPotions.add(new HealthPotion()); }
    public void addManaPotion() { manaPotions.add(new ManaPotion()); }
    public void addRestorationPotion() { restorationPotions.add(new RestorationPotion()); }

    public boolean useHealthPotion() {
        if (healthPotions.isEmpty()) {
            return false;
        } else {
            HealthPotion hpt = healthPotions.get(0);
            if ( Math.random() > 0.05) {
                curHP = Math.min(curHP + hpt.getMainEffect().getStat(), baseHP + Bonuses.get(EffectType.HP_BOOST));
                GameLog.getInstance().addLog(getName() + " used a Health Potion");
            } else {   // 5% chance to deal damage instead
                curHP = Math.max(curHP-5, 0);
                GameLog.getInstance().addLog("Oh no... Health Potion was poisoned... " + getName() + " took damage instead.");
            }
            hpt.use();
            if (hpt.getUsesLeft() == 0) { healthPotions.remove(hpt); }
            return true;
        }
    }
    public boolean useManaPotion() {
        if (manaPotions.isEmpty()) {
            return false;
        } else {
            ManaPotion mpt = manaPotions.get(0);
            curMP = Math.min(curMP + mpt.getMainEffect().getStat(), baseMP + Bonuses.get(EffectType.MP_BOOST));
            GameLog.getInstance().addLog(getName() + " used a Mana Potion");
            mpt.use();
            if (mpt.getUsesLeft() == 0) { manaPotions.remove(mpt); }
            return true;
        }
    }
    public boolean useRestorationPotion() {
        if (restorationPotions.isEmpty()) {
            return false;
        } else {
            RestorationPotion rpt = restorationPotions.get(0);
            curHP = Math.min(curHP + rpt.getMainEffect().getStat(), baseHP + Bonuses.get(EffectType.HP_BOOST));
            curMP = Math.min(curMP + rpt.getSecondaryEffect().getStat(), baseMP + Bonuses.get(EffectType.MP_BOOST));
            GameLog.getInstance().addLog(getName() + " used a Restoration Potion");
            rpt.use();
            if (rpt.getUsesLeft() == 0) { restorationPotions.remove(rpt); }
            return true;
        }
    }
}


class Warrior extends Hero {
    private final WarriorLevel wl;

    public Warrior(String Name, WarriorLevel wl) {
        super(Name);
        this.HT = HeroType.Warrior;
        this.wl = wl;
        baseHP = wl.getBaseHP();
        baseStr = wl.getBaseStr();
        setLevel(wl.getLvl());
        curHP = baseHP;
        baseMP = 0;
        curMP = 0;
        baseInt = 0;

        FoV = 4;
        AttackRange = 1;
    }


    public int attack() {
        return baseStr + Bonuses.get(EffectType.STR_BOOST);
    }
    public void slainedEnemy(Enemy e) { wl.addXP(e.getXP()); }
    public void updateBaseStates(int HitPoints, int Strength) {
        baseHP += HitPoints;
        curHP += HitPoints;
        baseStr += Strength;
        setLevel(getLevel()+1);
        GameLog.getInstance().addLog(getName() + " leveled up!");
    }

    public void rest() { curHP = Math.min(curHP+5, baseHP + Bonuses.get(EffectType.HP_BOOST)); }
}


class Wizard extends Hero {
    private final WizardLevel wl;

    public Wizard(String Name, WizardLevel wl) {
        super(Name);
        this.HT = HeroType.Wizard;
        this.wl = wl;
        baseHP = wl.getBaseHP();
        baseMP = wl.getBaseMP();
        baseInt = wl.getBaseInt();
        setLevel(wl.getLvl());
        curHP = baseHP;
        curMP = baseMP;
        baseStr = 0;

        FoV = 8;
        AttackRange = FoV;
    }


    public int attack() {
        if ( curMP >= wl.getSpellCost() ) {
            curMP -= wl.getSpellCost();
            return baseInt + Bonuses.get(EffectType.INT_BOOST);
        } else { return -1; }   // when wizard doesn't have enough mana
    }
    public void slainedEnemy(Enemy e) { wl.addXP(e.getXP()); }
    public void updateBaseStates(int HitPoints, int ManaPoints, int Int) {
        baseHP += HitPoints;
        curHP += HitPoints;
        baseMP += ManaPoints;
        curMP += ManaPoints;
        baseInt += Int;
        setLevel(getLevel()+1);
        GameLog.getInstance().addLog(getName() + " leveled up!");
    }

    public void rest() {
        curHP = Math.min(curHP+5, baseHP + Bonuses.get(EffectType.HP_BOOST));
        curMP = Math.min(curMP+5, baseMP + Bonuses.get(EffectType.MP_BOOST));
    }
}




abstract class Level {
    int lvl, totalxp;
    Map<Integer, Integer> LevelXP = new HashMap<>();
    Map<Integer, Integer> BaseHP = new HashMap<>();
    Map<Integer, Integer> BaseMP = new HashMap<>();
    Map<Integer, Integer> BaseStr = new HashMap<>();
    Map<Integer, Integer> BaseInt = new HashMap<>();
    Map<Integer, Integer> SpellCost = new HashMap<>();

    public void addXP(int xp) {
        totalxp += xp;
        calculateLevel(totalxp);
    }

    abstract void calculateLevel(int totalxp);

    int getBaseHP() { return BaseHP.get(lvl); }
    int getBaseMP() { return BaseMP.get(lvl); }
    int getBaseStr() { return BaseStr.get(lvl); }
    int getBaseInt() { return BaseInt.get(lvl); }
    int getSpellCost() { return SpellCost.get(lvl); }
    int getLvl() { return lvl; }
}


class WarriorLevel extends Level {
    private Warrior warrior;

    public WarriorLevel () {
        LevelXP.put(1, 299);
        LevelXP.put(2, 899);
        LevelXP.put(3, 2699);
        LevelXP.put(4, 6499);
        LevelXP.put(5, 13999);
        LevelXP.put(6, 10000000);   // upper limit, gia na min vgazei error h calculateLevel
        BaseHP.put(1, 30);
        BaseHP.put(2, 40);
        BaseHP.put(3, 50);
        BaseHP.put(4, 60);
        BaseHP.put(5, 80);
        BaseHP.put(6, 100);
        BaseStr.put(1, 10);
        BaseStr.put(2, 15);
        BaseStr.put(3, 25);
        BaseStr.put(4, 30);
        BaseStr.put(5, 40);
        BaseStr.put(6, 50);

        totalxp = 0;
        lvl = 1;
    }

    public void setWarrior(Warrior w) { this.warrior = w; }

    void calculateLevel(int totalxp) {
        if (totalxp > LevelXP.get(lvl)) {
            warrior.updateBaseStates(BaseHP.get(lvl+1)-BaseHP.get(lvl), BaseStr.get(lvl+1)-BaseStr.get(lvl));
            lvl += 1;
            calculateLevel(totalxp);
        }
    }
}

class WizardLevel extends Level {
    private Wizard wizard;

    public WizardLevel () {
        LevelXP.put(1, 299);
        LevelXP.put(2, 899);
        LevelXP.put(3, 2699);
        LevelXP.put(4, 6499);
        LevelXP.put(5, 13999);
        LevelXP.put(6, 10000000);   // upper limit, gia na min vgazei error h calculateLevel
        BaseHP.put(1, 15);
        BaseHP.put(2, 20);
        BaseHP.put(3, 25);
        BaseHP.put(4, 30);
        BaseHP.put(5, 35);
        BaseHP.put(6, 50);
        BaseMP.put(1, 40);
        BaseMP.put(2, 50);
        BaseMP.put(3, 60);
        BaseMP.put(4, 80);
        BaseMP.put(5, 100);
        BaseMP.put(6, 120);
        BaseInt.put(1, 20);
        BaseInt.put(2, 25);
        BaseInt.put(3, 30);
        BaseInt.put(4, 40);
        BaseInt.put(5, 55);
        BaseInt.put(6, 80);
        SpellCost.put(1, 5);
        SpellCost.put(2, 5);
        SpellCost.put(3, 8);
        SpellCost.put(4, 8);
        SpellCost.put(5, 10);
        SpellCost.put(6, 12);

        totalxp = 0;
        lvl = 1;
    }

    public void setWizard(Wizard w) { this.wizard = w; }

    void calculateLevel(int totalxp) {
        if (totalxp > LevelXP.get(lvl)) {
            wizard.updateBaseStates(BaseHP.get(lvl+1)-BaseHP.get(lvl), BaseMP.get(lvl+1)-BaseMP.get(lvl), BaseInt.get(lvl+1)-BaseInt.get(lvl));
            lvl += 1;
            calculateLevel(totalxp);
        }
    }
}
