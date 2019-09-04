package com.frame.mobilefast;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public interface MyListenerComRetornoDoNomeDoComponente {

    void onClick(View var1, int var2, String var3);

    void onClick(View var1, String var2);

    boolean onEditorAction(TextView var1, int var2, KeyEvent var3, String var4);

    void refresh(String var1);

    void result(DefaultListModel var1, String var2);

    void result(DefaultModelItem var1, String var2);

    void resultItem(List var1, String var2);

    void resultModel(List var1, String var2);
}
