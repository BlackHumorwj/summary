package com.kotlin.for2021.android.jetpack.coroutine

import java.io.Serializable

/**
 * @time 2018/5/21 14:03
 * @desc
 */
class AppVersion : Serializable {
    var lastVersion: LastVersionEntity? = null

    class LastVersionEntity : Serializable {
        var downloadUrl: String? = null
        var fileName: String? = null
        var releaseTime: String? = null
        var fileSize: String? = null
        var versionName: String? = null
        var content: String? = null
        var required: String? = null
        var versionNo = 0
    }
}