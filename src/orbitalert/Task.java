package orbitalert;

/**
 *
 * @author Aaron
 */
public abstract class Task {
    private boolean solved;
    private int difficulty;

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    private int getDifficulty(){
        return this.difficulty;
    }
}
