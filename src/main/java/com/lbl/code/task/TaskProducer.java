package com.lbl.code.task;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;


/**
 * @author libinglong
 * <a href="mailto:libinglong@huli.com">libinglong:2376771274@qq.com</a>
 * @since 2019/12/11
 */
public class TaskProducer {

    public static void countAndSumbitFile(File file, ExecutorService executorService) throws IOException {
        if(file.exists()){
            if(file.isDirectory()){
                for (File tmpFile : Objects.requireNonNull(file.listFiles())) {
                    countAndSumbitFile(tmpFile,executorService);
                }
            } else {
                if(!accept(file)){
                    return;
                }
                executorService.submit(new CodeCountRunnable(file,executorService));
            }
        }
    }

    private static boolean accept(File file) throws IOException {
        if (file.getName().endsWith(".java") && file.getCanonicalPath().contains("src")){
            return true;
        } else {
            return false;
        }
    }
}
