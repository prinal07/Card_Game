import java.io.File;

class Players{
    private int id;
    private String logFileName = "player" + id + "output.txt";
    private File outputFile = new File(logFileName);
    private int cardDeckId;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogFileName() {
        return logFileName;
    }
    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }
    public File getOutputFile() {
        return outputFile;
    }
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }
    public int getCardDeckId() {
        return cardDeckId;
    }
    public void setCardDeckId(int cardDeckId) {
        this.cardDeckId = cardDeckId;
    }
    
}