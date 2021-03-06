/*
 * Copyright (C) 2017 - present Instructure, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
package instructure.rceditor;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class RCEInsertDialog extends AppCompatDialogFragment {

    private static final String TITLE = "title";
    private static final String THEME_COLOR = "theme_color";
    private static final String BUTTON_COLOR = "button_color";

    private AppCompatEditText mUrlEditText, mAltEditText;
    private OnResultListener mCallback;

    public interface OnResultListener {
        void onResults(String url, String alt);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public static RCEInsertDialog newInstance(String title, @ColorInt int themeColor, @ColorInt int buttonColor) {
        RCEInsertDialog dialog = new RCEInsertDialog();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putInt(THEME_COLOR, themeColor);
        args.putInt(BUTTON_COLOR, buttonColor);
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.rce_dialog_insert, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(root);
        builder.setTitle(getArguments().getString(TITLE));
        builder.setPositiveButton(R.string.rce_dialogDone, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mCallback != null) {
                    mCallback.onResults(mUrlEditText.getText().toString(), mAltEditText.getText().toString());
                }
                dismiss();
            }
        });
        builder.setNegativeButton(R.string.rce_dialogCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        final int themeColor = getArguments().getInt(THEME_COLOR, Color.BLACK);
        final int highlightColor = RCEUtils.increaseAlpha(themeColor);
        final ColorStateList colorStateList = RCEUtils.makeEditTextColorStateList(Color.BLACK, themeColor);

        mAltEditText = (AppCompatEditText) root.findViewById(R.id.altEditText);
        mUrlEditText = (AppCompatEditText) root.findViewById(R.id.urlEditText);

        mAltEditText.setHighlightColor(highlightColor);
        mUrlEditText.setHighlightColor(highlightColor);

        mAltEditText.setSupportBackgroundTintList(colorStateList);
        mUrlEditText.setSupportBackgroundTintList(colorStateList);

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                final int buttonColor = getArguments().getInt(BUTTON_COLOR, Color.BLACK);
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(buttonColor);
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(buttonColor);
            }
        });

        return dialog;
    }

    public RCEInsertDialog setListener(OnResultListener callback) {
        mCallback = callback;
        return this;
    }
}
