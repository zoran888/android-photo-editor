package com.ahmedadeltito.photoeditorsdk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class StrokeTextEdit extends AppCompatEditText {
    private int _strokeWidth;
    private int _fillColor;

    public StrokeTextEdit(@NonNull Context context) {
        super(context);
    }

    public StrokeTextEdit(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeTextEdit(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStrokeWidth(int width) {
        _strokeWidth = width;
    }

    public void setFillColor(int color) {
        _fillColor = color;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (_strokeWidth > 0) {
            Paint p = getPaint();
            p.setStyle(Paint.Style.FILL);
            setTextColor(_fillColor);
        }
        super.onDraw(canvas);
    }
}
