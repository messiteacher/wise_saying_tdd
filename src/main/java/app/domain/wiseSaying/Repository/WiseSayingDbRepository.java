package app.domain.wiseSaying.Repository;

import app.domain.wiseSaying.WiseSaying;
import app.standard.simpleDb.SimpleDb;

public class WiseSayingDbRepository {

    private final SimpleDb simpleDb;

    public WiseSayingDbRepository() {
        this.simpleDb = new SimpleDb("localhost", "root", "qkfmtpffhsk10", "wiseSaying__test");
    }
}
