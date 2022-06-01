package com.kotlin.for2021;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class BluetoothViewActivity extends AppCompatActivity {


    private final String TAG = this.getClass().getSimpleName();

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, BluetoothViewActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        setContentView(R.layout.activity_second);


//        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
//        adapter.startDiscovery() ;
//        Set<BluetoothDevice> devices = adapter.getBondedDevices();
//
//        BluetoothSocket mClientSocket=mDevice.createRfcommSocketToServiceRecord(UUID.fromString(RF_UUID));
//
//
//        mClientSocket.connect() ;
//        OutputStream mOutPut = mClientSocket.getOutputStream();
//        mOutPut.write(bytes)


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
