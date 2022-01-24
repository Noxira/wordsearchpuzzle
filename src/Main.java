class Main {
    public static void main (String[] args){

        // Inisialisasi var
        Wordpuzzle wordpuzzle = new Wordpuzzle();

        // mengisi puzzle
        wordpuzzle.fillPuzzle();

        // mengeprint hasil pencarian
        wordpuzzle.bruteforceFindWords(true);

        // menghitung waktu pencarian (tanpa waktu mengeprint)
        // kalau ditambahkan bersama yg atas, bisa berdetik-detik waktu tambahannya
        wordpuzzle.bruteforceFindWords(false);
    }
}