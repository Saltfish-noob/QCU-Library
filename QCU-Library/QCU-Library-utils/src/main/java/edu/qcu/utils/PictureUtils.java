package edu.qcu.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.bytedeco.javacpp.Loader;

import java.io.File;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class PictureUtils {

    //修改分辨率
    public static String modifyResolution(
            String imagePath, String outputDir,String fileName,Integer width, Integer height) throws Exception {
        List<String> paths = Splitter.on(".").splitToList(imagePath);
        String ext = paths.get(paths.size() - 1);
        /*if (!Arrays.asList("jpg", "png").contains(ext)) {
            throw new Exception("format error");
        }*/
        String resultPath =
                Joiner.on(File.separator).join(Arrays.asList(outputDir, fileName));
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        ProcessBuilder builder =
                new ProcessBuilder(
                        ffmpeg,
                        "-i",
                        imagePath,
                        "-vf",
                        MessageFormat.format("scale={0}:{1}", String.valueOf(width), String.valueOf(height)),
                        resultPath);
        builder.inheritIO().start().waitFor();
        return resultPath;
    }
}
