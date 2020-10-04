package com.nicklasslagbrand.tvshow.data.datasource

import android.content.Context
import com.nicklasslagbrand.tvshow.domain.error.EmptyFileException
import com.nicklasslagbrand.tvshow.domain.error.ReadingAssetFileException
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets.UTF_8

class LocalFileStorage(
    private val context: Context
) {
    fun getTvShow(): String {
        val jsonString: String
        jsonString = try {
            val `is`: InputStream = context.assets.open(FILENAME_ASSET_API)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            throw ReadingAssetFileException()
        }
        when {
            jsonString.isBlank() -> throw EmptyFileException()
        }
        return jsonString
    }

    companion object {
        private val FILENAME_ASSET_API = "hbo-silicon-valley.json"
    }
}
