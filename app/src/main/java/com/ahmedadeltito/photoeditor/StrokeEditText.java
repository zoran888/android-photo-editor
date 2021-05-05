package com.ahmedadeltito.photoeditor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class StrokeEditText extends AppCompatEditText {
    private int _strokeColor;
    private int _strokeWidth;
    private int _fillColor;

    public StrokeEditText(@NonNull Context context) {
        super(context);
    }

    public StrokeEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        if ( colorCode == getResources().getColor(R.color.white) ) {
            return getResources().getColor(R.color.black);
        }

        return getResources().getColor(R.color.white);
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
