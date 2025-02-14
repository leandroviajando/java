package tennis;

/**
 * Represents a state where one player has won the game.
 */
public class WonState implements TennisGameState {
    private final boolean serverWon;

    /**
     * Creates a new won state.
     * 
     * @param serverWon true if server won, false if receiver won
     */
    public WonState(boolean serverWon) {
        this.serverWon = serverWon;
    }

    /**
     * Returns whether the server won the game.
     * 
     * @return true if server won, false if receiver won
     */
    public boolean didServerWin() {
        return serverWon;
    }

    @Override
    public TennisGameState serverWinsRally() {
        throw new IllegalStateException("Game is already over");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof WonState))
            return false;
        WonState other = (WonState) obj;
        return serverWon == other.serverWon;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(serverWon);
    }

    @Override
    public String toString() {
        return serverWon ? "Won(Server)" : "Won(Receiver)";
    }
}
