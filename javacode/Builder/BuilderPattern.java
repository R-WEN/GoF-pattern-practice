interface Maze {
    public void paint();
}

interface MazeBuilder {
    void buildRoad(int i, int j);
    void buildWall(int i, int j);
    void buildTreasure(int i, int j);
    Maze getMaze();
}

class MazeDirector {
    private int[][] maze;
    private MazeBuilder builder;
    
    MazeDirector(int[][] maze, MazeBuilder builder) {
    	this.maze = maze;
    	this.builder = builder;
    }
    
    Maze build() {
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                switch (maze[i][j]) {
                    case 0:
                    	builder.buildRoad(i, j);
                        break;
                    case 1:
                    	builder.buildWall(i, j);
                        break;
                    case 2:
                    	builder.buildTreasure(i, j);
                        break;
                }
            }
        }
        return builder.getMaze();
    }
}

class PlainBuilder implements MazeBuilder {
    private char[][] maze;
	
    PlainBuilder(int i, int j) {
	this.maze = new char[i][j];
    }
    public void buildRoad(int i, int j) {
        maze[i][j] = '　';
    }
    public void buildWall(int i, int j) {
        maze[i][j] = '□';
    }
    public void buildTreasure(int i, int j) {
        maze[i][j] = '★';
    }

    @Override
    public Maze getMaze() {
	return new Maze() {
		@Override
		public void paint() {
                    for(char[] row : maze) {
                        for(char ele : row) {
                            System.out.print(ele);
                        }
                        System.out.println();
                    }
		}
             };
    }
}

public class BuilderPattern {
    public static void main(String[] args) {
        final int[][] material = {
                {1, 1, 1, 1, 1, 1, 1}, 
                {1, 0, 0, 0, 0, 2, 1}, 
                {1, 0, 1, 0, 1, 0, 1}, 
                {1, 0, 2, 1, 0, 1, 1}, 
                {1, 1, 0, 1, 0, 1, 1}, 
                {1, 0, 0, 2, 0, 0, 1}, 
                {1, 1, 1, 1, 1, 1, 1}};

        MazeDirector director = new MazeDirector(material, 
                new PlainBuilder(material.length, material[0].length));
        director.build().paint();
    }
}