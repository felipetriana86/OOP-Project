package testClasses;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {				                               						  
	
	//SCANNER ARRAY
	static Scanner     [] reader        = new Scanner     [10];  
	static Scanner     [] readerCopy    = new Scanner     [10];  
	
	//PRINTWRITER ARRAY
	static PrintWriter [] IEEEwriter    = new PrintWriter [10];  
	static PrintWriter [] ACMwriter     = new PrintWriter [10];  
	static PrintWriter [] NJwriter      = new PrintWriter [10];  

	
	public static void main(String[] args) {

		System.out.println("\t\t\t\t\tADVANCED OBJET ORIENTED PROGRAMMING");
		System.out.println("\t\t\t\t\t\t420-PA3-AS C2-Gr 420");
		System.out.println("FINAL PROJECT: BibCreator");
		System.out.println("April, 2022. \n");
		System.out.println("Author(s): Danial Sasanpour,\n\t   Felipe Triana");
		System.out.println("==============================================================================================================");
		
		
		//MAIN METHODS
		
		//calling Read()to read and store the InputFiles into Scanner[].
		baseClasses.ReadInputFiles.Read(reader);  
		
		//calling Generate() to create create 3 different files (IEEE, ACM, NJ) into the PrintWriter []
		baseClasses.BibCreator.Generate(IEEEwriter, ACMwriter, NJwriter);
		
		//calling ProcessFilesForValidation() to check if the file is valid and then add the content in an output file.
		baseClasses.ValidateInputIntoOutput.ProcessFilesForValidation(reader, readerCopy, IEEEwriter, ACMwriter, NJwriter);
		
		//calling DeleteInvalidFiles() 
		baseClasses.ValidateInputIntoOutput.DeleteInvalidFiles(readerCopy, IEEEwriter, ACMwriter, NJwriter);
		
		//calling Show()
		baseClasses.DisplayOutputFiles.Show();
		
		//exit 
		System.out.println("\nGoodbye, and thanks for using BibCreator! ");
		System.exit(0);
	}
}
