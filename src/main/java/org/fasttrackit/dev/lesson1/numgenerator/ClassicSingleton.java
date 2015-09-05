package org.fasttrackit.dev.lesson1.numgenerator;

/**
 * Created by cosminciocan on 15/08/15.
 */
public class ClassicSingleton {
    private static ClassicSingleton instance = null;
    protected ClassicSingleton() {
        // Exists only to defeat instantiation.
    }
    public static ClassicSingleton getInstance() {
        if(instance == null) {
            instance = new ClassicSingleton();
        }
        return instance;
    }
}
