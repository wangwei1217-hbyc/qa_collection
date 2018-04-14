package com.wangwei.util;

import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ray on 2017/4/14.
 */
public class FileUtils {

    public static InputStream base64ToInputStream(String base64String) throws IOException {
        if(StringUtils.isEmpty(base64String)) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        return new ByteArrayInputStream(decoder.decodeBuffer(base64String));
    }
}
