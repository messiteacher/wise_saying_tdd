package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.Repository.WiseSayingDbRepository;
import app.domain.wiseSaying.Repository.WiseSayingFileRepository;
import app.domain.wiseSaying.Repository.WiseSayingRepository;
import app.domain.wiseSaying.WiseSaying;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingDbRepositoryTest {

    private static WiseSayingDbRepository wiseSayingRepository = new WiseSayingDbRepository();

    @BeforeAll
    public static void dropTable() {
        wiseSayingRepository.createWiseSayingTable();
    }

    @BeforeEach
    void truncateWiseSayingTable() {
        wiseSayingRepository.truncateWiseSayingTable();
    }

    @Test
    public void test() {
        System.out.println("hihi");
    }

    @Test
    @DisplayName("save 테스트")
    void t1() {

        WiseSaying wiseSaying = new WiseSaying("현재를 사랑하라", "작자미상");
        wiseSaying = wiseSayingRepository.save(wiseSaying);

        Optional<WiseSaying> opWiseSaying = wiseSayingRepository.findById(wiseSaying.getId());

        WiseSaying found = opWiseSaying.orElse(null);

        assertThat(wiseSaying.getId()).isEqualTo(1);
        assertThat(found).isEqualTo(wiseSaying);
    }

    @Test
    @DisplayName("명언 삭제")
    void t2() {

        WiseSaying wiseSaying = new WiseSaying(1, "aaa", "bbb");
        wiseSaying = wiseSayingRepository.save(wiseSaying);

        boolean delRst = wiseSayingRepository.deleteById(wiseSaying.getId());

        assertThat(delRst).isTrue();
    }

    @Test
    @DisplayName("모든 명언 가져오기")
    void t3() {

        WiseSaying wiseSaying1 = new WiseSaying("aaa1", "bbb1");
        WiseSaying wiseSaying2 = new WiseSaying("aaa2", "bbb2");
        WiseSaying wiseSaying3 = new WiseSaying("aaa3", "bbb3");

        wiseSayingRepository.save(wiseSaying1);
        wiseSayingRepository.save(wiseSaying2);
        wiseSayingRepository.save(wiseSaying3);

        List<WiseSaying> wiseSayings = wiseSayingRepository.findAll();

        assertThat(wiseSayings).hasSize(3);
        assertThat(wiseSayings).contains(wiseSaying1, wiseSaying2, wiseSaying3);
    }
}
