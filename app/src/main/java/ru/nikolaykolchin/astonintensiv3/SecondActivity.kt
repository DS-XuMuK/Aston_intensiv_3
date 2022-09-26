package ru.nikolaykolchin.astonintensiv3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.net.URL
import java.util.concurrent.Executors

private const val KEY_BITMAP = "bitmap"

class SecondActivity : AppCompatActivity(), OnClickListener {
    private lateinit var imageView: ImageView
    private lateinit var editText: EditText

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        outState.putParcelable(KEY_BITMAP, bitmap)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.editText)
        imageView.setOnClickListener(this)

        if (savedInstanceState != null) {
            val bitmap: Bitmap =
                savedInstanceState.getParcelable(KEY_BITMAP) ?: BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ic_launcher_background
                )
            imageView.setImageBitmap(bitmap)
        }
    }

    override fun onClick(view: View?) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap?
        val toast = Toast.makeText(applicationContext, R.string.error_toast, Toast.LENGTH_SHORT)
        executor.execute {
            try {
                val imageURL = editText.text.toString()
                val inputStream = URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(inputStream)
                handler.post {
                    imageView.setImageBitmap(image)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                toast.show()
            }
        }
    }
}