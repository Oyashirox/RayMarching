package fr.oyashirox.raymarching.objects

import fr.oyashirox.raymarching.Scene
import fr.oyashirox.raymarching.math.Vec

/**
 * Created by Oyashirox on 20/09/2017.
 */

class Sphere(val center: Vec, val radius: Double, val color: Int): Scene {
    override fun color(position: Vec): Int = color
    override fun distance(position: Vec) = (position - center).norm() - radius
}