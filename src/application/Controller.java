package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent; 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;      
import javafx.scene.control.TextField;    
import javafx.stage.Stage;  
import javafx.scene.Node;  

 
public class Controller 
{  
	@FXML
	ComboBox<String> combobx;
	
	@FXML public TextField mangaBox;       
	    
	@FXML public TextField chapterBox;
	                  
	@FXML public TextField sourceList; 
	
	public static String boxC;
	     
	//@FXML public ProgressBar bar;     
  
	//@FXML public MenuButton panda;     
	
	@FXML  
	public void switcher(ActionEvent event) throws IOException
	{
		Parent rooot = FXMLLoader.load(getClass().getResource("ReaderPage.fxml"));
		Scene library = new Scene(rooot);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    		      
		window.setScene(library);          
		window.show();
	} 
	  
	@FXML  
	public void switcher4(ActionEvent event) throws IOException
	{
		Parent rooot = FXMLLoader.load(getClass().getResource("readerPicker.fxml"));
		Scene library = new Scene(rooot);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    		
		window.setScene(library);               
		window.show();
	} 
	@FXML
	public void pressB(ActionEvent event) 
	{  
 		 
	//	String option = panda.getText();
	//	System.out.println(option);     
		    
		//String text = mangaBox.getText();    
		String chapter = chapterBox.getText();      
		String source = sourceList.getText();     
		int chapt = Integer.parseInt(chapter);  
		

		String Bc = getBoxC(); 
		
		String thing = Bc.toLowerCase();          
		String nrep = thing.replace(" ", "-");  
		String nrep2 = nrep.replaceAll("\\[(.*?)\\]", ""); 
		String nrep3 = nrep2.replace("/", ""); 
		String nrep4 = nrep3.replace("!",""); 
 		
		mangaFinder.mkURL(source, chapt, nrep4); 

		chapterBox.clear();
		sourceList.clear();  
		mangaBox.clear();   
   
				  
	}    
	 
		public static String getBoxC()
		{
		return boxC;
		} 

		public static void setBoxC(String boxC) 
		{
			Controller.boxC = boxC;
		}


		
		@FXML
		public void initialize() throws IOException {
		    //Read items from txt File
			
			BufferedReader br = new BufferedReader(new FileReader("/Users/moehuwio/Desktop/mangaList.txt"));
		    try 
		    {
		        StringBuilder sb = new StringBuilder();  
		        String line = br.readLine();     
		        line = line.toLowerCase();
		        
		        while (line != null)      
		        {
		            //Add Item          
		            combobx.getItems().add(line);   
		            
  
		            sb.append(line);
		            line = br.readLine();  
		        }         
		    } 
		    finally  
		    {    
		        br.close();    
		    }  

		    //Default Selection first item        
		   //combobx.setEditable(true);
		   combobx.getSelectionModel();

		   new ComboBoxAutoComplete<String>(combobx); 
 
		   
 
		   //combobx.setOnAction(null);
		   //String rat = combobx.getValue();
		   //setBoxC(rat);    
		   // String rat = combobx.getAccessibleText();        
		   // System.out.println(rat);
		}
		
		@FXML
		public void Csender(ActionEvent event) 
		{
			String rat = combobx.getValue();
 			setBoxC(rat);
		}
		
} 
  