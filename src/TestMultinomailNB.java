import java.util.LinkedList;
import java.util.List;

public class TestMultinomailNB {
    private int[][] confusionMatrix;
    private double rateOfMiss;

    public TestMultinomailNB(){
        this.confusionMatrix = new int[2][2];
    }

    public TestMultinomailNB test(String testData, String testLabel,TrainMultinomialNB trainBenoullinbOf0,
                                  TrainMultinomialNB trainBenoullinbOf1, List<Vocabulary> vocabulariesOfTrain,
                                  List<Vocabulary> vocabulariesOfTrain0, List<Vocabulary> vocabulariesOfTrain1){
        DataConfiguration dataConfiguration = new DataConfiguration();
        List<Prediction> predictions= new LinkedList<>();
        int count = 0;
        String[] linesOfTest = testData.split("\n");
        for(String line:linesOfTest){
            Prediction prediction = new Prediction(trainBenoullinbOf0,trainBenoullinbOf1,line);
            List<Vocabulary> vocabulariesOfTest = dataConfiguration.getVocabulary(line);
            for(Vocabulary vocabulary: vocabulariesOfTest){
                int indexInTraVoca = 0;
                for(Vocabulary sameVocabulary:vocabulariesOfTrain){
                    if(sameVocabulary.getContent().equals(vocabulary.getContent())){
                        indexInTraVoca = vocabulariesOfTrain0.indexOf(sameVocabulary);
                    }
                }
                prediction.score0 += Math.log(vocabulariesOfTrain0.get(indexInTraVoca).getConditionalProbability())/Math.log(2);
                prediction.score1 += Math.log(vocabulariesOfTrain1.get(indexInTraVoca).getConditionalProbability())/Math.log(2);
            }
            if(prediction.score0>prediction.score1){
                prediction.setResult("0");
            }else{
                prediction.setResult("1");
            }
            predictions.add(prediction);
        }

        //Confusion Matrix:
        int predictionResult,actualValue;

        String[] labels = testLabel.split("\n");
        for(String label:labels){
            if(count ==0){
                count++;
                continue;
            }
            predictionResult = Integer.valueOf(predictions.get(count).getResult());
            actualValue = Integer.valueOf(label);
            this.confusionMatrix[predictionResult][actualValue]++;
            count++;
        }

        //misclassified:
        double numberOfMisclass = confusionMatrix[0][1]+confusionMatrix[1][0];
        this.rateOfMiss = numberOfMisclass/count;

        return this;
    }

    public int[][] getConfusionMatrix() {
        return confusionMatrix;
    }

    public void setConfusionMatrix(int[][] confusionMatrix) {
        this.confusionMatrix = confusionMatrix;
    }

    public double getRateOfMiss() {
        return rateOfMiss;
    }

    public void setRateOfMiss(double rateOfMiss) {
        this.rateOfMiss = rateOfMiss;
    }
}
