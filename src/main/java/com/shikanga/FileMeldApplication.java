package com.shikanga;

import com.shikanga.domain.user.UserProcessor;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
@QuarkusMain
public class FileMeldApplication implements QuarkusApplication {

    public static final String PATH_TO_DATA_FOLDER = "data/";
    public static final String PATH_TO_RESULTS_FOLDER = "results/users";

    @Inject
    UserProcessor userProcessor;

    @Override
    public int run(String... args) {
        log.info(" Yes I'm Up and running ");
        userProcessor.mergeDataFiles(PATH_TO_DATA_FOLDER, PATH_TO_RESULTS_FOLDER);
        return 0;
    }
}
