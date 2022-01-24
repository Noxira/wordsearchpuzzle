public class Statistics {

    // Inisialisasi var
    private long stopwatchStart = 0;
    private long stopwatchEnd = 0;
    private long checkLetterCounter = 0;

    public void checkedALetter(){
        this.checkLetterCounter++;
    }

    public void startStopwatch(){
        this.stopwatchStart = System.currentTimeMillis();
    }

    public void stopStopwatch(){
        this.stopwatchEnd = System.currentTimeMillis();
    }

    public long getElapsedMilisecond(){
        return (long) this.stopwatchEnd - this.stopwatchStart;
    }

    public long getLetterCount(){
        return (long) checkLetterCounter;
    }
}
