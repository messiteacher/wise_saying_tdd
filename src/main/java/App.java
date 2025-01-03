import domain.wiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {

    private final Scanner sc;
    private int lastId;
    private List<WiseSaying> wiseSayingList;

    public App(Scanner sc) {
        this.sc = sc;
        lastId = 0;
        wiseSayingList = new ArrayList<>();
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {

            System.out.print("명령 ) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {

                System.out.println("명언앱을 종료합니다.");
                break;
            } else if (cmd.equals("등록")) {

                System.out.println("명언 : ");
                String content = sc.nextLine();
                System.out.println("작가 : ");
                String author = sc.nextLine();

                int id = ++lastId;
                WiseSaying wiseSaying = new WiseSaying(id, content, author);
                wiseSayingList.add(wiseSaying);

                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            } else if (cmd.equals("목록")) {

                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                Collections.reverse(wiseSayingList);
                wiseSayingList.forEach(w -> {
                    System.out.printf("%d / %s / %s\n", w.getId(), w.getAuthor(), w.getContent());
                });
            }
        }
    }
}
