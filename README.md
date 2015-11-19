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
      
