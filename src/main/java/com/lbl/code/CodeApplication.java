package com.lbl.code;

import com.lbl.code.cli.CliUtils;
import com.lbl.code.task.CodeCountRunnable;
import com.lbl.code.task.LineCountUtils;
import com.lbl.code.task.MyRejectedExecutionHandler;
import com.lbl.code.task.TaskProducer;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author libinglong
 * <a href="mailto:libinglong@huli.com">libinglong:2376771274@qq.com</a>
 * @since 2019/12/11
 */
public class CodeApplication {

    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
        CommandLine commandLine = CliUtils.initCliArgs(args);
        File dir = (File) commandLine.getParsedOptionValue("dir");
        if(!dir.exists()){
            throw new FileNotFoundException(dir.getName());
        }
        String t = commandLine.getOptionValue("t");
        int threadNum;
        if (t != null){
            threadNum = Integer.parseInt(t);
        } else {
            threadNum = Runtime.getRuntime().availableProcessors();
        }
        ExecutorService executorService = new ThreadPoolExecutor(threadNum, threadNum, 0,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(), new MyRejectedExecutionHandler());
        TaskProducer.countAndSumbitFile(dir,executorService);
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE,TimeUnit.SECONDS);
        if(CodeCountRunnable.e != null){
            System.out.println("统计异常" + CodeCountRunnable.e.getMessage());
        } else{
            System.out.println("代码行数为" + LineCountUtils.codeCount.get());
            System.out.println("注释行数为" + LineCountUtils.commentCount.get());
            System.out.println("空行行数为" + LineCountUtils.blankCount.get());
        }
    }

}
