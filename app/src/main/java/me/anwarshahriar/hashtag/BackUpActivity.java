package me.anwarshahriar.hashtag;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;


public class BackUpActivity extends Activity {
    Auto matv;
    ArrayAdapter<String> peopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        peopleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{
                "Jalal",
                "Kamal",
                "Jamil",
                "Joy",
                "Kabir"
        });

        matv = (Auto) findViewById(R.id.matv);
        matv.setTokenizer(new PeopleAndHashTagTokenizer());
        matv.setThreshold(1);
        matv.setAdapter(peopleAdapter);
    }

    public class PeopleAndHashTagTokenizer implements MultiAutoCompleteTextView.Tokenizer {
        char atTheRateOf = '@';

        public int findTokenStart(CharSequence text, int cursor) {
            int i = cursor;

            while (i > 0 && (text.charAt(i - 1) != atTheRateOf)) {
                i--;
            }

            while (i < cursor && (text.charAt(i) == atTheRateOf)) {
                i++;
            }

            if (i == 0 && cursor > 0 && (text.charAt(i) != atTheRateOf)) {
                return cursor;
            }

            if(i == 0 && cursor == 0) {
                return cursor;
            }

            return i - 1;
        }

        public int findTokenEnd(CharSequence text, int cursor) {
//            int i = cursor;
//            int len = text.length();
//
//            while (i < len) {
//                if (text.charAt(i) == atTheRateOf) {
//                    return i;
//                } else {
//                    i++;
//                }
//            }

            return -1;
        }

        public CharSequence terminateToken(CharSequence text) {
            text = "@" + text;

            int i = text.length();

            while (i > 0 && text.charAt(i - 1) == ' ') {
                i--;
            }

            if (i > 0 && (text.charAt(i - 1) == atTheRateOf)) {
                return text;
            } else {
                if (text instanceof Spanned) {
                    SpannableString sp = new SpannableString(text + " ");
                    TextUtils.copySpansFrom((Spanned) text, 0, text.length(),
                            Object.class, sp, 0);
                    return sp;
                } else {
                    SpannableString sp = new SpannableString(text + " ");
                    sp.setSpan(new BackgroundColorSpan(Color.parseColor("#57c4c9")), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    sp.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    return sp;
                }
            }
        }
    }
}
