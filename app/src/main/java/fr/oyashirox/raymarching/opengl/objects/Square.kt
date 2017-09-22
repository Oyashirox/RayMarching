package fr.oyashirox.raymarching.opengl.objects

import android.content.Context
import android.opengl.GLES20
import fr.oyashirox.raymarching.R
import fr.oyashirox.raymarching.opengl.inputStreamToShader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

class Square(val context: Context) {
    companion object {
        // number of coordinates per vertex in this array
        internal val COORDS_PER_VERTEX = 3
        internal var squareCoords = floatArrayOf(-1f, 1f, 0.0f, // top left
                -1f, -1f, 0.0f, // bottom left
                1f, -1f, 0.0f, // bottom right
                1f, 1f, 0.0f) // top right
        internal val drawOrder = shortArrayOf(0, 1, 2, 0, 2, 3) // order to draw vertices
        internal val color = floatArrayOf(1f, 0f, 0f, 1.0f)

    }

    private val vertexBuffer: FloatBuffer // Vertex (squareCoords)
    private val vertexCount = squareCoords.size / COORDS_PER_VERTEX
    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per coord

    private val drawListBuffer: ShortBuffer // Order (drawOrder)

    private val program: Int // Shader (vertexShader, fragmentShader)
    private var positionHandle: Int = 0 // Access to vertex shader variable
    private var colorHandle: Int = 0 // Access to fragment shadder variable

    init {
        // initialize vertex byte buffer for shape coordinates
        val bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                squareCoords.size * 4)
        bb.order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer.put(squareCoords)
        vertexBuffer.position(0)

        // initialize byte buffer for the draw list
        val dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.size * 2)
        dlb.order(ByteOrder.nativeOrder())
        drawListBuffer = dlb.asShortBuffer()
        drawListBuffer.put(drawOrder)
        drawListBuffer.position(0)

        // OpenGL shader initialization
        val vertexShader = inputStreamToShader(GLES20.GL_VERTEX_SHADER, context.resources.openRawResource(R.raw.vertex_shader))
        val fragmentShader = inputStreamToShader(GLES20.GL_FRAGMENT_SHADER, context.resources.openRawResource(R.raw.fragment_shader))

        program = GLES20.glCreateProgram()
        GLES20.glAttachShader(program, vertexShader)
        GLES20.glAttachShader(program, fragmentShader)
        GLES20.glLinkProgram(program)
    }


    fun draw() {
        GLES20.glUseProgram(program) // Choose the program with the shader

        // Send coordinates to vertex shader
        positionHandle = GLES20.glGetAttribLocation(program, "vPosition")
        GLES20.glEnableVertexAttribArray(positionHandle)
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer)

        //Send color to fragment shader
        colorHandle = GLES20.glGetUniformLocation(program, "vColor")
        GLES20.glUniform4fv(colorHandle, 1, color, 0)

        // Draw !
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.size, GLES20.GL_UNSIGNED_SHORT, drawListBuffer)

        GLES20.glDisableVertexAttribArray(positionHandle)

    }
}