package localhost.thien.replacecharactersfromstring;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharacterReplacementTest {

    @Test
    public void testReplacesCharacterUsingStringReplaceMethod() {
        CharacterReplacement characterReplacement = new CharacterReplacement();
        String resultingString = characterReplacement.replaceCharacterUsingStringReplaceMethod("Most of them", 'o', 'i');
        String expectedString = "Mist if them";
        assertThat(resultingString, equalTo(expectedString));
    }

    @Test
    public void testReplacesCharacterUsingIteration() {
        CharacterReplacement characterReplacement = new CharacterReplacement();
        String resultingString = characterReplacement.replaceCharacterUsingIteration("I am a moose", 'a', 'o');
        String expectedString = "I om o moose";
        assertThat(resultingString, equalTo(expectedString));
    }

    @Test
    public void testReplacesCharacterUsingCollectionPipeline() {
        CharacterReplacement characterReplacement = new CharacterReplacement();
        String resultingString = characterReplacement.replaceCharacterUsingCollectionPipeline("Because you are", 'c', 'g');
        String expectedString = "Begause you are";
        assertThat(resultingString, equalTo(expectedString));
    }

}
