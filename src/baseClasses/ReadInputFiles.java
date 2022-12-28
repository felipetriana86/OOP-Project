package baseClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadInputFiles {
	
	public static void Read(Scanner[] reader) {
		
		FileInputStream InputFile;
		
		for (int i = 0; i < 10; i++) {
			
			String path = "bibFiles\\Latex" + (i+1) + ".bib";										   
			
			try {																										    				
				try {					
					InputFile = new FileInputStream(path);																   
					reader[i] = new Scanner(InputFile);			
				} catch (FileNotFoundException e) {
					throw new FileInvalidException((i+1));					
				}
					
			} catch (FileInvalidException e) {
				
				System.out.println(e.getMessage()); 																		

				for (int j = 0; j < i; j++) {
					reader[j].close(); 															
				}
				System.exit(0);																									}
		}
	}
}
