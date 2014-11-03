import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Ventana extends JFrame implements KeyListener, MouseListener,ActionListener {
  
	private Funciones panel;
	private Editor editor;
	private double posX;
	private double posY;
	
	public Ventana(){
		super();
		setTitle("jPlot");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new Funciones();
		editor = new Editor();
		this.posX= 800/-2*this.panel.getZoom();
		this.posY= 529/-2*this.panel.getZoom();
		this.panel.setPos(posX, posY);
		this.add(editor,BorderLayout.SOUTH);
		this.add(panel,BorderLayout.CENTER);
		this.panel.setBackground(Color.WHITE);
		this.panel.setFocusable(true);
		this.setFocusable(true);
		this.setFocusableWindowState(true);
		//this.setResizable(false);
		this.addKeyListener(this);
		
		
		this.addMouseListener(this);
		this.editor.cambiar.addActionListener(this);
		this.editor.botonBorrar.addActionListener(this);
                this.editor.botonOcultar.addActionListener(this);
                this.editor.botonAcercaDe.addActionListener(this);
                this.editor.botonColor.addActionListener(this);
		
		this.pack();
		this.setVisible(true);
		setBounds(100,100,800,600);
		//this.posX= this.getWidth()/-2*this.panel.getZoom();
		//this.posY= this.panel.getHeight()/-2*this.panel.getZoom();
	}
        
        
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
            if(e.getSource()==this.editor.cambiar){
                String textF = this.editor.getTextField();
                if (textF.contains("^")){
                    System.out.println("Entre al if del ^");
                    String temp;
                    int pos;
                    pos = textF.indexOf("^");
                    if(textF.charAt(pos+1)=='0'){
                    	temp = textF.substring(0, pos-1)+"1"+textF.substring(pos+2);
                    }else{
	                    temp = textF.substring(0, pos-1)+textF.charAt(pos-1);
	                    for(int i = 1;i<Integer.parseInt(""+textF.charAt(pos+1));i++){
	                    	temp +="*"+textF.charAt(pos-1);
	                    }
	                    temp += textF.substring(pos+2);
                    }
                    textF = temp;
                }
                if ((textF.contains("x")||
                    textF.contains("0")||
                    textF.contains("1")||
                    textF.contains("2")||
                    textF.contains("3")||
                    textF.contains("4")||
                    textF.contains("5")||
                    textF.contains("6")||
                    textF.contains("7")||
                    textF.contains("8")||
                    textF.contains("9"))&& 
                    !textF.equalsIgnoreCase(null)&&
                    !textF.contains("^")){
                        this.panel.addFunc(textF,editor.getColor());
                        this.editor.del.addItem(this.editor.getTextField());
                        this.repaint();
                        this.requestFocus();
                        this.editor.randomColor();
                }else{
                    JOptionPane.showMessageDialog(this.panel, "Valor Inválido, tienes que introducir una función o una constante\n"
                                                            + "Recuerda que el formato de entrada sólo acepta numeros, 'x' & los signos de +  -  *  /");
                }

            }
            else if(e.getSource()==this.editor.botonColor){
            	editor.setColor(JColorChooser.showDialog(null, "Selecciona el color que deseas para tu funcion: ", Color.GREEN));
            	editor.actualizarColor();
            }
            
            else if(e.getSource()==this.editor.botonBorrar){
                if (!this.panel.getFunc().isEmpty()){
                    System.out.println("Borrando: "+this.editor.del.getSelectedIndex());
                    this.panel.delFunc(this.editor.del.getSelectedIndex());
                    this.editor.del.removeItem(this.editor.del.getSelectedItem());
                    this.repaint();
                    this.requestFocus();
                }
                else{
                    JOptionPane.showMessageDialog(null, "No existe ninguna función \no constante que puedas borrar");
                }
                
            }
            else if (e.getSource()==this.editor.botonOcultar){
                if (!this.panel.getFunc().isEmpty()){
                    System.out.println("Borrando: "+this.editor.del.getSelectedIndex());
                    this.panel.setPintarFunc(this.editor.del.getSelectedIndex());
                    this.repaint();
                    this.requestFocus();
                }
                else{
                    JOptionPane.showMessageDialog(null, "No existe ninguna función \no constante que puedas ocultar");
                }
            }
            else if(e.getSource()==this.editor.botonAcercaDe){
                JOptionPane.showMessageDialog(null, "ZoomPositivo (+)\n"
                                                  + "ZoomNegativo (-)\n"
                                                  + "Regresar a Posición Inicial (R)\n"
                                                  + "Navegación (Flechas del Teclado)\n"
                                                  + "\n"
                                                  + "Hecho por: \n"
                                                  + "Mario Miguel Moreno\n"
                                                  + "Diego Andrés Camargo");
            }
        }
        
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.requestFocus();
	}
        
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	public void keyPressed(KeyEvent e){
		this.repaint();
		if(e.getKeyCode()==KeyEvent.VK_ADD || e.getKeyCode()==KeyEvent.VK_PLUS){ //zoom in
			System.out.println("Presionaste + ");
			
			this.posX += this.getWidth()/4*panel.getZoom();
			this.posY += (this.panel.getHeight()+4)/4*panel.getZoom();
			panel.setPos(this.posX, this.posY);
			panel.zoomIn();
		}else if(e.getKeyCode()==KeyEvent.VK_MINUS || e.getKeyCode()==KeyEvent.VK_SUBTRACT){ //zoom out
			System.out.println("Presionaste -");
			panel.zoomOut();
			this.posX -= this.getWidth()/4*panel.getZoom();
			this.posY -= (this.panel.getHeight()+4)/4*panel.getZoom();
			panel.setPos(this.posX, posY);
			
		}else if(e.getKeyCode()== KeyEvent.VK_UP){ // MOVER ARRIBA
			System.out.println("Presionaste arriba");
			this.posY+= this.panel.getZoom();
			panel.setPos(this.posX, this.posY);
			
		}else if(e.getKeyCode()== KeyEvent.VK_DOWN){ // MOVER ABAJO
			System.out.println("Presionaste abajo");
			this.posY-= this.panel.getZoom();
			panel.setPos(this.posX, this.posY);
		}else if(e.getKeyCode()== KeyEvent.VK_RIGHT){ // MOVER CAMARA DERECHA
			System.out.println("Presionaste derecha");
			this.posX+= this.panel.getZoom();
			panel.setPos(this.posX, this.posY);
		}else if(e.getKeyCode()== KeyEvent.VK_LEFT){ // MOVER CAMARA IZQUIERDA
			System.out.println("Presionaste izquierda");
			this.posX-= this.panel.getZoom();
			panel.setPos(this.posX, this.posY);
		}else if(e.getKeyCode()== KeyEvent.VK_R){	// Centrar la vista
			System.out.println("Presionaste R ");
			this.posX= this.getWidth()/-2*this.panel.getZoom();
			this.posY= (this.panel.getHeight()+1)/-2*this.panel.getZoom();
			panel.setPos(posX, posY);
			/*
		}else if(e.getKeyCode() == KeyEvent.VK_MULTIPLY){ //Baja la resolucion
			System.out.println("Presionaste *");
			
		}else if(e.getKeyCode() == KeyEvent.VK_DIVIDE){ //Sube la resolucion
			System.out.println("Presionaste /");
		}else if(e.getKeyCode()== KeyEvent.VK_COMMA){	// disminulle la cantidad de iteraciones
			System.out.println("Presionaste . ");
			
		}else if(e.getKeyCode()== KeyEvent.VK_0){
			System.out.println("Presionaste 0");
			
		}else if(e.getKeyCode()== KeyEvent.VK_9){	// disminulle la cantidad de iteraciones
			System.out.println("Presionaste 9");
			
		}else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			
			System.out.println("Presionaste enter");
			*/
		}else{
			System.out.println(e.getKeyCode());
		}
		
	}
	public void keyReleased(KeyEvent e){

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
	public static void main(String[] args) {
		Ventana a=new Ventana();
		a.repaint();
	}
}
