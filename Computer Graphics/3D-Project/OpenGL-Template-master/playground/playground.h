#ifndef PLAYGROUND_H
#define PLAYGROUND_H

// Include GLEW
#include <GL/glew.h>

// Include GLM
#include <glm/glm.hpp>

//some global variables for handling the buffers
GLuint vertexbuffer;
GLuint colorbuffer;
GLuint normalbuffer;
GLuint VertexArrayID;
GLuint vertexbuffer_size;

//program ID of the shaders, required for handling the shaders with OpenGL
GLuint programID;

//global variables to handle the MVP matrix
GLuint MatrixMVPID;
glm::mat4 MatrixMVP;

GLuint MatrixMVID;
glm::mat4 MatrixMV;



// ADDED: simple object
struct pgObject
{
	// position in space
	glm::vec3 position;

	// rotation in radians
	glm::vec3 rotation;

	// scale
	glm::vec3 scale;
};


int main(void); //<<< main function, called at startup
void updateAnimationLoop(); //<<< updates the animation loop
bool initializeWindow(); //<<< initializes the window using GLFW and GLEW
bool initializeMVPTransformation(const glm::vec3& position, const glm::vec3& rotation, const glm::vec3& scale);
bool initializeVertexbuffer(); //<<< initializes the vertex buffer array and binds it OpenGL
bool cleanupVertexbuffer(); //<<< frees all recources from the vertex buffer
bool closeWindow(); //<<< Closes the OpenGL window and terminates GLFW

#endif
