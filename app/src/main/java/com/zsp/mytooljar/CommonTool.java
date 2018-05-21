package com.zsp.mytooljar;

import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/5/21 0021.
 * 常用工具类
 */

public class CommonTool {
    /**
     * 保留两位小数
     * @param s
     * @param et
     */
    public static void keep2Decimal(TextWatcher textWatcher, CharSequence s, EditText et) {
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
                et.removeTextChangedListener(textWatcher);
                et.setText(s);
                et.setSelection(s.length());
                et.addTextChangedListener(textWatcher);
            }
        }
        if (".".equals(s.toString())) {
            s = "0" + s;
            et.removeTextChangedListener(textWatcher);
            et.setText(s);
            et.setSelection(2);
            et.addTextChangedListener(textWatcher);
        }
        if (s.toString().startsWith("0") && s.toString().trim().length() > 1 && !".".equals(s.toString().substring(1, 2))) {
            et.removeTextChangedListener(textWatcher);
            et.setText(s.toString().substring(1, s.length()));
            et.setSelection(s.length() - 1);
            et.addTextChangedListener(textWatcher);
        }
    }
}
