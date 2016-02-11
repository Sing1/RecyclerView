1、build.gradle 配置：
```Java
    dependencies { 
       ......
       compile 'com.android.support:recyclerview-v7:+'
       ......
    }
``` 
2、布局：
```Java
    <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```
3、Adapter的写法：
    1)不在继承BaseAdapter,继承RecyclerView.Adapter<VH>，
    2)实现3个方法 onCreateViewHolder()、onBindViewHolder()、getItemCount();
    3)关于继承的类的泛型参数需要自己定义ViewHolder，同样的这个ViewHolder也是继承与RecyclerView.ViewHolder，
```Java
        class ViewHolder extends RecyclerView.ViewHolder { 
            private TextView textView; 
            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text);
            }
        }
```
    4)关于实现的三个方法
```Java
        private List<String> list;
        private LayoutInflater inflater;

        public MyAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
```
       所用的item的代码也贴出来吧  R.layout.item.xml
```Java
        <?xml version="1.0" encoding="utf-8"?>
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="#123123"> 
            
            <TextView
                android:id="@+id/text"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:gravity="center"
                android:text="1"
                android:textColor="#fff"
                android:textSize="20dp" />

        </LinearLayout>
```
    到这里adapter基本差不多完了。
4、activity中的使用
```Java
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<String>();
    private MyAdapter adapter;
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        
        init();//添加list的数据 
        
        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        adapter = new MyAdapter(list);
        
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//必须有 
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(StaggeredGridLayoutManager.HORIZONTAL,3));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }
```
5、关于装饰器的添加
```Java
    recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
```
    下载地址：http://pan.baidu.com/s/1mg4IdZy
6、关于事件的点击
    点击事件也是比较灵活，自己随便定义，接口类型
```Java
    interface onItemClickListener{
        void onClick(View v, int position, String a);
    }
```
    在adapter里定义，并提供set方法
```Java
    private onItemClickListener onItemClickListener;
    
    public void setOnItemClickListener(MyAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
```
    在ViewHolder里进行绑定
```Java
    class ViewHolder extends RecyclerView.ViewHolder {
    
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null){
                        onItemClickListener.onClick(v,getLayoutPosition(),list.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
```
    然后在activity中进行调用:
```Java
    adapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
        @Override
        public void onClick(View v, int position, String a) {
            Toast.makeText(MainActivity.this, "点击了第" + position + "位置，值是" + a, Toast.LENGTH_LONG).show();
        }
    });
```
7、数据的增加、删除和刷新
    在这里， notifyDataSetChanged();的方法不再建议使用，推荐单条刷新。
```Java
    public void add(int position,String a){
        if (list != null){
            list.add(position,a);
            notifyItemInserted(position);
        }
    }

    public void remove(int position){
        if (list != null && list.size() > 0 ){
            list.remove(position);
            notifyItemRemoved(position);
        }
    }
```
