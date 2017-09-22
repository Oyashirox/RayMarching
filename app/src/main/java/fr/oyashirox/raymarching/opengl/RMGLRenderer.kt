package fr.oyashirox.raymarching.opengl

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import fr.oyashirox.raymarching.opengl.objects.Square
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class RMGLRenderer(val context: Context) : GLSurfaceView.Renderer {
    private lateinit var square: Square

    override fun onSurfaceCreated(unused: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        square = Square(context)
    }

    override fun onSurfaceChanged(unused: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(unused: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        square.draw()
    }

}