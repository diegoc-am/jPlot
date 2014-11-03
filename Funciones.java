import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

import javax.swing.*;

@SuppressWarnings("serial")
public class Funciones extends JPanel implements MouseMotionListener{
	private Stack<String> operacion;
	private ListaEnlazada <Color> C_funciones;
	private ListaEnlazada <String> funciones;
        private ListaEnlazada <Boolean> pintarFuncion;
	private double posX;
	private double posY;
	private double tam;
        private double valorActual;
        private int mouseX;
        private int mouseY;
	
	public Funciones(){
		this.C_funciones = new ListaEnlazada<>();
		this.funciones = new ListaEnlazada<>();
        this.pintarFuncion = new ListaEnlazada<>();
		this.operacion = new Stack<>();
		this.posX = 0;
		this.posY = 0;
		this.tam = 0.5;
		this.valorActual=posX;
                this.addMouseMotionListener(this);
	}
        
        public ListaEnlazada<String> getFunc(){
            return this.funciones;
        }
        public ListaEnlazada<Color> getFuncCol(){
            return this.C_funciones;
        }
        
        public void setPintarFunc(int pos){
            if (this.pintarFuncion.getEn(pos).booleanValue()){
                this.pintarFuncion.getNodo(pos).setDato(false);
            }
            else{
                this.pintarFuncion.getNodo(pos).setDato(true);
            }
            
        }
        
        public double getMouseX(){
        	return this.posX+ (this.mouseX)*this.tam;
            
        }
        public double getMouseY(){
        	return this.posY+ (this.getHeight()-this.mouseY)*this.tam;
        }
	public void addFunc(String func,Color colours){
		System.out.println("Se agrego: "+ func);
		
                C_funciones.insertarFin(colours);
		funciones.insertarFin(this.toPostfix(this.espaciar(func)));
                pintarFuncion.insertarFin(Boolean.TRUE);
                
	}
	public void delFunc(int func){
		funciones.borrarEn(func);
                C_funciones.borrarEn(func);
	}
        @Override
	public void paint(Graphics g){
		super.paint(g);	
		int maxX=20,maxY=20;
		System.out.println("Ancho: "+this.getWidth());
		System.out.println("Alto: "+this.getHeight());
		System.out.println("Pos X: "+this.posX);
		System.out.println("Pos Y: "+this.posY);
		if(posY<= 0 || (this.getHeight()*tam)+posY >=0){
			int ejeY = -(int)Math.round(posY /tam) ;
			g.drawLine(0,this.getHeight()- ejeY, this.getWidth(),this.getHeight()- ejeY);
			int ejeX=0;
			for(int x = 0; x<21;x++){
				if((x*tam+posX) % (tam*20)==0){
					ejeX=x;
					break;
				}
			}
			//minX = ejeX;
			for(int x = ejeX;x<this.getWidth();x+=20){
				g.drawLine(x, this.getHeight()- ejeY-5, x, this.getHeight()- ejeY+5);
				maxX=x;
			}
			String maximX =""+ (this.posX+ (maxX)*this.tam);
			
			g.drawString(""+(this.posX+ (ejeX)*this.tam), ejeX, this.getHeight()-ejeY+15);
			g.drawString(maximX, maxX-maximX.length()*7, this.getHeight()-ejeY+15);
		}
		if(posX<= 0 || (this.getHeight()*tam)+posX >=0){
			int ejeX = -(int)Math.round(posX /tam) ;
			g.drawLine(ejeX, 0, ejeX, this.getHeight());
			int ejeY = 0;
			for(int y = 0; y<21;y++){
				if((y*tam+posY) % (tam*20)==0){
					ejeY=y;
					break;
				}
			}
			for(int y = ejeY;y<this.getHeight();y+=20){
				g.drawLine(ejeX-5, this.getHeight()- y, ejeX+5,this.getHeight()- y);
				maxY=y;
			}
			String maximY =""+ (this.posY+ (maxY)*this.tam);
			
			g.drawString(""+(this.posY+ (ejeY)*this.tam), ejeX+10,this.getHeight()- ejeY);
			g.drawString(maximY, ejeX+10, this.getHeight()- maxY+10);
         }
		
		for(int i = 0; i< funciones.size();i++){
                    if (this.pintarFuncion.getEn(i)){
                        this.valorActual= posX;
                        int y1 =(int)Math.round((this.evaluacion(funciones.getEn(i))-posY)/tam);
                        this.valorActual+=this.tam;
                        g.setColor(C_funciones.getEn(i));
                        for(int x = 1; x<this.getWidth();x++){
                                int y2 =(int)Math.round((this.evaluacion(funciones.getEn(i))-posY)/tam);
                                g.drawLine(x-1, this.getHeight()-y1, x, this.getHeight()-y2);
                                y1=y2;
                                this.valorActual+=this.tam;
                    }
    		}
    		
    	}
                g.setColor(Color.BLACK);
                g.drawRect(0, 0, 100, 30);
                g.setColor(Color.white);
                g.fill3DRect(0, 0, 100, 30, true);
                g.setColor(Color.black);
                g.drawString(("X: "+this.getMouseX()), 0, 10);
                g.drawString("Y: "+this.getMouseY(),0,20);
		
 }
	
