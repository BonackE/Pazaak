/**
 * Class name: SaveFormatter
 * purpose: To output a save file that can be loaded in the card menu
 * @author Elijah Bonack
 */
package pazaakMain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;



public class SaveFormatter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int num = 0;
	public SaveFormatter(ArrayList<Card> cards, String saveFile) {
		try (FileOutputStream startDeck = new FileOutputStream(saveFile);
				ObjectOutputStream deck = new ObjectOutputStream(startDeck)) {
			
			for(int i = 0; i< cards.size(); i++) {
				deck.writeUTF(cards.get(i).getLocation());
				deck.writeObject(cards.get(i).getValue());
				System.out.println(num);
				this.num ++;
				
			}
			
			deck.close();
			
			System.out.println("an");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public int getNum() {
		return num;
	}

}
