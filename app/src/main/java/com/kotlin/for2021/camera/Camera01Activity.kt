package com.kotlin.for2021.camera

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import androidx.annotation.NonNull
import com.kotlin.for2021.R
import com.kotlin.for2021.android.jetpack.viewBinding.BaseViewBindingActivity
import com.kotlin.for2021.databinding.ActivityCamera01Binding
import java.io.File

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class Camera01Activity : BaseViewBindingActivity() {
    private lateinit var binding: ActivityCamera01Binding

    fun getLayoutResId(): Int {
        return R.layout.activity_camera_01
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //辅助文件生成太多，影响包体积和方法数
        binding = ActivityCamera01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initView(binding)
    }


    fun initView(binding: ActivityCamera01Binding) {

        binding.btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        binding.btnPickFile.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.setType("*/*");
            startActivityForResult(intent, 2)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                val originalUri = data?.data // 获得图片的uri
                if (originalUri != null) {
                    // handleUri(originalUri)
                    //val filePath = UriTool.getFilePathByUri(this, originalUri)

                    //将图片复制一份到自身的App-specific 目录下,
                    // 然后使用 Luban 设置成App-specific目录下的文件夹
                    val file = FileUtil.compressPic(
                        this,
                        false,
                        originalUri.toString(),
                        "test01.jpg"
                    )
                    binding.tvName.text = "${file?.absolutePath}"
                    binding.iv.setImageBitmap(BitmapFactory.decodeFile(file?.absolutePath))

                }
                return
            }else if (requestCode==2){
                val originalUri = data?.data
                val filePath = UriTool.getFilePathByUri(this, originalUri)
                binding.tvName.text = "$filePath"
            }
        }


        super.onActivityResult(requestCode, resultCode, data)
    }


    fun getBitMap(path: String) {
        val mImageBitmap: Bitmap = MediaStore.Images.Media.getBitmap(
            applicationContext.contentResolver,
            Uri.parse(path)
        )
    }


    private val IMAGE_PROJECTION = arrayOf( //查询图片需要的数据列
        MediaStore.Images.Media.DISPLAY_NAME,  //图片的显示名称  aaa.jpg
        MediaStore.Images.Media.DATA,  //图片的真实路径  /storage/emulated/0/pp/downloader/wallpaper/aaa.jpg
        // MediaStore.Images.Media.SIZE,           //图片的大小，long型  132492
        //   MediaStore.Images.Media.WIDTH,          //图片的宽度，int型  1920
        //   MediaStore.Images.Media.HEIGHT,         //图片的高度，int型  1080
        MediaStore.Images.Media.MIME_TYPE,  //图片的类型     image/jpeg
        MediaStore.Images.Media.DATE_ADDED,
        MediaStore.Images.Media._ID
    )


    private fun handleUri(contentUri: Uri) {


        //选择文件的条件
        val where = null
        //排序
        val sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC"

        val contentResolver: ContentResolver = contentResolver
        //获取Cursor
        val data = contentResolver.query(
            contentUri,
            IMAGE_PROJECTION,
            where,
            null,
            sortOrder
        )


        data?.let {
            while (data.moveToNext()) {
                //查询数据
                val imageName = data.getString(
                    data.getColumnIndexOrThrow(
                        IMAGE_PROJECTION.get(
                            0
                        )
                    )
                )

                var imagePath = data.getString(
                    data.getColumnIndexOrThrow(
                        IMAGE_PROJECTION.get(
                            1
                        )
                    )
                )
                val file = File(imagePath)
                if (!file.exists() || file.length() <= 0) {
                    continue
                }
                val imageMimeType = data.getString(
                    data.getColumnIndexOrThrow(
                        IMAGE_PROJECTION.get(
                            2
                        )
                    )
                )
                val imageAddTime = data.getLong(
                    data.getColumnIndexOrThrow(
                        IMAGE_PROJECTION.get(
                            3
                        )
                    )
                )


                //Android Q 公有目录只能通过Content Uri + id的方式访问，以前的File路径全部无效，如果是Video，记得换成MediaStore.Videos
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val id = data.getInt(
                        data.getColumnIndexOrThrow(
                            IMAGE_PROJECTION.get(
                                4
                            )
                        )
                    )
                    val uri: Uri? =
                        getImageContentUri(
                            this,
                            imagePath
                        )
                    if (uri != null) {
                        imagePath = uri.toString()
                    } else {
                        Log.e("GlobalImageLoader", "uri is null")
                    }
                }
            }
            data.close()
        }


    }


    /**
     *
     * @param context Context
     * @param path String
     * @return Uri?
     */
    fun getImageContentUri(context: Context, path: String): Uri? {
        var uri: Uri? = null

        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Images.Media._ID),
            MediaStore.Images.Media.DATA + "=? ",
            arrayOf(path),
            null
        )



        if (cursor != null && cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID))
            uri = MediaStore.Images.Media //
                .EXTERNAL_CONTENT_URI ///
                .buildUpon() //
                .appendPath(id.toString()) //
                .build()
        } else {
            // 如果图片不在手机的共享图片数据库，就先把它插入。
            if (File(path).exists()) {
                val values = ContentValues()
                values.put(MediaStore.Images.Media.DATA, path)
                uri = context.contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    values
                )
            } else {

            }
        }


        cursor?.close()
        return uri
    }

}