    public ListaEnlazada<ListaEnlazada<Double>> calcular(){
    	ListaEnlazada<Double> temp = new ListaEnlazada<>();
    	ListaEnlazada<ListaEnlazada<Double>> rest = new ListaEnlazada<>();
    	for(int i = 0; i< funciones.size();i++){
    		this.valorActual= posX;
    		for(int x = 0; x<this.getWidth();x++){
    			this.valorActual+=this.tam*x;
    			temp.insertarFin(this.evaluacion(funciones.getEn(i)));
    		}
    		rest.insertarFin(temp);
    	}
    	return rest;
    }
        
	public void setPos(double x, double y){
		this.posX=x;
		this.posY=y;
	}
	public void zoomIn(){
		this.tam = this.tam/2;
	}
	public void zoomOut(){
		this.tam = this.tam*2;
	}
	public double getZoom(){
		return tam;
	}

	public Double evaluacion(String postfix){
        String[] expr = postfix.split("\\s");
        double op1 = 0;
        double op2 = 0;
        for (int i = 0; i < expr.length; i++){
            String s = expr[i];
            if (esNumero(s) || s.equalsIgnoreCase("x")){
                if (esNumero(s)){
                    	operacion.push(s);
                    }
                    else{
                        String temp = ""+this.valorActual;
                    	operacion.push(temp);
                    }
            }
            else if (esOperacion(s)){
                op2 = Double.parseDouble(operacion.pop());
                op1 = Double.parseDouble(operacion.pop());
                switch (s) {
                    case "+":
                        this.operacion.push(Double.toString(op1+op2));
                        break;
                    case "-":
                        this.operacion.push(Double.toString(op1-op2));
                        break;
                    case "*":
                        this.operacion.push(Double.toString(op1*op2));
                        break;
                    case "/":
                        this.operacion.push(Double.toString(op1/op2));
                        break;
                }
            }
        }
        return Double.parseDouble(operacion.peek());
    }
    
    public String[] espaciar(String infix){
    	System.out.println(infix);
        String op="";
        if (infix.charAt(0)=='-' || infix.charAt(0)=='+'){
            op+="0";
        }
        for (int i = 0 ; i<infix.length();i++){
            String temp = infix.charAt(i)+"";
            if (esNumero(temp) || temp.equalsIgnoreCase("x")){
                op+=temp;
            }   
            else if (esOperacion(temp) || esParentesis(temp)){
                op+=" "+temp+" ";
            }
        }
        System.out.println(operacion);
        return op.split(" ");
    }
    
    public String toPostfix(String[] infix){
        Stack<String> stack = new Stack<String>();
        String postfix = "";
        
        for (int i = 0; i < infix.length; i++){
            String c = infix[i];
            String o = "";
            if (esNumero(c)||c.equalsIgnoreCase("x")){
                postfix += c+" ";
            }
            else if (esOperacion(c)){
                while (!stack.isEmpty()){
                    o = stack.pop();
                    if (prioridad(c, o)){
                        stack.push(o);
                        break;
                    }
                    postfix += o+" ";
                }
                stack.push(c);
            }
            else if (esParentesis(c)){
                if (c.equals(")")){
                    while (!stack.isEmpty()){
                        o = stack.pop();
                        if (o.equals("(")){
                            break;
                        }
                        postfix += o+" ";
                    }
                }
                else
                    stack.push(c);
            }
        }
        while (!stack.isEmpty()){
            String o = stack.pop();
            postfix += o+" ";
        }
        return postfix;
    }
    public boolean esNumero(String op){
    	try{
                Double.parseDouble(op);
                return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    public boolean esOperacion(String op){
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
    }
    public boolean prioridad(String op1, String op2){
        if (op1.equals("*") || op1.equals("/")){
            if (op2.equals("+") || op2.equals("-")){       
                return true;
            }
        }
        if (op1.equals("*") || op1.equals("/") || op1.equals("+") || op1.equals("-")){
            if (op2.equals("(")){       
                return true;
            }
        }
        return false;
    }
    public boolean esParentesis(String str){
        return str.equals("(") || str.equals(")");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseX=e.getX();
        this.mouseY=e.getY();
        this.repaint();
    }
}
