package jogl.zelda.triforce;


import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.awt.GLCanvas;
import java.awt.event.*;
import javax.swing.JFrame;

public class Main {

    public static void main(String... args) {
        JFrame frame = new JFrame("Triforce");
        Triforce renderer = new Triforce();

        GLCanvas canvas = new GLCanvas();
        Animator animator = new Animator(canvas);

        canvas.addGLEventListener(renderer);
        canvas.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    animator.stop();
                    System.exit(0);
                }
            }

        });

        frame.add(canvas);
        frame.setSize(800, 600);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                animator.stop();
                frame.dispose();
            }
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        animator.setUpdateFPSFrames(1, System.out);
        animator.start();
        canvas.requestFocus();
    }
}
