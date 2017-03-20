package com.android.loter.ui.fragment.fifth.child;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.widget.ActionSheetDialog;
import com.android.loter.util.imageloader.ImageLoaderUtil;
import com.android.loter.util.log.Logger;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.lijunguan.imgselector.ImageSelector;

import static com.android.loter.util.TimeUtil.getTime;

/**
 * Created by we-win on 2017/3/19.
 */

public class InfomationFragment extends BaseBackFragment implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_avator)
    CircleImageView mIvAvator;

    String imagePath = "http://img.cnjiayu.net/3211573049-3310678237-21-0.jpg";
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;

    public static BaseFragment newInstance() {
        InfomationFragment infomationFragment = new InfomationFragment();
        return infomationFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_improve_infomation;
    }

    @Override
    protected void initData() {
        mTvTitle.setText(getResources().getString(R.string.improveinfomationactivity_title));
        ImageLoaderUtil.getInstance().loadImage(imagePath, R.mipmap.ic_launcher, mIvAvator);
    }

    @Override
    protected void bindEvent() {
        mIvAvator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_avator:
                new ActionSheetDialog(_mActivity)
                        .builder()
                        .setTitle("设置头像")
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("头像预览", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        avatarPreview();
                                    }
                                })
                        .addSheetItem("选择头像", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        ImageSelector.getInstance()
                                                .setSelectModel(ImageSelector.AVATOR_MODE)
                                                .setMaxCount(1)
                                                .setGridColumns(3)
                                                .setShowCamera(true)
                                                .setToolbarColor(getResources().getColor(R.color.colorPrimary))
                                                .startSelect(_mActivity);
                                    }
                                })
                        .show();
                break;
        }

    }

    private void avatarPreview() {
        showDialog = new Dialog(_mActivity, R.style.FullScreenDialog);
        Display display = _mActivity.getWindowManager()
                .getDefaultDisplay();
        View main = LayoutInflater.from(_mActivity).inflate(
                R.layout.dialog_avatar_preview, null);
        ImageView iv_image = (ImageView) main.findViewById(R.id.dialog_img_big);
        RelativeLayout dialog_img_ly = (RelativeLayout) main.findViewById(R.id.dialog_img_ly);
        ImageLoaderUtil.getInstance().loadImage(imagePath, iv_image);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(display.getWidth(),
                display.getHeight());

        showDialog.setContentView(main, params);
        showDialog.show();
        showDialog.setCancelable(true);
        showDialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = showDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.AnimationPreviewPop);
        dialog_img_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog.dismiss();
            }
        });
    }

    Dialog showDialog;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.i("onActivityResult ...");
        switch (requestCode) {
            case ImageSelector.REQUEST_SELECT_IMAGE:
                if (resultCode == -1) {
                    ArrayList<String> imagesPath = data.getStringArrayListExtra(ImageSelector.SELECTED_RESULT);
                    if (imagesPath != null) {
                        Logger.i("onResponse:imagesPath =  " + imagesPath.get(0));
                        ImageLoaderUtil.getInstance().loadImage("http://img05.tooopen.com/images/20150202/sy_80219211654.jpg", R.mipmap.default_avatar, mIvAvator);
                    }
                }
                break;
            default:
                break;
        }
    }

    private OptionsPickerView pvCustomOptions;
    private ArrayList<String> cardItem = new ArrayList<String>(Arrays.asList("男", "女"));

    @OnClick(R.id.rl_sex)
    public void mRlSex() {
        // 注意：自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针
        // 具体可参考demo 里面的两个自定义布局
        pvCustomOptions = new OptionsPickerView.Builder(_mActivity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1);
                mTvSex.setText(tx);
            }
        }).setLayoutRes(R.layout.pickerview_sex, new CustomListener() {
            @Override
            public void customLayout(View v) {
                final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvCustomOptions.returnData(tvSubmit);
                    }
                });
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvCustomOptions.dismiss();
                    }
                });

            }
        })
                .build();
        pvCustomOptions.setPicker(cardItem);//添加数据
        pvCustomOptions.show();
    }

    private TimePickerView pvCustomTime;

    @OnClick(R.id.rl_birthday)
    public void mRlBirthday() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2016, 11, 31);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(_mActivity, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvBirthday.setText(getTime(date).substring(0, 10));
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView tvCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData(tvSubmit);
                            }
                        });
                        tvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setDividerColor(Color.BLACK)
                .build();
        pvCustomTime.show();
    }

    @OnClick(R.id.rl_shipping_address)
    public void mRlShippingAddress() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
