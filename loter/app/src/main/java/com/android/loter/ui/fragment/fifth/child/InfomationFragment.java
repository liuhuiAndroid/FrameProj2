package com.android.loter.ui.fragment.fifth.child;

import android.app.Dialog;
import android.content.Intent;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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

import java.util.ArrayList;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.lijunguan.imgselector.ImageSelector;

/**
 * Created by we-win on 2017/3/19.
 */

public class InfomationFragment extends BaseBackFragment implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_avator)
    CircleImageView mIvAvator;

    String imagePath = "http://img.cnjiayu.net/3211573049-3310678237-21-0.jpg";

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

        switch (v.getId()){

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
                        ImageLoaderUtil.getInstance().loadImage("http://img05.tooopen.com/images/20150202/sy_80219211654.jpg",R.mipmap.default_avatar,mIvAvator);
                    }
                }
                break;
            default:
                break;
        }
    }
}
