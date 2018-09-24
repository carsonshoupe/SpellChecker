import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.Arrays;
import org.junit.Before;
import java.lang.String;

public class SpellCheckerTest{

	@Test
	public void testSpellCheck(){
		SpellChecker sc = new SpellChecker(); 
		sc.spellCheck("helo");
		sc.spellCheck("nosepeice"); 
		sc.spellCheck("the"); 
		sc.spellCheck("teh"); 
		sc.spellCheck("wellcome"); 
		sc.spellCheck("yourselve");
	}
}


//javac -cp .;C:\junit\junit-4.10.jar SpellCheckerTest.java
//java -cp .;C:\junit\junit-4.10.jar org.junit.runner.JUnitCore SpellCheckerTest