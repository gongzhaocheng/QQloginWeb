package com.cgz.qqloginweb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.InputFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 输入流工具类
 * @author chenggongzhao
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class StreamUtils {
    public static String  readStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer,0,len);
        }
        is.close();

        String result = baos.toString();
        if ( result.contains("gb2312")){
            return baos.toString("gb2312");
        }else{
            return result;
        }
    }
    public  static Bitmap readBitmap(InputStream is){
        return BitmapFactory.decodeStream(is);
    }
}
