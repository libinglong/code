package com.lbl.code.cli;

import org.apache.commons.cli.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;


/**
 * @author libinglong
 * <a href="mailto:libinglong@huli.com">libinglong:2376771274@qq.com</a>
 * @since 2019/12/11
 */
public class CliUtils {

    private static Options OPTIONS = new Options();

    /**
     * 参数预处理
     * @param args 命令行参数数组
     * @return
     */
    public static CommandLine initCliArgs(String[] args) {
        CommandLineParser parser = new DefaultParser();
        //help
        OPTIONS.addOption("help","usage help");
        Option dirOpt = Option.builder("dir")
                .argName("code dir")
                .required()
                .hasArg(true)
                .type(File.class)
                .desc("the dir of code")
                .build();
        OPTIONS.addOption(dirOpt);
        Option threadOpt = Option.builder("t")
                .argName("thread count")
                .hasArg(true)
                .type(Number.class)
                .desc("the thread number than we use to execute the task,default cpuNum * 2")
                .build();
        OPTIONS.addOption(threadOpt);
        try {
            return parser.parse(OPTIONS, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage() + "\n" + getHelpString());
            System.exit(0);
            return null;
        }
    }

    /**
     * 获取help内容
     * @return help字符串
     */
    private static String getHelpString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (PrintWriter printWriter = new PrintWriter(byteArrayOutputStream)){
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp(printWriter, HelpFormatter.DEFAULT_WIDTH, "scp -help", null,
                    OPTIONS, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD, null);
            printWriter.flush();
            return new String(byteArrayOutputStream.toByteArray());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
