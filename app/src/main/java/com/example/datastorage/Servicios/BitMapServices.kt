package com.example.datastorage.Servicios

import android.content.Context
import android.graphics.Bitmap.CompressFormat
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory




class BitMapServices(context: Context) {

    fun getBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }

    fun getImage(image: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }
}