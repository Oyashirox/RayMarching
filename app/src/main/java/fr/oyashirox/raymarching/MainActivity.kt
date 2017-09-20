package fr.oyashirox.raymarching

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import fr.oyashirox.raymarching.math.Vec
import fr.oyashirox.raymarching.objects.Sphere

class MainActivity : AppCompatActivity() {

    private val viewportSize = 750

    private lateinit var image: ImageView
    private lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.imageview)
        loading = findViewById(R.id.loading)

        val sphere = Sphere(Vec(0.0, 0.0, 0.0), 6.0, 0xFFFF0000.toInt())
        val camera = Camera(Vec(0.0, 0.0, -10.0), Vec(0.0, 0.0, 1.0))
        val renderer = Renderer(sphere, camera, viewportSize)

        //val bitmapArray = renderer.render()
        //val bitmap = Bitmap.createBitmap(bitmapArray, viewportSize, viewportSize, Bitmap.Config.ARGB_8888)
        //image.setImageBitmap(bitmap)

        renderer.asyncRender {
            val bitmap = Bitmap.createBitmap(it, viewportSize, viewportSize, Bitmap.Config.ARGB_8888)
            image.post {
                image.setImageBitmap(bitmap)
                loading.visibility = View.GONE
            }
        }
    }
}