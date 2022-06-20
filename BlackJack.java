package blackjack;

import java.util.Scanner;

public class BlackJack {

    static Game game = new Game();

    public static String scores() {
        int winner = -1;
        int winnerCnt = 0;
        int blackJack = 0;
        int blackJackWinner = -1;
        for (int i = 0; i < 3; i++) {
            // see if any of the players has a BLACKJACK
            if (game.players[i].isBlackJ()) {
                blackJack++;
                blackJackWinner = i;
            }
            // calculate how many players got the VALID maxScore
            else if (game.players[i].getScore() == game.getMaxScore()) {
                winnerCnt++;
                winner = i;
            }
        }
        if (blackJack != 0 && game.players[3].isBlackJ()) // if any player and the dealer both has a BLACKJACK
            return "ITS A PUSH ---- TIE";

        else if (game.players[3].isLost() && blackJack != 0) { // if the dealer doesn't have a BLACKJACK but some player(s) do(es)
            if (blackJack == 1) // one player has a BLACKJACK
                return game.players[blackJackWinner].getName();
            else return "ITS A PUSH ---- TIE"; //more than one player has a BLACKJACK
        }

        else if ((game.players[3].isBlackJ() && blackJack == 0)) // if the dealer has a BLACKJACK while none of the players do
            return "DEALER WINS";


        else if (!game.players[3].isLost() && game.players[3].getScore() > game.getMaxScore()) // if none has a BLACKJACK and the dealer surpassed maxScore while not BUSTING
            return "DEALER WINS";

        else if (winnerCnt == 1) // if the dealer is busted and only one player has the maxScore
            return game.players[winner].getName();

        else return "ITS A PUSH ---- TIE"; // if the dealer is busted but more than one player has the maxScore
    }

    public static void main(String[] args) {
        GUI gui = new GUI();

        game.fill(); // fill card deck
        game.setPlayers(); // set players' and dealer's info


        gui.runGUI(game.deck, game.players[0].cards, game.players[1].cards, game.players[2].cards, game.players[3].cards);

        //players' turns START THE GAME
        for (int i = 0; i < 3; i++) {
            int indexOfCards = 2;

            System.out.println();
            System.out.println(game.players[i].getScore());

            System.out.println("hit or stand?");

            Scanner sc = new Scanner(System.in);
            String play = sc.next();

            while (play.equals("hit")) {

                indexOfCards++;

                Card card = game.draw();

                game.players[i].cards[indexOfCards] = card;

                gui.updatePlayerHand(card, i);

                game.players[i].setScore(game.players[i].getScore() + game.players[i].cards[indexOfCards].getValue());

                System.out.println();
                System.out.println(game.players[i].getScore());

                if (game.players[i].getScore() > 21) {
                    game.players[i].setLost(true);

                    System.out.println();
                    System.out.println("BUSTED");
                    System.out.println();

                    break;
                }


                if (game.players[i].getScore() == 21) {
                    game.players[i].setBlackJ(true);

                    System.out.println();
                    System.out.println("BLACKJACK");
                    System.out.println();

                    break;
                }

                game.calcMax();

                System.out.println("hit or stand?");
                play = sc.next();
            }
        }


        game.calcMax();

        //dealer's turn if his score is less than or equals maxScore of all players

        System.out.println();
        System.out.println(game.players[3].getScore());
        System.out.println();

        if (game.players[3].getScore() > game.getMaxScore()) {
            System.out.println("WINNER");

        } else {

            int indexOfCards = 2;


            while (true) {

                indexOfCards++;

                Card card = game.draw();

                game.players[3].cards[indexOfCards] = card;

                gui.updateDealerHand(card, game.deck);

                game.players[3].setScore(game.players[3].getScore() + game.players[3].cards[indexOfCards].getValue());

                System.out.println();
                System.out.println(game.players[3].getScore());


                if (game.players[3].getScore() > 21) {
                    game.players[3].setLost(true);

                    System.out.println();
                    System.out.println("BUSTED");
                    System.out.println();

                    break;
                }


                if (game.players[3].getScore() == 21) {
                    game.players[3].setBlackJ(true);

                    System.out.println();
                    System.out.println("BLACKJACK");
                    System.out.println();

                    break;
                }

                if (game.players[3].getScore() > game.getMaxScore()) {

                    System.out.println();
                    System.out.println("WINNER");
                    System.out.println();

                    break;
                }

            }
        }

        // print winner
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("WINNER IS: " + scores());
    }
}
