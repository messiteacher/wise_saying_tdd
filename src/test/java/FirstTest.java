import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTest {

    @Test
    void t1() {

        int rst = 1;

        assertThat(rst).isEqualTo(1);
    }

    @Test
    void t2() {

        App app = new App();
        app.run();

        // aaa가 출력되는가?
//        assertThat(result).isEqualTo("aaa");
    }

    @Test
    void t3() {

        // 테스트봇 선입력
//        Scanner sc = new Scanner("종료\n");
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        TestApp app = new TestApp();
//        app.run();

        TestBot testBot = new TestBot();
        String out = testBot.run("종료");

        assertThat(out).contains("명언앱을 종료합니다.");

        // 출력값을 체크
    }

    @Test
    @DisplayName("앱 시작시 '== 명언 앱 ==' 출력")
    void t4() {

        String out = TestBot.run("종료");

        assertThat(out)
                .containsSubsequence("== 명언 앱 ==", "명언앱을 종료합니다.");
    }

    @Test
    @DisplayName("등록 - 명언 1개 입력")
    void t5() {

        String out = TestBot.run("등록\n현재를 사랑하라.\n작자미상\n종료");

        assertThat(out)
                .containsSubsequence("명언 : ", "작가 : ");
    }
}
