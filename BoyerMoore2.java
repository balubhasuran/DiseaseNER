import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class BoyerMoore2 {
    private final int R;     
    private int[] right;     
    private String pat;      

    
    public BoyerMoore2(String pat) {
        this.R = 96293;
        this.pat = pat;

        
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }

    
    public ArrayList<Integer> search(String txt) {
        int M = pat.length();
        int N = txt.length();
        ArrayList<Integer> newArrayInt = new ArrayList<Integer>();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) 
            {
                newArrayInt.add(i);    
                skip++;
            }
        }
        return newArrayInt;                      
    }

   
    public static void main(String[] args) {
       String filePath = "disease.txt";
	String filePath1 = "Full Disease List.txt";
    BufferedReader br,br1;          
    String line = "";
	String disease="";
	String res="";
String searchMe="";
 
    try {
         br = new BufferedReader(new FileReader(filePath));
		
	        try {
            while((line = br.readLine()) != null)
             {
			 
			List<Integer> list = new ArrayList<>();
			  br1 = new BufferedReader(new FileReader(filePath1));
                String [] result =line.split("	", 2);
				
                        String first = result[0];
                      						
						int searchMeLength = searchMe.length();
                System.out.println(result[1]);
														while((disease = br1.readLine()) != null)
                                                    {
															
															String pat=disease;
															searchMe= result[1];
															disease="<B><U><color=\"red\">"+disease+"</B></U>";
							//list= matcher.findMatches(searchMe.toCharArray(), findMe.toCharArray());
							 BoyerMoore2 boyermoore1 = new BoyerMoore2(pat);
							 ArrayList<Integer> offset = boyermoore1.search(searchMe);
					if(offset.size()>0)
					{
					System.out.println(pat);
					
					result[1]=searchMe.replaceAll(pat,disease);	
					}
				for(int ress: offset)
													{
													 System.out.println(ress);
													}
													
																	
												
													}
											System.out.println(result[1]);	
													
												
				br1.close();
				}									
            
			 br.close();
			
		
        } catch (IOException e) {
            
            e.printStackTrace();
								}
		
   
    
    } catch (FileNotFoundException e) {
       
        e.printStackTrace();
									  }
	
      }
 } 
       