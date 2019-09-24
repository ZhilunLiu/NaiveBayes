public class Vocabulary {
    private String Content;
    private Double AppearsTimes;
    private Double ConditionalProbability;

    public Vocabulary(String content){
        Content = content;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Double getAppearsTimes() {
        return AppearsTimes;
    }

    public void setAppearsTimes(Double appearsTimes) {
        AppearsTimes = appearsTimes;
    }

    public Double getConditionalProbability() {
        return ConditionalProbability;
    }

    public void setConditionalProbability(Double conditionalProbability) {
        ConditionalProbability = conditionalProbability;
    }
}
