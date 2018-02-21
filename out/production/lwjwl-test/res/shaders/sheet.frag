#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
    vec2 tc;
} fs_in;

uniform sampler2D tex;
uniform int facing;
uniform vec2 u_spriteSize;
uniform vec2 u_spritePosition;

void main(){
    //color = texture(tex, fs_in.tc);
    //color = texture(tex, fs_in.tc * vec2(1.0,1.0) + vec2(0,0));
    //color = texture(tex, fs_in.tc * u_spriteSize + u_spritePosition);

    if(facing == -1){
        color = texture(tex, vec2(0.0 - fs_in.tc.x,fs_in.tc.y) * u_spriteSize + vec2(1.0 - u_spritePosition.x - u_spriteSize.x, u_spritePosition.y));
    }else{
        color = texture(tex, fs_in.tc * u_spriteSize + u_spritePosition);
    }

    if(color.w < 1.0)
        discard;
}