package com.cn.mvpdemo.service.view;



import com.cn.mvpdemo.service.entity.Book;

/**
 * Created by win764-1 on 2016/12/12.
 */

public interface BookView extends View{
    void requestLoading();
    void onSuccess(Book mBook);
    void onError(String result);
}
