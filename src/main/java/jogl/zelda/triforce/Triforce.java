package jogl.zelda.triforce;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

import java.nio.FloatBuffer;

public class Triforce implements GLEventListener {

    private double rotationSpeed = 500;
    private double deltaTime = 0.001;
    private double rotationAngle = 0.0;
    private final int zPosition = -30;

    private final FloatBuffer yellowMaterial = FloatBuffer.wrap(new float[]{0.86f, 0.74f, 0.14f, 1.0f});
    private final FloatBuffer orangeMaterial = FloatBuffer.wrap(new float[]{0.78f, 0.59f, 0.0f, 1.0f});

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0.5f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        // Enable lightening
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);

        // Sets the position of the light
        float[] lightPos = {0.0f, 0.0f, 10.0f, 1.0f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, FloatBuffer.wrap(lightPos));
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // No cleanup needed
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        drawTriangle(gl, -5, -5);
        drawTriangle(gl, 5, -5);
        drawTriangle(gl, 0, 5);

        rotationAngle = (rotationAngle + rotationSpeed * deltaTime) % 360.0;
    }

    private void drawTriangle(GL2 gl, float x, float y) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, zPosition);
        gl.glRotated(rotationAngle, 0.0f, 1.0f, 0.0f);

        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, yellowMaterial);
        drawFace(gl);

        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, orangeMaterial);
        drawSides(gl);

        gl.glPopMatrix();
    }

    private void drawFace(GL2 gl) {
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glNormal3f(0, 0, -1);
        gl.glVertex3f(-5, -5, -1);
        gl.glVertex3f(0, 5, -1);
        gl.glVertex3f(5, -5, -1);

        gl.glNormal3f(0, 0, 1);
        gl.glVertex3f(-5, -5, 1);
        gl.glVertex3f(0, 5, 1);
        gl.glVertex3f(5, -5, 1);
        gl.glEnd();
    }

    private void drawSides(GL2 gl) {
        gl.glBegin(GL2.GL_QUADS);

        // Left side
        gl.glNormal3f(-1, 0, 0);
        gl.glVertex3f(-5, -5, -1);
        gl.glVertex3f(0, 5, -1);
        gl.glVertex3f(0, 5, 1);
        gl.glVertex3f(-5, -5, 1);

        // Right side
        gl.glNormal3f(1, 0, 0);
        gl.glVertex3f(5, -5, 1);
        gl.glVertex3f(0, 5, 1);
        gl.glVertex3f(0, 5, -1);
        gl.glVertex3f(5, -5, -1);

        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        if (height <= 0) height = 1;

        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        float aspect = (float) width / height;

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, aspect, 1.0f, 100.0f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
