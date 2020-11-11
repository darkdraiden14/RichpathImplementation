package com.example.stickereditor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.annotation.SuppressLint;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.richpath.RichPath;
import com.richpath.RichPathDrawable;
import com.richpath.model.Vector;
import com.richpath.util.XmlParser;
import com.xiaopo.flying.sticker.BitmapStickerIcon;
import com.xiaopo.flying.sticker.DeleteIconEvent;
import com.xiaopo.flying.sticker.DrawableSticker;
import com.xiaopo.flying.sticker.FlipHorizontallyEvent;
import com.xiaopo.flying.sticker.Sticker;
import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.TextSticker;
import com.xiaopo.flying.sticker.ZoomIconEvent;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Arrays;

public class Main3Activity extends AppCompatActivity {

    StickerView stickerView;

    Vector alienVector;
    RichPath stickerPart;
    XmlResourceParser xrp;

    Drawable stickerDrawable;
    RichPathDrawable alienRichPathDrawable;
    ImageView.ScaleType alienScaleType = ImageView.ScaleType.FIT_XY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        stickerView = (StickerView) findViewById(R.id.sticker_view);

        BitmapStickerIcon deleteIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                com.xiaopo.flying.sticker.R.drawable.sticker_ic_close_white_18dp),
                BitmapStickerIcon.LEFT_TOP);
        deleteIcon.setIconEvent(new DeleteIconEvent());

        BitmapStickerIcon zoomIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                com.xiaopo.flying.sticker.R.drawable.sticker_ic_scale_white_18dp),
                BitmapStickerIcon.RIGHT_BOTOM);
        zoomIcon.setIconEvent(new ZoomIconEvent());

        BitmapStickerIcon flipIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                com.xiaopo.flying.sticker.R.drawable.sticker_ic_flip_white_18dp),
                BitmapStickerIcon.RIGHT_TOP);
        flipIcon.setIconEvent(new FlipHorizontallyEvent());

        BitmapStickerIcon heartIcon =
                new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp),
                        BitmapStickerIcon.LEFT_BOTTOM);
        heartIcon.setIconEvent(new HelloIconEvent());

        stickerView.setIcons(Arrays.asList(deleteIcon, zoomIcon, flipIcon, heartIcon));

        //default icon layout
        //stickerView.configDefaultIcons();

        stickerView.setBackgroundColor(Color.WHITE);
        stickerView.setLocked(false);
        stickerView.setConstrained(true);

        loadSticker();
    }

    @SuppressLint("ResourceType")
    private void loadSticker() {
        alienVector = new Vector();
        xrp = getResources().getXml(R.drawable.ic_aliens_12);

        try {
            XmlParser.parseVector(alienVector,xrp,Main3Activity.this);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        alienRichPathDrawable = new RichPathDrawable(alienVector, alienScaleType);


        stickerPart = alienRichPathDrawable.findRichPathByName("path_5");
        assert stickerPart != null;
        stickerPart.setFillColor(Color.BLUE);

        alienRichPathDrawable.invalidateSelf();
        stickerDrawable =  new DrawableSticker(alienRichPathDrawable).getDrawable();

        stickerView.addSticker(new DrawableSticker(stickerDrawable));


/*        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_aliens_12);
        stickerView.addSticker(new DrawableSticker(drawable));
  */  }
}
