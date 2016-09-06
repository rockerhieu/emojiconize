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

import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.style.DynamicDrawableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.rockerhieu.emojicon.emoji.Emojicon;

/**
 * @author Hieu Rocker (rockerhieu@gmail.com)
 */
public final class Emojiconize {
    public static Builder activity(AppCompatActivity activity) {
        return new Builder(activity);
    }

    public static Builder view(View view) {
        return new Builder(view);
    }

    public static final class Builder {
        AppCompatActivity activity;
        View view;
        int size = -1;
        @Emojicon.Alignment
        int alignment = DynamicDrawableSpan.ALIGN_BASELINE;
        boolean gone = false;

        public Builder(@NonNull AppCompatActivity activity) {
            if (activity == null) {
                throw new IllegalArgumentException("The given activity must not be null");
            }
            this.activity = activity;
        }

        public Builder(@NonNull View view) {
            if (view == null) {
                throw new IllegalArgumentException("The given view must not be null");
            }
            this.view = view;
        }

        public Builder size(int size) {
            checkGone();
            this.size = size;
            return this;
        }

        public Builder alignment(@Emojicon.Alignment int alignment) {
            checkGone();
            this.alignment = alignment;
            return this;
        }

        public void go() {
            checkGone();
            if (activity != null) {
                LayoutInflaterCompat.setFactory(activity.getLayoutInflater(),
                        new EmojiconLayoutInflaterFactory(this));
            } else {
                emojiconize(view);
            }
            gone = true;
        }

        private void emojiconize(View view) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int index = 0; index < viewGroup.getChildCount(); index++) {
                    emojiconize(viewGroup.getChildAt(index));
                }
            } else if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.addTextChangedListener(new EmojiconTextWatcher(textView, this));
            }
        }

        void checkGone() {
            if (gone) {
                throw new IllegalStateException("Sorry, it's already gone");
            }
        }
    }
}
