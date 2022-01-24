import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Wordpuzzle {

    // Inisialisasi var
    char matrix[][] = new char[50][50];
    int iEff = 0; int jEff = 0;
    String[] words = new String[50];
    int wordsEff = 0;
    Statistics stats = new Statistics();

    void printWordpuzzle(){
        for (int i = 0; i < this.iEff; i++){
            for (int j = 0; j < this.jEff; j++){
                System.out.print(this.matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }

        System.out.println("");
        for (int i = 0; i < this.wordsEff; i++){
            System.out.println(words[i]);
        }
    }

    void fillPuzzle(){
        try {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input filename (with '.txt' on the end, with file on the 'test' folder): ");
        String filename = scanner.next();
        scanner.close();
        File txtFile = new File("../test/" + filename);
        Scanner txtReader = new Scanner(txtFile);
        int n = 0; int m = 0;
        boolean hasReadPuzzle = false;

        //reads the puzzle
        while(txtReader.hasNextLine() && !hasReadPuzzle){
            String line = txtReader.nextLine();
            char[] lineChars = line.toCharArray();
            int b = 0;
            if (lineChars.length == 0){
                hasReadPuzzle = true;
                break;
            }
            else {
                for (int a = 0; a < lineChars.length; a++) {
                    if(Character.isLetter(lineChars[a])){
                        this.matrix[n][b] = lineChars[a];
                        b++;
                    }
                }
            }
            m = b;
            n++;
        }

        int c = 0;
        // reads the words that needs to be found in the puzzle
        while(txtReader.hasNextLine()){
            String line = txtReader.nextLine();
            this.words[c] = line;
            c++;
        }

        this.iEff = n;
        this.jEff = m;
        this.wordsEff = c;

        txtReader.close();
        } 
        catch (FileNotFoundException e) {}
    }

    void printUpwards(int i, int j, int wordLen){
        for (int a = 0; a < this.iEff; a++){
            for (int b = 0; b < this.jEff; b++){
                if (((wordLen - i + a) > 0 && (a <= i)) && j == b) System.out.print(this.matrix[a][b]);
                else System.out.print("_");
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    void printUpRight(int i, int j, int wordLen){
        int tempLen = wordLen - 1;

        for (int a = 0; a < this.iEff; a++){
            for (int b = 0; b < this.jEff; b++){
                if (a == (i - tempLen) && b == (j + tempLen) && tempLen >= 0){
                    tempLen--;
                    System.out.print(this.matrix[a][b]);  
                } 
                else System.out.print("_");
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    void printRight(int i, int j, int wordLen){
        for (int a = 0; a < this.iEff; a++){
            for (int b = 0; b < this.jEff; b++){
                if (((b - j + 1) <= wordLen && (b >= j)) && i == a) System.out.print(this.matrix[a][b]);
                else System.out.print("_");
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    void printDownRight(int i, int j, int wordLen){
        int tempLen = 0;

        for (int a = 0; a < this.iEff; a++){
            for (int b = 0; b < this.jEff; b++){
                if (a == (i + tempLen) && b == (j + tempLen) && tempLen < wordLen){
                    tempLen++;
                    System.out.print(this.matrix[a][b]);  
                } 
                else System.out.print("_");
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    void printDownwards(int i, int j, int wordLen){
        for (int a = 0; a < this.iEff; a++){
            for (int b = 0; b < this.jEff; b++){
                if (((a - i + 1) <= wordLen && (a >= i)) && j == b) System.out.print(this.matrix[a][b]);
                else System.out.print("_");
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    void printDownLeft(int i, int j, int wordLen){
        int tempLen = 0;

        for (int a = 0; a < this.iEff; a++){
            for (int b = 0; b < this.jEff; b++){
                if (a == (i + tempLen) && b == (j - tempLen) && tempLen < wordLen){
                    tempLen++;
                    System.out.print(this.matrix[a][b]);  
                } 
                else System.out.print("_");
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    void printLeft(int i, int j, int wordLen){
        for (int a = 0; a < this.iEff; a++){
            for (int b = 0; b < this.jEff; b++){
                if (((wordLen - j + b) > 0 && (b <= j)) && i == a) System.out.print(this.matrix[a][b]);
                else System.out.print("_");
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    void printUpLeft(int i, int j, int wordLen){
        int tempLen = wordLen - 1;

        for (int a = 0; a < this.iEff; a++){
            for (int b = 0; b < this.jEff; b++){
                if (a == (i - tempLen) && b == (j - tempLen) && tempLen >= 0){
                    tempLen--;
                    System.out.print(this.matrix[a][b]);  
                } 
                else System.out.print("_");
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    void deleteWord(int idx){
        this.words[idx] = this.words[this.wordsEff - 1];
        this.wordsEff--;
    }

    void checkAllDirections(int i, int j, boolean print){
        int idx = 0;
        while(idx < this.wordsEff && this.wordsEff > 0){
            boolean found = false;
            int wordLen = this.words[idx].length();
            
            // checks up
            if (!found){
                if (i + 1 - wordLen >= 0){
                    int traverseCheck = 0;
                    boolean matches = true;
                    while(traverseCheck < wordLen && matches){
                        this.stats.checkedALetter();
                        if (this.matrix[i - traverseCheck][j] != this.words[idx].charAt(traverseCheck)){
                            matches = false;
                        }
                        traverseCheck++;
                    }

                    if(matches) {
                        found = true;

                        // deals with printing
                        if (print) printUpwards(i, j, wordLen);

                        // deals with deleting word from word list
                        deleteWord(idx);
                    }
                }
            }

            // checks up-right
            if (!found){
                if (i + 1 - wordLen >= 0 && j + wordLen <= this.jEff){
                    int traverseCheck = 0;
                    boolean matches = true;
                    while(traverseCheck < wordLen && matches){
                        this.stats.checkedALetter();
                        if (this.matrix[i - traverseCheck][j + traverseCheck] != this.words[idx].charAt(traverseCheck)){
                            matches = false;
                        }
                        traverseCheck++;
                    }

                    if(matches) {
                        found = true;

                        // deals with printing
                        if (print) printUpRight(i, j, wordLen);

                        // deals with deleting word from word list
                        deleteWord(idx);
                    }
                }
            }

            // checks right
            if (!found){
                if (j + wordLen <= this.jEff){
                    int traverseCheck = 0;
                    boolean matches = true;
                    while(traverseCheck < wordLen && matches){
                        this.stats.checkedALetter();
                        if (this.matrix[i][j + traverseCheck] != this.words[idx].charAt(traverseCheck)){
                            matches = false;
                        }
                        traverseCheck++;
                    }

                    if(matches) {
                        found = true;

                        // deals with printing
                        if (print) printRight(i, j, wordLen);

                        // deals with deleting word from word list
                        deleteWord(idx);
                    }
                }
            }

            // checks down-right
            if (!found){
                if (i + wordLen <= this.iEff && j + wordLen <= this.jEff){
                    int traverseCheck = 0;
                    boolean matches = true;
                    while(traverseCheck < wordLen && matches){
                        this.stats.checkedALetter();
                        if (this.matrix[i + traverseCheck][j + traverseCheck] != this.words[idx].charAt(traverseCheck)){
                            matches = false;
                        }
                        traverseCheck++;
                    }

                    if(matches) {
                        found = true;

                        // deals with printing
                        if (print) printDownRight(i, j, wordLen);

                        // deals with deleting word from word list
                        deleteWord(idx);
                    }
                }
            }

            // checks down
            if (!found){
                if (i + wordLen <= this.iEff){
                    int traverseCheck = 0;
                    boolean matches = true;
                    while(traverseCheck < wordLen && matches){
                        this.stats.checkedALetter();
                        if (this.matrix[i + traverseCheck][j] != this.words[idx].charAt(traverseCheck)){
                            matches = false;
                        }
                        traverseCheck++;
                    }

                    if(matches) {
                        found = true;

                        // deals with printing
                        if (print) printDownwards(i, j, wordLen);

                        // deals with deleting word from word list
                        deleteWord(idx);
                    }
                }
            }   

            // checks down-left
            if (!found){
                if (i + wordLen <= this.iEff && j + 1 - wordLen >= 0){
                    int traverseCheck = 0;
                    boolean matches = true;
                    while(traverseCheck < wordLen && matches){
                        this.stats.checkedALetter();
                        if (this.matrix[i + traverseCheck][j - traverseCheck] != this.words[idx].charAt(traverseCheck)){
                            matches = false;
                        }
                        traverseCheck++;
                    }

                    if(matches) {
                        found = true;

                        // deals with printing
                        if (print) printDownLeft(i, j, wordLen);

                        // deals with deleting word from word list
                        deleteWord(idx);
                    }
                }
            }

            // checks left
            if (!found){
                if (j + 1 - wordLen >= 0){
                    int traverseCheck = 0;
                    boolean matches = true;
                    while(traverseCheck < wordLen && matches){
                        this.stats.checkedALetter();
                        if (this.matrix[i][j - traverseCheck] != this.words[idx].charAt(traverseCheck)){
                            matches = false;
                        }
                        traverseCheck++;
                    }

                    if(matches) {
                        found = true;

                        // deals with printing
                        if (print) printLeft(i, j, wordLen);

                        // deals with deleting word from word list
                        deleteWord(idx);
                    }
                }
            }
     
            // checks up-left
            if (!found){
                if (i + 1 - wordLen >= 0 && j + 1 - wordLen >= 0){
                    int traverseCheck = 0;
                    boolean matches = true;
                    while(traverseCheck < wordLen && matches){
                        this.stats.checkedALetter();
                        if (this.matrix[i - traverseCheck][j - traverseCheck] != this.words[idx].charAt(traverseCheck)){
                            matches = false;
                        }
                        traverseCheck++;
                    }

                    if(matches) {
                        found = true;

                        // deals with printing
                        if (print) printUpLeft(i, j, wordLen);

                        // deals with deleting word from word list
                        deleteWord(idx);
                    }
                }
            }

            if(found){
                System.out.println("");
            }
            else{
                idx++;
            }
        }

    }

    void bruteforceFindWords(boolean print){
        this.stats.startStopwatch();
        int i = 0; int j = 0;
        while (this.wordsEff > 0 && i < this.iEff){
            while(this.wordsEff > 0 && j < this.jEff){
                this.checkAllDirections(i, j, print);
                j++;
            }
            i++;
            j = 0;
        }
        this.stats.stopStopwatch();
        if (!(print)) {
            System.out.println("\nTime taken : " + this.stats.getElapsedMilisecond() + " miliseconds"); 
            System.out.println("Checks done : " + this.stats.getLetterCount());
        }
        }

}