package com.ahmedadeltito.photoeditorsdk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class HiddenTextEdit extends AppCompatEditText {
    private int _strokeColor;
    private int _strokeWidth;

    public HiddenTextEdit(@NonNull Context context) {
        super(context);
    }

    public HiddenTextEdit(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HiddenTextEdit(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStrokeWidth(int width) {
        _strokeWidth = width;
    }

    public void setFillColor(int color) {
        _strokeColor = getStrokeColor(color);
    }

    private int getStrokeColor(int colorCode) {
        if ( colorCode == Color.parseColor("#FFFFFF") ) {
            return Color.parseColor("#000000");
        }

        return Color.parseColor("#FFFFFF");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (_strokeWidth > 0) {
            Paint p = getPaint();
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(_strokeWidth);
            setTextColor(_strokeColor);
        }
        super.onDraw(canvas);
    }
}
