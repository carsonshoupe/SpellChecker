import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.Arrays;
import org.junit.Before;
import java.lang.String;

public class StringHashTableTest{
	//Instance Variables:
	public Object[] words; 
	StringHashTable sht;
	
	/* 
	@Before
	public void textParserTest() throws FileNotFoundException{
		StringHashTable sht = new StringHashTable(0); 
		Object[] parsed = {};
		try{
			parsed = sht.textParser("words.txt"); 
		}
		catch (FileNotFoundException e){
			System.out.println("File Not Found");
		}
		this.words = parsed; 
		
		//System.out.println(Arrays.toString(parsed));
		//System.out.println("Words.length: " + parsed.length); 
	}	
 */	
	
	@Before
	public void hashWords(){
		this.sht = new StringHashTable(200003, "words.txt");
		try{
			System.out.println(sht.getStringAtHash("hello")); 
		}
		catch (StringHashTableException e){
			System.out.println("Word not in dictionary"); 
		}
		System.out.println(Arrays.toString(sht.bucketArr[sht.hashString("hello")]));
	}
	
	@Test 
	public void seeIfHashSucks(){
		for (int counter = 0; counter < this.sht.bucketArr.length; counter++){
			if (this.sht.bucketArr[counter][5] != null){
				System.out.println(Arrays.toString(this.sht.bucketArr[counter]));
				System.out.println("THIS HASH SUCKS"); 
			}
		}
	}
	
	@Test
	public void checkArrToString(){
		char[] testArr = {'h', " ", 'e', 'l', 'l', 'o'}; 
		System.out.println("\n" + new String(testArr) + "\n"); 
	}
		
		
	
	
	
	
	
	
}


//javac -cp .;C:\junit\junit-4.10.jar StringHashTableTest.java
//java -cp .;C:\junit\junit-4.10.jar org.junit.runner.JUnitCore StringHashTableTest