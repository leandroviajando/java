package tennis;

/**
 * Represents the state of a tennis game.
 */
public interface TennisGameState {
    /**
     * Returns the next state after the server wins a rally.
     * 
     * @return the new state of the game
     */
    TennisGameState serverWinsRally();
}
