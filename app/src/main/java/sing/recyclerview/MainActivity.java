package sing.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<String>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        adapter = new MyAdapter(list);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        adapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onClick(View v, int position, String a) {
                Toast.makeText(MainActivity.this, "点击了第" + position + "位置，值是" + a, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        list.add("阿斯");
        list.add("前往");
        list.add("从是");
        list.add("现在");
        list.add("分分");
        list.add("还好");
        list.add("由于");
        list.add("噢噢");
        list.add("品牌");
        list.add("哪年");
        list.add("厄尔");
        list.add("哪年");
        list.add("润土");
        list.add("缺你");
        list.add("好的");
        list.add("如果");
        list.add("萨达");
    }

    public void add(View view) {
        adapter.add(2, "新新");
    }

    public void remove(View view) {
        adapter.remove(0);
    }

    public void list(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void grid(View view) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    public void falls(View view) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL));
    }
}
