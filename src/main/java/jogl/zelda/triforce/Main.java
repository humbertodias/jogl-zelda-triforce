package jogl.zelda.triforce;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

public class Main {
    private static final boolean DEBUG = false;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static boolean isFullscreen = false;
    private static boolean isWireframe = false;

    private static Rectangle windowedBounds;
    private static GraphicsDevice device;
    private static GLCanvas canvas;
    private static JFrame frame;
    private static Animator animator;

    public static void main(String... args) {
        EventQueue.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        frame = new JFrame("Triforce");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        canvas = createGLCanvas();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.add(canvas);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        canvas.requestFocusInWindow();
    }

    private static GLCanvas createGLCanvas() {
        Triforce renderer = new Triforce();
        GLCanvas glCanvas = new GLCanvas();
        canvas = glCanvas;

        canvas.addGLEventListener(renderer);

        animator = new Animator(canvas);
        setupKeyListener(renderer);
        setupWindowListener();

        if (DEBUG) {
            animator.setUpdateFPSFrames(1, System.out);
        }

        animator.start();
        return canvas;
    }

    private static void setupKeyListener(Triforce renderer) {
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        exitApplication();
                        break;
                    case KeyEvent.VK_F:
                        toggleFullscreen();
                        break;
                    case KeyEvent.VK_W:
                        toggleWireframe(renderer);
                        break;
                }
            }
        });
    }


    private static void setupWindowListener() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApplication();
            }
        });
    }

    private static void toggleWireframe(Triforce renderer) {
        isWireframe = !isWireframe;
        renderer.setWireframe(isWireframe);
        canvas.display();
    }

    private static void toggleFullscreen() {
        boolean enteringFullscreen = !isFullscreen;

        if (enteringFullscreen) {
            windowedBounds = frame.getBounds();
        }

        frame.remove(canvas);
        frame.dispose();
        frame.setUndecorated(enteringFullscreen);
        frame.setResizable(!enteringFullscreen);

        if (enteringFullscreen) {
            device.setFullScreenWindow(frame);
        } else {
            device.setFullScreenWindow(null);
            frame.setBounds(windowedBounds);
        }

        frame.add(canvas);
        frame.setVisible(true);
        canvas.requestFocusInWindow();

        isFullscreen = enteringFullscreen;
    }

    private static void exitApplication() {
        new Thread(() -> {
            try {
                if (animator != null && animator.isStarted()) {
                    animator.stop();
                }

                EventQueue.invokeAndWait(canvas::destroy);
            } catch (InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
                System.exit(1);
            } finally {
                frame.dispose();
                System.exit(0);
            }
        }).start();
    }
}
