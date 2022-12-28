package baseClasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DisplayOutputFiles {
	
	public static void Show() {
		Scanner scanner = new Scanner(System.in);
		BufferedReader FileReader = null;
		String OutpuFileName;
		int CurrentChance = 0;
		int run;
		String Content = null;
		boolean isDone = false;
		do {			
			if (CurrentChance > 0) {
				System.out.println("\nWARNING, you just have one more chance to enter the name of the file.");
			}
			System.out.print("Please enter the name of the file you want to check: ");
			OutpuFileName = scanner.next();
			run = 0;
			try {
				while (run == 0) {
					try {																																				
						FileReader = new BufferedReader(new FileReader("Outputs\\" + OutpuFileName));
						run = 1;
						try {
							System.out.println("\n\nHere is the content of the sucessfully created output file: " + OutpuFileName + "\n==========================================================================================================================================================================================\n");
							Content = FileReader.readLine();
							while (Content != null) {
								System.out.println(Content);
								Content = FileReader.readLine();
							}
							System.out.println("==========================================================================================================================================================================================");
							isDone = true;
						} catch (IOException e) {
							System.out.println("There is a problem reading the file!");
						}						
						break;
					} catch (FileNotFoundException e) {
								
					}
					
					try {																																				
						FileReader = new BufferedReader(new FileReader("Outputs\\" + OutpuFileName));
						run = 1;
						try {
							System.out.println("\n\nHere is the content of the sucessfully created output file: \"" + OutpuFileName + "\n==========================================================================================================================================================================================\n");
							Content = FileReader.readLine();
							while (Content != null) {
								System.out.println(Content);
								Content = FileReader.readLine();
							}
							System.out.println("==========================================================================================================================================================================================");
							isDone = true;
						} catch (IOException e) {
							System.out.println("There is a problem reading the file!");
						}	
						break;
					} catch (FileNotFoundException e) {
									
					}
					
					try {																																				
						FileReader = new BufferedReader(new FileReader("Outputs\\" + OutpuFileName));
						run = 1;
						try {
							System.out.println("\n\nHere is the content of the sucessfully created output file: " + OutpuFileName + "\n==========================================================================================================================================================================================\n");
							Content = FileReader.readLine();
							while (Content != null) {
								System.out.println(Content);
								Content = FileReader.readLine();
							}
							System.out.println("==========================================================================================================================================================================================");
							isDone = true;
						} catch (IOException e) {
							System.out.println("There is a problem reading the file!");
						}		
						break;
					} catch (FileNotFoundException e) {
									
					}
					run = 1;
					throw new FileInvalidException(OutpuFileName, CurrentChance);																
				}
			} catch (FileInvalidException e) {
				System.out.println(e.getMessage());																														
			}
			
			if (isDone == true) {
				break;
			}
			CurrentChance++;
			
		} while (CurrentChance < 2);
		
		try {
			if (FileReader != null) {																																	
				FileReader.close();																																		
			}
			//CLOSING SCANNER
			scanner.close();																																			
		} catch (IOException e) {
			System.out.println("There is a problem while closing Scanner and FileReader objects");			
		}
	}
}
