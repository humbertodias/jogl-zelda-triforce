package jogl.zelda.triforce;


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import java.nio.FloatBuffer;

public class Triforce implements GLEventListener {

    double dElapsed = 0.001;
    double x = 0.0f;
    int z = -30;
    float floatMatYellowDiffuse[] = {0.86f, 0.74f, 0.14f, 1.0f};
    float floatMatOrangeDiffuse[] = {0.78f, 0.59f, 0.0f, 1.0f};
    FloatBuffer MatYellowDiffuse = FloatBuffer.wrap(floatMatYellowDiffuse);
    FloatBuffer MatOrangeDiffuse = FloatBuffer.wrap(floatMatOrangeDiffuse);

    @Override
    public void init(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
        gl.glEnable(GL2.GL_DEPTH_TEST);							// Enables Depth Testing
        gl.glDepthFunc(GL2.GL_LEQUAL);								// The Type Of Depth Testing To Do

        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);	// Really Nice Perspective Calculations

    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);    // clear screen and depth buffers
        gl.glLoadIdentity();                                    // reset modelview matrix

        // get the angl.gle we wish to use
        if (x >= 360.0f) {
            x = 0.0f;
        }

        // move and rotate the cube
        gl.glPushMatrix();
//        gl.glTranslatef(-5, -5, -35);
        gl.glTranslatef(-5, -5, z);
        gl.glRotated(x, 0.0f, 1.0f, 0.0f);

        gl.glColor3ub((byte) 220, (byte) 190, (byte) 35);           // yellow
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, MatYellowDiffuse);

        // draw the cube
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f(-5, -5, -1);         // left
        gl.glVertex3f(0, 5, -1);           // top
        gl.glVertex3f(5, -5, -1);          // right

        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f(-5, -5, 1);          // left
        gl.glVertex3f(0, 5, 1);            // top
        gl.glVertex3f(5, -5, 1);           // right
        gl.glEnd();

        gl.glColor3ub((byte) 200, (byte) 150, (byte) 0);            // dark yellow
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, MatOrangeDiffuse);

        gl.glBegin(GL2.GL_QUADS);
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f(-5, -5, -1);         // bottom left
        gl.glVertex3f(0, 5, -1);           // top left
        gl.glVertex3f(0, 5, 1);            // top right
        gl.glVertex3f(-5, -5, 1);          // bottom right

        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(5, -5, 1);           // bottom left
        gl.glVertex3f(0, 5, 1);            // top left
        gl.glVertex3f(0, 5, -1);           // top right
        gl.glVertex3f(5, -5, -1);          // bottom right
        gl.glEnd();
        gl.glPopMatrix();

        gl.glPushMatrix();
//        gl.glTranslatef(5, -5, -35);
        gl.glTranslatef(5, -5, z);
        gl.glRotated(x, 0.0f, 1.0f, 0.0f);

        gl.glColor3ub((byte) 220, (byte) 190, (byte) 35);           // yellow
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, MatYellowDiffuse);

        // draw the cube
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f(-5, -5, -1);         // left
        gl.glVertex3f(0, 5, -1);           // top
        gl.glVertex3f(5, -5, -1);          // right

        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f(-5, -5, 1);          // left
        gl.glVertex3f(0, 5, 1);            // top
        gl.glVertex3f(5, -5, 1);           // right
        gl.glEnd();

        gl.glColor3ub((byte) 200, (byte) 150, (byte) 0);            // dark yellow
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, MatOrangeDiffuse);

        gl.glBegin(GL2.GL_QUADS);
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f(-5, -5, -1);         // bottom left
        gl.glVertex3f(0, 5, -1);           // top left
        gl.glVertex3f(0, 5, 1);            // top right
        gl.glVertex3f(-5, -5, 1);          // bottom right

        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(5, -5, 1);           // bottom left
        gl.glVertex3f(0, 5, 1);            // top left
        gl.glVertex3f(0, 5, -1);           // top right
        gl.glVertex3f(5, -5, -1);          // bottom right
        gl.glEnd();
        gl.glPopMatrix();

        gl.glPushMatrix();

        gl.glTranslatef(0, 5, z);
        gl.glRotated(x, 0.0f, 1.0f, 0.0f);

        gl.glColor3ub((byte) 220, (byte) 190, (byte) 35);           // yellow
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, MatYellowDiffuse);

        // draw the cube
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f(-5, -5, -1);         // left
        gl.glVertex3f(0, 5, -1);           // top
        gl.glVertex3f(5, -5, -1);          // right

        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f(-5, -5, 1);          // left
        gl.glVertex3f(0, 5, 1);            // top
        gl.glVertex3f(5, -5, 1);           // right
        gl.glEnd();

        gl.glColor3ub((byte) 200, (byte) 150, (byte) 0);            // dark yellow
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, MatOrangeDiffuse);

        gl.glBegin(GL2.GL_QUADS);
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f(-5, -5, -1);         // bottom left
        gl.glVertex3f(0, 5, -1);           // top left
        gl.glVertex3f(0, 5, 1);            // top right
        gl.glVertex3f(-5, -5, 1);          // bottom right

        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(5, -5, 1);           // bottom left
        gl.glVertex3f(0, 5, 1);            // top left
        gl.glVertex3f(0, 5, -1);           // top right
        gl.glVertex3f(5, -5, -1);          // bottom right
        gl.glEnd();
        gl.glPopMatrix();

        // rotate 45 degrees every second
        x += 45.0 * dElapsed;

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        // avoid a divide by zero error!
        if (height <= 0) {
            height = 1;
        }

        final float h = (float) width / (float) height;
        // (re)size the viewport to consume the entire client area
        gl.glViewport(0, 0, width, height);
        // reset the projection matrix
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        // (re)calculate the aspect ratio of the viewport (0,0 is bottom left)
        glu.gluPerspective(45.0f, h, 1.0f, 100.0f);
        // lastly, reset the modelview matrix
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

}
