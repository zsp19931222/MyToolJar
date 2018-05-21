package com.zsp.mytooljar;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Administrator on 2018/5/21 0021.
 * <p>
 * 颜色相关工具类
 */

public class ColorTool {
    /**
     * 获取一个随机的rgb颜色
     *
     * @return
     */
    public static int getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(150) + 30;//0-190
        int green = random.nextInt(150) + 30;
        int blue = random.nextInt(150) + 30;

        return Color.rgb(red, green, blue);
    }
}
