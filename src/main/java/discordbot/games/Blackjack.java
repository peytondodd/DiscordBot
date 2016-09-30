package discordbot.games;

import discordbot.games.blackjack.BlackJackHand;
import discordbot.games.card.Card;
import discordbot.main.Config;

import java.util.ArrayList;
import java.util.Collections;

public class Blackjack {

	private final String playerMention;
	private BlackJackHand dealerHand;
	private BlackJackHand playerHand;
	private ArrayList<Card> deck;
	private boolean gameInProgress = true;
	private boolean playerStands = false;

	public Blackjack(String playerMention) {
		this.playerMention = playerMention;

		resetGame();
	}

	/**
	 * Is the game still going?
	 *
	 * @return gamestatus
	 */
	public boolean isInProgress() {
		return gameInProgress;
	}

	public boolean playerIsStanding() {
		return playerStands;
	}

	public String printPlayerHand() {
		return playerHand.printHand();
	}

	public int getPlayerValue() {
		return playerHand.getValue();
	}

	public int getDealerValue() {
		return dealerHand.getValue();
	}

	private Card drawCard() {
		return deck.remove(0);
	}

	public void hit() {
		if (playerStands) {
			return;
		}
		if (playerHand.getValue() == 0) {
			playerHand.add(drawCard());
		}
		playerHand.add(drawCard());
		if (dealerHand.getValue() == 0) {
			dealerHand.add(drawCard());
		}
		if (getPlayerValue() > 21) {
			gameInProgress = false;
		}
	}

	public boolean dealerHit() {
		if (getPlayerValue() <= 21 && getDealerValue() < 21 && getDealerValue() <= getPlayerValue()) {
			dealerHand.add(drawCard());
			return true;
		}
		gameInProgress = false;
		return false;
	}

	public void stand() {
		playerStands = true;
	}

	public void resetGame() {

		dealerHand = new BlackJackHand();
		playerHand = new BlackJackHand();
		deck = Card.newDeck();
		Collections.shuffle(deck);
		gameInProgress = true;
		playerStands = false;
	}

	@Override
	public String toString() {
		StringBuilder game = new StringBuilder("Blackjack game: " + Config.EOL);
		game.append(String.format("Dealers hand (%s):" + Config.EOL, getDealerValue()));
		game.append(dealerHand.printHand()).append(Config.EOL);
		game.append(Config.EOL);
		game.append(String.format("%s's hand (%s):" + Config.EOL, playerMention, getPlayerValue()));
		game.append(playerHand.printHand()).append(Config.EOL);
		if (getPlayerValue() > 21) {
			game.append("**Bust!** I win, better luck next time.").append(Config.EOL);
		} else if (!gameInProgress) {
			game.append(Config.EOL);
			if (getPlayerValue() == getDealerValue()) {
				game.append("Looks like it ended in a draw");
			} else if (getPlayerValue() > getDealerValue() || getDealerValue() > 21) {
				game.append("Alright you win this one.");
			} else {
				game.append("Yey! I win");
			}
		}
		return game.toString();
	}
}
