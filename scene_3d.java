import processing.core.*; 
import processing.xml.*; 

import processing.opengl.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class scene_3d extends PApplet {

// 3D Scene Example

/// Required Items:

/// Camera motion:
// My camera zooms into and around/out of scene.

/// Object Animation:
/// The train circles around the christmas tree.

/// Object instancing:
/// The box cars are all instances of the same object.
/// I also instance the locamotive, but only one.

/// Object contact:
/// Objects that make exact contact include:
/// 1) The red rug on the brown floor.
/// 2) The tree stump on the red rug.
/// 3) The train wheels on the brown floor.

/// Duration: Mine just repeats.

/// Lighting and shading:
/// Aside from the ambient light sources, I have 3 directional light sources.
/// One of them shines over head of the tree, and another moves with the camera.

/// Perspective projection:
/// Perspective projection that was already in the sample project.



float time = 0;  // keep track of passing of time

public void setup() {
  size(800, 800, OPENGL);  // must use OPENGL here !!!
  noStroke();     // do not draw the edges of polygons
}

// Draw a scene with a cylinder, a sphere and a box
public void draw() {
  
  resetMatrix();  // set the transformation matrix to the identity (important!)

  background(0xff87CEFA);  // clear the screen to black
  
  // set up for perspective projection
  perspective (PI * 0.333f, 1.0f, 0.01f, 1000.0f);
  
  float X = 200;
  float Y = -cos(radians(time*4)) * 200;
  float Z = -cos(radians(time*4)) * 400;
  
  // place the camera in the scene (just like gluLookAt())
  camera (X, Y, Z, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f);
  //camera (200, 0, 0, 0.0, 0.0, -1.0, 0.0, 1.0, 0.0);

  scale (1.0f, -1.0f, 1.0f);  // change to right-handed coordinate system
  
  // create an ambient light source
  ambientLight(102, 102, 102);
  
  // create two directional light sources
  lightSpecular(204, 204, 204);
  
  directionalLight(200, 200, 255, -1, -1, 0);
  
  directionalLight(255, 255, 255, -0.7f, -0.7f, -Z);
  directionalLight(152, 152, 152, 0, 0, -1);
  
  fill(139, 69, 19);       // "fill" sets both diffuse and ambient color (yuck!)
  ambient (139, 69, 19);      // set ambient color
  

  pushMatrix();
  translate(0, -8, 0);
  cylinder(10, 30, 100);
  popMatrix();
  
  
  pushMatrix();
  translate(0, -10, 0); //-10
  cylinder(90, 1, 100);
  popMatrix();
  
  fill(255, 0, 0);       // "fill" sets both diffuse and ambient color (yuck!)
  ambient (255, 0, 0);      // set ambient color
  
  
  pushMatrix();
  translate(0, -8.7f, 0);
  cylinder(60, 1, 100);
  popMatrix();
  
  fill(34, 139, 34);       // "fill" sets both diffuse and ambient color (yuck!)
  ambient (34, 139, 34);      // set ambient color
  
  
  pushMatrix();
  rotateZ(radians(180));
  translate(0, -145, 0);
  fullcone(40, 130, 100);
  popMatrix();

  pushMatrix();
  rotateZ(radians(180));
  translate(0, -70, 0);
  fullcone(40, 50, 100);
  popMatrix();

  pushMatrix();
  rotateZ(radians(180));
  translate(0, -75, 0);
  fullcone(40, 50, 100);
  popMatrix();
  
  pushMatrix();
  rotateZ(radians(180));
  translate(0, -80, 0);
  fullcone(40, 50, 100);
  popMatrix();
  
  pushMatrix();
  rotateZ(radians(180));
  translate(0, -85, 0);
  fullcone(40, 50, 100);
  popMatrix();
  
  
  
  
  /// 90 Deg
  
  boxcarcolor=0;
  rotate (time/4, 0.0f, 1.0f, 0.0f);
  pushMatrix();
  translate(-80, 0, 0);
  rotateY(radians(-90));
  locamotive();
  popMatrix();

  boxcarcolor++;    
  pushMatrix();
  translate(80, 0, 0);
  rotateY(-radians(90));
  boxcar();
  popMatrix();

  boxcarcolor++;
  pushMatrix();
  translate(0, 0, -80);
  boxcar();
  popMatrix();
  
  boxcarcolor++;
  pushMatrix();
  translate(0, 0, 80);
  boxcar();
  popMatrix();
  
  /// 45 deg
  
  boxcarcolor++;
  pushMatrix();
  translate(80/sqrt(2), 0, -80/sqrt(2));
  rotateY(radians(90+45));
  boxcar();
  popMatrix();
  
  boxcarcolor++;
  pushMatrix();
  translate(-80/sqrt(2), 0, 80/sqrt(2));
  rotateY(radians(90+45));
  boxcar();
  popMatrix();

  boxcarcolor++;
  pushMatrix();
  translate(-80/sqrt(2), 0, -80/sqrt(2));
  rotateY(radians(45));
  boxcar();
  popMatrix();
  
  boxcarcolor++;
  pushMatrix();
  translate(80/sqrt(2), 0, 80/sqrt(2));
  rotateY(radians(45));
  boxcar();
  popMatrix();

  
  /// 45/2 Deg
  
  boxcarcolor++;
  pushMatrix();
  translate(cos(radians(45/2))*80, 0, -sin(radians(45/2))*80);
  rotateY(radians(90+(45/2)));
  boxcar();
  popMatrix();
  
  boxcarcolor++;    
  pushMatrix();
  translate(-cos(radians(45/2))*80, 0, -sin(radians(45/2))*80);
  rotateY(-radians(90+(45/2)));
  boxcar();
  popMatrix();
  
  boxcarcolor++;
  pushMatrix();
  translate(cos(radians(45/2))*80, 0, sin(radians(45/2))*80);
  rotateY(-radians(90+(45/2)));
  boxcar();
  popMatrix();
  
  boxcarcolor++;
  pushMatrix();
  translate(sin(radians(45/2))*80, 0, cos(radians(45/2))*80);
  rotateY(radians(45/2));
  boxcar();
  popMatrix();
  
  boxcarcolor++;
  pushMatrix();
  translate(sin(radians(45/2))*80, 0, -cos(radians(45/2))*80);
  rotateY(-radians(45/2));
  boxcar();
  popMatrix();
      
  boxcarcolor++;
  pushMatrix();
  translate(-sin(radians(45/2))*80, 0, -cos(radians(45/2))*80);
  rotateY(radians(45/2));
  boxcar();
  popMatrix();
  
  boxcarcolor++;
  pushMatrix();
  translate(-sin(radians(45/2))*80, 0, cos(radians(45/2))*80);
  rotateY(radians(-45/2));
  boxcar();
  popMatrix();
  

  // step forward in time
  time += 0.05f;
}

public void locamotive()
{
  
  fill(84,84,84);         // "fill" sets both diffuse and ambient color (yuck!)
  ambient (192, 192, 192);     // set ambient color
  specular(192, 192, 192);  // set specular color
  shininess(192);         // set specular exponent
  
  //rotate (time, 1.0, 0.0, 0.0);      // rotate based on "time"
  rotate (radians(180), 1.0f, 0.0f, 0.0f);
  rotateZ(radians(90));
  translate(0, -25, 0);
  rotateY(radians(-60));
  translate(2, 0, -4);
  cone(5, 10, 5);
  translate(-2, 0, 4);
  rotateY(radians(60));
  translate(0, 25, 0);
  rotateZ(radians(-90));
  
  fill(255, 50, 50);       // "fill" sets both diffuse and ambient color (yuck!)
  ambient (255, 0, 0);      // set ambient color
  specular(255, 255, 255);   // set specular color
  shininess(200);            // set specular exponent
  
  box(30, 10, 10); 
  translate (-10, -5, 0); 
  
  
  fill(84,84,84);         // "fill" sets both diffuse and ambient color (yuck!)
  ambient (192, 192, 192);     // set ambient color
  specular(192, 192, 192);  // set specular color
  shininess(192);       
  
  translate(0,-1,0);
  box(11, 7, 12);
  translate (10, 5, 0);
  translate(0,1,0);
  
  translate (11.5f, 5, -5.1f);
  rotate (1.5f, 1.0f, 0.0f, 0.0f);

  translate(0,-1,0);
  
  
  fill(0, 0, 0);         // "fill" sets both diffuse and ambient color (yuck!)
  ambient (0, 0, 0);     // set ambient color
  specular(10, 10, 10);  // set specular color
  shininess(10);         // set specular exponent
  
    
  cylinder(3, 12, 10);
  translate (-23, 0, 0);
  cylinder(3, 12, 10);
}


int boxcarcolor = 0;


public void boxcar()
{

  if (0 == boxcarcolor%4)
    fill(70, 70, 255);       // "fill" sets both diffuse and ambient color (yuck!)
  else if (0 == boxcarcolor%3)
    fill(255, 70, 70);       // "fill" sets both diffuse and ambient color (yuck!)
  else
    fill(215, 215, 0);       // "fill" sets both diffuse and ambient color (yuck!)
  
  ambient (100, 100, 100);      // set ambient color
  specular(0, 0, 0);   // set specular color
  shininess(1000);            // set specular exponent
  
  rotate (radians(180), 1.0f, 0.0f, 0.0f);
  box(30, 10, 10);
  translate (11.5f, 5, -5.1f);
  rotate (1.5f, 1.0f, 0.0f, 0.0f);
  
  fill(0, 0, 0);         // "fill" sets both diffuse and ambient color (yuck!)
  ambient (0, 0, 0);     // set ambient color
  specular(10, 10, 10);  // set specular color
  shininess(10);         // set specular exponent
  
  translate(0,-1,0);
    
  cylinder(3, 12, 10);
  translate (-23, 0, 0);
  cylinder(3, 12, 10);
}

// Draw a cylinder of a given radius, height and number of sides.
// The base is on the y=0 plane, and it extends vertically in the y direction.
public void cone (float radius, float height, int sides) {
  int i,ii;
  float []c = new float[sides];
  float []s = new float[sides];

  for (i = 0; i < sides; i++) {
    float theta = TWO_PI * i / (float) sides;
    c[i] = cos(theta);
    s[i] = sin(theta);
  }
  
  // bottom end cap
  
  normal (0.0f, -1.0f, 0.0f);
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape(TRIANGLES);
    vertex (c[ii] * (i/sides), 0.0f, s[ii] * (i/sides));
    vertex (c[i] * (i/sides), 0.0f, s[i] * (i/sides));
    vertex (0.0f, 0.0f, 0.0f);
    endShape();
    
    if (i >= sides/2) break;
  }
  
  // top end cap

  normal (0.0f, 1.0f, 0.0f);
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape(TRIANGLES);
    vertex (c[ii] * radius, height, s[ii] * radius);
    vertex (c[i] * radius, height, s[i] * radius);
    vertex (0.0f, height, 0.0f);
    endShape();
        if (i >= sides/2) break;
  }
  
  // main body of cylinder
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape();
    normal (c[i], 0.0f, s[i]);
    vertex (c[i] * radius*(i/sides), 0.0f, s[i] * radius*(i/sides));
    vertex (c[i] * radius, height, s[i] * radius);
    normal (c[ii], 0.0f, s[ii]);
    vertex (c[ii] * radius, height, s[ii] * radius);
    vertex (c[ii] * radius*(i/sides), 0.0f, s[ii] * radius*(i/sides));
    endShape(CLOSE);
        if (i >= sides/2) break;
  }
}

