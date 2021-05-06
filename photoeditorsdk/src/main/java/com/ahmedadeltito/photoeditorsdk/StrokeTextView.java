package com.ahmedadeltito.photoeditorsdk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

public class StrokeTextView extends androidx.appcompat.widget.AppCompatTextView {
    private int _strokeColor;
    private int _strokeWidth;
    private int _fillColor;
    public StrokeTextView(Context context) {
        super(context);
    }

    public StrokeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStrokeWidth(int width) {
        _strokeWidth = width;
    }

    public void setFillColor(int color) {
        _strokeColor = getStrokeColor(color);
        _fillColor = color;
    }

    private int getStrokeColor(int colorCode) {
        if (colorCode == Color.parseColor("#000000")) {
            return Color.parseColor("#ffffff");
        }

        return Color.parseColor("#000000");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = getPaint();

        p.setStyle(Paint.Style.FILL);
        p.setColor(_fillColor);
        canvas.drawText(getText().toString(), 0, getPivotY(), p);

        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(_strokeWidth);
        p.setColor(_strokeColor);
        canvas.drawText(getText().toString(), 0, getPivotY(), p);
    }
}
