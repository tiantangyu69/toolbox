/*******************************************************
 * @author LIZHTIAO
 * @date 2014-3-27 下午1:05:49
 * @name CommandUtil.java
 * @JDK version 1.6
 * @version 0
 ******************************************************/
package me.flyness.toolbox.system;

import java.io.IOException;

/**
 * @author LIZHITAO 调用系统的命令执行相关命令
 */
public class CommandUtil {
    public static void execute(String command) {
        try {
            if (SystemUtils.IS_OS_WINDOWS) {// windows 系统
                Runtime rt = Runtime.getRuntime();
                rt.exec("cmd /c" + command);
            } else if (SystemUtils.IS_OS_LINUX) {// linux 系统
                Runtime.getRuntime().exec(new String[]{"sh", "-c", command});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}