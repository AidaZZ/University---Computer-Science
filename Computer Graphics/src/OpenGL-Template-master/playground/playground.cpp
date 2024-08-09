#include "playground.h"
//#include <ft2build.h>

#include <stdio.h>
#include <stdlib.h>
#include <iostream>


#include <glfw3.h>
GLFWwindow* window;

#include <glm/gtc/matrix_transform.hpp>
using namespace glm;

#include <common/shader.hpp>

// my dice m
pgObject cubes[2];

int main(void)
{
	// Initialize window
	bool windowInitialized = initializeWindow();
	if (!windowInitialized) return -1;

	// Initialize vertex buffer
	bool vertexbufferInitialized = initializeVertexbuffer();
	if (!vertexbufferInitialized) return -1;

	glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LESS);

	// Create and compile our GLSL program from the shaders
	programID = LoadShaders("SimpleVertexShader.vertexshader", "SimpleFragmentShader.fragmentshader");

	MatrixMVID = glGetUniformLocation(programID, "MV");
	MatrixMVPID = glGetUniformLocation(programID, "MVP");



	// initialize our cubes m
	cubes[0].position = glm::vec3(2.0f, 0.0f, 0.0f);
	cubes[0].rotation = glm::vec3(0.0f, 0.0f, 0.0f);
	cubes[0].scale = glm::vec3(1.f, 1.f, 1.f);

	cubes[1].position = glm::vec3(-2.0f, 0.0f, 0.0f);
	cubes[1].rotation = glm::vec3(0.0f, 0.0f, 0.0f);
	cubes[1].scale = glm::vec3(1.f, 1.f, 1.f);

	// start animation loop until escape key is pressed
	do {
		updateAnimationLoop();
	} // Check if the ESC key was pressed or the window was closed
	while (glfwGetKey(window, GLFW_KEY_ESCAPE) != GLFW_PRESS &&
		glfwWindowShouldClose(window) == 0);

	// Cleanup and close window
	cleanupVertexbuffer();
	glDeleteProgram(programID);
	closeWindow();

	return 0;
}

void updateAnimationLoop() {
	// variable to control rotation m
	static bool isRotating = true;
	const float rotationSpeedAuto = 0.0005f;
	const float rotationSpeedManual = 0.01f;

	// Clear the screen
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	// Use the shader
	glUseProgram(programID);

	// Toggle rotation on space bar press
	static bool spaceKeyPressed = false;

	if (glfwGetKey(window, GLFW_KEY_SPACE) == GLFW_PRESS) {
		if (!spaceKeyPressed) {
			isRotating = !isRotating; // Toggle rotation
			spaceKeyPressed = true;
		}
	}
	else {
		spaceKeyPressed = false;
	}

	// The angle to add to the current rotation of the objects m
	glm::vec3 rotation(0.0f, 0.0f, 0.0f);

	if (isRotating) {
		// Automatic rotation
		rotation[1] += rotationSpeedAuto;
		rotation[2] += rotationSpeedAuto;
	}

	// Manual rotation controls
	if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) {
		rotation[1] += rotationSpeedManual;
	}
	else if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) {
		rotation[1] -= rotationSpeedManual;
	}

	if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) {
		rotation[2] -= rotationSpeedManual;
	}
	else if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) {
		rotation[2] += rotationSpeedManual;
	}




		// Rotate & draw our objects m
	for (int i = 0; i < 2; i++)
	{
		if (i == 0)
			cubes[i].rotation += rotation;
		else
			cubes[i].rotation -= rotation;

		// Rest of the transformation code
		initializeMVPTransformation(cubes[i].position, cubes[i].rotation, cubes[i].scale);

		// Send our transformation to the currently bound shader
		glUniformMatrix4fv(MatrixMVPID, 1, GL_FALSE, &MatrixMVP[0][0]);
		glUniformMatrix4fv(MatrixMVID, 1, GL_FALSE, &MatrixMV[0][0]);

		// Draw the triangles
		glDrawArrays(GL_TRIANGLES, 0, vertexbuffer_size * 3); // 3 indices starting at 0 -> 1 triangle
	}

	// Swap buffers
	glfwSwapBuffers(window);
	glfwPollEvents();
}

