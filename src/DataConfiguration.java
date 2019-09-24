import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataConfiguration {

    //Constructor
    public DataConfiguration(){}

    ///Read the text file and return String
    public String readFile(String path){
        try{
            File file = new File(path);
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String data = "";
            String newLine;
            while((newLine = buffer.readLine())!=null){
                data = data.concat( "\n"+newLine);
            }

            return data;
        }catch(Exception e){
            return "Reading File failed";
        }
    }

    ///Get the Different vocabularies of the String
    public List<Vocabulary> getVocabulary(String data){
        try{
            Scanner scanner = new Scanner(data);
            List<Vocabulary> vocabularies = new ArrayList<Vocabulary>();
            while(scanner.hasNext()){
                String nextVocabulary = scanner.next();
                if(vocabularies.isEmpty()){
                    Vocabulary vocabulary = new Vocabulary(nextVocabulary);
                    vocabularies.add(vocabulary);
                }
                outterloop:
                while(true) {
                    for (Vocabulary vocabularyInLoop : vocabularies) {
                        if (nextVocabulary.equals(vocabularyInLoop.getContent())) {
                            break outterloop;
                        }
                    }
                    Vocabulary vocabulary = new Vocabulary(nextVocabulary);
                    vocabularies.add(vocabulary);
                    break outterloop;
                }
            }

            return vocabularies;
        }catch (Exception e){
            System.out.println("Exception is "+e );
            return null;
        }
    }

    public List<Vocabulary> calTimesCondPro(List<Vocabulary> vocabularies, String data){
        List<Vocabulary> inputVocabularies = vocabularies;
        for(Vocabulary vocabularyInOtherLoop: inputVocabularies){
            String word = vocabularyInOtherLoop.getContent();
            vocabularyInOtherLoop.setAppearsTimes(countSameWord(data,word));
            double AppearsTimes = vocabularyInOtherLoop.getAppearsTimes();
            vocabularyInOtherLoop.setConditionalProbability((AppearsTimes+1)/(AppearsTimes+inputVocabularies.size()));
        }
        return inputVocabularies;
    }

    public Double countSameWord(String data, String word){
        try{
            Scanner scanner = new Scanner(data);
            double sameWords = 0;
            while(scanner.hasNext()){
                String nextVocabulary = scanner.next();
                if(nextVocabulary.equals(word)){
                    sameWords++;
                }
            }
            return sameWords;
        }catch (Exception e){
            System.out.println("Exception is "+e );
            return 0.0;
        }
    }


}
