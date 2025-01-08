package app.domain.wiseSaying.Repository;

import app.global.AppConfig;

public class RepositoryProvider {

    public static WiseSayingRepository provide() {

        if (AppConfig.isFileDb()) return new WiseSayingFileRepository();
        else return new WiseSayingMemRepository();
    }
}
