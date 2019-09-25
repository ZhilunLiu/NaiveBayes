import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Let's begin training!");
        ///Reading the file
        DataConfiguration dataConfiguration = new DataConfiguration();
        File currentDir = new File("");
        String curDir = currentDir.getAbsolutePath();
        String trainData = dataConfiguration.readFile(curDir+"/fortune_cookies/traindata.txt");
        String trainLabel = dataConfiguration.readFile(curDir+"/fortune_cookies/trainlabels.txt");
        //String trainData = dataConfiguration.readFile("/Users/CharlesLiu/Desktop/NaiveBayes/fortune_cookies/traindata.txt");
        //String trainLabel = dataConfiguration.readFile("/Users/CharlesLiu/Desktop/NaiveBayes/fortune_cookies/trainlabels.txt");

        String testDateContent = dataConfiguration.readFile(curDir+"/fortune_cookies/testdata.txt");
        String testLabelContent = dataConfiguration.readFile(curDir+"/fortune_cookies/testlabels.txt");


        TrainMultinomialNB trainBenoullinbOf1 = new TrainMultinomialNB(trainData,trainLabel,"1");
        String dataof1 = trainBenoullinbOf1.getTheStringOfLabel();

        TrainMultinomialNB trainBenoullinbOf0 = new TrainMultinomialNB(trainData,trainLabel,"0");
        String dataof0 = trainBenoullinbOf0.getTheStringOfLabel();

        List<Vocabulary> vocabulariesOfTrain = dataConfiguration.getVocabulary(trainData);
        List<Vocabulary> vocabulariesOfTrain1 = dataConfiguration.calTimesCondPro(vocabulariesOfTrain,dataof1);
        vocabulariesOfTrain = dataConfiguration.getVocabulary(trainData);
        List<Vocabulary> vocabulariesOfTrain0 = dataConfiguration.calTimesCondPro(vocabulariesOfTrain,dataof0);

        System.out.println("\nTrainingResult:\nprior prob of 0 is "+trainBenoullinbOf0.getPriorPro()
                +"\nprior prob of 1 is "+ trainBenoullinbOf1.getPriorPro()+"\n");

        //Test part:
        //Test on training Data
        String testData = trainData;
        String testLabel = trainLabel;

        TestMultinomailNB testMultinomailNB = new TestMultinomailNB();
        testMultinomailNB = testMultinomailNB.test(testData,testLabel,trainBenoullinbOf0,trainBenoullinbOf1,
                vocabulariesOfTrain,vocabulariesOfTrain0,vocabulariesOfTrain1);

        int confusionMatrix[][] = testMultinomailNB.getConfusionMatrix();
        double rateOfMiss = testMultinomailNB.getRateOfMiss();

        System.out.println("Test on Training Data done: ");
        outputResult(confusionMatrix,rateOfMiss);

        //Test on test data
        testData = testDateContent;
        testLabel = testLabelContent;

        testMultinomailNB = testMultinomailNB.test(testData,testLabel,trainBenoullinbOf0,trainBenoullinbOf1,
                vocabulariesOfTrain,vocabulariesOfTrain0,vocabulariesOfTrain1);

        confusionMatrix = testMultinomailNB.getConfusionMatrix();
        rateOfMiss = testMultinomailNB.getRateOfMiss();

        System.out.println("\nTest on Test Data done: ");
        outputResult(confusionMatrix,rateOfMiss);

    }

    private static void outputResult(int[][] confusionMatrix, Double missRate){
        System.out.println("\nThe confusion matrix is :\n"
                +confusionMatrix[0][0]+"     "+ confusionMatrix[0][1]+"\n"
                +confusionMatrix[1][0]+"     "+confusionMatrix[1][1]);
        System.out.println("\nThe Missclassification rate is "+missRate);
    }
}
