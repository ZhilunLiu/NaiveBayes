import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TrainMultinomialNB {
    private Double NumberOfElements;
    private Double Numberofclass;
    private Double priorPro;
    private String data;
    private String label;
    private String classLabel;

    public TrainMultinomialNB(String data, String Label,String ClassLabel){
        this.data = data;
        this.label = Label;
        this.classLabel = ClassLabel;
        this.Numberofclass = 0.0;
        this.NumberOfElements = 0.0;
        this. priorPro = 0.0;
    }

    public String getTheStringOfLabel(){
        DataConfiguration dataConfiguration = new DataConfiguration();
        calculatedProriPro();
        List<String> index = new ArrayList<String>(150);
        List<String> labelList = new ArrayList<String>(Arrays.asList(label.split("\n")));
        List<String> dataList = new ArrayList<>();
        Scanner scanner = new Scanner(data);
        for(int i = 0;i < labelList.size()-1;i++){
            String oneLine = scanner.nextLine();
            if(labelList.get(i).equals(classLabel)){
                dataList.add(oneLine);
            }
        }
        String result ="";
        for(String eachLine : dataList){
            result = result.concat("\n"+eachLine);
        }
        return result;
    }

    private void calculatedProriPro(){
        String[] lines = data.split("\n");
        double linesOfElementInDouble = lines.length;
        NumberOfElements = linesOfElementInDouble;

        String[] lines2 = label.split("\n");
        double count = 0.0;
        for(String labels:lines2){
            if(labels.equals(classLabel)){
                count++;
            }
        }
        Numberofclass = count;

        priorPro = Numberofclass/NumberOfElements;
    }

    public String getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(String classLabel) {
        this.classLabel = classLabel;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getNumberOfElements() {
        return NumberOfElements;
    }

    public void setNumberOfElements(Double numberOfElements) {
        NumberOfElements = numberOfElements;
    }

    public Double getNumberofclass() {
        return Numberofclass;
    }

    public void setNumberofclass(Double numberofclass) {
        Numberofclass = numberofclass;
    }

    public Double getPriorPro() {
        return priorPro;
    }

    public void setPriorPro(Double priorPro) {
        this.priorPro = priorPro;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
