package com.yeapao.andorid.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.scottfu.sflibrary.net.CloudClient;
import com.scottfu.sflibrary.net.JSONResultHandler;
import com.scottfu.sflibrary.util.LogUtil;
import com.scottfu.sflibrary.util.ToastManager;
import com.yeapao.andorid.R;
import com.yeapao.andorid.api.ConstantYeaPao;
import com.yeapao.andorid.dialog.DialogCallback;
import com.yeapao.andorid.dialog.DialogUtils;
import com.yeapao.andorid.model.VersionDataModel;


/**
 * Created by fujindong on 2017/10/17.
 */

public class DownloadManager {

    private static final int START = 0;
    private static final int LOADING = 1;
    private static final int SUCCESS = 2;
    private AlertDialog dialog = null;
    private Window dialogWindow = null;
    private Context mContext;
    private HttpHandler<File> httpHandler;

    public interface SuccessCallback {
        void onSuccess(String version);
    }


    public interface DownloadCallback {
        //void onStart();
        void onSuccess();
        //void onFailuer();
    }

    public DownloadManager(Context context) {
        this.mContext = context;
    }

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/download/";
    public static final String CATEGORY_PROTOCOL_PATH = PATH + "protocol/";


    public void checkUpdate(final SuccessCallback callback) {
        checkUpdate(true, callback);
    }

    public void checkUpdate(final boolean showDialog, final SuccessCallback callback) {
//        if (showDialog) DialogUtil.showProgressDialog(mContext, "检测中...");

        CloudClient.getHttpRequest(mContext, ConstantYeaPao.UPDATA_VERSION, new JSONResultHandler() {
            @Override
            public void onSuccess(String jsonString) {
                LogUtil.e("downloadManager",jsonString);
                Gson gson = new Gson();
                VersionDataModel versionDataModel = gson.fromJson(jsonString, VersionDataModel.class);
                LogUtil.e("downloadmanager",ToolUtil.getVersionName(mContext));
                String currentVer = ToolUtil.getVersionName(mContext);
                String newVer = versionDataModel.getData().get(0).getVersion();
                if (newVer.compareTo(currentVer) > 0) {
                    updateApk(versionDataModel.getData().get(0).getUrl(), "v" + newVer);
                    callback.onSuccess("有新版本");
                } else {
                    callback.onSuccess("v"+newVer);
                }

            }

            @Override
            public void onError(VolleyError errorMessage) {

            }
        });


//        CloudClient.getHttpRequest(mContext, ConstantYeaPao.UPDATA_VERSION,  new JSONResultHandler() {
//
//            @Override
//            public void onSuccess(String response) {
//                // TODO Auto-generated method stub
//                YXTVersionInfo versionInfo = JSON.parseObject(response, YXTVersionInfo.class);
//                if (versionInfo == null) {
//                    return;
//                }
//                String currentVer = ToolUtil.getVersionName(mContext);
//                String newVer = versionInfo.getVersion();
//                if (newVer.compareTo(currentVer) > 0) {
//                    updateApk(versionInfo.getUrl(), "v" + newVer);
//                    if (callback != null) callback.onSuccess("有新版本");
//                } else {
//                    if (showDialog) ToastUtil.show(mContext, "当前版本已是最新");
//                    if (callback != null) callback.onSuccess("v" + newVer);
//                }
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//                // TODO Auto-generated method stub
//                DialogUtil.cancelProgressDialog();
//                if (showDialog) ToastUtil.show(mContext, "请求服务器失败");
//            }
//        });

    }

    private void updateApk(final String url, final String versionName) {
        DialogUtils.showMessageTwoButtonDialog(mContext, "提示", "发现新版本是否立即更新？", new DialogCallback() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {
                final String fileName = "Shopping-" + versionName;
                checkApkExist(fileName);
                download(url, PATH + fileName, new DownloadCallback() {

                    @Override
                    public void onSuccess() {
                        installApk(mContext, fileName);
                    }

                });
            }
        });

    }

    private Handler downloadHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START:
                    showDownloadProgress(0, "准备下载...");
                case LOADING:
                    int progress = msg.arg1;
                    showDownloadProgress(progress, "正在下载中" + progress + "%");
                    break;
                case SUCCESS:
                    dialog.cancel();
                    dialog = null;
                    dialogWindow = null;
            }
        }
    };

    private void showDownloadProgress(int progress, String message) {
        if (dialog == null) {
            dialog = new ProgressDialog(mContext);
            dialog.setCancelable(false);
            dialog.show();
            dialogWindow = dialog.getWindow();
            dialogWindow.setContentView(R.layout.download_view);

            TextView text = (TextView) dialogWindow.findViewById(R.id.item_title);
            text.setText("下载文件");

            Button btnCancel = (Button) dialogWindow.findViewById(R.id.btn_cancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (httpHandler != null) {
                        httpHandler.cancel();
                        httpHandler = null;
                    }
                    dialog.dismiss();
                    dialog = null;
                }
            });
        }
        TextView text = (TextView) dialogWindow.findViewById(R.id.text_progress);
        text.setText(message);

        ProgressBar bar = (ProgressBar) dialogWindow.findViewById(R.id.progress);
        bar.setMax(100);
        bar.setProgress(progress);
        WindowManager m = ((Activity) mContext).getWindowManager();
        Display display = m.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth() * 0.9);
        dialog.getWindow().setAttributes(params);

    }

//    public void downloadFile(final String url, final String fileStorePath, final DownloadCallback callback) {
//        DialogUtil.showDialog(mContext, "协议文件下载", "下载看看", "不想看", new DialogCallback() {
//
//            @Override
//            public void onOk() {
//                download(url, fileStorePath, callback);
//            }
//        });
//    }

    private void download(String url, final String fileStorePath, final DownloadCallback callback) {

        downloadHandler.sendEmptyMessage(START);
        HttpUtils http = new HttpUtils();
        http.download(url, fileStorePath, true, new RequestCallBack<File>() {

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                Message msg = Message.obtain();
                msg.what = LOADING;
                msg.arg1 = (int) (current * 100 / total);
                downloadHandler.sendMessage(msg);
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(ResponseInfo<File> arg0) {
                downloadHandler.sendEmptyMessage(SUCCESS);
                callback.onSuccess();
            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                }

                ToastManager.showToast(mContext, "下载失败");
            }
        });
    }


    private static boolean checkApkExist(String fileName) {
        File file = new File(PATH + fileName);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    public static boolean checkFileExist(String fileName) {
        File file = new File(CATEGORY_PROTOCOL_PATH + fileName);
        return file.exists();
    }

    public static void installApk(Context context, String fileName) {
        File file = new File(PATH + fileName);
        if (!file.exists()) {
            return;
        }
        LogUtils.e("file exist");
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(context,"com.yeapao.andorid.fileProvider",file);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(contentUri,"application/vnd.android.package-archive");

        }else{

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        }



        context.startActivity(intent);
    }
}
