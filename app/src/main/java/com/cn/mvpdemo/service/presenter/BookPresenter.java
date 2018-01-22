package com.cn.mvpdemo.service.presenter;

import android.content.Context;
import android.content.Intent;


import com.cn.mvpdemo.service.entity.Book;
import com.cn.mvpdemo.service.manager.DataManager;
import com.cn.mvpdemo.service.view.BookView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class BookPresenter implements Presenter {
    private DataManager manager;
    private Context mContext;
    private BookView mBookView;
    //CompositeSubscription是用来存放RxJava中的订阅关系的(注意请求完数据要及时清掉这个订阅关系，不然会发生内存泄漏)
    private CompositeSubscription mCompositeSubscription;
    private Book mBook;

    public BookPresenter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(BookView view) {
        mBookView = (BookView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchBooks(String name, String tag, int start, int count) {
        if (mBookView != null) {
            mBookView.requestLoading();
        }
        mCompositeSubscription.add(manager.getSearchBooks(name, tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        if (mBook != null) {
                            mBookView.onSuccess(mBook);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBookView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(Book book) {
                        mBook = book;
                    }
                })
        );
    }


}
