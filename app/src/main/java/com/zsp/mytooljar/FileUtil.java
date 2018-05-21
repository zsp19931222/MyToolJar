package com.zsp.mytooljar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件操作工具类
 */
public class FileUtil {

    /**
     * 在指定的位置创建指定的文件
     * @param filePath 完整的文件路径
     * @param mkdir 是否创建相关的文件夹
     * @throws IOException
     */
    public static void mkFile(String filePath, boolean mkdir) throws IOException{
        File file = new File(filePath);
        /**
         * mkdirs()创建多层目录，mkdir()创建单层目录
         * writeObject时才创建磁盘文件。
         * 若不创建文件，readObject出错。
         */
        file.getParentFile().mkdirs();
        file.createNewFile();
        file = null;
    }

    /**
     * 在指定的位置创建文件夹
     * @param dirPath 文件夹路径
     * @return 若创建成功，则返回True；反之，则返回False
     */
    public static boolean mkDir(String dirPath) {
        return new File(dirPath).mkdirs();
    }

    /**
     * 删除指定的文件
     * @param filePath 文件路径
     * @return 若删除成功，则返回True；反之，则返回False
     */
    public static boolean delFile(String filePath) {
        return new File(filePath).delete();
    }

    /**
     * 删除指定的文件夹
     * @param dirPath 文件夹路径
     * @param delFile 文件夹中是否包含文件
     * @return 若删除成功，则返回True；反之，则返回False
     */
    public static boolean delDir(String dirPath, boolean delFile) {
        if (delFile) {
            File file = new File(dirPath);
            if (file.isFile()) {
                return file.delete();
            } else if (file.isDirectory()) {
                if (file.listFiles().length == 0) {
                    return file.delete();
                } else {
                    int zFiles = file.listFiles().length;
                    File[] delfile = file.listFiles();
                    for (int i = 0; i < zFiles; i++) {
                        if (delfile[i].isDirectory()) {
                            delDir(delfile[i].getAbsolutePath(), true);
                        }
                        delfile[i].delete();
                    }
                    return file.delete();
                }
            } else {
                return false;
            }
        } else {
            return new File(dirPath).delete();
        }
    }

    /**
     * 复制文件/文件夹 若要进行文件夹复制，请勿将目标文件夹置于源文件夹中
     * @param source 源文件（夹）
     * @param target 目标文件（夹）
     * @param isFolder 若进行文件夹复制，则为True；反之为False
     * @throws IOException
     */
    public static void copy(String source, String target, boolean isFolder) throws IOException{
        if (isFolder) {
            new File(target).mkdirs();
            File a = new File(source);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (source.endsWith(File.separator)) {
                    temp = new File(source + file[i]);
                } else {
                    temp = new File(source + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(target + File.separator + temp.getName().toString());
                    byte[] b = new byte[1024];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                } if (temp.isDirectory()) {
                    copy(source + File.separator + file[i], target + File.separator + file[i], true);
                }
            }
        } else {
            int byteread = 0;
            File oldfile = new File(source);
            if (oldfile.exists()) {
                InputStream inputStream = new FileInputStream(source);
                File file = new File(target);
                file.getParentFile().mkdirs();
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                while ((byteread = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer, 0, byteread);
                }
                inputStream.close();
                outputStream.close();
            }
        }
    }
}
