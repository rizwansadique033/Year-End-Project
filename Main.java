/*First idea turned out to be too ambitious and too complex. I realized
that as I was designing the algorithm.

Additionally, the idea seemed more about my preferences than my client's.

The first idea was supposed to be a turn-based wizard duel. It would've had to involve many
    different possible inputs each following a unique, complex process.
    Some processes would have been too advanced with my current knowledge.
    For example, moves that have a temporary effect lasting one or multiple
        turns would require that I detect how many turns have passed and also a
        variable for each player for each type of effect which determines the
        turn when the effect ends.

My second idea is a random money prize game*/
import java.util.*;
public class Main {
    //setting variables
    public static boolean gameEnd = false;
    public static int turns = 0;
    public static int choice = 0;
    public static void main(String[] args) {
        Random r = new Random();
        Scanner input = new Scanner(System.in);
        System.out.println("What's your name?");
        String name = input.next();

        //welcome message
        System.out.println();
        System.out.println("Hello, " + name + "! " +"Welcome to Door Roulette. " +
            "In this game, you will be presented with 3 doors to choose from. ");

        //Rework: instead of -$10, the lowest amount possible from a door is 1
        System.out.println("Every door has a certain amount of money behind, ranging from $1 to $25. ");
        System.out.println("You choose a door by typing in it's number. For example, if I ");

        //Rework: No losing. Game continues until player wins.
        /*System.out.println("wanted to choose door 3, I would type '3'. Your goal is to make $100. If you");
        System.out.println("reach -$50, you lose. Good luck!");*/
        System.out.println("wanted to choose door 3, I would type '3'. Good luck!");


        //setting variables
        int money = 0;

        //gameplay loop
        while(!(gameEnd)){
            turns++;
            System.out.println();
            System.out.println("-------------TURN " + turns + "-------------------");

            //print current total money
            String displayMoney = sign(money);
            System.out.println();
            System.out.println("Money: "+displayMoney);
            System.out.println();

            //setting options to random number between -10 and 25
            //Reworked to 1 and 25
            int door1 =r.nextInt(25)+1;
            int door2 =r.nextInt(25)+1;
            int door3 =r.nextInt(25)+1;
            int doorArr[] = {door1,door2,door3};

            //prompt
            choose();

            //add the prize to the money
            money += doorArr[choice-1];
            System.out.println();

            String displayPrize = sign(doorArr[choice-1]);
            System.out.println("You earned "+ displayPrize);

            //reveal other doors' options
            show(doorArr, choice);

            //test if game is over yet
            gameOver(money, name);

        }

    }

    //Method to return string with either "-$" or "$"
    public static String sign(int value){
        if (value<0){
            value = value*=-1;
            String newVal = "-$"+value;
            return newVal;
        }else {
            String newVal = "$"+value;
            return newVal;
        }
    }

    //Method to reveal other doors' options
    public static void show(int[] doorArr, int doorNum){
        doorArr[doorNum-1] = 27;
        for (int i =0; i < doorArr.length; i++){
            if (!(doorArr[i] > 25)){
                String displayMoney = sign(doorArr[i]);
                System.out.println("Behind Door "+(i+1)+" was "+displayMoney);
            }
        }
    }

    //Method to check if game should end
    //Rework: No lose screen. Game continues until player wins.
    public static void gameOver(int money, String name){
        if (money>=100){
            gameEnd = true;
            System.out.println("--------------------OVER---------------------");
            System.out.println();
            System.out.println("YOU WIN!");
            System.out.println();
            System.out.println("Congratulations, " + name + "! You have accumulated " +
                    "$100 or more and so, won the game!");
            stats(money,turns);
        } /*else if (money <= -50){
            gameEnd = true;
            System.out.println("--------------------OVER---------------------");
            System.out.println();
            System.out.println("Game Over, " + name + "! You have accumulated -$50 or less" +
                    " and so, lost the game!");
            System.out.println();
            System.out.println("Better luck next time!");
            stats(money,turns);
        }*/

    }


    //Method to print user stats
    public static void stats(int money, int turns){
        String moneyDisplay = sign(money);
        System.out.println("Total money: " + moneyDisplay);
        System.out.println();
        System.out.println("Turns taken: " + turns);
    }

    //Method to inform of invalid input and re prompt
    public static void invalid(){
        System.out.println("Invalid input entered (must be an integer between " +
                "1 and 3). Try again.");
        System.out.println();
        choose();
    }
    //Method to prompt user which door they pick
    public static void choose(){
        Scanner input = new Scanner(System.in);

        //(FOR DEBUGGING) System.out.println(choice);
        System.out.println("There are 3 doors. Which door do you pick?");
        try {
            choice = input.nextInt();
        } catch(Exception e){
            invalid();
        }
        if ((choice > 3)||(choice<1)){
            invalid();
        }
    }
}