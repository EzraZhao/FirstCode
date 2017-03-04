package com.ezra.notificationtest;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;

    private ImageView picture;
    private Uri imageUri;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);

        Button takePhoto = (Button) findViewById(R.id.take_photo);
        takePhoto.setOnClickListener(this);

        picture = (ImageView) findViewById(R.id.picture);

        Button chooseFromAlbum = (Button) findViewById(R.id.choose_from_album);
        chooseFromAlbum.setOnClickListener(this);

        Button play_music = (Button) findViewById(R.id.play_music);
        play_music.setOnClickListener(this);

        Button pause_music = (Button) findViewById(R.id.pause_music);
        pause_music.setOnClickListener(this);

        Button stop_music = (Button) findViewById(R.id.stop_music);
        stop_music.setOnClickListener(this);

        Button play_video = (Button) findViewById(R.id.play_video);
        play_video.setOnClickListener(this);

        Button pause_video = (Button) findViewById(R.id.pause_video);
        pause_video.setOnClickListener(this);

        Button stop_video = (Button) findViewById(R.id.stop_video);
        stop_video.setOnClickListener(this);

        videoView = (VideoView) findViewById(R.id.video_view);

        //运行时权限申请
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initMediaPlayer();//初始化music播放器
            initVideoPlayer();//初始化video播放器
        }
    }

    private void initVideoPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "work.mp4");
        videoView.setVideoPath(file.getPath());
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    "She.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //不管
                manager.cancel(1);
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")//设置标题
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and" +
                                "use voice actions. Get the official Android IDE and developer tools" +
                                "to build apps for Android"))//设置长文本
//                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
//                                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)))//设置大图片
//                        .setContentText("Learn how to build notifications, send and sync data, and" +
//                                "use voice actions. Get the official Android IDE and developer tools" +
//                                "to build apps for Android")//设置文本
                        .setWhen(System.currentTimeMillis())//设置被创建的时间
                        .setSmallIcon(R.mipmap.ic_launcher)//设置小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                R.mipmap.ic_launcher))//设置大图标
                        .setContentIntent(pi)//设置点击效果
                        .setAutoCancel(true)//设置自动取消
//                        .setSound(Uri.fromFile(new File("/system/media/audio/notifications/Adara.ogg")))//设置声音
//                        .setVibrate(new long[] {0, 1000, 0, 1000})//设置震动
//                        .setLights(Color.GREEN, 1000,1000)//设置灯光
                        .setDefaults(NotificationCompat.DEFAULT_ALL)//设置默认声音，震动及灯光效果
                        .setPriority(NotificationCompat.PRIORITY_MAX)//设置重要程度
                        .build();
                manager.notify(1, notification);
                break;
            case R.id.take_photo:
                //创建File对象，用于存储拍照后的图片
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    //若系统版本高于7.0，就调用FileProvider的getUriForFile（）方法
                    // 将File对象转换成一个封装过得Uri对象
                    imageUri = FileProvider.getUriForFile(MainActivity.this,
                            "com.ezra.notification.fileprovider", outputImage);
                } else {
                    //Uri的fromFile（）方法将File对象转换成Uri对象，这个对象标识着图片的本地真实路径
                    imageUri = Uri.fromFile(outputImage);
                }

                //启动相机
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                break;
            case R.id.choose_from_album:
                    openAlbum();
                break;
            case R.id.play_music:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.pause_music:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop_music:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            case R.id.play_video:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.pause_video:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.stop_video:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
            default:
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        //将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(
                                getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (requestCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        //4.4以下系统使用这个方法处理图片
                        handleImageBrforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void handleImageBrforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this ,uri)) {
            //如果是document类型的Uri，通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID +"=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(contentUri, null);
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的Uri，使用普通方式处理
                imagePath = getImagePath(uri, null);
            } else if ("file".equals(uri.getAuthority())) {
                //如果是file类型的Uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
            displayImage(imagePath);//根据图片路径显示图片
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过Uri和selectin来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "filed to get iamge", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放music资源
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        //释放video资源
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
