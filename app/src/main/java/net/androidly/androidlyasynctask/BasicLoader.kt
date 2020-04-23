package net.androidly.androidlyasynctask

import android.content.AsyncTaskLoader
import android.content.Context

class BasicLoader(context: Context, private val params: Int): AsyncTaskLoader<String>(context) {

    override fun onStartLoading() { // Start loading
        forceLoad()
    }

    override fun loadInBackground(): String? { // Some work, e.g. load something from internet
        val resp: String?
        resp = try {
            val time = params.times(1000)
            time.toLong().let { Thread.sleep(it / 2) }
            "Android was sleeping for $params seconds"
        } catch (e: InterruptedException) {
            e.printStackTrace()
            e.message
        } catch (e: Exception) {
            e.printStackTrace()
            e.message
        }

        return resp
    }

    override fun deliverResult(data: String?) {
        if (isStarted) { // Deliver result if loader is currently started
            super.deliverResult(data)
        }
    }

    override fun onStopLoading() {
        cancelLoad()
    }

    override fun onReset() {
        super.onReset()
        // Ensure the loader is stopped
        onStopLoading()
    }
}