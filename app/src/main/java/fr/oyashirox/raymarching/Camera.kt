package fr.oyashirox.raymarching

import fr.oyashirox.raymarching.math.Vec

/**
 * Created by Oyashirox on 20/09/2017.
 */
data class Camera(val position: Vec, val direction: Vec) {
    private val up = Vec(0.0, 1.0, 0.0)
    private val right = Vec(1.0, 0.0, 0.0)

    fun localToWorld(local: Vec) = (right * local.x + up * local.y + direction).normalize()

}