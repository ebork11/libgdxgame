package com.apcs.game.rooms;

public class LevelGeneration {
    private static Room[][] level1;

    public LevelGeneration() {
        level1 = new Room[6][6];
        loadLevel1();
    }

    public void loadLevel1() {
        int numRooms = 0;

        StartingRoom start = new StartingRoom();
        RoomManager.setCurrentRoom(start);

        level1[3][3] = start;

        int row, col;

        while (numRooms < 10) {
            row = (int)(Math.random() * level1.length);
            col = (int)(Math.random() * level1.length);

            if (level1[row][col] == null) {
                if (((row != 0 && level1[row - 1][col] != null) || (col != 0 && level1[row][col - 1] != null)) || ((row + 1) != level1.length && level1[row + 1][col] != null) || ((col + 1) != level1.length && level1[row][col + 1] != null)) {
                    level1[row][col] = new DefaultRoom(); // adds room if there is a room on one of four sides next to it
                    numRooms++;
                }
            }
        }

        for (int cnt = 0; cnt < level1[0].length; cnt++) {
            for (int cnt2 = 0; cnt2 < level1.length; cnt2++) {
                if (level1[cnt][cnt2] != null) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }

        addDoors(level1);
    }

    public void addDoors(Room[][] level) {
        for (int col = 0; col < level[0].length; col++) {
            for (int row = 0; row < level.length; row++) {
                if (level[row][col] != null && row != 0 && level[row - 1][col] != null) {
                    level[row][col].getDoors().add(new Door(level[row - 1][col], "top"));
                    level[row - 1][col].getDoors().add(new Door(level[row][col], "bottom"));
                } if (level[row][col] != null && col != 0 && level[row][col - 1] != null) {
                    level[row][col].getDoors().add(new Door(level[row][col - 1], "left"));
                    level[row][col - 1].getDoors().add(new Door(level[row][col], "right"));
                }
            }
        }
    }

    public static Room[][] getLevel() {
        return level1;
    }


}
