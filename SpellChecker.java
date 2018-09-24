import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Arrays;

class SpellChecker{
	//Instance Variables:
	StringHashTable hashTable; 
	ArrayList<String> possibleWords = new ArrayList<>();
	static final char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	//Constructors: 
	SpellChecker(){
		this.hashTable = new StringHashTable(200003, "words.txt"); 
	}
	
	//Methods
	public void spellCheck(String inputString){ 
		//main spell check logic
		if (hashTable.checkStringInTable(inputString) == true){
			System.out.println("" + inputString + " is a word."); 
		}
		else{
			char[] stringAsCharArray = inputString.toCharArray();
			checkLetterSwaps(stringAsCharArray); 
			checkMissingLetter(inputString); 
			checkExtraLetter(inputString);
			checkSingleTypo(stringAsCharArray);
			System.out.println("" + inputString + " is not a word.\n Did you mean " + this.possibleWords.toString()); 
			this.possibleWords = new ArrayList<>();
		}
	}
	
	public void checkLetterSwaps(char[] inputStringAsArr){
		//checks letter swaps
		String checkWord;
		char[] workingInputStringAsArr; 
		for (int counter = 0; counter < inputStringAsArr.length-1; counter++){
			workingInputStringAsArr = Arrays.copyOf(inputStringAsArr, inputStringAsArr.length); 
			workingInputStringAsArr[counter] = inputStringAsArr[counter+1]; 
			workingInputStringAsArr[counter+1] = inputStringAsArr[counter]; 
			checkWord = new String(workingInputStringAsArr);
			if (hashTable.checkStringInTable(checkWord) == true){
				this.possibleWords.add(checkWord); 
			}
		}
	}
	
	public void checkMissingLetter(String inputString){
		//checks if there's a missing letter
		StringBuilder workingStringBuilder;
		for (int counter = 0; counter < inputString.length()+1; counter++){
			for (int alphabetCounter = 0; alphabetCounter < 26; alphabetCounter++){
				workingStringBuilder = new StringBuilder(inputString); 
				workingStringBuilder.insert(counter, alphabet[alphabetCounter]); 
				if (hashTable.checkStringInTable(workingStringBuilder.toString()) == true){
					this.possibleWords.add(workingStringBuilder.toString()); 
				}
			}
		}
	}
	
	public void checkExtraLetter(String inputString){
		//checks if there is an extra letter
		StringBuilder workingStringBuilder;
		for (int counter = 0; counter < inputString.length(); counter++){
			workingStringBuilder = new StringBuilder(inputString); 
			workingStringBuilder.deleteCharAt(counter); 
			if (hashTable.checkStringInTable(workingStringBuilder.toString()) == true){
				this.possibleWords.add(workingStringBuilder.toString()); 
			}
		}
	}
	
	public void checkSingleTypo(char[] inputStringAsArr){
		//checks if there is a typo
		String checkWord; 
		char[] workingInputStringAsArr; 
		for (int counter = 0; counter < inputStringAsArr.length; counter++){
			for (int alphabetCounter = 0; alphabetCounter < 26; alphabetCounter++){
				workingInputStringAsArr = Arrays.copyOf(inputStringAsArr, inputStringAsArr.length); 
				workingInputStringAsArr[counter] = alphabet[alphabetCounter]; 
				checkWord = new String(workingInputStringAsArr);
				if (hashTable.checkStringInTable(checkWord) == true){
					this.possibleWords.add(checkWord); 
				}
			}
		}
	}
}