/**
 * Class name: Pazaak AI
 * purpose: to go down a tree to define what the AI does. 
 * @author Elijah Bonack
 */
package pazaakGame;

import java.util.ArrayList;
import pazaakMain.Card;

public class PazaakAI {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private int compScore;
	private int roundCountPlayer;
	private int roundCountComp;
	public boolean isStand = false;
	private boolean playerStand = false;
	private Card AICard = null;
	private boolean checkedOnce = false;

	// Constructor to store the cards list
	public PazaakAI(ArrayList<Card> cardsList, int compScore, int roundCountP, int roundCountC) {
		this.cards = cardsList;
		this.compScore = compScore;
		this.roundCountPlayer = roundCountP;
		this.roundCountComp = roundCountC;
	}

	public Card computerPath(int points) {
		AICard = null;
		if (points > 20 && compScore <= 20 && playerStand == true) {
			isStand = true;
			return null;
		}
		if (compScore > 20 && points <= 20) {
			for (int i = 0; i < cards.size(); i++) {
				if (roundCountComp == 0) { // Depending on round score, AI will be more aggressive
					if (cards.get(i).getValue() + compScore >= points && cards.get(i).getValue() + compScore <= 20) {
						if (AICard != null) {
							if (AICard.getValue() > cards.get(i).getValue() && cards.get(i).getValue() + compScore <= 20) {
								break;
							}
						}

						AICard = cards.get(i);
					}
				} else if (roundCountComp == 1) {
					if (cards.get(i).getValue() + compScore <= 20 && cards.get(i).getValue() + compScore >= points) {
						AICard = cards.get(i);
					} else if (cards.get(i).getValue() + compScore >= points && playerStand == true) {
						AICard = cards.get(i);
					}
				}

			}
			if (AICard == null && checkedOnce == true) {
				checkedOnce = false;
				isStand = true;
				return AICard;
			}
			if (AICard == null) {
				checkedOnce = true;
				return AICard;
			}
			if (AICard.getValue() + compScore > 20) {
				isStand = true;
				return null;
			}
			if (playerStand == true) {
				isStand = true;
				return AICard;
			} else if (AICard.getValue() + compScore <= 20) {
				isStand = true;
				return AICard;
			} else if (AICard.getValue() + compScore >= points && points <= 20 && playerStand == true) {
				isStand = true;
				return AICard;
			}

		} else if (compScore < 20 && points <= 20) {
			for (int i = 0; i < cards.size(); i++) {
				if (roundCountPlayer == 0 || roundCountComp == 0) {
					if (cards.get(i).getValue() + compScore == 20) {
						AICard = cards.get(i);
					} else if (cards.get(i).getValue() + compScore <= 20
							&& cards.get(i).getValue() + compScore >= points && playerStand == true
							&& compScore >= 14) {
						if(AICard != null) {
							if (AICard.getValue() > cards.get(i).getValue()) {
								break;
							}
						}
						
						AICard = cards.get(i);
					}
				} else if (roundCountPlayer == 1 || roundCountComp == 1) {
					if (cards.get(i).getValue() + compScore == 20) {
						if(AICard != null) {
							if (AICard.getValue() > cards.get(i).getValue()) {
								break;
							}
						}
						AICard = cards.get(i);
					} else if (cards.get(i).getValue() + compScore <= 20
							&& cards.get(i).getValue() + compScore >= points && playerStand == true
							&& compScore >= 14) {
						if(AICard != null) {
							if (AICard.getValue() > cards.get(i).getValue()) {
								break;
							}
						}
						AICard = cards.get(i);
					}
				} else if (roundCountPlayer == 2 || roundCountComp == 2) {
					if (cards.get(i).getValue() + compScore == 20) {
						if(AICard != null) {
							if (AICard.getValue() > cards.get(i).getValue()) {
								break;
							}
						}
						AICard = cards.get(i);
					} else if (cards.get(i).getValue() + compScore <= 20
							&& cards.get(i).getValue() + compScore >= points && playerStand == true
							&& compScore >= 14) {
						if(AICard != null) {
							if (AICard.getValue() > cards.get(i).getValue()) {
								break;
							}
						}
						AICard = cards.get(i);
					}
				}

			}
			if (AICard == null) {
				if (compScore == points && playerStand == true) {
					isStand = true;
					return AICard;
				}
				return AICard;
			}

			if (AICard.getValue() + compScore == 20) {
				isStand = true;
				return AICard;
			} else if (AICard.getValue() + compScore >= points && points <= 20 && playerStand == true) {
				isStand = true;
				return AICard;
			}

		} else if (compScore == 20) {
			isStand = true;
			return null;
		}

		return null;
	}

	public void setPoints(int points) {
		this.compScore = points;
	}

	public void setCards(ArrayList<Card> card) {
		this.cards = card;
	}

	public void setRoundCounts(int roundCountP, int roundCountC) {
		this.roundCountPlayer = roundCountP;
		this.roundCountComp = roundCountC;
	}

	public void checkPlayerStand(boolean stand) {
		this.playerStand = stand;
	}

}
