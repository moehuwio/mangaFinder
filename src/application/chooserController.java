package application;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
                 
public class chooserController 
{   
	@FXML
	public TextField manga;       
	@FXML 
	public TextField chapterBox;            
	
 	public static String pathInfoo; 
  	
 	
	public File file = new File("/Users/moehuwio/Desktop");
	      
	public String[] directories = file.list(new FilenameFilter()
	{
	                 
	  @Override 
	  public boolean accept(File current, String name) 
		  { 
	    return new File(current, name).isDirectory();
	  }         
	}); 
	
	
	public static String getPathInfoo() 
	{            
		return pathInfoo;
	}


       
	public void setPathInfoo(String pathInfoo) 
	{
		chooserController.pathInfoo = pathInfoo;
	}

	@FXML
	public void pressB(ActionEvent event)       
	{ 
 		
	//	String option = panda.getText(); 
	//	System.out.println(option);     
		
		readerController stuff = new readerController();
		String[] locArr = stuff.directories; 
		//System.out.println(Arrays.toString(locArr));
       
		
		
		String text = manga.getText();    
		String textt = text.replace(" ","-"); 
		
		
		String chapter = chapterBox.getText();      
 		int chapt = Integer.parseInt(chapter); 
		
 		String fName = textt + " Chapter " + chapt;     
		
        List<String> list = Arrays.asList(locArr); 
        
        if(list.contains(fName))  
        {                         
        	setPathInfoo(fName);
        	System.out.println("**********"+fName+"**********");
        }   
        

 		
 		chapterBox.clear();
 		manga.clear();      
 		readerController rc = new readerController(); 
 		rc.initialize(null, null); 
  
 	}             
	        
	@FXML      
	public void switcher3(ActionEvent event) throws IOException
	{
		
		Parent rooot = FXMLLoader.load(getClass().getResource("userSide.fxml"));
		Scene library = new Scene(rooot);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(library);  
		window.show();       
	} 
	 
	@FXML      
	public void switcher5(ActionEvent event) throws IOException
	{
		
		Parent rooot = FXMLLoader.load(getClass().getResource("ReaderPage.fxml"));
		Scene library = new Scene(rooot);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(library);  
		window.show();      
	} 
	
	
	
}
