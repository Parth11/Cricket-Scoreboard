package sample;

public class ScoreManager {
    private static int BALLS_PER_OVER = 6;

    private final int ballsPerMatch;

    private int targetRuns;
    private int score;
    private int wickets;
    private int overs;
    private int balls;
    private int remainingBalls;
    private int remainingRunsToWin;

    public ScoreManager(int noOfOvers) {
        ballsPerMatch = noOfOvers * BALLS_PER_OVER;
        remainingBalls = ballsPerMatch;
        targetRuns = 0;
    }

    public ScoreManager(int noOfOvers, int targetRuns) {
        ballsPerMatch = noOfOvers * BALLS_PER_OVER;
        remainingBalls = ballsPerMatch;
        this.targetRuns = targetRuns;
        remainingRunsToWin = targetRuns;
    }

    public void addRuns(int runs) {
        score += runs;
        updateRemainingRuns();
    }

    public void subtractRuns(int runs) {
        if (score - runs >= 0) {
            score -= runs;
            updateRemainingRuns();
        }
    }

    public void decrementRun() {
        if (score == 0) {
            return;
        }
        --score;
        updateRemainingRuns();
    }

    private void updateRemainingRuns() {
        remainingRunsToWin = targetRuns - score;
    }

    public void addWicket() {
        ++wickets;
    }

    public void decrementWicket() {
        if (wickets == 0) {
            return;
        }
        --wickets;
    }

    public void addBall() {
        if(balls + 1 == BALLS_PER_OVER) {
            balls = 0;
            overs++;
        } else {
            ++balls;
        }
        updateRemainingBalls();
    }

    public void decrementBall() {
        if(balls - 1 == -1) {
            if(overs - 1 >= 0) {
                overs--;
                balls = 5;
            }
        } else {
            --balls;
        }
        updateRemainingBalls();
    }

    private void updateRemainingBalls() {
        remainingBalls = ballsPerMatch - (overs * BALLS_PER_OVER + balls);
    }

    public void resetNewTargetRuns(int targetRuns) {
        this.targetRuns = targetRuns;
        updateRemainingRuns();
    }

    public int getTargetRuns() {
        return targetRuns;
    }

    public int getScore() {
        return score;
    }

    public int getWickets() {
        return wickets;
    }

    public int getOvers() {
        return overs;
    }

    public int getBalls() {
        return balls;
    }

    public int getRemainingBalls() {
        return remainingBalls;
    }

    public int getRemainingRunsToWin() {
        return Math.abs(remainingRunsToWin);
    }
}
