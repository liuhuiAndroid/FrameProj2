package com.android.loter.ui.fragment.fifth.child;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by we-win on 2017/3/19.
 */

public class SettingFragment extends BaseBackFragment {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_version_code)
    TextView mTvVersionCode;

    public static BaseFragment newInstance() {
        SettingFragment settingFragment = new SettingFragment();
        return settingFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initData() {
        mTvTitle.setText(getResources().getString(R.string.settingfragment_title));
        mTvVersionCode.setText(getVersion());
    }

    @Override
    protected void bindEvent() {

    }

    @OnClick(R.id.btn_exit)
    public void mBtnExit() {

    }

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = _mActivity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(_mActivity.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
