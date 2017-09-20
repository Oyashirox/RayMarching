package fr.oyashirox.raymarching.math

/**
 * Created by Oyashirox on 20/09/2017.
 */

data class Vec(val x: Double, val y: Double, val z: Double) {
    fun norm() = Math.sqrt(x * x + y * y + z * z)
    fun normalize() = this / norm()

    operator fun times(scalar: Double) = Vec(x * scalar, y * scalar, z * scalar)
    operator fun div(scalar: Double) = Vec(x / scalar, y / scalar, z / scalar)
    operator fun plus(other: Vec) = Vec(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Vec) = Vec(x - other.x, y - other.y, z - other.z)
}
