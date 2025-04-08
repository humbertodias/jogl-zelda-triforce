package jogl.zelda.triforce;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static final boolean DEBUG = false;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static void main(String... args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Triforce");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GLCanvas canvas = createGLCanvas(frame);
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.add(canvas);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas.requestFocusInWindow();
    }

    private static GLCanvas createGLCanvas(JFrame frame) {
        Triforce renderer = new Triforce();
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(renderer);

        Animator animator = new Animator(canvas);
        setupKeyListener(canvas, animator);
        setupWindowListener(frame, animator);

        if (DEBUG) {
            animator.setUpdateFPSFrames(1, System.out);
        }

        animator.start();
        return canvas;
    }

    private static void setupKeyListener(GLCanvas canvas, Animator animator) {
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    animator.stop();
                    System.exit(0);
                }
            }
        });
    }

    private static void setupWindowListener(JFrame frame, Animator animator) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                animator.stop();
            }
        });
    }
}