bool initializeWindow()
{
	// Initialise GLFW
	if (!glfwInit())
	{
		fprintf(stderr, "Failed to initialize GLFW\n");
		getchar();
		return false;
	}

	glfwWindowHint(GLFW_SAMPLES, 4);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); 
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

	// Open a window and create its OpenGL context
	window = glfwCreateWindow(1024, 768, "Dice", NULL, NULL);
	if (window == NULL) {
		fprintf(stderr, "Failed to open GLFW window. If you have an Intel GPU, they are not 3.3 compatible. Try the 2.1 version of the tutorials.\n");
		getchar();
		glfwTerminate();
		return false;
	}
	glfwMakeContextCurrent(window);

	// Initialize GLEW
	glewExperimental = true; // Needed for core profile
	if (glewInit() != GLEW_OK) {
		fprintf(stderr, "Failed to initialize GLEW\n");
		getchar();
		glfwTerminate();
		return false;
	}

	// Ensure we can capture the escape key being pressed below
	glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);

	// background
	glClearColor(0.8f, 1.0f, 0.8f, 0.0f);
	return true;
}

bool initializeMVPTransformation(const glm::vec3& position, const glm::vec3& rotation, const glm::vec3& scale)
{
	// Get a handle for our "MVP" uniform
	GLuint MatrixIDnew = glGetUniformLocation(programID, "MVP");
	MatrixMVPID = MatrixIDnew;


	// Projection matrix : 45? Field of View, 4:3 ratio, display range : 0.1 unit <-> 100 units
	glm::mat4 Projection = glm::perspective(glm::radians(45.0f), 4.0f / 3.0f, 0.1f, 100.0f);
	//glm::mat4 Projection = glm::frustum(-2.0f, 2.0f, -2.0f, 2.0f, -2.0f, 2.0f);

	// Camera matrix
	glm::mat4 View = glm::lookAt(
		glm::vec3(8, -3, 5), // Camera is at (4,3,-3), in World Space
		glm::vec3(0, 0, 0), // and looks at the origin
		glm::vec3(0, -1, 0)  // Head is up (set to 0,-1,0 to look upside-down)
	);

	// Model matrix: an identity matrix (model will be at the origin)
	glm::mat4 Model = glm::mat4(1.0f);

	//Additional translation of the model m
	Model = glm::translate(Model, position);

	//Additional rotation of the model m
	Model = glm::rotate(Model, rotation[0], glm::vec3(1.0f, 0.0f, 0.0f));
	Model = glm::rotate(Model, rotation[1], glm::vec3(0.0f, 1.0f, 0.0f));
	Model = glm::rotate(Model, rotation[2], glm::vec3(0.0f, 0.0f, 1.0f));

	Model = glm::scale(Model, scale);



// Our ModelViewProjection : multiplication of our 3 matrices
	MatrixMVP = Projection * View * Model;// *transformation; // Remember, matrix multiplication is the other way around
	MatrixMV = View * Model;// *transformation; //We also need MV in the shader to transform the light position

	return true;
}

