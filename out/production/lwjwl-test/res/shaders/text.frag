#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
    vec2 tc;
} fs_in;

uniform sampler2D tex;
uniform vec2 u_spriteSize;
uniform vec2 u_spritePosition;

void main(){
    //color = texture(tex, fs_in.tc);
    color = texture(tex, fs_in.tc * u_spriteSize + u_spritePosition);

    if(color.r > 0.0)
        discard;
}