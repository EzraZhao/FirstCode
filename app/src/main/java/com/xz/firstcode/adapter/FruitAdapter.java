package com.xz.firstcode.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xz.firstcode.R;
import com.xz.firstcode.bean.Fruit;

import java.util.List;

/**
 * Created by xz on 2016/11/29.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter(Context context, int resource, List<Fruit> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取当前的一个实例
        Fruit fruit = getItem(position);

//        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);

        //优化
        View view;
        //再度优化
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);

            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);
        } else {
            view = convertView;

            viewHolder = (ViewHolder) view.getTag();
        }

//        ImageView fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
//        TextView frtitName = (TextView) view.findViewById(R.id.fruit_name);
//        fruitImage.setImageResource(fruit.getImageId());
//        frtitName.setText(fruit.getName());


        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());



        return view;
    }

    class ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
    }

}
