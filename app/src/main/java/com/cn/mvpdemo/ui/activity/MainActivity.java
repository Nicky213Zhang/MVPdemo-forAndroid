package com.cn.mvpdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.mvpdemo.R;
import com.cn.mvpdemo.service.entity.Book;
import com.cn.mvpdemo.service.presenter.BookPresenter;
import com.cn.mvpdemo.service.view.BookView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nicky on 2017/12/11.
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.button)
    Button button;
    private BookPresenter mBookPresenter = new BookPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);
    }

    @OnClick({R.id.text, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text:
                break;
            case R.id.button:
                mBookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
                break;
        }
    }

    private BookView mBookView = new BookView() {
        @Override
        public void requestLoading() {
            text.setText("请求中,请稍后..");
        }

        @Override
        public void onSuccess(Book mBook) {
            text.setText(mBook.toString());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {//当活动销毁时记得调用BookPresenter的onStop方法来释放订阅关系，防止内存泄漏。
        super.onDestroy();
        mBookPresenter.onStop();
    }

}
