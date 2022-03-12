import javax.swing.*;
import java.awt.*;

public class PhotoshapeWindow extends JFrame {

    public PhotoshapeWindow(String title) {

        super(title);
        setLayout(new BorderLayout());

        JPanel photoshapeCanvas = new PhotoshapeCanvas();
        JPanel sectionToolSelection = new SectionToolSelectionPanel((PhotoshapeCanvas) photoshapeCanvas);
        MenuBar headerBar = new HeaderBar((PhotoshapeCanvas) photoshapeCanvas);

        add(photoshapeCanvas);
        add(sectionToolSelection, BorderLayout.WEST);
        setMenuBar(headerBar);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
