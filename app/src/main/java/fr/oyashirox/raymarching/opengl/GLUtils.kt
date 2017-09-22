package fr.oyashirox.raymarching.opengl

import android.opengl.GLES20
import android.util.Log
import org.apache.commons.io.IOUtils
import java.io.InputStream

/**
 * Created by Florian on 22/09/2017.
 */

fun loadShader(type: Int, code: String): Int {
    val shader = GLES20.glCreateShader(type)

    GLES20.glShaderSource(shader, code)
    GLES20.glCompileShader(shader)
    Log.w("GLUtils", GLES20.glGetShaderInfoLog(shader))

    return shader
}

fun inputStreamToShader(shaderType: Int, inputStream: InputStream): Int {
    val shaderCode = IOUtils.toString(inputStream, "UTF-8")
    val shader = loadShader(shaderType, shaderCode)
    return shader
}
