import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        ///Reading the file
        DataConfiguration dataConfiguration = new DataConfiguration();
       // String trainData = dataConfiguration.readFile("/Users/CharlesLiu/Desktop/ECE503" +
                //"/Assignment1/DataConfiguration/fortune_cookies/traindata.txt");
        //String trainLabel = dataConfiguration.readFile("/Users/CharlesLiu/Desktop/ECE503" +
                //"/Assignment1/DataConfiguration/fortune_cookies/trainlabel.txt");
        String trainData = dataConfiguration.readFile("C:/Users/charles/Desktop/" +
                "/NaiveBayes/fortune_cookies/traindata.txt");

        String trainLabel = dataConfiguration.readFile("C:/Users/charles/Desktop" +
                "/NaiveBayes/fortune_cookies/trainlabels.txt");


        TrainMultinomialNB trainBenoullinbOf1 = new TrainMultinomialNB(trainData,trainLabel,"1");
        String dataof1 = trainBenoullinbOf1.getTheStringOfLabel();

        TrainMultinomialNB trainBenoullinbOf0 = new TrainMultinomialNB(trainData,trainLabel,"0");
        String dataof0 = trainBenoullinbOf0.getTheStringOfLabel();

        List<Vocabulary> vocabulariesOfTrain = dataConfiguration.getVocabulary(trainData);
        List<Vocabulary> vocabulariesOfTrain1 = dataConfiguration.calTimesCondPro(vocabulariesOfTrain,dataof1);
        List<Vocabulary> vocabulariesOfTrain0 = dataConfiguration.calTimesCondPro(vocabulariesOfTrain,dataof0);

        //Test part:
        String testData = trainData;
        List<Prediction> predictions= new LinkedList<>();
        int count = 0;
        String[] linesOfTest = testData.split("\n");
        for(String line:linesOfTest){
            Prediction prediction = new Prediction(trainBenoullinbOf0,trainBenoullinbOf1,line);
            List<Vocabulary> vocabulariesOfTest = dataConfiguration.getVocabulary(line);
            for(Vocabulary vocabulary: vocabulariesOfTest){
                prediction.score0 += Math.log(vocabulariesOfTrain0.get(vocabulariesOfTest.indexOf(vocabulary)).getConditionalProbability())/Math.log(2);
                prediction.score1 += Math.log(vocabulariesOfTrain1.get(vocabulariesOfTest.indexOf(vocabulary)).getConditionalProbability())/Math.log(2);
            }
            predictions.add(prediction);
        }


        System.out.println(predictions);
    }
}
