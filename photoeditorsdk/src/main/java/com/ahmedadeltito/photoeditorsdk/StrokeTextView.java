package com.ahmedadeltito.photoeditorsdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    public int spToPx(float sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public int getDeviceWidth(Activity ctx) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        return width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = getPaint();
        String text = getText().toString();
        Rect rect = new Rect();
        p.getTextBounds("y", 0, 1, rect);
        int sp40InPx = /*(int)p.measureText("a")*/rect.width();
        int sp45InPx = /*spToPx(40, getContext())*/rect.height() + 20;
        int textLength = (int)p.measureText(text);
        int deviceWidthPx = getDeviceWidth((Activity) getContext());
        deviceWidthPx = deviceWidthPx - (deviceWidthPx % sp40InPx);
        int displayLimitLength = deviceWidthPx / sp40InPx;

        for (int i = 0; i <= textLength/deviceWidthPx; i++) {
            int endPos = (i+1)*displayLimitLength;
            if (endPos > text.length()) {
                endPos = text.length();
            }

            String lineStr = text.substring(i*displayLimitLength, endPos);

            p.setStyle(Paint.Style.FILL);
            p.setColor(_fillColor);
            canvas.drawText(lineStr, 0, sp45InPx * i + getPivotY(), p);


            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(_strokeWidth);
            p.setColor(_strokeColor);
            canvas.drawText(lineStr, 0, sp45InPx * i + getPivotY(), p);
        }
    }
}