bool initializeVertexbuffer()
{
	glGenVertexArrays(1, &VertexArrayID);
	glBindVertexArray(VertexArrayID);

	vertexbuffer_size = 8 * 12;
	//int x = 1;

	static  GLfloat  cube0[8][108];
	static  GLfloat  colors[8][108];
	static  GLfloat  normals[8][108];
	int i = 0;
	int j = 0;

	for (int z = 0; z <= 1; z++) {
		for (int y = 0; y <= 1; y++) {
			for (int x = 0; x <= 1; x++) {
				//bottom square
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 0.0f - z;

				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 0.0f - z;

				//top square
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 1.0f - z;
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 1.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 1.0f - z;

				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 1.0f - z;
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 1.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 1.0f - z;


				//front square
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 1.0f - z;

				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 1.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 1.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 0.0f - z;


				//back square
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 1.0f - z;

				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 1.0f - z;
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 1.0f - z;
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 0.0f - z;

				//left square
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 1.0f - z;

				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 1.0f - z;
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 0.0f - y; cube0[i][j++] = 1.0f - z;

				//right square
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 1.0f - z;

				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 0.0f - z;
				cube0[i][j++] = 1.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 1.0f - z;
				cube0[i][j++] = 0.0f - x;  cube0[i][j++] = 1.0f - y; cube0[i][j++] = 1.0f - z;

				j = 0;

				colors[i][j++] = 0.4; colors[i][j++] = 0.0; colors[i][j++] = 0.3;
				colors[i][j++] = 0.4; colors[i][j++] = 0.0; colors[i][j++] = 0.3;
				colors[i][j++] = 0.4; colors[i][j++] = 0.0; colors[i][j++] = 0.3;
				colors[i][j++] = 0.4; colors[i][j++] = 0.0; colors[i][j++] = 0.3;
				colors[i][j++] = 0.4; colors[i][j++] = 0.0; colors[i][j++] = 0.3;
				colors[i][j++] = 0.4; colors[i][j++] = 0.0; colors[i][j++] = 0.3;

				colors[i][j++] = 1.0; colors[i][j++] = 0.0; colors[i][j++] = 0.0;
				colors[i][j++] = 1.0; colors[i][j++] = 0.0; colors[i][j++] = 0.0;
				colors[i][j++] = 1.0; colors[i][j++] = 0.0; colors[i][j++] = 0.0;
				colors[i][j++] = 1.0; colors[i][j++] = 0.0; colors[i][j++] = 0.0;
				colors[i][j++] = 1.0; colors[i][j++] = 0.0; colors[i][j++] = 0.0;
				colors[i][j++] = 1.0; colors[i][j++] = 0.0; colors[i][j++] = 0.0;

				colors[i][j++] = 0.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;
				colors[i][j++] = 0.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;
				colors[i][j++] = 0.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;
				colors[i][j++] = 0.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;
				colors[i][j++] = 0.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;
				colors[i][j++] = 0.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;

				colors[i][j++] = 0.0; colors[i][j++] = 0.0; colors[i][j++] = 1.0;
				colors[i][j++] = 0.0; colors[i][j++] = 0.0; colors[i][j++] = 1.0;
				colors[i][j++] = 0.0; colors[i][j++] = 0.0; colors[i][j++] = 1.0;
				colors[i][j++] = 0.0; colors[i][j++] = 0.0; colors[i][j++] = 1.0;
				colors[i][j++] = 0.0; colors[i][j++] = 0.0; colors[i][j++] = 1.0;
				colors[i][j++] = 0.0; colors[i][j++] = 0.0; colors[i][j++] = 1.0;

				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;
				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;
				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;
				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;
				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;
				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 0.0;

				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 1.0;
				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 1.0;
				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 1.0;
				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 1.0;
				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 1.0;
				colors[i][j++] = 1.0; colors[i][j++] = 1.0; colors[i][j++] = 1.0;

				j = 0;

				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = -1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = -1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = -1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = -1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = -1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = -1.0;

				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;

				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = .0;

				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;
				normals[i][j++] = 0.0; normals[i][j++] = 0.0; normals[i][j++] = 1.0;

				normals[i][j++] = 0.0; normals[i][j++] = -1.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = -1.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = -1.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = -1.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = -1.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = -1.0; normals[i][j++] = 0.0;

				normals[i][j++] = 0.0; normals[i][j++] = 1.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = 1.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = 1.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = 1.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = 1.0; normals[i][j++] = 0.0;
				normals[i][j++] = 0.0; normals[i][j++] = 1.0; normals[i][j++] = 0.0;

				j = 0;

				i++;
			};
		};
	};

	//moved pointer initialization here, to it's proper place m
	glGenBuffers(1, &vertexbuffer);
	glBindBuffer(GL_ARRAY_BUFFER, vertexbuffer);
	glBufferData(GL_ARRAY_BUFFER, sizeof(cube0), &cube0[0][0], GL_STATIC_DRAW);

	// 1st attribute buffer : vertices
	glEnableVertexAttribArray(0);
	glVertexAttribPointer(
		0,         // attribute
		3,         // size
		GL_FLOAT,  // type
		GL_FALSE,  // normalized?
		0,         // stride
		(void*)0   // array buffer offset
	);

	glGenBuffers(1, &colorbuffer);
	glBindBuffer(GL_ARRAY_BUFFER, colorbuffer);
	glBufferData(GL_ARRAY_BUFFER, sizeof(colors) * 6, &colors[0][0], GL_STATIC_DRAW);

	// 2nd attribute buffer : colors
	glEnableVertexAttribArray(1);
	glVertexAttribPointer(
		1,         // attribute
		3,         // size
		GL_FLOAT,  // type
		GL_FALSE,  // normalized?
		0,         // stride
		(void*)0   // array buffer offset
	);

	glGenBuffers(1, &normalbuffer);
	glBindBuffer(GL_ARRAY_BUFFER, normalbuffer);
	glBufferData(GL_ARRAY_BUFFER, sizeof(normals) * 6, &normals[0][0], GL_STATIC_DRAW);

	// 3rd attribute buffer : normals
	glEnableVertexAttribArray(2);
	glVertexAttribPointer(
		2,         // attribute
		3,         // size
		GL_FLOAT,  // type
		GL_FALSE,  // normalized?
		0,         // stride
		(void*)0   // array buffer offset
	);

	return true;
}

bool cleanupVertexbuffer()
{
	// Disable vertex attribute arrays
	glDisableVertexAttribArray(0);
	glDisableVertexAttribArray(1);
	glDisableVertexAttribArray(2);

	// Cleanup VBO
	glDeleteBuffers(1, &vertexbuffer);
	glDeleteVertexArrays(1, &VertexArrayID);

	glDeleteBuffers(1, &colorbuffer);
	glDeleteVertexArrays(1, &VertexArrayID);

	return true;
}

bool closeWindow()
{
	glfwTerminate();
	return true;
}