// Draw a cylinder of a given radius, height and number of sides.
// The base is on the y=0 plane, and it extends vertically in the y direction.
public void fullcone (float radius, float height, int sides) {
  int i,ii;
  float []c = new float[sides];
  float []s = new float[sides];

  for (i = 0; i < sides; i++) {
    float theta = TWO_PI * i / (float) sides;
    c[i] = cos(theta);
    s[i] = sin(theta);
  }
  
  // bottom end cap
  
  normal (0.0f, -1.0f, 0.0f);
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape(TRIANGLES);
    vertex (c[ii] * (i/sides), 0.0f, s[ii] * (i/sides));
    vertex (c[i] * (i/sides), 0.0f, s[i] * (i/sides));
    vertex (0.0f, 0.0f, 0.0f);
    endShape();
  }
  
  // top end cap

  normal (0.0f, 1.0f, 0.0f);
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape(TRIANGLES);
    vertex (c[ii] * radius, height, s[ii] * radius);
    vertex (c[i] * radius, height, s[i] * radius);
    vertex (0.0f, height, 0.0f);
    endShape();
  }
  
  // main body of cylinder
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape();
    normal (c[i], 0.0f, s[i]);
    vertex (c[i] * radius*(i/sides), 0.0f, s[i] * radius*(i/sides));
    vertex (c[i] * radius, height, s[i] * radius);
    normal (c[ii], 0.0f, s[ii]);
    vertex (c[ii] * radius, height, s[ii] * radius);
    vertex (c[ii] * radius*(i/sides), 0.0f, s[ii] * radius*(i/sides));
    endShape(CLOSE);
  }
}

