package com;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import java.util.*;

public class ChineseSegmenterDemo {

    public static void main(String[] args) {
        // set up pipeline properties
        Properties props = new Properties();
        props.setProperty("annotators", "segment");

        // set up Stanford CoreNLP pipeline
        props.setProperty("segment.model", "edu/stanford/nlp/models/segmenter/chinese/ctb.gz");
        props.setProperty("segment.dict", "edu/stanford/nlp/models/segmenter/chinese/dict-chris6.ser.gz");


        // set up pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create a document object
        CoreDocument doc = new CoreDocument("这是一段中文文本。");

        // annotate the document
        pipeline.annotate(doc);

        // extract the segmented tokens
        List<CoreLabel> tokens = doc.tokens();

        // print the tokens
        for (CoreLabel token : tokens) {
            System.out.println(token.word());
        }
    }
}

