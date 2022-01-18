package com.example.diary_recycler

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log


public class RealPathUtil {

    val TAG = "GalleryPhoto"

    @SuppressLint("NewApi")
    fun getRealPathFromURI_API19(context: Context?, photoUri: Uri): String? {
        Log.d(TAG, "> API 19")
        var path: String? = ""
        if (DocumentsContract.isDocumentUri(context, photoUri)) {
            Log.d(TAG, "isDocumentUri")
            Log.d(TAG, "photoUri:$photoUri")
            // ExternalStorageProvider
            if (isExternalStorageDocument(photoUri)) {
                Log.d(TAG, "externalStorageDocument")
                val docId = DocumentsContract.getDocumentId(photoUri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    path = Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
            } else if (isDownloadsDocument(photoUri)) {
                Log.d(TAG, "DownloadsDocument")
                val id = DocumentsContract.getDocumentId(photoUri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )
                path = getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(photoUri)) {
                Log.d(TAG, "MediaDocument")
                val docId = DocumentsContract.getDocumentId(photoUri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(
                    split[1]
                )
                path = getDataColumn(context, contentUri, selection, selectionArgs)
            } else {
                path = photoUri.toString()
            }
        } else if ("content".equals(photoUri.scheme, ignoreCase = true)) {
            Log.d(TAG, "content")
            path = getDataColumn(context, photoUri, null, null)
        } else if ("file".equals(photoUri.scheme, ignoreCase = true)) {
            Log.d(TAG, "file")
            path = photoUri.path
        }
       // Log.d(TAG, path)
        return path
    }

    @SuppressLint("NewApi")
    fun getRealPathFromURI_API11to18(context: Context, contentUri: Uri): String? {
        Log.d(TAG, "API 11 to 18")
        var result: String? = null
        val cursor = context.contentResolver.query(contentUri, null, null, null, null)
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentUri.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }
    fun getRealPathFromURI_BelowAPI11(context: Context, contentUri: Uri?): String? {
        Log.d(TAG, "API Below 11")
        var path = ""
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(contentUri!!, proj, null, null, null)
        val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        path = cursor.getString(column_index)
        cursor.close()
        return path
    }
    fun getDataColumn(
        context: Context?, uri: Uri?, selection: String?,
        selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(
            column
        )
        try {
            cursor = context?.contentResolver?.query(
                uri!!, projection, selection, selectionArgs,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(column_index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }
    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri.authority
    }
}
