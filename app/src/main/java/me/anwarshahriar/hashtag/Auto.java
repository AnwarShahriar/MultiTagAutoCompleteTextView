package me.anwarshahriar.hashtag;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;

/**
 * Created by shishir on 3/22/15.
 */
public class Auto extends MultiAutoCompleteTextView {
    public Auto(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean enoughToFilter() {
        boolean isEnough = super.enoughToFilter();
        return isEnough;
    }

    @Override
    public int getSelectionEnd() {
        int end = super.getSelectionEnd();
        return end;
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        super.performFiltering(text, keyCode);
    }

    @Override
    protected void performFiltering(CharSequence text, int start, int end, int keyCode) {
        if (text.charAt(start) == '@') {
            start++;
        }

        /*// If you want to stop showing dropdown when only triggerChar is present
        if (end - start == 0 && (end != 0 && start != 0)) {
            return;
        }*/

        super.performFiltering(text, start, end, keyCode);
    }
}
