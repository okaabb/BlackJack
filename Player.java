package blackjack;

public class Player {
    private final String Name;
    private int Score;
    Card[] cards = new Card[11];
    private boolean blackJ = false;
    private boolean lost = false;


    public Player(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getScore() {
        return Score;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public void setBlackJ(boolean blackJ) {
        this.blackJ = blackJ;
    }

    public boolean isLost() {
        return lost;
    }

    public boolean isBlackJ() {
        return blackJ;
    }
}
