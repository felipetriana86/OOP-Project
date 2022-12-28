package baseClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class BibCreator {
	
	public static void Generate(PrintWriter[] IEEEwriter, PrintWriter[] ACMwriter, PrintWriter[] NJwriter) {
		
		for (int i = 0; i < 10; i++) {

			try {																																	
				try {	
					//CREATING AND IEEE FILE AND STORE IT IN PrintWriter []
					IEEEwriter[i] = new PrintWriter(new FileOutputStream("Outputs\\IEEE" + (i+1) + ".json"));								
				} catch (FileNotFoundException e) {
					throw new FileInvalidException("IEEE", (i+1), ".json");		
				}
				
				try {
					//CREATING AND ACM FILE AND STORE IT IN PrintWriter []
					ACMwriter[i]  = new PrintWriter(new FileOutputStream("Outputs\\ACM" + (i+1) + ".json"));									
				} catch (FileNotFoundException e) {
					throw new FileInvalidException("ACM", (i+1), ".json");			
				}
				
				try {	
					//CREATING AND NJ FILE AND STORE IT IN PrintWriter []
					NJwriter[i]   = new PrintWriter(new FileOutputStream("Outputs\\NJ" + (i+1) + ".json"));											
				} catch (FileNotFoundException e) {
					throw new FileInvalidException("NJ", (i+1), ".json");				
				}
			
			} catch (FileInvalidException e) {				
				System.out.println(e.getMessage());																											
				//DELETE THE IEEE, ACM ANN NJ FILES IN CASE ONE OF THEM FAILED BEING CREATED.
				File[] IEEEFile = new File[10];
				File[] ACMFile  = new File[10];
				File[] NJFile   = new File[10];

				for (int j = 0; j < 10; j++) {
					
					IEEEFile[j] = new File("Outputs\\IEEE" + (j+1) + ".json");																
					if (IEEEFile[j].exists()) {																									
						if (IEEEwriter[j] != null) {																							
							IEEEwriter[j].close();																																														
						}
						IEEEFile[j].delete();																									
					}

					ACMFile[j] = new File("Outputs\\ACM" + (j+1) + ".json");
					if (ACMFile[j].exists()) {																									
						if (ACMwriter[j] != null) {																								
							ACMwriter[j].close();																									
						}	
						ACMFile[j].delete();																									
					}

					NJFile[j] = new File("Outputs\\NJ" + + (j+1) + ".json");
					if (NJFile[j].exists()) {																									
						if (NJwriter[j] != null) {																								
							NJwriter[j].close();																								
						}							
						NJFile[j].delete();																										
					}
				}
				System.exit(0);																													
			}
		}
	}
}
