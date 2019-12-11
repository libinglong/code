package com.lbl.code.file;

import java.io.File;


/**
 * @author libinglong
 * <a href="mailto:libinglong@huli.com">libinglong:2376771274@qq.com</a>
 * @since 2019/12/11
 */
public interface FileType {

    LineOpt getLineOpt();

    public static FileType getFileType(File file) {
        if (file.getName().endsWith(".java")){
            return new JavaFileType();
        }
        throw new UnsupportedOperationException(file.getName());
    }

}
