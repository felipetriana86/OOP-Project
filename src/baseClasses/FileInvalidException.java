package baseClasses;

@SuppressWarnings("serial") //FIX "SERIALIZABLE" WARNING
public class FileInvalidException extends Exception {

	String error;
	
	//ERROR MESSAGE WHEN IT IS NOT POSSIBLE TO OPEN THE FILE
	public FileInvalidException(int i) {
		
		error = "\nCould not open input file bibFiles\\Latex" + i + ".bib for reading." +
				"\nPlease check if file exists! Program will terminate after closing any opened files.";
	}
	
	//ERROR MESSAGE WHEN IT IS NOT POSSIBLE TO CREATE THE 3 OUTPUT FILES
	public FileInvalidException(String OutpuFileName, int i, String OutputFileType) {
		
		error = "\nNo Output File Created For bibFiles\\Latex" + i + ".bib" +
				"\nThere is No Such Outputs\\ Directory Available For " + OutpuFileName + i + OutputFileType;
	}
	
	//ERROR MESSAGE WHEN THERE IS AN EMPTY FIELD
	public FileInvalidException (int i, String EmptyField) {
		
		error = "\nFile Invalid Exception has detected a problem with the file: bibFiles\\Latex" + i + ".bib" +
				"\n* Invalid File: Latex" + i + ".bib" +
				"\n* Error: Field  \"" + EmptyField + "\" is empty." + " The process for " + "Latex" + i + ".bib" + " stopped !\n* The output files (ACM, IEEE, NJ) are not going to be created.\n" + "\n====================================================================================\n";
	}
	
	//ERROR MESSAGE WHEN THE FILE IS NOT FOUND
	public FileInvalidException(String OutpuFileName, int CurrentChance) {
		
		if (CurrentChance+1 == 2) {
			error = "Could not open file with the name of \"" + OutpuFileName + "\"! File does not exist; possibly it could not be created, or wrong file name entered!" + 
					"\n\nSorry! the desire file does not exist! Exiting program...\n";
		} else if (CurrentChance == 0) {
			error = "File : \"" + OutpuFileName + "\" does not exist; possibly the name is wrong or it was not created because the original file is invalid!\n";
		} else if (CurrentChance > 0) {
			error = "File : \"" + OutpuFileName + "\" does not exist; possibly the name is wrong or it was not created because the original file is invalid!\n";
		}
		
	}
	
	@Override
	public String getMessage() {
		return error;
	}
}
