import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // 1. 입력을 봇에게 맡길 수 있다.
        Scanner sc = new Scanner("등록\n현재를 사랑하라.\n작자미상");

        String val1 = sc.nextLine();
        System.out.println(val1);

        String val2 = sc.nextLine();
        System.out.println(val2);

        String val3 = sc.nextLine();
        System.out.println(val3);
    }
}
