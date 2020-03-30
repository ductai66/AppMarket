package com.tai06.dothetai.appmarketphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

    private Context context;
    private String[] numberWord;
    private int[] numberImage;
    private int[] numberPrice;
    private LayoutInflater inflater;

    public MainAdapter(Context c, String[] numberWord, int[] numberImage,int[] numberPrice) {
        context = c;
        this.numberWord = numberWord;
        this.numberImage = numberImage;
        this.numberPrice = numberPrice;
    }

    @Override
    public int getCount() {
        return numberWord.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView = inflater.inflate(R.layout.row_item,null);
        }
        ImageView imageView = convertView.findViewById(R.id.image_view);
        TextView textView = convertView.findViewById(R.id.text_view);
        TextView textView1 = convertView.findViewById(R.id.text_money);
        imageView.setImageResource(numberImage[position]);
        textView.setText(numberWord[position]);
        textView1.setText(""+numberPrice[position]);
        return convertView;
    }
}
