import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class Editor extends JPanel implements ActionListener {
	

	private JLabel inst;
	private JTextField boxText;
	public JButton cambiar;
        private JLabel separador;
        public JComboBox<String> del;
        public JButton botonBorrar;
        private JLabel inst2;
        private Color colours;
        public JButton botonOcultar,botonColor;
        public JButton botonAcercaDe;
        private JLabel separador2;
	
	public Editor(){
		super();
		inst = new JLabel("f(x)= ");
		inst.setBackground(new Color(255,255,255));
		inst.setForeground(Color.BLACK);
        colours = Color.GREEN;
                inst2 = new JLabel("f(x)=");
                inst2.setBackground(new Color(255,255,255));
		inst2.setForeground(Color.BLACK);
                
                del = new JComboBox<>();
                
                botonBorrar = new JButton();
                botonBorrar.setIcon(new ImageIcon("botonBorrar.png"));
		botonBorrar.setVisible(true);
                
                botonOcultar = new JButton();
                botonOcultar.setIcon(new ImageIcon("botonOcultar.png"));
                botonOcultar.setVisible(true);
                
                botonAcercaDe = new JButton();
                botonAcercaDe.setIcon(new ImageIcon("botonAcercaDe.png"));
                botonAcercaDe.setVisible(true);
                
		botonColor= new JButton();
                botonColor.setIcon(new ImageIcon("transparente.png"));
		botonColor.setVisible(true);
		botonColor.setBackground(colours);
		
                boxText = new JTextField(10);
		boxText.setEditable(true);
		boxText.setText("");
		
		cambiar = new JButton();
		cambiar.setIcon(new ImageIcon ("botonAgregar.png"));
		cambiar.setVisible(true);
                
               
		this.add(inst);
		this.add(boxText);
		this.add(cambiar);
		this.add(botonColor);
                
                
                this.add(inst2);
                this.add(del);
                this.add(botonBorrar);
                this.add(botonOcultar);
                this.add(botonAcercaDe);
        }
	public void setTextField(String s){
		this.boxText.setText(s);
	}
	public String getTextField(){
		return this.boxText.getText();
	}
	public boolean getTextFieldFocus(){
		return this.boxText.isFocusOwner();
	}
	public void setColor(Color colurs){
		this.colours=colurs;
	}
	public Color getColor(){
		return this.colours;
	}
	public void randomColor(){
		this.colours = Color.getHSBColor((float)Math.random(), (float)Math.random(), 0.9f); // Agregale el codigo de random xD
		this.actualizarColor();
	}
	public void actualizarColor(){
		this.botonColor.setBackground(colours);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
