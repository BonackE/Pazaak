/**\
 * Class name: Cards (not to be confused with Card)
 * @author Elijah Bonack
 * Purpose: to generate card decks, a lot unused
 */
package pazaakMain;

import java.util.ArrayList;

import javafx.scene.image.Image;



public class Cards{

	private ArrayList<Card> plusCards = new ArrayList<Card>();
	private ArrayList<Card> minusCards = new ArrayList<Card>();


	private ArrayList<Card> mainCards = new ArrayList<Card>();
	private ArrayList<Card> playerCards = new ArrayList<Card>();
	private ArrayList<Card> starterDeck = new ArrayList<Card>();
	private ArrayList<Card> allCards = new ArrayList<Card>();
	private static ArrayList<Integer> cardCount = new ArrayList<Integer>(18);

	public ArrayList<Card> playerDeck() {
		for (int i = 0; i <= starterDeck.size() - 1; i++) {
			playerCards.add(starterDeck.get(i));
		}

		return playerCards;

	}

	public ArrayList<Card> starterDeck() {
		for (int i = 0; i < 12; i++) {
				cardCount.set(i, 2);
			}
		for (int i = 0; i < 6; i++) {
				starterDeck.add(plusCards.get(i));
			}
		for (int i= 0; i< 6; i++) {
			starterDeck.add(minusCards.get(i));
		}

		


		return starterDeck;
	}

	public ArrayList<Card> plusCardDeck() {
		for (int i = 1; i <= 6; i++) {
			
			Card plusCard = new Card(new Image(getClass().getResource("/Cards/plus" + i + ".png").toExternalForm()), i);
			plusCard.setLocation("/Cards/plus" + i + ".png");
			plusCards.add(plusCard);
			allCards.add(plusCard);

		}

		return plusCards;
	}

	public ArrayList<Card> minusCardDeck() {
		for (int i = 1; i <= 6; i++) {
			Card minusCard = new Card(new Image(getClass().getResource("/Cards/minus" + i + ".png").toExternalForm()),
					i * -1);
			minusCard.setLocation("/Cards/minus" + i + ".png");
			minusCards.add(minusCard);
			allCards.add(minusCard);
		}

		return minusCards;
	}



	public ArrayList<Card> mainCards() {
		for (int i = 1; i <= 10; i++) {
			Card mainCard = new Card(new Image(getClass().getResource("/Cards/main" + i + ".png").toExternalForm()), i);
			mainCards.add(mainCard);
		}
		return mainCards;
	}

	public ArrayList<Card> allCards() {
		return allCards;
	}

	public ArrayList<Integer> getCardCount() {

		for (int i = 0; i <= 17; i++) {
			cardCount.add(0);
		}

		return cardCount;
	}

}
