package org.fasttrackit.dev.lesson1.numgenerator;

/**
 * Created by condor on 29/11/14.
 * FastTrackIT, 2015
 */


/* didactic purposes

Some items are intentionally not optimized or not coded in the right way

FastTrackIT 2015

*/

public class NumGeneratorBusinessLogic {

    private static final int MAX_NUMBER = 10;

    private boolean isFirstTime = true;
    private boolean successfulGuess;
    private int numberOfGuesses;
    private int generatedNumber;
    private String hint;
    double startingTime;
    double guessTime;
    double timeToGuess;

    public NumGeneratorBusinessLogic(){
        resetNumberGenerator();
    }

    public boolean getSuccessfulGuess(){
        return successfulGuess;
    }
    public double getGuessTime(){
        return timeToGuess;
    }
    public String getHint(){
        return hint;
    }

    public int getNumGuesses(){
        return numberOfGuesses;
    }

    public boolean isFirstTime(){
        return isFirstTime;
    }

    public void resetNumberGenerator(){
        isFirstTime = true;
        numberOfGuesses = 0;
        hint = "";
    }

    public boolean determineGuess(int guessNumber){
        if (isFirstTime) {
            generatedNumber = NumGenerator.generate(MAX_NUMBER);
            System.out.println("gennr:"+generatedNumber);
            isFirstTime = false;
            startingTime = System.currentTimeMillis();
        }
        numberOfGuesses++;
        if (guessNumber == generatedNumber) {
            hint="";
            successfulGuess = true;
            guessTime = System.currentTimeMillis();
            double x = guessTime - startingTime;
            timeToGuess = x;
            timeToGuess = timeToGuess / 1000;
        } else if (guessNumber < generatedNumber) {
            hint = "higher";
            successfulGuess = false;
        } else if (guessNumber > generatedNumber) {
            hint = "lower";
            successfulGuess = false;
        }
        return successfulGuess;
    }


}