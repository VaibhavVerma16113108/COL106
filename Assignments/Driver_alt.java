import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Driver_alt {
    public static void main(String args[]) throws IOException{
        long startTime = System.nanoTime();
        File myObj = new File("./test101.txt");
        Scanner sc = new Scanner(myObj);
        int numTestCases;
        numTestCases = sc.nextInt();
        while (numTestCases-- > 0) {
            int size;
            size = sc.nextInt();
            A2DynamicMem obj = new A2DynamicMem(size);
            int numCommands = sc.nextInt();
            while (numCommands-- > 0) {
                String command;
                command = sc.next();
                int argument;
                argument = sc.nextInt();
                int result = -5;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                        break;
                    case "Defragment":
                        obj.Defragment();
                    default:
                        break;
                }
                // System.out.println(result);
            }

        }
        long stopTime = System.nanoTime();
        System.out.println((stopTime - startTime)/1000000000.0);
    }
}