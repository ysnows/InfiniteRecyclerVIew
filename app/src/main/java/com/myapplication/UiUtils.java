
package com.myapplication;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

public class UiUtils {
    static public int getScreenWidthPixels(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels;
    }


    static public int getScreenHeightPixels(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * dp转px
     *
     * @param context
     * @param dip
     * @return
     */
    static public int dp2px(Context context, int dip) {
        return (int) (dip * getScreenDensity(context) + 0.5f);
    }

    static public float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                    .getMetrics(dm);
            return dm.density;
        } catch (Exception e) {
            return DisplayMetrics.DENSITY_DEFAULT;
        }
    }


//    Rect frame = new Rect();
//    getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//    int statusBarHeight = frame.top;
//    上面这种方法基本上是可以的，但是下面这种方法更牛逼


    /**
     * 状态栏高度获取方法
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 75;
        }
    }


    /**
     * 设置TopPadding为StatusBar的高度
     *
     * @param view
     * @param colorPrimary
     */
    public static void setStatusBarPadding(View view, int colorPrimary) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (colorPrimary > 0) {
                view.setBackgroundResource(colorPrimary);
            }
            view.setPadding(0, UiUtils.getStatusBarHeight(view.getContext()), 0, 0);

//            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
//            layoutParams.topMargin = UiUtils.getStatusBarHeight(view.getContext());
//            layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT;
//            layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
//            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * 设置TopPadding为StatusBar的高度
     *
     * @param view
     * @param colorPrimary
     */
    public static void setStatusBarMargin(View view, int colorPrimary) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (colorPrimary > 0) {
                view.setBackgroundResource(colorPrimary);
            }

            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.topMargin = UiUtils.getStatusBarHeight(view.getContext());
            view.setLayoutParams(layoutParams);
        }
    }


    /**
     * 根据数据计算rv的高度
     *
     * @param size
     * @param colCount
     * @param rowHeight
     * @return
     */

    public static int getRVHeith(Context context, int size, int colCount, int rowHeight) {
        rowHeight = dp2px(context, rowHeight);
        if (size % colCount == 0) {
            return size / colCount * rowHeight;
        } else {
            return ((size / colCount) + 1) * rowHeight;

        }
    }

    /**
     * 根据数据计算rv的高度
     *
     * @param size
     * @param colCount
     * @param rowHeight
     * @return
     */

    public static int getRVHeithWithPx(Context context, int size, int colCount, int rowHeight) {
        if (size % colCount == 0) {
            return size / colCount * rowHeight;
        } else {
            return ((size / colCount) + 1) * rowHeight;
        }
    }


    /**
     * 根据数据计算rv的高度
     *
     * @param size
     * @param colCount
     * @param rowHeight
     * @return
     */
    public static void setRVHeith(Context context, int size, int colCount, int rowHeight, View view) {
        if (view == null) {
            return;
        }
        int rvHeith = UiUtils.getRVHeith(context, size, colCount, rowHeight);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = rvHeith;
        view.setLayoutParams(layoutParams);
    }

    /**
     * 根据数据计算rv的高度
     *
     * @param size
     * @param colCount
     * @param rowHeightPx
     * @return
     */
    public static void setRVHeithWithPx(Context context, int size, int colCount, int rowHeightPx, View view) {
        if (view == null) {
            return;
        }

        int rvHeith = UiUtils.getRVHeithWithPx(context, size, colCount, rowHeightPx);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = rvHeith;
        view.setLayoutParams(layoutParams);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    static public int dp2Px(Context context, float dip) {
        return (int) (dip * getScreenDensity(context) + 0.5f);
    }
}
