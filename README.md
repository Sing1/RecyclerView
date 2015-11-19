1、build.gradle 配置：

    dependencies { 
       ......
       compile 'com.android.support:recyclerview-v7:+'
       ......
    }
    
2、布局：

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
        
3、Adapter的写法：

    1)不在继承BaseAdapter,继承RecyclerView.Adapter<VH>，
    2)实现3个方法 onCreateViewHolder()、onBindViewHolder()、getItemCount();
    3)关于继承的类的泛型参数需要自己定义ViewHolder，同样的这个ViewHolder也是继承与RecyclerView.ViewHolder，
    
        class ViewHolder extends RecyclerView.ViewHolder { 
            private TextView textView; 
            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text);
            }
        }
    
    4)关于实现的三个方法
        
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
 
       所用的item的代码也贴出来吧  R.layout.item.xml
 
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
      
