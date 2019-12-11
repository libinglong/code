package com.lbl.code.file;


/**
 * @author libinglong
 * <a href="mailto:libinglong@huli.com">libinglong:2376771274@qq.com</a>
 * @since 2019/12/11
 */
public class JavaFileType implements FileType{

    @Override
    public LineOpt getLineOpt() {
        return new JavaLineOpt();
    }

    public static class JavaLineOpt implements LineOpt{

        @Override
        public boolean isComment(String line) {
            return line.trim().startsWith("//")
                    || line.startsWith("*")
                    || line.startsWith("/**")
                    || line.startsWith("/*");
        }

    }
}
