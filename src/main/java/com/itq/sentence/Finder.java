package com.itq.sentence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
 
//import com.fasterxml.jackson.databind.exc.InvalidFormatException;
 
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
 
/**
 * Sentence Detection Example in openNLP using Java
 * @author tutorialkart
 */
public class Finder {
 
    public static void main(String[] args) {
        try {
            new Finder().sentenceDetect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * This method is used to detect sentences in a paragraph/string
     * @throws InvalidFormatException
     * @throws IOException
     */
    public void sentenceDetect() throws IOException {
        String paragraph = "36 | 3 | Sesungguhnya kamu salah seorang dari rasul-rasul, 36 | 4 | (yang berada) diatas jalan yang lurus,";
 
        // refer to model file "en-sent,bin", available at link http://opennlp.sourceforge.net/models-1.5/
        InputStream is = new FileInputStream("en-sent.bin");
        SentenceModel model = new SentenceModel(is);
        
        // feed the model to SentenceDetectorME class 
        SentenceDetectorME sdetector = new SentenceDetectorME(model);
        
        // detect sentences in the paragraph
        String sentences[] = sdetector.sentDetect(paragraph);
 
        // print the sentences detected, to console
        for(int i=0;i<sentences.length;i++){
            System.out.println(sentences[i]);
        }
        is.close();
    }
}