package com.kotlin.for2021.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/10/27
 *     desc   :   https://juejin.cn/post/6881442312560803853?share_token=26b335d4-3690-4109-8736-b926852f6910
 *                https://www.mdnice.com/writing/75bf929eb60a4820a670b10f69d6cf10
 *     version: 1.0
 * </pre>
 */

class SpUtil {


    //sp 问题
    //1、通过 getXXX() 方法获取数据，可能会导致主线程阻塞
    //2、SP 不能保证类型安全
    //3、SP 加载的数据会一直留在内存中
    //4、apply() 方法是异步的，可能会发生 ANR
    //5、SP 不能用于跨进程通信

    private void init(Context context){

        final SharedPreferences sharedPreferences = context.getSharedPreferences("demo", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit().putInt("", 2);
        editor.apply();
        editor.commit();

        //
        sharedPreferences.getString("","");

    }


    //DateStore  Preferences DataStore
    //DataStore 优势
    //1、DataStore 是基于 Flow 实现的，所以保证了在主线程的安全性
    //2、以事务方式处理更新数据，事务有四大特性（原子性、一致性、 隔离性、持久性）
    //3、没有 apply() 和 commit() 等等数据持久的方法
    //4、自动完成 SharedPreferences 迁移到 DataStore，保证数据一致性，不会造成数据损坏
    //5、可以监听到操作成功或者失败结果

    /*
    序列化/反序列化使用 protobuf 实现

    Protobuf 的优点
    1、更小——序列化后，数据大小可缩小约3倍

    2、更快——序列化速度更快，比xml和JSON快20-100倍，体积缩小后，传输时，带宽也会优化

    3、更简单——proto编译器，自动进行序列化和反序列化

    4、维护成本低——跨平台、跨语言，多平台仅需要维护一套对象协议（.proto）

    5、可扩展——“向后”兼容性好，不必破坏已部署的、依靠“老”数据格式的程序就可以对数据结构进行升级

    6、加密性好——HTTP传输内容抓包只能看到字节

     */

}
