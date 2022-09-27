/**
 * Class name: Card
 * @author elija
 * Purpose: To make a new card with it's own class
 * Subclass of Gridpane to make values be attached to image
 */
package pazaakMain;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.*;
import javafx.scene.layout.GridPane;
public class Card extends GridPane implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image cardImage;
	private int value;
	private String cardLocation = "";
	private ImageView imageViewCard = new ImageView();
	public Card(Image image, int value) {
		this.cardImage = image;
		this.value = value;
		this.imageViewCard.setImage(image);
		imageViewCard.setFitWidth(60);
		imageViewCard.setFitHeight(85);
		getChildren().addAll(imageViewCard);
	}
	
	public Image getCard() {
		return cardImage;
	}

	public int getValue() {
		return value;
	}
	public ImageView getImageView() {
		return imageViewCard;
	}
	public void setLocation(String s) {
		this.cardLocation = s;
	}
	public String getLocation() {
		return cardLocation;
	}
	public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		cardImage = SwingFXUtils.toFXImage(ImageIO.read(s),null);
	}
	public void writeObject(ObjectOutputStream s) throws IOException{
		s.defaultWriteObject();
		ImageIO.write(SwingFXUtils.fromFXImage(cardImage, null), "png", s);
	}

	
}
