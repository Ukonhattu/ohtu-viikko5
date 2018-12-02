/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

/**
 *
 * @author daniel
 */
public class Player {

    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int Score) {
        this.score = Score;
    }

    public Player(String name, int Score) {
        this.name = name;
        this.score = Score;
    }

    public void addPoint() {
        this.score++;

    }

    public String getScoreString() {
        String scoreString = "";
        switch (this.score) {
            case 0:
                scoreString += "Love";
                break;
            case 1:
                scoreString += "Fifteen";
                break;
            case 2:
                scoreString += "Thirty";
                break;
            case 3:
                scoreString += "Forty";
                break;
        }
        return scoreString;
    }
}
