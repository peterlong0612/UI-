package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;

import java.io.InputStream;
import java.util.List;

/**
 * 大作业:实现一个抖音消息页面,所需资源已放在res下面
 */
public class Exercises3 extends AppCompatActivity implements  MyAdapter.ListItemClickListener {

    private List<Message> messages;
    private static final String TAG = "Message Views";
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        //获取需要展示的数据
        try{
            InputStream assetInput = getAssets().open("data.xml");
            messages = PullParser.pull2xml(assetInput);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        //定义recyclerview
        RecyclerView recyclerView = findViewById(R.id.rv_list);

        //设置Manager，即设置其样式
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        //mAdapter初始化
        mAdapter = new MyAdapter(messages,this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(String chat_target) {
//        System.out.println(chat_target);
        Intent it = new Intent(this, ChatRoom.class);
        it.putExtra("chat_target", chat_target);
        startActivity(it);
    }

}
