package project.vichita.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Vichita Fongmala on 2/4/15.
 */
public class GridAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private ShotList shotList;

    public GridAdapter(Context context, ShotList shotList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.shotList = shotList;
    }

    @Override
    public int getCount() {
        return shotList.getShots().size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView==null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(4,4,4,4);
        }else{
            imageView = (ImageView) convertView;
        }

        Picasso.with(context)
                .load(shotList.getShots().get(position).getImageUrl())
                .into(imageView);

        return imageView;
    }
}
