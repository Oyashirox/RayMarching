package fr.oyashirox.raymarching

import fr.oyashirox.raymarching.math.Vec

/**
 * Created by Florian on 21/09/2017.
 */
class DirectionalLight(val direction: Vec, val color: Int) {
    val normalizedLight = -direction.normalize()
}