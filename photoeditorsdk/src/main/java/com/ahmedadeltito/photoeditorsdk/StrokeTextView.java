package com.ahmedadeltito.photoeditorsdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
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
        int sp40InPx = rect.width();
        int sp45InPx = rect.height() + 20;
        int deviceWidthPx = getDeviceWidth((Activity) getContext());
        deviceWidthPx = deviceWidthPx - (deviceWidthPx % sp40InPx);
        int displayLimitLength = deviceWidthPx / sp40InPx;

        String[] strings = text.split("\n");
        int yOffset = 0;

        int maxCenterXOffset = 0;
        if (strings.length > 1) {
            for (String each : strings) {
                int centerOffset = sp40InPx * each.length() / 2;
                if (centerOffset > maxCenterXOffset) {
                    maxCenterXOffset = centerOffset;
                }
            }
        }

        for (String each: strings) {
            int textLength = (int)p.measureText(each);
            for (int i = 0; i <= textLength/deviceWidthPx; i++) {
                int endPos = (i+1)*displayLimitLength;
                if (endPos > each.length()) {
                    endPos = each.length();
                }
                int xOffset = strings.length > 1 ? maxCenterXOffset - (sp40InPx * each.length() / 2) : 0;
                String lineStr = each.substring(i*displayLimitLength, endPos);
                p.setStyle(Paint.Style.FILL);
                p.setColor(_fillColor);
                canvas.drawText(lineStr, xOffset, sp45InPx * i + getPivotY() + yOffset, p);

                p.setStyle(Paint.Style.STROKE);
                p.setStrokeWidth(_strokeWidth);
                p.setColor(_strokeColor);
                canvas.drawText(lineStr, xOffset, sp45InPx * i + getPivotY() + yOffset, p);
            }

            yOffset += sp45InPx;
        }
    }
}
