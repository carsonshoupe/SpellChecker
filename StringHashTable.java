import java.lang.Math;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays; 

class StringHashTable{
	//Instance Variables:
	public String[][] bucketArr; 
	private int capacity; 
	
	//Constructors:
	StringHashTable(int cap, String file){
		this.capacity = cap;
		this.bucketArr = new String[this.capacity][10];
		Object[] arrOfWords = null;
		try{
			arrOfWords = textParser(file);
			//System.out.println(Arrays.toString(arrOfWords));
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		for (int counter = 0; counter < arrOfWords.length; counter++){
			String word = arrOfWords[counter].toString(); 
			//System.out.println("Word: " + word); 
			put(word);
			//System.out.println("Put word number " + counter); 
		}
	}
		
	StringHashTable(int cap){
		this.capacity = cap;
		this.bucketArr = new String[cap][10];//chose 5 arbitrarily 
	}
	StringHashTable(){
		this(200003); 
	}
	
	//Methods: 
	private long getHashCode(String inputString){
		long hashCode = 0L; 
		for (int counter = 0; counter < inputString.length(); counter++){
			long charLong = (long) inputString.charAt(counter);
			hashCode = hashCode + charLong*((long)(Math.pow(37, counter))); 
		}
		return hashCode; 
	}

	private int compressHashCode(long hashCode){
		int bucketArrIndex = (int) (Math.abs(hashCode)%capacity); 
		return bucketArrIndex;
	}
	
	public int hashString(String inputString){
		return compressHashCode(getHashCode(inputString)); 
	}
	public void put(String inputString){
		int counter = 0;
		//System.out.println("Hashcode: " + getHashCode(inputString));
		//System.out.println("Hash Index: " + hashString(inputString));
		while (bucketArr[hashString(inputString)][counter] != null){
			counter++;
		}
		//System.out.println(hashString(inputString));
		this.bucketArr[hashString(inputString)][counter] = inputString;			
	}
	
	public boolean checkStringInTable(String inputString){
		if (bucketArr[hashString(inputString)][0] == null) {
			return false;
		}
		else {
			String[] bucketContents = this.bucketArr[hashString(inputString)];
			for (int counter = 0; counter < bucketContents.length; counter++){
				if (inputString.equals(bucketContents[counter])){
					return true;
				}
			}
			return false;
		}
	}
	
	public String getStringAtHash(String inputString) throws StringHashTableException{
		//if function returns null, then there was a hashcode collision between a non-word and a word.  
		if (checkStringInTable(inputString) == false){
			throw new StringHashTableException("String not in StringHashTable"); 
		}
		else{
			String[] bucketContents = bucketArr[hashString(inputString)];
			for (int counter = 0; counter < bucketContents.length; counter++){
				if (inputString.equals(bucketContents[counter])){
					return bucketContents[counter]; 
				}
			}
			return null; 
		}
	}
	
	public Object[] textParser(String file) throws FileNotFoundException{
		ArrayList<String> parsedText = new ArrayList<String>();
		FileReader input = new FileReader(file);
		BufferedReader bufRead = new BufferedReader(input); 
		String myLine = ""; 
		try{
			while ((myLine = bufRead.readLine()) != null){
				String[] helperArr = myLine.split(" "); 
				//System.out.println("helperArr: " + Arrays.toString(helperArr));
				for(int counter = 0; counter < helperArr.length; counter++){
					parsedText.add(helperArr[counter]); 
				}
			}
		}
		catch (IOException e){
			System.out.println("Threw IOException, whatever that is...");
		}
		return parsedText.toArray(); 
	}
				
}
	