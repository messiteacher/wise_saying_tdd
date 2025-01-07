package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.Repository.WiseSayingFileRepository;
import app.domain.wiseSaying.WiseSaying;
import app.standard.Util;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingFileRepositoryTest {

    WiseSayingFileRepository wiseSayingRepository = new WiseSayingFileRepository();

    @AfterAll
    static void afterAll() {
       Util.File.deleteForce("db/test");
    }

    @Test
    @DisplayName("명언 저장")
    void t1() {

        WiseSaying wiseSaying = new WiseSaying(1, "aaa", "bbb");
        wiseSayingRepository.save(wiseSaying);

        String filePath = "db/test/wiseSaying/1.json";

        boolean rst = Files.exists(Path.of(filePath));
        assertThat(rst).isTrue();

        Map<String, Object> map = Util.Json.readAsMap(filePath);
        WiseSaying restoredWiseSaying = WiseSaying.fromMap(map);

        assertThat(wiseSaying).isEqualTo(restoredWiseSaying);
    }

    @Test
    @DisplayName("명언 삭제")
    void t2() {

        WiseSaying wiseSaying = new WiseSaying(1, "aaa", "bbb");
        wiseSayingRepository.save(wiseSaying);

        String filePath = "db/test/wiseSaying/1.json";

        boolean delRst = wiseSayingRepository.deleteById(1);

        boolean rst = Files.exists(Path.of(filePath));
        assertThat(rst).isFalse();
        assertThat(delRst).isTrue();
    }
}
