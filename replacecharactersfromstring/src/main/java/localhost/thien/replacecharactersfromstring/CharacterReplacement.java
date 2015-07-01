package localhost.thien.replacecharactersfromstring;

import static java.util.stream.Collectors.joining;

public class CharacterReplacement {
    public String replaceCharacterUsingStringReplaceMethod(String stringToBeAltered,
                                                           char characterToBeReplaced,
                                                           char newCharacter) {
        return stringToBeAltered.replace(characterToBeReplaced, newCharacter);
    }

    public String replaceCharacterUsingIteration(String stringToBeAltered,
                                                 char characterToBeReplaced,
                                                 char newCharacter) {
        StringBuilder resultingString = new StringBuilder("");

        for (int index = 0; index < stringToBeAltered.length(); index++) {
            if (stringToBeAltered.charAt(index) == characterToBeReplaced) {
                resultingString.append(newCharacter);
            } else {
                resultingString.append(stringToBeAltered.charAt(index));
            }
        }

        return resultingString.toString();
    }

    public String replaceCharacterUsingCollectionPipeline(String stringToBeAltered,
                                                          char characterToBeReplaced,
                                                          char newCharacter) {
        return stringToBeAltered.chars().mapToObj(c -> (char) c)
            .map(c -> {
                if (c == characterToBeReplaced) {
                    return newCharacter;
                } else {
                    return c;
                }
            }).map(String::valueOf).collect(joining());
    }
}