package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MyImageView extends ImageView
{
	public String imagePath;
	
	public MyImageView(Image image)
	{
		setImage(image);
	}

	public String getImagePath() 
	{
		return imagePath;
	}

	public void setImagePath(String imagePath) 
	{
		this.imagePath = imagePath;
	}
}           
  