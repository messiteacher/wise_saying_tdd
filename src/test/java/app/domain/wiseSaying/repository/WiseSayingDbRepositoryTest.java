package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.Page;
import app.domain.wiseSaying.Repository.WiseSayingDbRepository;
import app.domain.wiseSaying.Repository.WiseSayingFileRepository;
import app.domain.wiseSaying.Repository.WiseSayingRepository;
import app.domain.wiseSaying.WiseSaying;
import app.standard.Util;
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
        wiseSayingRepository.createTable();
    }

    @BeforeEach
    void truncateWiseSayingTable() {
        wiseSayingRepository.truncateTable();
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

        WiseSaying wiseSaying = new WiseSaying("aaa", "bbb");
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

    @Test
    @DisplayName("build 하면 모든 명언을 모아 하나의 파일로 저장.")
    void t4() {

        WiseSaying wiseSaying1 = new WiseSaying("aaa", "bbb");
        wiseSayingRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("ccc", "ddd");
        wiseSayingRepository.save(wiseSaying2);

        wiseSayingRepository.build();

        String jsonStr = Util.File.readAsString(wiseSayingRepository.getBuildPath());

        assertThat(jsonStr)
                .isEqualTo("""
                        [
                            {
                                "id" : 1,
                                "content" : "aaa",
                                "author" : "bbb"
                            },
                            {
                                "id" : 2,
                                "content" : "ccc",
                                "author" : "ddd"
                            }
                        ]
                        """.stripIndent().trim());
    }

    @Test
    @DisplayName("현재 저장된 명언의 개수를 가져오는 count")
    void t5() {

        WiseSaying wiseSaying1 = new WiseSaying("aaa", "bbb");
        wiseSayingRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("ccc", "ddd");
        wiseSayingRepository.save(wiseSaying2);

        long count = wiseSayingRepository.count();

        assertThat(count)
                .isEqualTo(2);
    }

    @Test
    @DisplayName("페이지 정보와 결과 가져오기")
    void t6() {

        WiseSaying wiseSaying1 = new WiseSaying("aaa", "bbb");
        wiseSayingRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying("ccc", "ddd");
        wiseSayingRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying("eee", "fff");
        wiseSayingRepository.save(wiseSaying3);

        int itemsPerPage = 5;
        int page = 1;

        Page<WiseSaying> pageContent = wiseSayingRepository.findAll(itemsPerPage, 1);

        List<WiseSaying> wiseSayings = pageContent.getContent();
        int totalItems = pageContent.getTotalItems();
        int totalPages = pageContent.getTotalPages();

        assertThat(wiseSayings)
                .hasSize(3);

        assertThat(totalItems)
                .isEqualTo(3);

        assertThat(totalPages)
                .isEqualTo(1);
    }
}