// Draw a cylinder of a given radius, height and number of sides.
// The base is on the y=0 plane, and it extends vertically in the y direction.
public void cylinder (float radius, float height, int sides) {
  int i,ii;
  float []c = new float[sides];
  float []s = new float[sides];

  for (i = 0; i < sides; i++) {
    float theta = TWO_PI * i / (float) sides;
    c[i] = cos(theta);
    s[i] = sin(theta);
  }
  
  // bottom end cap
  
  normal (0.0f, -1.0f, 0.0f);
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape(TRIANGLES);
    vertex (c[ii] * radius, 0.0f, s[ii] * radius);
    vertex (c[i] * radius, 0.0f, s[i] * radius);
    vertex (0.0f, 0.0f, 0.0f);
    endShape();
  }
  
  // top end cap

  normal (0.0f, 1.0f, 0.0f);
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape(TRIANGLES);
    vertex (c[ii] * radius, height, s[ii] * radius);
    vertex (c[i] * radius, height, s[i] * radius);
    vertex (0.0f, height, 0.0f);
    endShape();
  }
  
  // main body of cylinder
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape();
    normal (c[i], 0.0f, s[i]);
    vertex (c[i] * radius, 0.0f, s[i] * radius);
    vertex (c[i] * radius, height, s[i] * radius);
    normal (c[ii], 0.0f, s[ii]);
    vertex (c[ii] * radius, height, s[ii] * radius);
    vertex (c[ii] * radius, 0.0f, s[ii] * radius);
    endShape(CLOSE);
  }
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "scene_3d" });
  }
}
