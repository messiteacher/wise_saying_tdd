package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.Repository.WiseSayingDbRepository;
import app.domain.wiseSaying.Repository.WiseSayingFileRepository;
import app.domain.wiseSaying.Repository.WiseSayingRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WiseSayingDbRepositoryTest {

    private static WiseSayingDbRepository wiseSayingRepository = new WiseSayingDbRepository();

    @BeforeAll
    public static void beforeAll() {
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
}
