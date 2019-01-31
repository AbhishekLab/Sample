package com.example.sample.fileUtil

import android.os.Environment
import java.io.File
import javax.inject.Inject

class FetchFiles @Inject constructor() {

    private var directoriess: ArrayList<String>? = ArrayList()

    /*fun fetchFileFromBackGround(): ArrayList<String> {
        val path = File(Environment.getExternalStorageDirectory(), "WhatsApp/Media/WhatsApp Images/Sent")
        if (path.exists()) {
            val fileNames = path.list()
            if (fileNames.size != 0) {
                for (i in fileNames) {
                    if (i != ".nomedia") {
                        directoriess!!.add(path.absolutePath + "/" + i)
                    }
                }
                return directoriess!!
            }
        } else {
            directoriess!!.add("no file")

        }
        return directoriess!!
    }*/
}