package com.kotlin.aidlservice

import android.os.Parcel
import android.os.Parcelable

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/06/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class BlogInfo() : Parcelable {

    var name : String? = "hello aidl"

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun readFromParcel(p: Parcel) {
        name = p.readString();
    }

    companion object CREATOR : Parcelable.Creator<BlogInfo> {
        override fun createFromParcel(parcel: Parcel): BlogInfo {
            return BlogInfo(parcel)
        }

        override fun newArray(size: Int): Array<BlogInfo?> {
            return arrayOfNulls(size)
        }
    }

}