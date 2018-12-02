package ohtu;

public class TennisGame {

    private Player player1;
    private Player player2;
    private final static int WINPOINT = 4;

    public TennisGame(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name, 0);
        this.player2 = new Player (player2Name, 0);
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName)) {
            player1.addPoint();
        } else {
            player2.addPoint();
        }
    }

    public String getScore() {
        String score = "";
        if (player1.getScore() == player2.getScore()) {
            score = sameScore(player1.getScore());
        } else if (player1.getScore() >= WINPOINT || player2.getScore() >= WINPOINT) {
            score = matchPointOrWin();
        } else {

            score += player1.getScoreString();
            score += "-";
            score += player2.getScoreString();

        }
        return score;
    }


    private String matchPointOrWin() {
        String score;
        int minusResult = player1.getScore() - player2.getScore();
        if (minusResult == 1) {
            score = "Advantage player1";
        } else if (minusResult == -1) {
            score = "Advantage player2";
        } else if (minusResult > 1) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }
        return score;
    }

    private String sameScore(int value) {
        String score;
        switch (value) {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            case 3:
                score = "Forty-All";
                break;
            default:
                score = "Deuce";
                break;

        }
        return score;
    }
}
