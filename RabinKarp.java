import java.io.BufferedReader;  
import java.io.FileReader;  
import java.util.LinkedList;  
import java.util.List;  
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
class RabinKarp {  
     
      private final static long d = Character.MAX_VALUE + 1;  
           private final static long q = 96293;  
           public List<Integer> findMatches(char[] text, char[] pattern) {  
           return findMatches(text, pattern, d, q);  
           }  
     
      public List<Integer> findMatches(char[] text, char[] pattern, long d, long q) {  
           
           int m = pattern.length;  
          
           if (m > text.length)  
                return new LinkedList<>();  
           LinkedList<Integer> ret = new LinkedList<>();  
           long p = 0;  
           long t = 0;  
           for (int i = 0; i < m; i++) {  
                
                p = (p * d + pattern[i]) % q;  
                t = (t * d + text[i]) % q;  
           }  
           
           long h = powMod(d, m - 1, q); 
           int nMinusM = text.length - m;  
           for (int i = 0; i <= nMinusM; i++) {  
                if (p == t)  
                     if (compare(text, i, pattern))  
                          ret.add(i);  
                if (i < nMinusM) {  
                     t -= h * text[i];  
                       
                     while (t < 0)  
                          t += q;  
                     t = (d * t + text[i + m]) % q;  
                }  
           }  
           return ret;  
      }  
    
      private boolean compare(char[] text, int start, char[] pattern) {  
           if (text.length - start < pattern.length)  
                return false;  
           for (int i = 0; i < pattern.length; i++)  
                if (text[i + start] != pattern[i])  
                     return false;  
           return true;  
      }  
     
      private long powMod(long d, long n, long q) {  
           if (n == 0)  
                return 1;  
           if (n == 1)  
                return d % q;  
           long j = powMod(d, n / 2, q);  
           j = (j * j) % q;  
           if (n % 2 == 0)  
                return j;  
           return ((j * d) % q);  
      }
      
      public static void main(String[] args){
     RabinKarp matcher = new RabinKarp();
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
															
															String findMe=disease;
															searchMe= result[1];
															disease="<B><U><color=\"red\">"+disease+"</B></U>";
							list= matcher.findMatches(searchMe.toCharArray(), findMe.toCharArray());
					if(list.size()>0)
					{
					System.out.println(findMe);
					result[1]=searchMe.replaceAll(findMe,disease);	
					}
				for(int ress: list)
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