package fr.oyashirox.raymarching.opengl

import android.content.Context
import android.opengl.GLSurfaceView

/**
 * Created by Florian on 22/09/2017.
 */
class RMGLSurfaceView(context: Context): GLSurfaceView(context) {
    val renderer = RMGLRenderer(getContext())

    init {
        setEGLContextClientVersion(2) // OpenGL ES 2
        setRenderer(renderer)
        renderMode = RENDERMODE_WHEN_DIRTY  // Render only when data changes
    }

}

