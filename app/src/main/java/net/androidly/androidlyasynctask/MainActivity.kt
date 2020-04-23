package net.androidly.androidlyasynctask

import android.app.LoaderManager
import android.content.Loader
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

    var myVariable = 10
    // Unique id for loader
    private val LDR_BASIC_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDoAsync.setOnClickListener {
            loaderManager.initLoader(LDR_BASIC_ID, Bundle.EMPTY, this)
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        progressBar.visibility = View.VISIBLE
        return BasicLoader(this, 10)
    }

    override fun onLoadFinished(loader: Loader<String>?, data: String?) {
        progressBar.visibility = View.GONE
        textView.text = data.let { it }
        myVariable = 100
    }

    override fun onLoaderReset(loader: Loader<String>?) {
        progressBar.visibility = View.VISIBLE
    }
}
