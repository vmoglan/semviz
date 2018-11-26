# VERTEX_SHADER
#version 400 core
in vec3 position;

void main()
{
    gl_Position = vec4(position, 1.0f);
}

# FRAGMENT_SHADER
#version 400 core
out vec4 frag_color;

void main()
{
	frag_color = vec4(0.5f, 0.5f, 0.5f, 1.0f);
}