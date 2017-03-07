package com.android.loter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WE-WIN-027 on 2016/6/20.
 *
 * @des ${TODO}
 */
public class AppError implements Thread.UncaughtExceptionHandler {

    private App mApp;

    public AppError(App app) {

        mApp = app;
    }

    private static final String TAG = "AppError";
    protected boolean isSendEmail = false;

    //系统默认的UncaughtException处理类
    protected Thread.UncaughtExceptionHandler mDefaultHandler;

    /*初始化*/
    public void initUncaught() {
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /*当UncaughtException发生时会转入该函数来处理*/
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
                //AppException.exc(e).makeToast(AppContext.getContext());
            }


//            Intent intent = new Intent(mApp.getApplicationContext(), LoginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            PendingIntent restartIntent = PendingIntent.getActivity(
//                    mApp.getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
//            //退出程序
//            AlarmManager mgr = (AlarmManager) mApp.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 800,
//                    restartIntent); // 1秒钟后重启应用
            //退出程序
            AppManager.getAppManager().AppExit(App.getmAppContext());

            // 重新启动
//            ToastUtil.Infotoast(mApp.getApplicationContext(),"很抱歉,程序出现异常,会返回登录页面.");
//            AlarmManager mgr = (AlarmManager) mApp.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(mApp.getApplicationContext(), LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra("crash", true);
//            PendingIntent restartIntent = PendingIntent.getActivity(mApp.getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
//            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            System.gc();

        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        Toast.makeText(App.getmAppContext(), "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
        if (isSendEmail) {
            sendErrorInfoMail(ex);
        }
        //保存日志文件
        saveErrorLog(ex);

        return true;
    }

    public void sendErrorInfoMail(Throwable ex) {
        SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateTimeInstance();

        StringBuffer sb = new StringBuffer();
        sb.append("--------------" + (sdf.format(new Date())) + ",version" + getVersion() + "---------------\n");
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);

    }

    /**
     * 保存异常日志
     */
    public void saveErrorLog(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        try {
            ex.printStackTrace(printWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        //在控制台打印
        System.err.println(sb.toString());

        String errorlog = "throwable_log" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".txt";
        String savePath = "";
        //        String logFilePath = "";
        File logDir = null;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            //判断是否挂载了SD卡
            String storageState = Environment.getExternalStorageState();
            if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                //Environment.getExternalStorageDirectory().getAbsolutePath()
                savePath = App.getmAppContext().getExternalCacheDir() + Constants.LOG_PATH;
                logDir = new File(savePath);
                if (!logDir.exists()) {
                    logDir.mkdirs();
                }
            }
            //没有挂载SD卡，无法写文件
            if (logDir == null || !logDir.exists() || !logDir.canWrite()) {
                return;
            }
            File logFile = new File(logDir, errorlog);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            fw = new FileWriter(logFile, true);
            pw = new PrintWriter(fw);
            SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateTimeInstance();
            pw.println("--------------" + (sdf.format(new Date())) + ",version" + getVersion() + "---------------\n");
            pw.write(sb.toString());
            pw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = App.getmAppContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(App.getmAppContext().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
