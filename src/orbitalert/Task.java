package orbitalert;

/**
 *
 * @author Aaron
 */
public abstract class Task {
    private boolean isSolved;
    private int difficulty;

    public boolean getIsSolved(){
        return this.isSolved;
    }
    private int getDifficulty(){
        return this.difficulty;
    }
}
