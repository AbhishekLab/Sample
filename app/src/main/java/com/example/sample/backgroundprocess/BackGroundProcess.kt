package com.example.sample.backgroundprocess

import android.util.Log.d
import androidx.work.Worker
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class BackGroundProcess : Worker(){

    private var mStorageRef: StorageReference? = null

    override fun doWork(): WorkerResult {
        mStorageRef = FirebaseStorage.getInstance().getReference();

        //backgroun process
        d("process start","process start")
        return WorkerResult.SUCCESS
    }
}