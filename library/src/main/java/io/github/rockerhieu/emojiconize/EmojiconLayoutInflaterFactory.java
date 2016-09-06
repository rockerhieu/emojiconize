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

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import io.github.rockerhieu.emojicon.EmojiconEditText;
import io.github.rockerhieu.emojicon.EmojiconMultiAutoCompleteTextView;
import io.github.rockerhieu.emojicon.EmojiconTextView;

/**
 * @author Hieu Rocker (rockerhieu@gmail.com)
 */
class EmojiconLayoutInflaterFactory implements LayoutInflaterFactory {
    private final Emojiconize.Builder builder;

    public EmojiconLayoutInflaterFactory(Emojiconize.Builder builder) {
        this.builder = builder;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = builder.activity.getDelegate().createView(parent, name, context, attrs);
        if (view instanceof EmojiconTextView
                || view instanceof EmojiconEditText
                || view instanceof EmojiconMultiAutoCompleteTextView) {
            return view;
        }

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            try {
                view = inflater.createView(name, null, attrs);
            } catch (Exception ignored) {
            }
        }

        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.addTextChangedListener(new EmojiconTextWatcher(textView, builder));
        }
        return view;
    }
}
