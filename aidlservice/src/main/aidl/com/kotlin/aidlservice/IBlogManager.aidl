// IBlogManager.aidl
package com.kotlin.aidlservice;

import com.kotlin.aidlservice.BlogInfo;

interface IBlogManager {

    String pullFromService();

    void pushToService(inout  BlogInfo info);

  int getVersionCode();


}