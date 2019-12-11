package com.lbl.code.task;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * @author libinglong
 * <a href="mailto:libinglong@huli.com">libinglong:2376771274@qq.com</a>
 * @since 2019/12/11
 */
public class CodeCountRunnable implements Runnable{

    private File file;
    private ExecutorService executorService;

    public static Exception e;

    public CodeCountRunnable(File file, ExecutorService executorService){
        this.file = file;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        try {
            LineCountUtils.count(file);
        } catch (IOException e) {
            CodeCountRunnable.e = e;
            executorService.shutdown();
        }
    }
}
