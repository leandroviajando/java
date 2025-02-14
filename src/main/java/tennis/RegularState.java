package tennis;

/**
 * Represents a regular state in a tennis game where both players have scores
 * of 0, 15, 30, or 40.
 */
public class RegularState implements TennisGameState {
    private final int serverScore; // 0, 1, 2, or 3 representing 0, 15, 30, 40
    private final int receiverScore; // 0, 1, 2, or 3 representing 0, 15, 30, 40

    /**
     * Creates a new regular state with the given scores.
     * 
     * @param serverScore   server's score (0=0, 1=15, 2=30, 3=40)
     * @param receiverScore receiver's score (0=0, 1=15, 2=30, 3=40)
     * @throws IllegalArgumentException if scores are invalid
     */
    public RegularState(int serverScore, int receiverScore) {
        if (serverScore < 0 || serverScore > 3 || receiverScore < 0 || receiverScore > 3) {
            throw new IllegalArgumentException("Scores must be between 0 and 3");
        }
        this.serverScore = serverScore;
        this.receiverScore = receiverScore;
    }

    /**
     * Returns the server's score.
     * 
     * @return 0 for 0, 1 for 15, 2 for 30, 3 for 40
     */
    public int getServerScore() {
        return serverScore;
    }

    /**
     * Returns the receiver's score.
     * 
     * @return 0 for 0, 1 for 15, 2 for 30, 3 for 40
     */
    public int getReceiverScore() {
        return receiverScore;
    }

    @Override
    public TennisGameState serverWinsRally() {
        if (serverScore == 3) {
            if (receiverScore < 3) {
                return new WonState(true); // Server wins
            } else {
                return new AdvantageState(true); // Server has advantage
            }
        } else if (serverScore == 2 && receiverScore == 3) {
            return new RegularState(3, 3); // Deuce
        } else if (serverScore == 2) {
            return new RegularState(3, receiverScore); // Move to 40
        } else if (serverScore == 1) {
            return new RegularState(2, receiverScore); // Move to 30
        } else {
            return new RegularState(1, receiverScore); // Move to 15
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof RegularState))
            return false;
        RegularState other = (RegularState) obj;
        return serverScore == other.serverScore && receiverScore == other.receiverScore;
    }

    @Override
    public int hashCode() {
        return serverScore * 31 + receiverScore;
    }

    @Override
    public String toString() {
        return String.format("Regular(%s-%s)",
                scoreToString(serverScore),
                scoreToString(receiverScore));
    }

    private String scoreToString(int score) {
        switch (score) {
            case 0:
                return "0";
            case 1:
                return "15";
            case 2:
                return "30";
            case 3:
                return "40";
            default:
                throw new IllegalStateException("Invalid score");
        }
    }
}
