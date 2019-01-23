package application;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import java.util.*;

public class readerController implements Initializable
{

	File file = new File("/Users/moehuwio/Desktop");
	String[] directories = file.list(new FilenameFilter()
	{
	                 
	  @Override 
	  public boolean accept(File current, String name) 
		  { 
	    return new File(current, name).isDirectory();
	  }     
	});
	@FXML  
	public StackPane imageStack; 	  
	@FXML 
	public ScrollPane imageSlider;
	@FXML
	public TextField manga;       
	@FXML 
	public TextField chapterBox;           
	 
	  
 	public String pathInfoo; 
 	
 	public static int chaptPass; 
 	public static String mangaPass;  
 	public static String sourcePass;
	
	   
 	
 	
 	@FXML      
	public void switcher2(ActionEvent event) throws IOException
	{
		
		Parent rooot = FXMLLoader.load(getClass().getResource("userSide.fxml"));
		Scene library = new Scene(rooot);
		 
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(library);  
		window.show();  
		  
	} 
 	
 	
 	public static int getChaptPass() 
 	{
		return chaptPass;
	}


	public void setChaptPass(int chaptPass) 
	{
		readerController.chaptPass = chaptPass;
		System.out.println("setting chapter pass to" + readerController.chaptPass);
	}


	public static String getMangaPass() 
	{
		return mangaPass;
	}


	public void setMangaPass(String mangaPass) 
	{
		readerController.mangaPass = mangaPass;
		System.out.println("setting manga pass to" + readerController.mangaPass);

	}


	public static String getSourcePass()   
	{              
		return sourcePass;
	}


	public void setSourcePass(String sourcePass)  
	{
		readerController.sourcePass = sourcePass;
		System.out.println("setting src pass to" + readerController.sourcePass);
	}


	
	@FXML
	public void callNewChapt(ActionEvent event) 
	{
 		//int chaptP = getChaptPass(); 
 		//String mangaP = getMangaPass();
 		//String sourceP = getSourcePass(); 
 		
 		//System.out.println(chaptP +mangaP +sourceP);
	
 	
  		mangaFinder.nextChapt();
  	}
	               
	
	
	 
	                     
	            
	//public void miniDb()  
	//{
		            
		//System.out.println(Arrays.toString(directories));
	//	hub(directories);  
	//}	
	
	/*this is major shit code this may or may not mean anything
	public String getPathInfo() 
	{
		return pathInfo;   
	}
	
	public void setPathInfo(String pathInfo) 
	{
 		
		this.pathInfo2 = pathInfo;
		this.pathInfo = pathInfo; 
	}
	ignore this fucking code as it is a work in progress 
	*/
	
	@FXML
	public void pressB(ActionEvent event) 
	{ 
 		
	//	String option = panda.getText(); 
	//	System.out.println(option);     
		
		readerController stuff = new readerController();
		String[] locArr = stuff.directories; 
		//System.out.println(Arrays.toString(locArr));
       
		
		
		String text = manga.getText();    
		String chapter = chapterBox.getText();      
 		int chapt = Integer.parseInt(chapter); 
		
 		String fName = text + " Chapter " + chapt;     
		 
        List<String> list = Arrays.asList(locArr); 
        
        if(list.contains(fName))
        {
        	this.pathInfoo = fName;
        	System.out.println("**********"+fName+"**********");
     		//initialize(null, null);

        }   
         

 		
 		chapterBox.clear();
 		manga.clear();   
 	}            
	
	
	//@Override
	public void initialize(URL url, ResourceBundle rb)      
	{
		
		try 
		{
			String path;

		//String tryy = this.pathInfo2; 
		 		   
		//readerController stuff = new readerController();
		//System.out.println(stuff.getPathInfo()); 
		//System.out.println(tryy);

		         
		/*String[] trial = directories; 
		System.out.println(Arrays.toString(trial));
		
		System.out.println(Arrays.toString(directories));
		      
		miniDb();
		*/
		  
			//chooserController stuff = new chooserController();
			//System.out.println("--------"+stuff.pathInfoo+"-------------"); 
			String placer = chooserController.getPathInfoo();
			   
			
			if(placer != null)  
			{	                    
				path = "/Users/moehuwio/Desktop/" + placer;	
				System.out.println("------------"+path+"-----------");		
			}                     
			
			 
			else  
			{ 
				path = mangaFinder.getSendP();  
			} 
		   
			System.out.println("--------"+path+"--------");  
			File dir = new File(path);           
			File[] fList = dir.listFiles();         
			
			Arrays.sort(fList, new Comparator<File>()
			{     
			    public int compare(File f1, File f2)
			    {
			        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
			    } 
			});
			      
			
			TilePane tp = new TilePane();    
			tp.setHgap(5);            
			tp.setVgap(5);
			tp.setMaxWidth(200);                 
		
			for(int i = 0; i < fList.length; i++)
			{
				Image img = new Image(fList[i].toURI().toString());
				MyImageView imgV = new MyImageView(img);
				imgV.setImagePath(fList[i].toURI().toString());  
			
				imgV.setFitHeight(200);   
				imgV.setFitWidth(200);                 
				imgV.setPreserveRatio(true);    
				tp.getChildren().addAll(imgV); 
			     
				addEvent(imgV);
			}  
			imageSlider.setContent(tp);     
		}
		catch(Exception e) {}
		 
	}                        
	
	public void addEvent(ImageView img)           
	{
		try 
		{
			// this is some fucking test code!!!!!  this just prints out the array shit 
			
			//readerController hey = new readerController();
			//hey.miniDb();
			
			// hopefully what i can do is add an if else then choose the path based on some shit
			
			//miniDb();		
			img.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() 
			{ 
				@Override 
				public void handle(MouseEvent event)        
				{
					MyImageView imgV = (MyImageView) event.getSource();				 
					Image img = new Image(imgV.getImagePath());
					ImageView mainImgV = new ImageView(img);
					mainImgV.setFitHeight(1485);     
					mainImgV.setFitWidth(540);   
					mainImgV.setPreserveRatio(true);  
					imageStack.getChildren().clear();
					imageStack.getChildren().add(mainImgV);
					event.consume(); 
				}               
			});         
		} 
		catch (Exception e) {}        
	}  
	    
	       
	
}

      