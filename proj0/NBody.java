public class NBody{

	public static double readRadius(String fileName){
		In in = new In(fileName);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static int readNumber(String fileName){
		In in = new In(fileName);
		int N = in.readInt();
		return N;
	}

	public static Body[] readBodies(String fileName){

		//System.out.println(out[0].mass);
		In in = new In(fileName);
		int N = in.readInt(); //////
		in.readDouble();

		Body[] out = new Body[N]; // Should be ok!!!  "new" method should dynamically allocate the non-static memory
		for(int i=0;i<N;i++){
			out[i] = new Body();
		}

		for(int i=0; i < N; i++){

			out[i].xxPos = in.readDouble();
			out[i].yyPos = in.readDouble();
			out[i].xxVel = in.readDouble();
			out[i].yyVel = in.readDouble();
			out[i].mass = in.readDouble();
			out[i].imgFileName = in.readString();

		}
		return out;
	}

	public static void main(String[] args) {
		//Double.parseDouble("23.6");
		//Integer.parseInt(number);
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		int N = readNumber(filename);
		Body[] Bodies = readBodies(filename);


		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius,radius);
		


		double[] xForces = new double[N];
		double[] yForces = new double[N];

		// Time
		double t=0;
		for(;t<=T;t+=dt){
			for(int j=0;j<N;j++){
				xForces[j]=Bodies[j].calcNetForceExertedByX(Bodies);
				yForces[j]=Bodies[j].calcNetForceExertedByY(Bodies);
				Bodies[j].update(dt,xForces[j],yForces[j]);
			}


			
			StdDraw.clear();

			// Draw star field picture
			String imgToDraw="images/starfield.jpg";
			StdDraw.picture(0,0,imgToDraw);

			// Draw N bodies
			for(int i=0;i<N;i++){
				Bodies[i].draw();
			}


			StdDraw.show();
			StdDraw.pause(10);
		}

		

	}
	
}