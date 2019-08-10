package demoudp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainPanel extends JFrame {
    
    public MainPanel(){
       setTitle("Slider"); 
       setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
       setLayout(new FlowLayout());
       
       JSlider slider = new JSlider(0,16,0);
       slider.setMinorTickSpacing(0);
       slider.setMajorTickSpacing(1);
       slider.setPaintTicks(true);
       slider.setPaintLabels(false);
       
       JTextField field = new JTextField(10);

 
       slider.addChangeListener(new ChangeListener() {
           @Override
           public void stateChanged(ChangeEvent e) {
           field.setText(String.valueOf(slider.getValue()));
           MainPanel.sliderData = slider.getValue();
           }
        });

 
       field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            String number = field.getText();
            if (!"".equals(number)) {
                slider.setValue(Integer.parseInt(number));
              }
            }
        });
       
       
       slider.setVisible(true);
       field.setVisible(true);
             
       add(field);
       add(slider);
       
       setVisible(true);
       
       setLocationRelativeTo(null);
       pack();
    }
    
    public int getSliderValue() {
        this.sliderData = this.slider.getValue();
        return this.sliderData;
    }
    
    private JTextField field;
    private JSlider slider;
    public static int sliderData;
    
}
