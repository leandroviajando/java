package tennis;

/**
 * Represents a state where one player has an advantage.
 */
public class AdvantageState implements TennisGameState {
  private final boolean serverHasAdvantage;

  /**
   * Creates a new advantage state.
   * 
   * @param serverHasAdvantage true if server has advantage, false if receiver has
   *                           advantage
   */
  public AdvantageState(boolean serverHasAdvantage) {
    this.serverHasAdvantage = serverHasAdvantage;
  }

  /**
   * Returns whether the server has the advantage.
   * 
   * @return true if server has advantage, false if receiver has advantage
   */
  public boolean hasServerAdvantage() {
    return serverHasAdvantage;
  }

  @Override
  public TennisGameState serverWinsRally() {
    if (serverHasAdvantage) {
      return new WonState(true); // Server wins
    } else {
      return new RegularState(3, 3); // Deuce
    }
  }

  @Override
  public boolean equals(Object obj) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public int hashCode() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public String toString() {
    return serverHasAdvantage ? "Advantage(Server)" : "Advantage(Receiver)";
  }
}
