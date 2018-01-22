package com.cn.mvpdemo.service.presenter;


import android.content.Intent;

import com.cn.mvpdemo.service.view.BookView;


/**
 * Created by win764-1 on 2016/12/12.
 */

public interface Presenter {
    void onCreate();

    void onStart();

    void onStop();

    void pause();

    void attachView(BookView view);

    void attachIncomingIntent(Intent intent);
}
