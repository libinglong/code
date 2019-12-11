package com.lbl.code.task;


import com.lbl.code.file.FileType;
import com.lbl.code.file.LineOpt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author libinglong
 * <a href="mailto:libinglong@huli.com">libinglong:2376771274@qq.com</a>
 * @since 2019/12/11
 */
public class LineCountUtils {

    public static AtomicInteger commentCount = new AtomicInteger(0);
    public static AtomicInteger codeCount = new AtomicInteger(0);
    public static AtomicInteger blankCount = new AtomicInteger(0);


    public static void count(File file) throws IOException {
        FileType fileType = FileType.getFileType(file);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while (true){
                String line = reader.readLine();
                if(line == null){
                    break;
                }
                processLine(line,fileType.getLineOpt());
            }
        } catch (IOException e) {
            throw e;
        }
    }

    private static void processLine(String line, LineOpt lineOpt){
        if(line.length() == 0){
            blankCount.incrementAndGet();
        } else if (lineOpt.isComment(line)){
            commentCount.incrementAndGet();
        } else {
            codeCount.incrementAndGet();
        }
    }

}
