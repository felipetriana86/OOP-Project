package baseClasses;


import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class ValidateInputIntoOutput {
	
	static int numOfInvalidFiles = 0;
	static String[] ToBeDeletedFiles;
	static String[] readerCopy;
	
	static String Author 		= null;
	static String Journal 		= null;
	static String Title 		= null;
	static String Year 			= null;
	static String Volume 		= null;
	static String Number 		= null;
	static String Pages 		= null;
	static String Doi 			= null;
	static String Month 		= null;	
	static String Paragraph 	= null;
	static String [] Article 	= null;	
	static String IEEEData 		= null;
	static String ACMData 		= null;
	static String NJData 		= null;
	static int ACMCount;
	
	public static void ProcessFilesForValidation(Scanner[] reader, Scanner[] readerCopy, PrintWriter[] IEEEwriter, PrintWriter[] ACMwriter, PrintWriter[] NJwriter) {
		
		baseClasses.ReadInputFiles.Read(readerCopy); 																																	
		String Content;
		String EmptyField;
		ToBeDeletedFiles = new String[10];																																																	
		
		for (int i = 0; i < 10; i++) {
																																																																	
			try {																																																												
				reader[i].useDelimiter("\\{\\s*}"); 																																																		
				  																																																    		
				Content = reader[i].next();																																																																																																								
				 																																																			
				
				if (reader[i].hasNext()) { 																																																							
					numOfInvalidFiles++;																																																					
					EmptyField = Content.substring((Content.lastIndexOf("\n") + 1), (Content.lastIndexOf("=")));																																			
					ToBeDeletedFiles[i] = Integer.toString(i+1);																																															
					reader[i].close();																																																						
					throw new FileInvalidException((i+1), EmptyField);																																		
				} else {
					reader[i].close();																																																						
					WriteDataIntoOutputFile(i, readerCopy, IEEEwriter, ACMwriter, NJwriter);			
					}
			}
			catch (FileInvalidException e) {
				System.out.println(e.getMessage());																																																			
			}
			
		}
	}

	private static void WriteDataIntoOutputFile(int i, Scanner[] readerCopy, PrintWriter[] IEEEwriter, PrintWriter[] ACMwriter, PrintWriter[] NJwriter) {
		
		readerCopy[i].useDelimiter("@ARTICLE");																																																					
		ACMCount = 1;																																																										 
		
		while (readerCopy[i].hasNext()) { 																																																					

			Paragraph = readerCopy[i].next();  																																																				
			//System.out.println("Paragraph: \n" + Paragraph + "\n==============The end of paragraph============");     																																	
			Article = Paragraph.split("\n");	

			for (int line = 0; line < Article.length; line++) {																																																
				
				if (Article[line].toLowerCase().contains("author=")) {																																														
																																																															
					Author = Article[line].substring(Article[line].indexOf("{")+1, Article[line].indexOf("}"));																																				

				} else if (Article[line].toLowerCase().contains("journal=")) {																																												
																																																															
					Journal = Article[line].substring(Article[line].indexOf("{")+1, Article[line].indexOf("}"));																																			
					
				} else if (Article[line].toLowerCase().contains("title=")) {																																												
																																																															
					Title = Article[line].substring(Article[line].indexOf("{")+1, Article[line].indexOf("}"));																																				
																																																															
				} else if (Article[line].toLowerCase().contains("year=")) {																																													
																																																															
					Year = Article[line].substring(Article[line].indexOf("{")+1, Article[line].indexOf("}"));																																				
						
				} else if (Article[line].toLowerCase().contains("volume=")) {																																												
																																																															
					Volume = Article[line].substring(Article[line].indexOf("{")+1, Article[line].indexOf("}"));																																				
					
				} else if (Article[line].toLowerCase().contains("number=")) {																																												
																																																															
					Number = Article[line].substring(Article[line].indexOf("{")+1, Article[line].indexOf("}"));																																				
					
				} else if (Article[line].toLowerCase().contains("pages=")) {																																												
																																																															
					Pages = Article[line].substring(Article[line].indexOf("{")+1, Article[line].indexOf("}"));																																				
					
				} else if (Article[line].toLowerCase().contains("doi=")) {																																													
																																																															
					Doi = Article[line].substring(Article[line].indexOf("{")+1, Article[line].indexOf("}"));																																				
						
				} else if (Article[line].toLowerCase().contains("month=")) {																																												
																																																															
					Month = Article[line].substring(Article[line].indexOf("{")+1, Article[line].indexOf("}"));																																				
					
				} else {
					continue;
				}
			}
			
			if (Author.contains(" and ")) {																																																					
				IEEEData = (Author.replace(" and", ",") + ". \"" + Title + "\", " + Journal + ", vol. " + Volume + ", no. " + Number + ", p. " + Pages + ", " + Month + " " + Year + ".");																	
				ACMData = ("[" + ACMCount++ + "] " + Author.substring(0, Author.indexOf(" and")) + " et al. " + Year + ". " + Title + ". " + Journal + ". " + Volume + ", " + Number + " (" + Year + "), " + Pages + ". DOI:https://doi.org/" + Doi + ".");	
				NJData = (Author.replace("and", "&") + ". " + Title + ". " + Journal + ". " + Volume + ", " + Pages + "(" + Year + ").");																																
			} else {		
				IEEEData = (Author + ". \"" + Title + "\", " + Journal + ", vol. " + Volume + ", no. " + Number + ", p. " + Pages + ", " + Month + " " + Year + ".");																						
				ACMData = ("[" + ACMCount++ + "] " + Author + " et al. " + Year + ". " + Title + ". " + Journal + ". " + Volume + ", " + Number + " (" + Year + "), " + Pages + ". DOI:https://doi.org/" + Doi + ".");										
				NJData = (Author + ". " + Title + ". " + Journal + ". " + Volume + ", " + Pages + "(" + Year + ").");																																		
			}
			
			IEEEwriter[i].println(IEEEData + "\n");																																																			
			IEEEwriter[i].checkError();	 																																																					
				
			ACMwriter[i].println(ACMData + "\n");																																																			
			ACMwriter[i].checkError();	 																																																					
						
			NJwriter[i].println(NJData + "\n");																																																				
			NJwriter[i].checkError();	 																																																					
			   																																																					
		}
		
	}
	
	public static void DeleteInvalidFiles(Scanner[] readerCopy, PrintWriter[] IEEEwriter, PrintWriter[] ACMwriter, PrintWriter[] NJwriter) {
		
		if (numOfInvalidFiles == 0) {																																																						
			
			for (int i = 0; i < readerCopy.length; i++) {
				readerCopy[i].close(); 																																																						
				IEEEwriter[i].close();  																																																					 
				ACMwriter[i].close();																																																						 
				NJwriter[i].close();																																																						 
			}
			
			System.out.println("All \"" + 10 + "\" input files(s) were \"Valid\" and \"" + (10*3) + "\" output file(s) have been created.\n\n");																			
		
		} else {
			
			File[] InvalidFile = new File[3]; 
			for (int i = 0; i < ToBeDeletedFiles.length; i++) {																																																
				
				if (i < 10) {
					readerCopy[i].close(); 																																																					
					IEEEwriter[i].close();  																																																				 
					ACMwriter[i].close();																																																					 
					NJwriter[i].close();																																																					 
				}
				
				if (ToBeDeletedFiles[i] != null) {																																																			
					
					InvalidFile[0] = new File("Outputs\\IEEE" + (i+1) + ".json");
					InvalidFile[1] = new File("Outputs\\ACM"  + (i+1) + ".json");
					InvalidFile[2] = new File("Outputs\\NJ"   + (i+1) + ".json");

					InvalidFile[0].delete();																																																				
					InvalidFile[1].delete();																																																				
					InvalidFile[2].delete();																																																				

				}
			}
			
			System.out.println("\nAfter reading the " + 10 +" input files:\n\n = " + numOfInvalidFiles + " files are invalid. So the respective IEEE, ACM and NJ files were NOT created. " +
								"\n = " + (10 - numOfInvalidFiles) + " files are valid. So the respective IEEE, ACM and NJ files are SUCCESFULLY created.\n\n");						
		}
	}
}
