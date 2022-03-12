import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ColorChooserUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SectionToolSelection extends JMenuBar {
    PhotoshapeCanvas canvas;
    JMenuItem paintBrush;
    JMenuItem lineTool;
    JMenuItem eraser;
    JMenuItem colorChooser;


    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public SectionToolSelection(PhotoshapeCanvas canvas) {
        this.canvas = canvas;

        eraser = new SectionToolSelectionButton("Eraser", new Erase(canvas));
        lineTool = new SectionToolSelectionButton("Line Tool", new LineTool(canvas));
        paintBrush = new SectionToolSelectionButton("Paint Brush", new PaintBrush(canvas));

        colorChooser = new ColorChooserButton("Color");

        add(paintBrush);
        add(lineTool);
        add(eraser);
        add(colorChooser);
    }

    class SectionToolSelectionButton extends JMenuItem implements ActionListener {
        MouseInputListener toolFunction;

        public SectionToolSelectionButton(String title, MouseInputListener toolFunction) {
            super(title);
            addActionListener(this);
            this.toolFunction = toolFunction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.photoshapeGraphics.changeDrawFunction(toolFunction);
        }
    }

    class ColorChooserButton extends JMenuItem implements ActionListener {
        public ColorChooserButton(String title) {
            super(title);
            addActionListener(this);
            JOptionPane optionPane = new JOptionPane();
            optionPane.createDialog("hi");
            //JOptionPane.
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.photoshapeGraphics.penColor = JColorChooser.showDialog(this, "Choose Color", Color.RED);
        }

    }
}