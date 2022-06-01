package com.kotlin.for2021.camera;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.kotlin.for2021.AppData;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import androidx.annotation.NonNull;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


public class FileUtil {


    static final String TAG = FileUtil.class.getName();

    /**
     * KB与Byte的倍数
     */
    public static final int KB = 1024;

    private final static String[] proj = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};

    private FileUtil() {
    }

    /**
     * 判断是否有SDcard
     *
     * @return
     */
    public static boolean hasSDCard() {
        File file = Environment.getExternalStorageDirectory();
        if (file.isDirectory()) {
            return true;
        }
        return false;
    }

    public static String getLastPathSegment(String content) {
        if (content == null || content.length() == 0) {
            return "";
        }
        String[] segments = content.split("/");
        if (segments.length > 0) {
            return segments[segments.length - 1];
        }
        return "";
    }

    @NonNull
    public static File getUirFile(Uri fileUri) {
        return new File(fileUri.getPath());
    }


    public static boolean exists(@NonNull File file) {
        return file.exists();
    }


    /**
     * 复制或移动文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     * @param isMove       是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     */
    public static boolean copyOrMoveFile(String srcFilePath, String destFilePath, boolean isMove) {
        return copyOrMoveFile(getFileByPath(srcFilePath, false), getFileByPath(destFilePath, true), isMove);
    }

    /**
     * 复制或移动文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @param isMove   是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     */
    public static boolean copyOrMoveFile(File srcFile, File destFile, boolean isMove) {
        if (srcFile == null || destFile == null) {

            Log.e("copyOrMoveFile", "null");

            return false;
        }

        // 源文件不存在或者不是文件则返回false
        if (!srcFile.exists() || !srcFile.isFile()) {

            Log.e("copyOrMoveFile", "源文件不存在或者不是文件则返回false");

            return false;
        }

        // 目标文件存在且是文件则返回false
        if (destFile.exists() && destFile.isFile()) {

            Log.e("copyOrMoveFile", "目标文件存在且是文件则返回false");


            return false;
        }

        // 目标目录不存在返回false
        if (!createOrExistsDir(destFile.getParentFile())) {
            Log.e("copyOrMoveFile", "目标目录不存在返回false");
            return false;
        }

        try {
            return isToFile(destFile, new FileInputStream(srcFile), false) && !(isMove && !deleteFile(srcFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(File file) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }


    /**
     * 将输入流写入文件
     *
     * @param file   文件
     * @param is     输入流
     * @param append 是否追加在文件末
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     */
    public static boolean isToFile(File file, InputStream is, boolean append) {
        if (file == null || is == null)
            return false;
        if (!createOrExistsFile(file))
            return false;
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file, append));
            byte data[] = new byte[KB];
            int len;
            while ((len = is.read(data, 0, KB)) != -1) {
                os.write(data, 0, len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeIO(is, os);
        }
    }


    /**
     * 根据文件路径获取文件
     *
     * @param filePath       文件路径
     * @param isDestFilePath 是否是目标路径
     * @return 文件
     */
    public static File getFileByPath(String filePath, boolean isDestFilePath) {
        if (isDestFilePath) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            return file;
        } else {
            File file = new File(filePath);


            return TextUtils.isEmpty(filePath) ? null : file;
        }
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(File file) {
        if (file == null)
            return false;
        // 如果存在，是文件则返回true，是目录则返回false
        if (file.exists())
            return file.isFile();
        if (!createOrExistsDir(file.getParentFile()))
            return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除文件
     *
     * @param file 文件
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }

    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null)
            return;
        try {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static File getLocalFileDir() {
        return getAppSpecificDwDir(AppData.Companion.getContext());
    }


    /**
     * 图片外部存储路径
     *
     * @param filePath 路径
     * @return
     */
    public static String getLocalPicPath(String filePath) {
        return Environment.getExternalStorageDirectory() + "/" + filePath;
    }

    /**
     * 获取私有文件夹下图片目录
     *
     * @param context
     * @return
     */
    public static File getAppSpecificPicDir(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    }

    /**
     * 获取私有文件夹下 下载目录
     *
     * @param context
     * @return
     */
    public static File getAppSpecificDwDir(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
    }


    /**
     * 压缩图片
     *
     * @param context     Context
     * @param isTakePhoto 是否来自拍照
     * @param filePath    原文件路径uri
     * @param newFileName 新文件的文件名
     * @return File
     */
    public static File compressPic(Context context, boolean isTakePhoto, String filePath, String newFileName) {
        File newFile;
        try {
            boolean copySuccess;

            if (isTakePhoto) {
                //拍照获取的图片
                newFile = new File(filePath);
                copySuccess = true;
            } else {
                newFile = FileUtil.newFileInAs(context, newFileName);
                //将系统的图片复制一份到 应用沙箱中 并且将压缩后的路径设置为应用沙箱的路径
                copySuccess = FileUtil.copyFileToAppSpecificDir(context, Uri.parse(filePath), newFile);
            }

            if (newFile == null) {
                return null;
            }

            if (copySuccess) {
                //压缩自己私有目录下的文件，并设置压缩后的路径也在私有目录下
                final List<File> files = Luban.with(context)//
                        .load(newFile)//
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                Log.e(TAG, "onStart");
                            }

                            @Override
                            public void onSuccess(File file) {
                                Log.e(TAG, "onSuccess+" + file.getAbsolutePath());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError+" + e.getMessage());
                            }
                        })
                        .setTargetDir(FileUtil.getAppSpecificPicDir(context).getAbsolutePath())//设置压缩后的文件目录
                        .get();//

                return files.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return null;

    }


    /**
     * 在私有目录下创建一个文件
     *
     * @param context  Context
     * @param fileName 文件名
     * @return File文件
     */
    public static File newFileInAs(Context context, String fileName) {
        //获取私有目录图片文件夹
        final File primaryDir = getAppSpecificPicDir(context);
        if (primaryDir == null)
            return null;

        //storage/emulated/0/Android/data/com.example.androidq/files/Pictures
        //在目录下的创建一个新的文件对象
        return new File(primaryDir.getAbsolutePath(), fileName);
    }


    /**
     * 复制文件到沙箱私有目录下
     *
     * @param fileUri 要复制文件的uri
     * @param newFile 新文件的对象
     * @return 是否复制成功
     */
    public static boolean copyFileToAppSpecificDir(Context context, Uri fileUri, File newFile) {
        InputStream fileOS = null;
        //将图片Uri文件拷贝到 沙箱的目录中
        try {
            fileOS = getFileInputStream(context, fileUri);

            //拷贝到文件
            return is2File(newFile, fileOS, false);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeIO(fileOS);
        }
        return false;
    }

    /**
     * 获取文件的输入流
     *
     * @param fileUri 原始文件的uri
     * @return FileInputStream
     * @throws FileNotFoundException
     */
    public static InputStream getFileInputStream(Context context, Uri fileUri) throws FileNotFoundException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            return context.getContentResolver().openInputStream(fileUri);

            //            ParcelFileDescriptor parcelFileDescriptor = context.getContentResolver().openFileDescriptor(fileUri, "r");
            //            if (parcelFileDescriptor != null) {
            //                final FileInputStream inputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
            ////                try {
            ////                   // parcelFileDescriptor.close();
            ////                } catch (IOException e) {
            ////                    e.printStackTrace();
            ////                }
            //                return inputStream;
            //            } else {
            //                return null;
            //            }
        } else {
            return new FileInputStream(getFileFromUri(context, fileUri));
        }
    }

    /**
     * 通过uri查找对应的File文件
     *
     * @param context Context
     * @param uri     uri
     * @return
     */
    public static File getFileFromUri(Context context, Uri uri) {
        String imgPath = "";

        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor == null) {
            imgPath = uri.getPath();
        } else {
            imgPath = cursor.getString(cursor.getColumnIndexOrThrow(proj[0]));
        }
        File file = new File(imgPath);
        if (cursor != null) {
            cursor.close();
        }
        return file;

    }


    /**
     * 输入流 拷贝 到输出流中
     *
     * @param is InputStream
     * @param os OutputStream
     * @return 是否成功
     */
    public static boolean is2Os(InputStream is, OutputStream os) {
        if (is == null || os == null) {
            return false;
        }

        try {
            os = new BufferedOutputStream(os);
            byte data[] = new byte[KB];
            int len;
            while ((len = is.read(data, 0, KB)) != -1) {
                os.write(data, 0, len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeIO(is, os);
        }
    }

    /**
     * 输入流宝 拷贝文件中
     *
     * @param file   文件
     * @param is     输入流
     * @param append
     * @return
     */
    public static boolean is2File(File file, InputStream is, boolean append) {
        if (file == null || is == null)
            return false;
        try {
            return is2Os(is, new FileOutputStream(file, append));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static File bitmap2File(Bitmap bitmap, File file) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }

    /**
     * androidQ中将  File 存储到外部空间
     *
     * @param context
     * @param file
     */
    public static void addFile2External(Context context, File file) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DESCRIPTION, "This is an image");
        values.put(MediaStore.Images.Media.DISPLAY_NAME, file.getName());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.Images.Media.TITLE, "Image");

        Uri external = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver resolver = context.getContentResolver();

        Uri insertUri = resolver.insert(external, values);

        OutputStream os = null;
        try {
            if (insertUri != null) {
                //将数据写入外部存储空间
                os = resolver.openOutputStream(insertUri);
            }
            if (os != null) {
                os2File(os, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * androidQ中将 bitmap存储到外部空间
     *
     * @param context
     * @param bitmap
     * @param picName
     * @return
     */
    public static boolean addBitmap2External(Context context, Bitmap bitmap, String picName) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DESCRIPTION, "This is an image");
        values.put(MediaStore.Images.Media.DISPLAY_NAME, picName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        values.put(MediaStore.Images.Media.TITLE, picName);

        Uri external = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver resolver = context.getContentResolver();

        Uri insertUri = resolver.insert(external, values);

        OutputStream os = null;
        try {
            if (insertUri != null) {
                //将数据写入外部存储空间
                os = resolver.openOutputStream(insertUri);
            }
            if (os != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * @param os
     * @param file
     * @throws IOException
     */
    private static void os2File(OutputStream os, File file) throws IOException {
        final FileInputStream fis = new FileInputStream(file);
        try {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getNameFromUrl(String path) {
        if (TextUtils.isEmpty(path) || path.lastIndexOf("/") == -1) {
            return "";
        }

        return path.substring(path.lastIndexOf("/"), path.length());
    }
}
