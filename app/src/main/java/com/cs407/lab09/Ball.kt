package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f  // (posX, posY) is top-left corner of ball
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }
        // update distance traveled using Equation (2) first
        // l = v0 * dT + (1/6) * dT^2 * (3a0 + a1)
        posX += velocityX * dT + (1/6f) * dT * dT * (3 * accX + xAcc)
        posY += velocityY * dT + (1/6f) * dT * dT * (3 * accY + yAcc)

        // update velocity using Equation (1)
        // v1 = v0 + a0 * dT + (a1 - a0) * dT / 2f
        velocityX += accX * dT + (xAcc - accX) * dT / 2f
        velocityY += accY * dT + (yAcc - accY) * dT / 2f

        // update acceleration
        accX = xAcc
        accY = yAcc

    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // Check for collision with the left wall
        if (posX < 0) {
            posX = 0f
            velocityX = 0f
            accX = 0f
        }
        // Check for collision with the right wall
        else if (posX > backgroundWidth - ballSize) {
            posX = backgroundWidth - ballSize
            velocityX = 0f
            accX = 0f
        }

        // Check for collision with the top wall
        if (posY < 0) {
            posY = 0f
            velocityY = 0f
            accY = 0f
        }
        // Check for collision with the bottom wall
        else if (posY > backgroundHeight - ballSize) {
            posY = backgroundHeight - ballSize
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        posX = (backgroundWidth - ballSize) / 2f
        posY = (backgroundHeight - ballSize) / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}