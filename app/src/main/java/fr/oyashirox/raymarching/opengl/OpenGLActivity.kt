package fr.oyashirox.raymarching.opengl

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class OpenGLActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = RMGLSurfaceView(this)
        setContentView(view)
    }
}
