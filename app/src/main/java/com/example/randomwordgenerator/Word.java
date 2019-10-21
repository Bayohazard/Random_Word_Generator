package com.example.randomwordgenerator;

public class Word {
    private String word;
    private int length;
    private SpeechPart speechPart;

    public Word(String word, int length, SpeechPart speechPart){
        this.word = word;
        this.length = length;
        this.speechPart = speechPart;
    }

    public int getLength() {
        return length;
    }

    public String getWord() {
        return word;
    }

    public SpeechPart getSpeechPart() {
        return speechPart;
    }
}
