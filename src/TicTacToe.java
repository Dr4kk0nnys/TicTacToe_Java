import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    static String[] field = new String[9];
    static int play = 0; // It's used to determine which player should play

    private static void CreateField(){ // It initializes the field
        Arrays.fill(field, " ");
    }

    private static void ShowField(){ // Aesthetic method
        int pos = 0;
        for(int i = 0; i < 3; i++){
            StringBuilder posValue = new StringBuilder();
            for(int j = 0; j < 3; j ++){
                posValue.append(field[pos++]).append(" | ");
            }
            System.out.println(posValue);
        }
    }

    private static void TakeUserInput(){
        Scanner scanner = new Scanner(System.in);

        String player = (play % 2 == 0 ? "X" : "O");

        System.out.print("[ 1 ~ 9 ]: ");

        while(!scanner.hasNext("[123456789]")){
            System.out.println("Invalid Play!\n[ 1 ~ 9 ]: ");
            scanner.next();
        }

        field[Integer.parseInt(scanner.next()) -1] = player;
    }

    private static boolean CheckGameStatus(){
        String player = (play % 2 == 0 ? "O" : "X");

        int win = 0;
        int position = 0;

        for(int i = 0; i < 3; i++){ // Horizontal  [0, 1, 2], [3, 4, 5], [6, 7, 8]
            for(int j = 0; j < 3; j++){
                if(field[position++].equals(player)) win++;
            }
            if(win >= 3) {
                System.out.println("Player " + player + " won!!!");
                return true;
            }
            win = 0;
        }

        for(int i = 0; i < 3; i++){ // Vertical  [0, 3, 6], [1, 4, 7], [2, 5, 8]
            position = i;
            for(int j = 0; j < 3; j++){
                if(field[position].equals(player)) win++;
                position += 3;
            }
            if(win >= 3){
                System.out.println("Player " + player + " won!!!");
                return true;
            }
            win = 0;
        }

        if(field[0].equals(player) && field[4].equals(player) && field[8].equals(player)){ // Crossed  [0, 4, 8], [2, 4, 6]
            System.out.println("Player " + player + " won!!!");
            return true;
        }
        if(field[2].equals(player) && field[4].equals(player) && field[6].equals(player)) { // Crossed  [0, 4, 8], [2, 4, 6]
            System.out.println("Player " + player + " won!!!");
            return true;
        }

        int noOneWon = 0;
        for(String s : field){
            if (!s.equals(" ")) noOneWon++;
        }
        if(noOneWon >= 9) {
            System.out.println("NO ONE WON!!!");
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        CreateField();

        while(!CheckGameStatus()){
            TakeUserInput();
            ShowField();
            ++play;
        }
    }
}
