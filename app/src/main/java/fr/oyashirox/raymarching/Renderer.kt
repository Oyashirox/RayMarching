package fr.oyashirox.raymarching

import android.graphics.Color
import android.os.SystemClock
import android.util.Log
import fr.oyashirox.raymarching.math.Vec

/**
 * Created by Oyashirox on 20/09/2017.
 */
class Renderer(val scene: Scene, val camera: Camera, val light: DirectionalLight, val viewportSize: Int) {
    private val defaultColor = 0xFFFFFFAA.toInt()
    private val bitmap = IntArray(viewportSize * viewportSize, { defaultColor })

    fun asyncRender(callback: (IntArray) -> Unit) {
        Thread(Runnable {
            callback(render())
        }).start()
    }

    fun render(): IntArray {
        val start = SystemClock.elapsedRealtime()

        for (y in 0..viewportSize - 1) {
            for (x in 0..viewportSize - 1) {
                val u = x / (viewportSize.toDouble() - 1) * 2 - 1 // Convert 0..N px coord to [-1, 1]
                val v = (viewportSize - 1 - y) / (viewportSize.toDouble() - 1) * 2 - 1  // Convert 0..N px coord to [1, -1]

                val rayVector = camera.localToWorld(Vec(u, v, 0.0))

                bitmap[x + y * viewportSize] = trace(rayVector)
            }
        }

        Log.d("Renderer", "Render time : ${SystemClock.elapsedRealtime() - start} ms")
        return bitmap
    }

    private val EPSILON = 0.0000000001
    private val MAX_DIST = 500
    private val MAX_STEP = 500

    private fun trace(rayVector: Vec): Int {
        var distance = EPSILON
        var step = 0.0
        var position = camera.position

        for (i in 0..MAX_STEP) {
            distance = scene.distance(position)

            if (distance < EPSILON || step > MAX_DIST) {
                break
            } else {
                step += distance
                position = position + rayVector * step
            }
        }

        if (distance < EPSILON) {
            return computeLight(position, scene.color(position))
        }

        return defaultColor
    }

    private fun computeLight(position: Vec, color: Int): Int {
        val normal = scene.normal(position).normalize()
        val factor = Math.max(0.0, light.normalizedLight.dot(normal))
        val r = Color.red(color) / 255.0 * Color.red(light.color) / 255.0 * factor
        val g = Color.green(color) / 255.0 * Color.green(light.color) / 255.0 * factor
        val b = Color.blue(color) / 255.0 * Color.blue(light.color) / 255.0 * factor
        val red = (r * 255).toInt()
        val green = (g * 255).toInt()
        val blue = (b * 255).toInt()

        return Color.argb(255, red, green, blue)
    }
}