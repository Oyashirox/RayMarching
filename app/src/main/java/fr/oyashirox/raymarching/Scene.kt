package fr.oyashirox.raymarching

import fr.oyashirox.raymarching.math.Vec

/**
 * Created by Oyashirox on 20/09/2017.
 */
interface Scene {
    fun distance(position: Vec): Double
    fun color(position: Vec): Int
    fun normal(position: Vec): Vec
}