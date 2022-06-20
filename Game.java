package blackjack;

import java.util.Random;
import java.util.Scanner;

public class Game {
    Player[] players = new Player[4];
    Card[] deck = new Card[53];
    private int maxScore = 0;

    public int getMaxScore() {
        return maxScore;
    }

    public void fill() {
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0, value = 1; j < 13; value++, j++) {
                if (value > 10)
                    value = 10;
                Card temp = new Card(i, j, value);
                deck[index] = temp;
                index++;
            }
        }
    }

    public Card draw() {
        Random random = new Random();
        int RandomChoice = random.nextInt(52);
        while (deck[RandomChoice] == null) {
            RandomChoice = random.nextInt(52);
        }
        Card card = new Card(deck[RandomChoice].getSuit(), deck[RandomChoice].getRank(), deck[RandomChoice].getValue());
        deck[RandomChoice] = null;
        return card;
    }

    public void setPlayers() {
        for (int i = 0; i < 4; i++) {
            System.out.println("enter name");
            Scanner sc = new Scanner(System.in);
            String Pname = sc.next();
            Player temp = new Player(Pname);
            for (int j = 0; j < 2; j++) {
                Card card = draw();
                temp.setScore(temp.getScore() + card.getValue());
                temp.cards[j] = card;
            }
            players[i] = temp;
        }
    }


    public void calcMax() {
        int max = 0;
        for (int i = 0; i < 3; i++) {
            if (players[i].getScore() > max && !players[i].isLost())
                max = players[i].getScore();
        }
        maxScore = max;
    }


}
