import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        DataConfiguration dataConfiguration = new DataConfiguration();
        String trainData = dataConfiguration.readFile("/Users/CharlesLiu/Desktop/ECE503" +
                "/Assignment1/DataConfiguration/fortune_cookies/traindata.txt");
        String trainLabel = dataConfiguration.readFile("/Users/CharlesLiu/Desktop/ECE503" +
                "/Assignment1/DataConfiguration/fortune_cookies/trainlabel.txt");


        TrainMultinomialNB trainBenoullinb = new TrainMultinomialNB();
        int NumberOfElements = 0;
        int Numberof1 = 0;


        for(int i = 0; i< trainData.length()-1;i++){
            if(trainData.charAt(i) == '\n'){
                NumberOfElements ++;
            }
        }
        for(int i = 0;i <trainLabel.length()-1;i++){
            if(trainLabel.charAt(i) == '1'){
                Numberof1++;
            }
        }
        //double probabilityOf1 = Numberof1/NumberOfElements;

        List<Vocabulary> vocabulariesOfTrain = dataConfiguration.getVocabulary(trainData);

    }
}
