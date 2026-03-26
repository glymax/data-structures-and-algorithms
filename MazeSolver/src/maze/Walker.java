package maze;

public class Walker {

    public enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    private final boolean[][] maze;
    private final Result result;

    public Walker(boolean[][] maze, Result result) {
        this.result = result;
        this.maze = maze;
    }

    public boolean walk() {
        int xLenght = maze.length;
        int yLenght = maze[0].length;
        Direction position = Direction.UP;
        int x = 1;
        int y = 0;
        result.addLocation(x, y);
        do {
            switch (position) {
                case UP: //0
                    if (maze[x - 1][y]) {
                        if (maze[x][y + 1]) {
                            if (maze[x + 1][y]) {
                                result.addLocation(x, y - 1);
                                position = Direction.DOWN; //2
                                y--;
                            } else {
                                result.addLocation(x + 1, y);
                                position = Direction.LEFT; //1
                                x++;
                            }
                        } else {
                            result.addLocation(x, y + 1);
                            y++;
                        }
                    } else {
                        result.addLocation(x - 1, y);
                        position = Direction.RIGHT; //3
                        x--;
                    }
                    break;
                case LEFT:
                    if (maze[x][y + 1]) {
                        if (maze[x + 1][y]) {
                            if (maze[x][y - 1]) {
                                result.addLocation(x - 1, y);
                                position = Direction.RIGHT;
                                x--;
                            } else {
                                result.addLocation(x, y - 1);
                                position = Direction.DOWN;
                                y--;
                            }
                        } else {
                            result.addLocation(x + 1, y);
                            x++;
                        }
                    } else {
                        result.addLocation(x, y + 1);
                        position = Direction.UP;
                        y++;
                    }
                    break;
                case DOWN:
                    if (maze[x + 1][y]) {
                        if (maze[x][y - 1]) {
                            if (maze[x - 1][y]) {
                                result.addLocation(x, y + 1);
                                position = Direction.UP;
                                y++;
                            } else {
                                result.addLocation(x - 1, y);
                                position = Direction.RIGHT;
                                x--;
                            }
                        } else {
                            result.addLocation(x, y - 1);
                            y--;
                        }
                    } else {
                        result.addLocation(x + 1, y);
                        position = Direction.LEFT;
                        x++;
                    }
                    break;
                case RIGHT:
                    if (maze[x][y - 1]) {
                        if (maze[x - 1][y]) {
                            if (maze[x][y + 1]) {
                                result.addLocation(x + 1, y);
                                position = Direction.LEFT;
                                x++;
                            } else {
                                result.addLocation(x, y + 1);
                                position = Direction.UP;
                                y++;
                            }
                        } else {
                            result.addLocation(x - 1, y);
                            x--;
                        }
                    } else {
                        result.addLocation(x, y - 1);
                        position = Direction.DOWN;
                        y--;
                    }
                    break;
            }
        }
        while ((x != xLenght - 1 || y != yLenght - 2) && (x != 1 || y != 0));

        return x != 1 || y != 0;
    }

    public static void main(String[] args) {
        boolean[][] maze1 = Maze.generateMaze(30, 30, 30);
        StudentResult result = new StudentResult();
        Walker walker = new Walker(maze1, result);
        System.out.println(walker.walk());
        Maze.draw(maze1, result);
    }
}
