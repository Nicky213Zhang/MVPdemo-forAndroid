package com.cn.mvpdemo.service.manager;

import android.content.Context;

import com.cn.mvpdemo.service.RetrofitHelper;
import com.cn.mvpdemo.service.RetrofitService;
import com.cn.mvpdemo.service.entity.Book;

import rx.Observable;


/**
 * Created by Nicky on 2017/12/11.
 */
public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
    public Observable<Book> getSearchBooks(String name, String tag, int start, int count){
        return mRetrofitService.getSearchBooks(name,tag,start,count);
    }
}
