public class Prediction {
    public Double score0;
    public Double score1;
    private String content;

    public Prediction(TrainMultinomialNB trainMultinomialNB0, TrainMultinomialNB trainMultinomialNB1,String Content){
        score0 = Math.log(trainMultinomialNB0.getPriorPro())/Math.log(2);
        score1 = Math.log(trainMultinomialNB1.getPriorPro())/Math.log(2);
        content = Content;
    }

    public Double getScore0() {
        return score0;
    }

    public void setScore0(Double score0) {
        this.score0 = score0;
    }

    public Double getScore1() {
        return score1;
    }

    public void setScore1(Double score1) {
        this.score1 = score1;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
