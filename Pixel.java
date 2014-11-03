
public class Pixel {

	private int red,blue,green;

	public Pixel(){
		this.red = 0;
		this.blue =0;
		this.green =0;
	}

	public Pixel(int red,int green,int blue){
		this.red=red;
		this.green=green;
		this.blue=blue;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getBlue() {
		return this.blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getGreen() {
		return this.green;
	}

	public void setGreen(int green) {
		this.green = green;
	}
}
