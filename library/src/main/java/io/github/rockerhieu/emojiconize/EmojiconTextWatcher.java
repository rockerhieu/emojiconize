/*
 * Copyright 2016 Hieu Rocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.rockerhieu.emojiconize;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import io.github.rockerhieu.emojicon.EmojiconHandler;

/**
 * @author Hieu Rocker (rockerhieu@gmail.com)
 */
public class EmojiconTextWatcher implements TextWatcher {
    static final int START_DEFAULT = -1;
    static final int START_SKIP_TEXT_WATCHER = -2;
    private TextView mTextView;
    private final Emojiconize.Builder builder;
    private int start = START_DEFAULT;

    public EmojiconTextWatcher(TextView textView, Emojiconize.Builder builder) {
        this.mTextView = textView;
        this.builder = builder;

        if (!TextUtils.isEmpty(textView.getText())) {
            if (textView instanceof EditText) {
                updateText(((EditText) textView).getText());
            } else {
                Editable text = new SpannableStringBuilder(textView.getText());
                updateText(text);
                mTextView.setText(text);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (this.start == START_SKIP_TEXT_WATCHER) return;
        if (this.start == START_DEFAULT && count > 0) {
            this.start = start;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (start >= 0) {
            start = START_SKIP_TEXT_WATCHER;
            updateText(editable);
            start = START_DEFAULT;
        }
    }

    private void updateText(Editable editable) {
        int textSize = (int) mTextView.getTextSize();
        EmojiconHandler.addEmojis(mTextView.getContext(), editable,
                builder.size > 0 ? builder.size : textSize,
                builder.alignment, textSize);
    }
}
