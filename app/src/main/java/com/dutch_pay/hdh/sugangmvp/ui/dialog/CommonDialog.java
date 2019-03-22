package com.dutch_pay.hdh.sugangmvp.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dutch_pay.hdh.sugangmvp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonDialog implements DialogInterface {
    public static final int BUTTON_POSITIVE = DialogInterface.BUTTON_POSITIVE;
    public static final int BUTTON_NEGATIVE = DialogInterface.BUTTON_NEGATIVE;
    public static final int BUTTON_EXIT = -100;

    private View mContainer = null;

    private RelativeLayout mHeadContainer = null;
    private ImageView mExitButton = null;
    private TextView mTitleTextView = null;
    private RelativeLayout mBodyContainer = null;
    private TextView mBodyTextView = null;
    private ImageView mIcon = null;

    private LinearLayout mTailContainer = null;
    private Button mNegativeButton = null;
    private Button mPositiveButton = null;

    private ListView mListView = null;

    // button_basic listener
    private OnClickListener mPositiveButtonOnClickListener = null;
    private OnClickListener mNegativeButtonOnClickListener = null;

    private OnClickListener mExitButtonOnClickListener = null;

    private OnClickListener mNullOnClickListener = new OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };

    private OnCancelListener mOnCancelListener = null;
    private OnDismissListener mOnDismissListener = null;

    private Dialog mDialog = null;

    private final Context mContext;

    public CommonDialog(Context context) {
        mContext = context;

        mContainer = View.inflate(mContext, R.layout.layout_common_alert, null);
        initialize();

        mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setCancelable(false);
        mDialog.setContentView(mContainer);
    }

    private View.OnClickListener mButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (null == v) {
                return;
            }
            switch (v.getId()) {
                case R.id.common_dialog_tail_button_cancel:
                    if (null != mNegativeButtonOnClickListener) {
                        mNegativeButtonOnClickListener.onClick(CommonDialog.this, BUTTON_NEGATIVE);
                    }
                    break;
                case R.id.common_dialog_tail_button_confirm:
                    if (null != mPositiveButtonOnClickListener) {
                        mPositiveButtonOnClickListener.onClick(CommonDialog.this, BUTTON_POSITIVE);
                    }
                    break;
                case R.id.common_dialog_head_cancel:
                    if (null != mExitButtonOnClickListener) {
                        mExitButtonOnClickListener.onClick(CommonDialog.this, BUTTON_EXIT);
                    }
                    break;
                default:
                    return;
            }

            return;
        }
    };

    private void initialize() {
        if (null == mContainer) {
            return;
        }

        mHeadContainer = (RelativeLayout) mContainer.findViewById(R.id.common_dialog_head_container);
        /*if (null != mHeadContainer) {
            mHeadContainer.setVisibility(View.GONE);
        }*/

        mExitButton = (ImageView) mContainer.findViewById(R.id.common_dialog_head_cancel);
        if (null != mExitButton) {
            mExitButton.setOnClickListener(mButtonOnClickListener);
        }

        mTitleTextView = (TextView) mContainer.findViewById(R.id.common_dialog_title);
        /*if (null != mTitleTextView) {
            mHeadContainer.setVisibility(View.GONE);
        }*/

        mIcon = (ImageView) mContainer.findViewById(R.id.common_dialog_body_icon);

        mBodyContainer = (RelativeLayout) mContainer.findViewById(R.id.common_dialog_body_container);
        if (null != mBodyContainer) {
            mBodyTextView = (TextView) mContainer.findViewById(R.id.common_dialog_body_msg);
            mListView = (ListView) mContainer.findViewById(R.id.common_dialog_body_listview);
            if (null != mListView) {
                mListView.setVisibility(View.GONE);
            }
        }

        mTailContainer = (LinearLayout) mContainer.findViewById(R.id.common_dialog_tail_container);
        if (null != mTailContainer) {
            mNegativeButton = (Button) mContainer.findViewById(R.id.common_dialog_tail_button_cancel);
            if (null != mNegativeButton) {
                mNegativeButton.setOnClickListener(mButtonOnClickListener);
            }

            mPositiveButton = (Button) mContainer.findViewById(R.id.common_dialog_tail_button_confirm);
            if (null != mPositiveButton) {
                mPositiveButton.setOnClickListener(mButtonOnClickListener);
            }

            mTailContainer.setVisibility(View.GONE);
        }
    }
    public void setHideTop (){
        mHeadContainer.setVisibility(View.GONE);
    }

    public void setBackgroundColor(int bodyResId, int separatorResId) {
        mContainer.setBackgroundResource(bodyResId);
        mBodyContainer.setBackgroundResource(bodyResId);
    }

    public void setExitButton(OnClickListener listener) {
        if (null != mHeadContainer) {
            mHeadContainer.setVisibility(View.VISIBLE);
            mExitButton.setVisibility(View.VISIBLE);

            if (null == listener) {
                mExitButtonOnClickListener = new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
            } else {
                mExitButtonOnClickListener = listener;
            }
        }
    }

    public void setIcon(int resourceId){
        mIcon.setImageResource(resourceId);
        mIcon.setVisibility(View.VISIBLE);
    }

    public void setViewPadding(int left, int top, int right, int height) {
        if (null != mBodyContainer) {
            mBodyContainer.setPadding(left, top, right, height);
        }
    }

    public void setTitle(int titleId) {
        if (null != mTitleTextView) {
            mTitleTextView.setText(titleId);
        }
    }

    public void setTitle(CharSequence title) {
        if (null != mTitleTextView) {
            mTitleTextView.setText(title);
        }
    }

    public void setMessage(int messageId) {
        if (null != mBodyTextView) {
            mBodyTextView.setText(messageId);
        }
    }

    public void setMessage(CharSequence message) {
        if (null != mBodyTextView) {
            mBodyTextView.setText(message);
        }
    }


    public void setPositiveButton(int textId, OnClickListener listener) {
        if (null != mTailContainer) {
            mTailContainer.setVisibility(View.VISIBLE);
        }

        if (null != mPositiveButton) {
            mPositiveButton.setText(textId);
            mPositiveButton.setVisibility(View.VISIBLE);
            mPositiveButton.setOnClickListener(mButtonOnClickListener);
        }

        if (null == listener) {
            mPositiveButtonOnClickListener = mNullOnClickListener;
        } else {
            mPositiveButtonOnClickListener = listener;
        }
    }

    public void setPositiveButton(CharSequence text, OnClickListener listener) {
        if (null != mTailContainer) {
            mTailContainer.setVisibility(View.VISIBLE);
        }

        if (null != mPositiveButton) {
            mPositiveButton.setText(text);
            mPositiveButton.setVisibility(View.VISIBLE);
            mPositiveButton.setOnClickListener(mButtonOnClickListener);
        }

        if (null == listener) {
            mPositiveButtonOnClickListener = mNullOnClickListener;
        } else {
            mPositiveButtonOnClickListener = listener;
        }
    }

    public void setNegativeButton(int textId, OnClickListener listener) {
        if (null != mTailContainer) {
            mTailContainer.setVisibility(View.VISIBLE);
        }

        if (null != mNegativeButton) {
            mNegativeButton.setText(textId);
            mNegativeButton.setVisibility(View.VISIBLE);
            mNegativeButton.setOnClickListener(mButtonOnClickListener);
        }

        if (null == listener) {
            mNegativeButtonOnClickListener = mNullOnClickListener;
        } else {
            mNegativeButtonOnClickListener = listener;
        }
    }

    public void setNegativeButton(CharSequence text, OnClickListener listener) {
        if (null != mTailContainer) {
            mTailContainer.setVisibility(View.VISIBLE);
        }

        if (null != mNegativeButton) {
            mNegativeButton.setText(text);
            mNegativeButton.setVisibility(View.VISIBLE);
            mNegativeButton.setOnClickListener(mButtonOnClickListener);
        }

        if (null == listener) {
            mNegativeButtonOnClickListener = mNullOnClickListener;
        } else {
            mNegativeButtonOnClickListener = listener;
        }
    }

    public void setCancelable(boolean cancelable) {
        if (null != mDialog) {
            mDialog.setCancelable(cancelable);
        }
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        mOnCancelListener = onCancelListener;
        if (null != mDialog) {
            mDialog.setOnCancelListener(mOnCancelListener);
        }
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
        if (null != mDialog) {
            mDialog.setOnDismissListener(mOnDismissListener);
        }
    }

    public void setSingleChoiceItems(int itemsId, int checkedItem, OnClickListener listener) {
        //return super.setSingleChoiceItems(itemsId, checkedItem, listener);
        // TODO: implements
    }

    public void setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, OnClickListener listener) {
        //return super.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener);
        // TODO: implements
    }

    public void setSingleChoiceItems(CharSequence[] items, int checkedItem, final OnClickListener listener) {
        if (null != mBodyContainer) {
            if (null != mBodyTextView) {
                mBodyTextView.setVisibility(View.GONE);
            }

            if (null != mListView) {
                mListView.setVisibility(View.VISIBLE);
                mListView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if (null != listener) {
                                    listener.onClick(CommonDialog.this, position);
                                }
                            }
                        });

                List list = new ArrayList<String>();
                Collections.addAll(list, items);

                mListView.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_single_choice , list));

                mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                mListView.setItemChecked(checkedItem, true);
            }
        }
    }

    public void setSingleChoiceItems(ListAdapter adapter, int checkedItem, final OnClickListener listener) {
        if (null != mBodyContainer) {
            if (null != mBodyTextView) {
                mBodyTextView.setVisibility(View.GONE);
            }

            if (null != mListView) {
                mListView.setVisibility(View.VISIBLE);
                mListView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if (null != listener) {
                                    listener.onClick(CommonDialog.this, position);
                                }
                            }
                        });
                if (null != adapter) {
                    mListView.setAdapter(adapter);
                }

                mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                mListView.isItemChecked(checkedItem);
            }
        }
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        //return super.setOnItemSelectedListener(listener);
        // TODO: implements
    }

    public void setView(View view) {
        if (null != view && null != mBodyContainer) {
            mBodyContainer.removeAllViews();

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            view.setLayoutParams(params);
            mBodyContainer.addView(view);
        }
    }

    public void show() {
        if (null != mDialog) {
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mDialog.show();
        }
    }

    public void cancel() {
        if (null != mDialog) {
            mDialog.cancel();
        }
    }

    public void dismiss() {
        if (null != mDialog) {
            mDialog.dismiss();
        }
    }

    public boolean isShowing() {
        if (null != mDialog) {
            return mDialog.isShowing();
        }

        return false;
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public Context getContext() {
        if (null != mDialog) {
            return mDialog.getContext();
        }

        return null;
    }

    public Bundle onSaveInstanceState() {
        if (null != mDialog) {
            return mDialog.onSaveInstanceState();
        }
        return null;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (null != mDialog) {
            mDialog.onRestoreInstanceState(savedInstanceState);
        }
    }
}
