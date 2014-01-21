package guiamovil.clases;

import java.util.ArrayList;
import java.util.Iterator;
import pucv.prototipo.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private int mGalleryItemBackground;
    private Context myContext;
    private Drawable[] drawables;

    public ImageAdapter(Context c, ArrayList<Drawable> dr) {
    	this.myContext = c;
    	drawables = new Drawable[dr.size()];
    	Iterator<Drawable> it = dr.iterator();
    	int i=0;
    	while(it.hasNext()){
    		drawables[i] = it.next();
    		i=i+1;
    	}
    	TypedArray a = c.obtainStyledAttributes(R.styleable.HelloGallery);
        mGalleryItemBackground = a.getResourceId(
                R.styleable.HelloGallery_android_galleryItemBackground, 0);
    	a.recycle();
    }
    
    /** Returns the amount of images we have defined. */
    public int getCount() { return this.drawables.length; }

    /* Use the array-Positions as unique IDs */
    public Object getItem(int position) { return position; }
    public long getItemId(int position) { return position; }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView i = new ImageView(this.myContext);
        i.setImageDrawable(drawables[position]);
        //i.setImageResource(this.myImageIds[position]);
        /* Image should be scaled as width/height are set. */
        i.setScaleType(ImageView.ScaleType.FIT_XY);
        /* Set the Width/Height of the ImageView. */
        i.setLayoutParams(new Gallery.LayoutParams(150, 150));
        i.setBackgroundResource(mGalleryItemBackground);
        return i;
    }

    /** Returns the size (0.0f to 1.0f) of the views
     * depending on the 'offset' to the center. */
    public float getScale(boolean focused, int offset) {
            /* Formula: 1 / (2 ^ offset) */
        return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset)));
    }
}