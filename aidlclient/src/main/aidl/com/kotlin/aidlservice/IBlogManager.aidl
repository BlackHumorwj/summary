// IBlogManager.aidl
package com.kotlin.aidlservice;

import com.kotlin.aidlservice.BlogInfo;
// Declare any non-default types here with import statements

interface IBlogManager {

    String pullFromService();

    void pushToService(inout  BlogInfo info);

  int getVersionCode();


}