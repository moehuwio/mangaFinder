package application;
import java.util.*; 
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import javafx.event.ActionEvent;
import java.io.*;
import java.net.URL;   

public class mangaFinder 
{    
	public static String sendP;   
                          
	   
	public static void nextChapt()
	{
		//System.out.println(chapter + manga + source );
		//int newChapt = chapter + 1;   
		//mkURL(source, newChapt, manga);    
		
		//readerController rc = new readerController();
		
	//	System.out.println("-------------" + rc.getChaptPass() + rc.getMangaPass() + rc.getSourcePass());

		int newChapt = readerController.getChaptPass(); 
		int newChaptt = newChapt + 1;
		String manga = readerController.getMangaPass();   
		String source = readerController.getSourcePass(); 

		
		//System.out.println("_________________"+ manga + source + newChapt );
		mkURL(source, newChaptt, manga);     

	}
	
	
	public static void getURL()  
	{ 
		  
		int chapt;    
		String manga; 		             
		String source;    
		Scanner input = new Scanner(System.in);           
		
		System.out.println    
				( 
				"what source do you want to use?"         
				+ "\ntype 1 for manga panda " 
				+ "\ntype 2 for manga park "
				+ "\ntype 3 for manga reader"     
				); 
		source = input.nextLine();  
		   
		switch(source) 
		{ 
		case "1":  
			source = "mangapanda"; 
			break;  
 		   
		case "2":  
 			source = "mangapark";
 			break;
			       
		case "3":      
 			source = "mangareader";    
 			break;
		}
 		System.out.println("what manga would you like?  ");  
		manga = input.nextLine();    
		
		System.out.println("what chapter are you on?"); 
		chapt = input.nextInt();  
		
		
		      
 		mkURL(source,chapt,manga);         
	}   
	
	public static void mkURL(String source, int chapt, String manga) 
	{ 
		//System.out.println("_______________chapt"+ chapt);     

		readerController RC = new readerController(); 
		RC.setChaptPass(chapt);
		RC.setMangaPass(manga);
		RC.setSourcePass(source);  
		System.out.println(RC.getChaptPass() + " " + RC.getMangaPass() + "" + RC.getSourcePass());
		String URL;  
		
		switch(source)     
		{ 
		case "mangapanda":           
		URL = "https://www." + source + ".com/"+manga+"/"+chapt+"/";  
		getIMG(URL,chapt,manga); 
	//	nextChapt(chapt, manga,source);
		break;  
		          
		case "mangapark": 
			URL = "https://www." + source + ".com/"+manga+"/"+chapt+"/";  
			getIMG(URL,chapt,manga); 
		//	nextChapt(chapt, manga,source);
			break; 
			
		  
		case "mangareader": 
			URL = "https://www." + source + ".net/"+manga+"/"+chapt+"/";  
			getIMG(URL,chapt,manga);  
			//nextChapt(chapt, manga,source); 
			break;    
		} 
 	}   
	 
	public static void getIMG(String URL,int chapt,String manga)
	{
		//int count;  
		Elements img; 
		Document doc;  
		String base = URL;  
		try      
		{    	               
			System.out.println(URL);    
			for(int i = 0; i < 100; i++ )   
			{
				doc = Jsoup.connect(URL).get();                          
				img = doc.getElementsByTag("img");  
 
				for (Element el : img)   
				{  
				//	for(int b = 0; b < 20; b++ )                    
					//{     
						URL = base; 
						 
						String src = el.absUrl("src");
						//System.out.println("image found" +"   \n  " + src);
						
						new Thread(new Runnable() 
						{
							@Override      
							public void run()   
							{
 								try 
 								{
									grabIMG(src,chapt,manga);
								} 
 								catch (IOException e) 
 								{
 									e.printStackTrace();      
								}
							}
							 
						}).start();   
						 
						
						  
						int pg = 1 + i;
						URL = URL +pg; 
						System.out.println(URL);
				//		System.out.println(pg);
				//		System.out.println(b);
				//	}
				}
			}
		} 
		catch (IOException e)   
		{ 
			//e.printStackTrace();  
			System.err.println("download complete ");
		}       
	}
	
	
	
	
	
	
	
	public static String getSendP() 
	{
		return sendP;
	}  

	public static void setSendP(String sendP)  
	{
		mangaFinder.sendP = sendP;
	}

	public static void grabIMG(String src,int chapt,String manga) throws IOException  
	{
	
 		
		File file = new File("/Users/moehuwio/Desktop/"+manga+" Chapter "+chapt); 
        file.mkdir(); 
		
        
        String pathh = "/Users/moehuwio/Desktop/"+manga+" Chapter "+chapt;
        final String path = pathh;          
          
        
        //readerController reader = new readerController();
       // reader.setPathInfo(pathh);   
       
        setSendP(pathh);            
        
        int indexname = src.lastIndexOf("/");
          
        if (indexname == src.length())        
        { 
            src = src.substring(1, indexname);   
        }  
       
        indexname = src.lastIndexOf("/");  
        String name = src.substring(indexname, src.length());      
                 
        System.out.println(name);         
         
        URL url = new URL(src);             
        InputStream in = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream( path + name));
        
        for (int b; (b = in.read()) != -1;)
        {    
            out.write(b);   
           // in.close();  
          //  out.close();
        }
        
          
        
	}                                                   
      
	     
	
	public static void main(String[] args) 
	{   
		getURL(); 
 //		final mangaFinder mf = new mangaFinder(); 
	}

}
